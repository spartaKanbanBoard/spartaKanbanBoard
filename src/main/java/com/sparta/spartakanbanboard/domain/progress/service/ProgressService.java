package com.sparta.spartakanbanboard.domain.progress.service;

import com.sparta.spartakanbanboard.domain.progress.repository.ProgressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final ProgressRepository columnRepository;
}
