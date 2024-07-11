package com.sparta.spartakanbanboard.domain.card.entity;

import com.sparta.spartakanbanboard.domain.card.dto.CardCreateRequestDto;
import com.sparta.spartakanbanboard.domain.progress.entity.Progress;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.global.entity.TimeStamped;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "db_card")
public class Card extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    @Enumerated(EnumType.STRING)
    private State status;

    @Column
    private String content;

    @Column
    private String writer;


    public static Card of(CardCreateRequestDto cardCreateRequestDto) {
        return Card.builder()
            .title(cardCreateRequestDto.getTitle())
            .content(cardCreateRequestDto.getContent())
            .status(State.BEFORE)
            .writer(cardCreateRequestDto.getWriter())
            .build();
    }



    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "progress_id")
    Progress progress;
}
