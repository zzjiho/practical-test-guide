package sample.cafekiosk.spring.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
// 1. 전역 예외 처리 기능을 제공, @ControllerAdvice + @ResponseBody
// 2. 애플리케이션 내 모든 컨트롤러에서 발생하는 예외를 한 곳에서 처리할 수 있으며, 반환 결과를 JSON과 같은 형태로 자동 변환해 클라이언트에게 응답
// 3. 주로 REST API 환경에서 예외 메시지와 상태 정보를 일관되게 전달할 때 유용
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // @ResponseStatus: 예외 처리 메서드가 반환하는 응답의 HTTP 상태 코드를 명시적으로 지정
    @ExceptionHandler(BindException.class) // @ExceptionHandler: 특정 예외(여기선 BindException)가 발생했을 때 메서드가 실행되도록 지정
    public ApiResponse<Object> bindException(BindException e) {
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                null
        );
    }

}

