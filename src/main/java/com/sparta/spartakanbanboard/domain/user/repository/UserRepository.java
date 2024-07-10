package com.sparta.spartakanbanboard.domain.user.repository;

import com.sparta.spartakanbanboard.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
