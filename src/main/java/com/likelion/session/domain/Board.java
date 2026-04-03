package com.likelion.session.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter // getter 메서드 자동 생성
@Entity // 해당 클래스 DB 테이블로 인식하고 관리
@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ;

    // 게시글 제목
    @Column(nullable = false, length = 100)
    private String ;

    // 게시글 내용
    @Column(nullable = false, columnDefinition = "TEXT")
    private String ;

    // 작성자
    @Column(nullable = false, length = 30)
    private String ;

    // 생성 시간
    @Column(nullable = false)
    private LocalDateTime ;

    // 수정 시간
    @Column(nullable = false)
    private LocalDateTime ;


    public Board(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}