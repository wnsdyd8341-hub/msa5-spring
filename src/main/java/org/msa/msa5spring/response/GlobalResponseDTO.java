package org.msa.msa5spring.response;

public record GlobalResponseDTO<T>(
    String code, //에러코드
    String message, //에러메세지
    T result // 유저한테 보낼 메세지

) { }
