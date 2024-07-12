package com.sparta.spartakanbanboard.domain.column.controller;

import com.sparta.spartakanbanboard.domain.column.dto.ColumnRequestDto;
import com.sparta.spartakanbanboard.domain.column.service.ColumnService;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    @PostMapping("/admins/boards/{bordId}/columns")
    public ResponseEntity<CommonResponseDto> createColumn(
        @PathVariable long bordId,
        @RequestBody ColumnRequestDto requestDto){

        CommonResponseDto commonResponseDto = columnService.createColumn(bordId, requestDto);

        return ResponseEntity.ok().body(commonResponseDto);
    }
}
