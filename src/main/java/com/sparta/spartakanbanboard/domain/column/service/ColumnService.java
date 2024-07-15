package com.sparta.spartakanbanboard.domain.column.service;

import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.service.BoardService;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnListResponseDto;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnRequestDto;
import com.sparta.spartakanbanboard.domain.column.dto.ColumnResponseDto;
import com.sparta.spartakanbanboard.domain.column.entity.KanbanColumn;
import com.sparta.spartakanbanboard.domain.column.repository.ColumnRepository;
import com.sparta.spartakanbanboard.global.BusinessLogicException;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import com.sparta.spartakanbanboard.global.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnRepository columnRepository;
    private final BoardService boardService;

    public CommonResponseDto createColumn(long boardId, ColumnRequestDto requestDto) {

        Board board = boardService.findById(boardId);

        if(!Objects.isNull(columnRepository.findByTitle(requestDto.getColumnTitle()))) {
            throw new BusinessLogicException("같은 이름의 컬럼이 존재합니다!");
        }

        Long maxOrderNumber = columnRepository.findMaxOrderNumber();
        if(maxOrderNumber == null) {
            maxOrderNumber = 0L;
        }

        KanbanColumn kanbanColumn = KanbanColumn.builder()
            .columnTitle(requestDto.getColumnTitle())
            .board(board)
            .orderNumber(maxOrderNumber+1)
            .build();

        columnRepository.save(kanbanColumn);

        ColumnResponseDto responseDto = KanbanColumn.of(kanbanColumn);

        return CommonResponseDto.builder()
            .msg(requestDto.getColumnTitle() + "컬럼이 성공적으로 생성되었습니다")
            .build();
    }

    public CommonResponseDto deleteColumn(long boardId, long columnsId) {

        Board board = boardService.findById(boardId);
        KanbanColumn kanbanColumn = findById(columnsId);

        if (!board.getKanbanColumn().contains(kanbanColumn)) {
            throw new BusinessLogicException("해당 보드에 들어있는 컬럼이 아닙니다");
        }

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

        List<KanbanColumn> columnList = columnRepository.findKanbanColumnsByBoard(boardId);

        long targetSequence = targetColumn.getOrderNumber();
        long baseSequence = baseColumn.getOrderNumber();
        long difference = targetSequence - baseSequence;

        if (difference != 0) {
            for (KanbanColumn c : columnList) {
                long cSequence = c.getOrderNumber();
                if (!c.getId().equals(baseId) &&
                    (difference > 0 && cSequence > baseSequence && cSequence <= targetSequence)
                    ||
                    (difference < 0 && cSequence < baseSequence && cSequence >= targetSequence)
                ) {
                    c.setOrderNumber(cSequence + (difference > 0 ? -1 : 1));
                }
            }

            baseColumn.setOrderNumber(targetSequence);
        }

        columnRepository.saveAll(columnList);

        ColumnListResponseDto columnListResponseDto = new ColumnListResponseDto();
        columnListResponseDto.getColumnResponseDtoList().addAll(columnList
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
    public CommonResponseDto getAllColumn(int page, int size, long boardId) {

        PageDto pageDto = PageDto.builder()
            .currentPage(page)
            .size(size)
            .build();

        // kanbanColumnList는 현재 양방향 참조로 무한 순환참조 일어나는중
        List<KanbanColumn> kanbanColumnList = columnRepository.findAllByBoardId(boardId ,pageDto.toPageable());
        List<ColumnResponseDto> responseDtoList = kanbanColumnList.stream().map(KanbanColumn::of).toList();

        ColumnListResponseDto columnListResponseDto = new ColumnListResponseDto();
        columnListResponseDto.getColumnResponseDtoList().addAll(kanbanColumnList
            .stream()
            .map(KanbanColumn::of)
            .toList());

        columnListResponseDto.getColumnResponseDtoList().sort(
            Comparator.comparing(
                ColumnResponseDto::getOrderNumber));

        return CommonResponseDto.builder()
            .msg("전체 컬럼 조회가 성공했습니다")
            .data(columnListResponseDto.getColumnResponseDtoList())
            .build();
    }

    public KanbanColumn findById(long id) {
        return columnRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 컬럼이 존재하지 않습니다!"));
    }
}



