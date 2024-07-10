package com.sparta.spartakanbanboard.domain.comment.repository;

import com.sparta.spartakanbanboard.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
