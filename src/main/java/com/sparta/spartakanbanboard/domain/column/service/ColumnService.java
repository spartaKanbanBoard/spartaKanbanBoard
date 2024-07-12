package com.sparta.spartakanbanboard.domain.column.service;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.service.BoardService;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnRequestDto;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.column.repository.ColumnRepository;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardService boardService;

    public CommonResponseDto createColumn(long bordId, ColumnRequestDto requestDto) {

        Board board = boardService.findById(bordId);
        KanbanColumn kanbanColumn = KanbanColumn.builder()
            .columnTitle(requestDto.getColumnTitle())
            .board(board)
            .build();

        columnRepository.save(kanbanColumn);
        ColumnResponseDto responseDto = KanbanColumn.of(kanbanColumn);

        return CommonResponseDto.builder()
            .msg(requestDto.getColumnTitle() + "컬럼이 성공적으로 생성되었습니다")
            .build();
    }

    public CommonResponseDto deleteColumn(long boardId, long columnsId) {

        KanbanColumn kanbanColumn = findById(columnsId);

        columnRepository.delete(kanbanColumn);

        return CommonResponseDto.builder()
            .msg(kanbanColumn.getColumnTitle() + "성공적으로 컬럼이 삭제되었습니다")
            .build();
    }

    public KanbanColumn findById(long id){
        return columnRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("잘못된 ID입니다"));
    }
}



