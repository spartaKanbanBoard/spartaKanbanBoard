package com.sparta.spartakanbanboard.domain.kanbanColumn.controller;

import com.sparta.spartakanbanboard.domain.kanbanColumn.service.KanbanColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class KanbanColumnController {

    private final KanbanColumnService kanbanColumnService;
}
