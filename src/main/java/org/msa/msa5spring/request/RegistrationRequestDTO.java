package org.msa.msa5spring.request;

import jakarta.validation.constraints.*;

//DTO방식으로 데이터 전송
public record RegistrationRequestDTO(
        @NotBlank(message = "이메일 필수")
        // ^부터$까지  [] 범위 {n,m} n글자~m글자
        @Pattern(regexp = "^[a-z]{4,8}$",message = "이메일 패턴 틀림") // NotBlank 보다 Pattern이 더 우선시
        String email,

        @NotBlank(message = "비밀번호 필수")
        @Size(min = 2, max = 10, message = "비밀번호는 2~10자")
        String password,
        String name,

        @Min(value = 0 , message = "나이는 최소 0살부터입니다")
        @Max(value = 999 , message = "나이는 최대 999살까지입니다.")
        Integer age
) {}
