package com.sparta.spartakanbanboard.global.exception;

import com.sparta.spartakanbanboard.global.BusinessLogicException;
import com.sparta.spartakanbanboard.global.dto.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<?> BusinessLogicExceptionHandler(BusinessLogicException e) {

        CommonResponseDto<?> responseDto = CommonResponseDto.builder()
                .msg(e.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }

}
