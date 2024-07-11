package com.sparta.spartakanbanboard.domain.kanbanColumn.service;

import com.sparta.spartakanbanboard.domain.kanbanColumn.repository.KanbanColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KanbanColumnService {

    private final KanbanColumnRepository columnRepository;
}
