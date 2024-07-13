package com.sparta.spartakanbanboard.domain.column.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ColumnListResponseDto {

    List<ColumnResponseDto> columnResponseDtoList = new ArrayList<>();
}
