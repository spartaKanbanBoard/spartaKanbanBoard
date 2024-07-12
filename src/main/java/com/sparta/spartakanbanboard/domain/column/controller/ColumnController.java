package com.sparta.spartakanbanboard.domain.column.controller;

import com.sparta.spartakanbanboard.domain.column.dto.ColumnRequestDto;
import com.sparta.spartakanbanboard.domain.column.service.ColumnService;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    @PostMapping("/admins/boards/{boardId}/columns")
    public ResponseEntity<CommonResponseDto> createColumn(
        @PathVariable long boardId,
        @RequestBody ColumnRequestDto requestDto) {

        CommonResponseDto commonResponseDto = columnService.createColumn(boardId, requestDto);

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @DeleteMapping("admins/boards/{boardId}/columns/{columnsId}")
    public ResponseEntity<CommonResponseDto> deleteColumn(
        @PathVariable long boardId,
        @PathVariable long columnsId) {

        CommonResponseDto commonResponseDto = columnService.deleteColumn(boardId, columnsId);

        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PutMapping("admins/boards/{boardId}/columns")
    public ResponseEntity<CommonResponseDto> moveColumn(
        @PathVariable long boardId,
        @RequestParam("baseColumn") long baseId,
        @RequestParam("targetColumn") long targetId) {

        CommonResponseDto commonResponseDto = columnService.moveColumn(boardId, baseId,targetId);

        return ResponseEntity.ok().body(commonResponseDto);
    }
}
