package com.example.restfulwebservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//예외처리 하기 위해 사용
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    // 컨트롤러에서 예외발생하면 핸들러가
    private Date timestamp; // 시간
    private String message;
    private String details;
}
