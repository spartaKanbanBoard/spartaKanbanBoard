package com.sparta.spartakanbanboard.global.dto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponseDto<T> {
    private int code;
    private T data;

    @Builder
    public CommonResponseDto(int code, T data){
        this.code = code;
        this.data = data;
    }

}
