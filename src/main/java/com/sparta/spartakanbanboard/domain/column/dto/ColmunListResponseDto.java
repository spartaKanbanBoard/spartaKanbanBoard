package com.sparta.spartakanbanboard.domain.column.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ColmunListResponseDto {

    List<ColumnResponseDto> columnResponseDtoList = new ArrayList<>();
}
