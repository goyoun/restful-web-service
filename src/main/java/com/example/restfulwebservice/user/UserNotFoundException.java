package com.example.restfulwebservice.user;
// HTTP Status code
// 2XX > OK
// 4xx > Client 문제
// 5XX > Server 문제

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // 에러값을 NOT_FOUND 로 변경
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
