package com.sparta.spartakanbanboard.domain.cardcomment.repositroy;

import com.sparta.spartakanbanboard.domain.cardcomment.entity.CardComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardCommentRepository extends JpaRepository<CardComment, Long> {

}
