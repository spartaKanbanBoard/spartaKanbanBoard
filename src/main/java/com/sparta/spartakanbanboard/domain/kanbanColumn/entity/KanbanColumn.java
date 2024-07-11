package com.sparta.spartakanbanboard.domain.kanbanColumn.entity;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table
public class KanbanColumn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToMany(mappedBy = "progress", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Card> cardList = new LinkedHashSet<>();


    public void addCard(User user) {
        Card newCard = Card.builder().kanbanColumn(this).user(user).build();
        this.cardList.add(newCard);
    }
}
