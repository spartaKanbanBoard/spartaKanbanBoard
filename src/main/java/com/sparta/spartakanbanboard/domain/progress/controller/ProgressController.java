package com.sparta.spartakanbanboard.domain.progress.controller;

import com.sparta.spartakanbanboard.domain.progress.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;
}
