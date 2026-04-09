package com.likelion.session.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter // Lombok: 데이터를 꺼내오기 위한 getter 메서드 자동 생성
@Setter // Lombok: 데이터를 필드에 넣어주기 위한 setter 메서드 자동 생성
@NoArgsConstructor // Lombok: 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // Lombok: 모든 필드를 파라미터로 받는 생성자 생성
public class BoardCreateRequest {
    // 넘겨주고 싶은 정보: 제목(title), 내용(content), 작성자(writer)
    private String title;
    private String content;
    private String writer;
}