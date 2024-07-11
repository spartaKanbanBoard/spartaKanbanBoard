package com.sparta.spartakanbanboard.domain.board.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.spartakanbanboard.domain.board.entity.Board;
import com.sparta.spartakanbanboard.domain.board.entity.QBoard;
import com.sparta.spartakanbanboard.global.dto.PageDto;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class BoardRepositoryQueryImpl implements BoardRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<Board> search(Pageable pageable) {
        QBoard board = QBoard.board;

        // Pageable에서 Sort 객체를 가져옴
        Sort sort = pageable.getSort();
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();

        // Sort 객체의 각 정렬 기준을 OrderSpecifier로 변환하여 리스트에 추가
        for (Sort.Order order : sort) {
            PathBuilder<?> pathBuilder = new PathBuilder<>(board.getType(), board.getMetadata());
            orderSpecifiers.add(new OrderSpecifier(
                order.isAscending() ? Order.ASC : Order.DESC,
                pathBuilder.get(order.getProperty())
            ));
        }

      List<Board> boards = jpaQueryFactory
          .selectFrom(board)
          .offset(pageable.getOffset())
          .limit(pageable.getPageSize()+1)
          .orderBy(orderSpecifiers.toArray(new OrderSpecifier[0]))
          .fetch();

      boolean hasNext = false;
      if(boards.size() > pageable.getPageSize()) {
          boards.remove(pageable.getPageSize());
          hasNext = true;
      }

      return new SliceImpl(boards, pageable, hasNext);
    }
}
