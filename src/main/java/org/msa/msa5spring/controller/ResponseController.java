package org.msa.msa5spring.controller;


import org.msa.msa5spring.request.QueryRequestDTO;
import org.msa.msa5spring.response.GlobalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResponseController {
    @GetMapping("/res")
    // 바디에 담기는건 제너릭 타입과 일치해야한다
    // status 200으로 설정하고 str 출력?
    public ResponseEntity<GlobalResponseDTO<String>> index(){
        // return ResponseEntity.status(200).body("str");
        // return ResponseEntity.ok("3"); 이렇게도 쓸수있다
        // return ResponseEntity.notFound().build(); 404에러 출력
        GlobalResponseDTO<String> globalResponseDTO = new GlobalResponseDTO<>("00","정상처리","레스폰스 테스트");
        return ResponseEntity.status(200).body(globalResponseDTO);
    }

    @GetMapping("/res/test")
    public ResponseEntity<GlobalResponseDTO<QueryRequestDTO>> test(
            QueryRequestDTO queryRequestDTO
    ){
        return ResponseEntity.status(200).body(new GlobalResponseDTO<>("00","정상 처리",new QueryRequestDTO(3L,"홍길동")));
    }
}
