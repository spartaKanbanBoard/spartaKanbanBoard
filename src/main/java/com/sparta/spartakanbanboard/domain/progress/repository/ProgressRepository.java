package com.sparta.spartakanbanboard.domain.progress.repository;

import com.sparta.spartakanbanboard.domain.progress.entity.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
}
