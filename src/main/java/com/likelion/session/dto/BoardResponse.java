package com.likelion.session.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter // Lombok: 응답 데이터를 꺼내 JSON으로 변환하기 위해 필요함
@AllArgsConstructor // Lombok: 모든 데이터를 담아 객체를 만들기 위한 생성자 생성
@Builder // Lombok: 빌더 패턴을 사용하여 객체 생성을 유연하고 가독성 있게 해줌
public class BoardResponse {

    // 돌려주고 싶은 응답: id, title, content, writer, createdAt, updatedAt
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}