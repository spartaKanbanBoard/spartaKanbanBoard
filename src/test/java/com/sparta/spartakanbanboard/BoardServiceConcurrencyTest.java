//package com.sparta.spartakanbanboard;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//
//import com.sparta.spartakanbanboard.domain.board.dto.BoardRequestDto;
//import com.sparta.spartakanbanboard.domain.board.dto.BoardResponseDto;
//import com.sparta.spartakanbanboard.domain.board.entity.Board;
//import com.sparta.spartakanbanboard.domain.board.repository.BoardRepository;
//import com.sparta.spartakanbanboard.domain.board.service.BoardService;
//import com.sparta.spartakanbanboard.domain.user.entity.User;
//import com.sparta.spartakanbanboard.domain.user.service.UserService;
//import java.util.Optional;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//
//@ExtendWith(MockitoExtension.class)
//public class BoardServiceConcurrencyTest {
//
//    @Mock
//    private RedissonClient redissonClient;
//
//    @Mock
//    private RLock lock;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private BoardRepository boardRepository;
//
//    @InjectMocks
//    private BoardService boardService;
//
//    private User user;
//    private BoardRequestDto boardRequestDto;
//    private Board board;
//
//    @BeforeEach
//    public void setUp() {
//        user = User.builder()
//            .id(1L)
//            .build();
//
//        boardRequestDto = new BoardRequestDto();
//        boardRequestDto.setTitle("New Title");
//        boardRequestDto.setContent("New Content");
//
//        board = Board.builder()
//                .id(1L)
//                .title("Old Title")
//                .boardInfo("Old Info")
//                .build();
//
//        given(redissonClient.getFairLock((String) any())).willReturn(lock);
//        given(lock.tryLock(10, 60, TimeUnit.SECONDS)).willReturn(true);
//        given(userService.findByUserId(user.getId())).willReturn(user);
//        given(boardRepository.findById(1L)).willReturn(Optional.of(board));
//        given(boardRepository.save(any(Board.class))).willReturn(board);
//    }
//
//    @Test
//    public void testUpdateBoard_concurrentAccess() throws InterruptedException {
//        int numberOfThreads = 10;
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
//
//        for (int i = 0; i < numberOfThreads; i++) {
//            executorService.submit(() -> {
//                try {
//                    BoardResponseDto responseDto = boardService.updateBoard(user, boardRequestDto, 1L);
//                    assertNotNull(responseDto);
//                    assertEquals("New Title", responseDto.getTitle());
//                    assertEquals("New Content", responseDto.getContent());
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//
//        latch.await(); // 모든 스레드가 작업을 완료할 때까지 대기
//        executorService.shutdown();
//    }
//}
