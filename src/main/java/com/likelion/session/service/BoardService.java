package com.likelion.session.service;

import com.likelion.session.domain.Board;
import com.likelion.session.dto.BoardCreateRequest;
import com.likelion.session.dto.BoardResponse;
import com.likelion.session.dto.BoardUpdateRequest;
import com.likelion.session.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j // Lombok: 로깅(로그 출력)을 위한 객체를 생성함
@Service // 이 클래스가 비즈니스 로직을 담당하는 서비스 레이어임을 Spring에 알림
@RequiredArgsConstructor // final이 붙은 필드(boardRepository)에 대한 생성자를 자동으로 생성하여 의존성 주입(DI)을 해줌
@Transactional // DB 작업 도중 에러가 나면 모든 작업을 이전으로 롤백(Rollback)하여 데이터 일관성을 유지함
public class BoardService {

    private final BoardRepository boardRepository;

    /*
        게시글 생성
        - Controller가 넘겨준 요청 DTO를 받아서
        - Entity로 바꾼 뒤
        - Repository를 통해 DB에 저장
        - 저장된 결과를 Response DTO로 변환해서 반환
     */
    public BoardResponse create(BoardCreateRequest request) { // DTO를 엔티티(Entity)로 변환하는 것
        Board board = new Board(
                request.getTitle(),
                request.getContent(),
                request.getWriter()
        );

        Board savedBoard = boardRepository.save(board); // Repository를 통해 DB에 저장

        return BoardResponse.builder() // 저장된 엔티티 정보를 DTO에 담아 반환
                .id(savedBoard.getId())
                .title(savedBoard.getTitle())
                .content(savedBoard.getContent())
                .writer(savedBoard.getWriter())
                .createdAt(savedBoard.getCreatedAt())
                .updatedAt(savedBoard.getUpdatedAt())
                .build();
    }

    /*
        게시글 전체 조회
        - DB에 있는 모든 게시글을 가져옴
        - Entity 리스트를 Response DTO 리스트로 변환
     */
    @Transactional(readOnly = true) // 조회 전용 설정으로 성능 최적화에 도움을 줌
    public List<BoardResponse> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(board -> BoardResponse.builder() // 각 엔티티 객체를 DTO 객체로 변환 (Mapping)
                        .id(board.getId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writer(board.getWriter())
                        .createdAt(board.getCreatedAt())
                        .updatedAt(board.getUpdatedAt())
                        .build())
                .toList(); // 리스트 형태로 반환
    }

    /*
        게시글 단건 조회
        - id로 게시글 조회
        - 없으면 예외 발생
     */
    @Transactional(readOnly = true) // 읽기 전용 트랜잭션
    public BoardResponse findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    /*
        게시글 수정
        - 기존 게시글을 찾음
        - 엔티티의 update 메서드로 값 변경
        - JPA 변경 감지로 update 반영
     */
    public BoardResponse update(Long id, BoardUpdateRequest request) { // 게시글 수정 메서드
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        board.update(request.getTitle(), request.getContent()); // 엔티티 객체의 정보를 수정함

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    /*
        게시글 삭제
        - id로 게시글을 찾고
        - 있으면 삭제
     */
    public void delete(Long id) { // 게시글 삭제 메서드
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        boardRepository.delete(board); // Repository를 통해 DB에서 삭제함
    }
}