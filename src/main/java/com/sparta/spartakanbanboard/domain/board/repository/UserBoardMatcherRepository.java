package com.sparta.spartakanbanboard.domain.board.repository;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.entity.UserBoardMatcher;
import com.sparta.spartakanbanboard.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserBoardMatcherRepository extends JpaRepository<UserBoardMatcher, Long> {

    @Query("select distinct ubm from UserBoardMatcher ubm where ubm.user = :user")
    List<UserBoardMatcher> findUserBoardMatcherByUser(@Param("user") User user);

}
