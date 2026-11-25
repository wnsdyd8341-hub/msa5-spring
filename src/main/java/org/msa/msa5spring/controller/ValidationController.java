package org.msa.msa5spring.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.msa.msa5spring.request.RegistrationRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ValidationController {
    //유효성 체크

    @GetMapping("/validation")
    //Json 형식으로 받음
    public String validation(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO){
        return "유효성 검사";
    }

    @GetMapping("/validation/{id}")
    public String validationSegment(
            @Min (value = 1, message = "1이상")@PathVariable Long id
    ){return id.toString();}
}
