package org.msa.msa5spring.error;

import lombok.extern.slf4j.Slf4j;
import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;
import java.util.stream.Collectors;

// 예외처리할때 꼭 넣어줘야함
// 에러날때 log파일에 에러메세지를 찍어줄려고 적음
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // ----------------------------------
    // 유효성 체크 예외 관련
    // 하나만 예외가(에러) 발생하면 바로 예외처리로 들어가는것
    // 하나만 에러나도 바로 실행
    // 데이터 타입 에러
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GlobalResponseDTO<String>> handle(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GlobalResponseDTO<>(
                "E01","유효성 체크 에러",String.format("%s : 필드를 확인해 주세요.",e.getName())));
    }


    // 우리가 만든 DTO 객체로 복수의 자료를 검증할때 복수의 에러메세지가 반환될때 이 에러 발생
    // 복수 에러체크하고나서 실행
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponseDTO<Map<String,String>>> handle(MethodArgumentNotValidException e){
        // 여러개의 에러처리를 담는?
        // getBindingResult() 각각의 필드의 정보를 담고있슴
        // getFieldErrors() 각각의 필드의 에러를 담고있슴
        // collect : 하나하나 요소들을 추출해서 map의 형태로 변환? 하는 메소드
        // Collectors.toMap() map 형태로 바꿔줌
        Map<String,String> errors =e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField, // fieldError -> fieldError.getField() 이걸 메소드레퍼런스,필드명을 가져와서 Map의 key로 사용
                        fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "유효하지 않은 값.", //밸류값
                        (existing,replacement) -> existing));  // 람다식 두개의 string타입을 넣고 existing 반환 existing 기존값
                        // replacement 새로운 값
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new GlobalResponseDTO("E01","유효성 체크 에러",errors));
        // 결과 보고싶으면 postMan에서 validation 실행

    }
    // ----------------------------------

    // 그 외 모든 예외 처리 핸들러 메소드
    // 타입이 Void라 널값 가능
    // 예외가 발생하면 아래의 메소드를 실행하겠다
    // Exception 라고만 적으면 데이터타입만 가르키니까
    // Exception.class는 객체로서의 인스턴스화를 하는것?  객체 자체를 전달?해주는것 클래스명.class는 객체로 만들어줌
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponseDTO<Void>> handle(Exception e){
        log.trace("트레이스 로깅");
        log.debug("디버그 로깅");
        log.info("인포 로깅");
        log.warn("워닝 로깅");
        log.error("에러 로깅");
        return ResponseEntity.status(500).body(new GlobalResponseDTO<>("E99","시스템 에러",null));
    }
}
