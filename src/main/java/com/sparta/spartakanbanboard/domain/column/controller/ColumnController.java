package com.sparta.spartakanbanboard.domain.column.controller;

import com.sparta.spartakanbanboard.domain.column.dto.ColumnRequestDto;
import com.sparta.spartakanbanboard.domain.column.service.ColumnService;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
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

    @GetMapping("/admins/boards/{boardId}/columns")
    public ResponseEntity<CommonResponseDto> getAllColumn(
        @RequestParam(value = "page",defaultValue = "1") int page,
        @RequestParam("size") int size,
        @PathVariable long boardId) {

        CommonResponseDto commonResponseDto = columnService.getAllColumn(page, size, boardId);
        return ResponseEntity.ok().body(commonResponseDto);
    }

    @PutMapping("/admins/boards/{boardId}/columns/{columnId}")
    public ResponseEntity<CommonResponseDto> updateColumn(
        @RequestBody ColumnRequestDto requestDto,
        @PathVariable long boardId,
        @PathVariable long columnId) {

        CommonResponseDto commonResponseDto = columnService.updateColumn(requestDto, boardId,columnId);
        return ResponseEntity.ok().body(commonResponseDto);
    }
}
