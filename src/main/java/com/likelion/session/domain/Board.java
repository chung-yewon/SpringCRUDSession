package com.likelion.session.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Getter // getter 메서드 자동 생성
@Entity // 해당 클래스 DB 테이블로 인식하고 관리
@Table(name = "boards") // 실제 DB에 생성될 테이블 이름을 "boards"로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 생성, PROTECTED로 설정하여 무분별한 객체 생성 방지
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자 자동 생성
public class Board {

    @Id // 테이블의 기본키(PK)임을 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 값을 DB가 자동으로 증가시켜줌 (MySQL Auto Increment)
    private Long id;

    // 게시글 제목
    @Column(nullable = false, length = 100) // null 불가, 최대 길이는 100자로 제한
    private String title;

    // 게시글 내용
    @Column(nullable = false, columnDefinition = "TEXT") // null 불가, 일반 문자열보다 긴 텍스트를 담기 위해 타입을 TEXT로 지정
    private String content;

    // 작성자
    @Column(nullable = false, length = 30) // null 불가, 최대 길이는 30자로 제한
    private String writer;

    // 생성 시간
    @Column(nullable = false) // null 불가
    private LocalDateTime createdAt;

    // 수정 시간
    @Column(nullable = false) // null 불가
    private LocalDateTime updatedAt;

    public Board(String title, String content, String writer) { // 새로운 게시글 객체를 처음 만들 때 사용
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    @PrePersist // 데이터가 처음 DB에 저장(persist)되기 직전에 자동으로 실행됨
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); // 생성 시간 기록
        this.updatedAt = LocalDateTime.now(); // 수정 시간도 생성 시간과 동일하게 초기화
    }

    @PreUpdate // 데이터가 수정(update)되기 직전에 자동으로 실행됨
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    } // 수정 시간을 현재 시간으로 갱신

    // 게시글 수정을 위한 메서드
    public void update(String title, String content) { // 이미 만들어진 게시글의 내용을 바꿀 때 사용
        this.title = title;
        this.content = content;
    }
}