package com.sparta.spartakanbanboard.domain.column.service;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.repository.BoardRepository;
import com.sparta.spartakanbanboard.domain.board.service.BoardService;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnListResponseDto;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnRequestDto;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.column.repository.ColumnRepository;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.domain.user.service.global.BusinessLogicException;
import com.sparta.spartakanbanboard.domain.user.service.global.dto.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    public CommonResponseDto createColumn(long bordId, ColumnRequestDto requestDto) {

        Board board = boardService.findById(bordId);
        KanbanColumn kanbanColumn = KanbanColumn.builder()
            .columnTitle(requestDto.getColumnTitle())
            .board(board)
            .orderNumber(KanbanColumn.getCreatedNumber())
            .build();

        columnRepository.save(kanbanColumn);
        ColumnResponseDto responseDto = KanbanColumn.of(kanbanColumn);

        KanbanColumn.plusNumber();

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

    @Transactional
    public CommonResponseDto moveColumn(long boardId, long baseId, long targetId) {
        Board board = boardService.findById(boardId);

        KanbanColumn baseColumn = findById(baseId);
        KanbanColumn targetColumn = findById(targetId);

        if (!board.getKanbanColumn().contains(baseColumn) || !board.getKanbanColumn().contains(targetColumn)) {
            throw new BusinessLogicException("해당 보드에 들어있는 컬럼이 아닙니다");
        }

        exChangeOrder(baseColumn, targetColumn);

        ColumnListResponseDto columnListResponseDto = new ColumnListResponseDto();
        columnListResponseDto.getColumnResponseDtoList().addAll(columnRepository.findAll()
            .stream()
            .map(KanbanColumn::of)
            .toList());

        columnListResponseDto.getColumnResponseDtoList().sort(
            Comparator.comparing(
                ColumnResponseDto::getOrderNumber));

        return CommonResponseDto.builder()
            .msg("성공적으로 컬럼위치가 바뀌었습니다")
            .data(columnListResponseDto)
            .build();
    }

    public KanbanColumn findById(long id) {
        return columnRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 컬럼이 존재하지 않습니다!"));
    }

    private void exChangeOrder(KanbanColumn base, KanbanColumn target){
        long temp = base.getOrderNumber();
        base.setOrderNumber(target.getOrderNumber());
        target.setOrderNumber(temp);
    }
}



