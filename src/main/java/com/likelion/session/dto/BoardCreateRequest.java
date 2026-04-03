package com.likelion.session.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateRequest {
    // 넘겨주고 싶은 정보: 제목(title), 내용(content), 작성자(writer)
    private String title;
    private String content;
    private String writer;
}