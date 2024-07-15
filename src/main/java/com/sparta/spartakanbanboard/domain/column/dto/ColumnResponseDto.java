package com.sparta.spartakanbanboard.domain.column.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ColumnResponseDto {

    private long id;
    private String columnTitle;
    private long orderNumber;
}
