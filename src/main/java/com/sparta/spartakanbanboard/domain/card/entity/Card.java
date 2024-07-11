package com.sparta.spartakanbanboard.domain.card.entity;

import com.sparta.spartakanbanboard.domain.card.dto.CreateCardRequestDto;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import com.sparta.spartakanbanboard.domain.user.service.global.entity.TimeStamped;
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

    private String content;

    @Enumerated(EnumType.STRING)
    private State state;

    private String writer;


    public static Card of(CreateCardRequestDto cardRequestDto) {
        return Card.builder()
            .title(cardRequestDto.getTitle())
            .content(cardRequestDto.getContent())
            .state(cardRequestDto.getState())
            .writer(cardRequestDto.getWriter())
            .build();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;



}
