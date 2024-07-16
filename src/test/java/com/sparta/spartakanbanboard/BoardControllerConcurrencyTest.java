package com.sparta.spartakanbanboard;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.spartakanbanboard.domain.board.contoroller.BoardController;
import com.sparta.spartakanbanboard.domain.board.service.BoardService;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.entity.UserRole;
import com.sparta.spartakanbanboard.domain.user.entity.UserStatus;
import com.sparta.spartakanbanboard.global.config.WebSecurityConfig;
import com.sparta.spartakanbanboard.global.security.UserDetailsImpl;
import java.security.Principal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ComponentScan(
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
@AutoConfigureMockMvc
public class BoardControllerConcurrencyTest {

    private MockMvc mockMvc;
    private Principal mockPrincipal;
    private UserDetailsImpl userDetails;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(springSecurity(new MockSpringSecurityFilter()))
            .build();
    }

    private void mockUserSetup() {
        // Mock 테스트 유저 생성
        User testUser = User.builder()
            .userName("ggu")
            .password("1234")
            .userRole(UserRole.ADMIN)
            .userStatus(UserStatus.ACTIVE)
            .build();
        userDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    @Test
    @Transactional
    public void testUpdateBoard_concurrentAccess() throws InterruptedException {
        mockUserSetup();
        int numberOfThreads = 100;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                try {
                    ResultActions resultActions = mockMvc.perform(put("/api/boards/{boardId}", 1L)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"title\": \"New Title\", \"boardInfo\": \"New Content\"}")
                            .principal(mockPrincipal));

                    resultActions
                        .andExpect(status().isOk());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // 모든 스레드가 작업을 완료할 때까지 대기
        executorService.shutdown();
    }
}
