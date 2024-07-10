package com.sparta.spartakanbanboard.domain.column.controller;

import com.sparta.spartakanbanboard.domain.column.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;
}
