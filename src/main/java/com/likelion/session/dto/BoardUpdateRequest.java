package com.likelion.session.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter // Lombok: 데이터 조회를 위한 getter 메서드 생성
@Setter // Lombok: 데이터 수정을 위한 setter 메서드 생성
@NoArgsConstructor // Lombok: 기본 생성자 생성
public class BoardUpdateRequest {
    @NotBlank(message = "제목은 필수입니다.") // Validation: 빈 값, 공백만 있는 값을 허용하지 않음
    @Size(max = 100, message = "제목은 100자 이하로 입력해주세요.") // Validation: 최대 100자까지만 허용
    private String title;

    @NotBlank(message = "내용은 필수입니다.") // Validation: 내용이 비어있는지 검사함
    private String content;
}

