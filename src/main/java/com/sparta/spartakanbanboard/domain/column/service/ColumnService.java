package com.sparta.spartakanbanboard.domain.column.service;

import com.sparta.spartakanbanboard.domain.column.repository.ColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
}
