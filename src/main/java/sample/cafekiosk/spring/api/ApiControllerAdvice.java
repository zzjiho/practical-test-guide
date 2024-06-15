package sample.cafekiosk.spring.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 이 메소드가 실행되면 400 에러를 리턴
    @ExceptionHandler(BindException.class)
    public ApiResponse<Object> bindException(BindException e) { // BindException이 발생하면 이 메소드를 실행
        return ApiResponse.of(HttpStatus.BAD_REQUEST, e.getMessage(), null);
    }

}
