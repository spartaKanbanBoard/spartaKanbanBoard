package com.sparta.spartakanbanboard.global.dto;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class CommonResDto<T> {
    private int code;
    private T data;

    @Builder
    public CommonResDto(int code, T data){
        this.code = code;
        this.data = data;
    }

}
