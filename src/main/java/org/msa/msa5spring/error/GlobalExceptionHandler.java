package org.msa.msa5spring.error;

import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 예외처리할때 꼭 넣어줘야함
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 타입이 Void라 널값 가능
    // 예외가 발생하면 아래의 메소드를 실행하겠다
    // Exception 라고만 적으면 데이터타입만 가르키니까
    // Exception.class는 객체로서의 인스턴스화를 하는것?  객체 자체를 전달?해주는것 클래스명.class는 객체로 만들어줌
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e){
        return ResponseEntity.status(500).body(new GlobalResponseDTO<>("E99","시스템 에러",null));
    }
}
