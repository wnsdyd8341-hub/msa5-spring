package org.msa.msa5spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

// Rest 시스템이라서 RestController 어노테이션 사용
// Spring boot에 이 클래스가 컨트롤러라고 인지시켜주는것
@RestController
public class RequestMappingController {
    //GET/users 로 요청이 올 경우에
    // GET         자원은users
    // 포트번호는 8080
    // 실행하면 서버가 열림 / localhost:8080/users 라고 주소창에 치면 작업 확인 가능
    // 주소로 여는건 무조건 GET으로 할때만 가능
    @GetMapping("/users")
    public String index(){
        return "유저 조회";
    }
    @PostMapping("/users/1")
    public String store(){
        return "유저 생성";
    }
}
