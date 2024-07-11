package com.sparta.spartakanbanboard.domain.board.entity;

import com.sparta.spartakanbanboard.domain.board.dto.BoardRequestDto;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "db_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "board_info", nullable = false)
    private String boardInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Singular("userList")
    private List<User> userList = new ArrayList<>();

    public static Board of(BoardRequestDto boardRequestDto) {
        return Board.builder()
            .title(boardRequestDto.getTitle())
            .boardInfo(boardRequestDto.getBoardInfo())
            .build();
    }

}
