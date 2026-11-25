package org.msa.msa5spring.controller;

import org.msa.msa5spring.request.QueryRequestDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// Rest 시스템이라서 RestController 어노테이션 사용
// Spring boot에 이 클래스가 컨트롤러라고 인지시켜주는것
@RestController
// RequestMapping 을 하면 클래스 안에 있는 어노테이션 주소를 다 통일시켜줌 users로
@RequestMapping("/api")
public class RequestMappingController {
    //GET/users 로 요청이 올 경우에
    // GET         자원은users
    // 포트번호는 8080
    // 실행하면 서버가 열림 / localhost:8080/users 라고 주소창에 치면 작업 확인 가능
    // 주소로 여는건 무조건 GET으로 할때만 가능
    @GetMapping
    public String index(){
        return "유저 조회";
    }
    @PostMapping
    public String store(){
        return "유저 생성";
    }
    @PutMapping
    public String update(){
        return "유저 수정";
    }
    @DeleteMapping
    public String destroy(){
        return "유저 삭제";
    }

    // 쿼리 파라미터로 보낸값 받는것
    @GetMapping("/query")
    public String query(QueryRequestDTO queryRequestDTO){
        return String.format("%d : %s",queryRequestDTO.id(),queryRequestDTO.name());
    }


    // 세그먼트 파라미터로 보낸값 받는것
    @GetMapping("/segment/{id}/{name}")
    public String segment(@ModelAttribute QueryRequestDTO queryRequestDTO){
        return String.format("segment = %d : %s",queryRequestDTO.id(),queryRequestDTO.name());
    }
    //
    // // 위에서 test/my 를 넣으면 세그먼트가 실행이 안되고 아래꺼가 실행이 되어서 설계할때 주의? 해야한다
    // @GetMapping("/segment/test/my")
    // public String test(){
    //     return "Test";
    // }

    // @ModelAttribute : 폼데이터로 오는거 받거나 세그먼트로 오는거 받는거
    // Form data 로 보낸값 받는거
    @PostMapping("/form")
    // key로 준 값이 MultipartFile file(key값이름) 이랑 일치해야 받을수있다
    public String form(@ModelAttribute MultipartFile file){
        return String.format("%s : %s",file.getContentType(),file.getOriginalFilename());
    }

    @GetMapping("/json")
    public String json(@RequestBody QueryRequestDTO queryRequestDTO){
        return String.format("json = %d : %s",queryRequestDTO.id(),queryRequestDTO.name());
    }


    // 데이터 보내는 방식
    // Query Parameter 방식
    // - 어노테이션 x , DTO객체 생성

    //Segment Parameter 방식
    // -@PathVariable , 각 키에 맞는 파라미터를 별도로 맵핑 메소드(컨트롤러 파라미터)로 생성
    // -@ModelAttribute , DTO 객체 생성해서 이용

    // Form Data 방식
    // - @ModelAttribute , DTO 객체 또는 컨트롤러 파라미터로

    // Json 방식
    // - @RequestBody , 일반적으로 DTO 객체 방식으로 받음

}
