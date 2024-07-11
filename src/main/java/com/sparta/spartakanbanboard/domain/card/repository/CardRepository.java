package com.sparta.spartakanbanboard.domain.card.repository;

import com.sparta.spartakanbanboard.domain.card.entity.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {


}
