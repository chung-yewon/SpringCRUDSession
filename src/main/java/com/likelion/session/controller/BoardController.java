package com.likelion.session.controller;

import com.likelion.session.dto.BoardCreateRequest;
import com.likelion.session.dto.BoardResponse;
import com.likelion.session.dto.BoardUpdateRequest;
import com.likelion.session.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 형태로 객체 데이터를 반환하는 컨트롤러임을 명시
@RequestMapping("/boards") // API 요청 경로의 공통 시작점 설정
@RequiredArgsConstructor // final이 붙은 BoardService 객체를 생성자 주입 방식으로 받아옴 (DI)
public class BoardController {

    private final BoardService boardService;

    /*
        게시글 생성

        [요청 흐름]
        Client
        -> DispatcherServlet
        -> HandlerMapping
        -> BoardController의 create() 메서드 선택
        -> Service 호출
        -> Repository 호출
        -> DB 저장
        -> 결과 반환
        -> JSON 응답
     */
    @Operation( // Swagger: API 문서에 제목과 상세 설명을 표시
            summary = "게시글 생성",
            description = "새로운 게시글을 생성합니다."
    )
    @PostMapping // HTTP POST 요청 처리: 주로 데이터를 생성할 때 사용
    public ResponseEntity<BoardResponse> create(@RequestBody BoardCreateRequest request) {
        BoardResponse response = boardService.create(request);
        return ResponseEntity.ok(response); // 성공 응답(200 OK)과 함께 데이터 반환
    }

    // 게시글 전체 조회
    @Operation( // API 기능 요약
            summary = "게시글 전체 조회",
            description = "등록된 모든 게시글을 조회합니다."
    )
    @GetMapping // HTTP GET 요청 처리: 주로 데이터를 조회할 때 사용
    public ResponseEntity<List<BoardResponse>> findAll() {
        List<BoardResponse> response = boardService.findAll();
        return ResponseEntity.ok(response);
    }

    // 게시글 단건 조회
    @Operation( // API 기능 요약
            summary = "게시글 단건 조회",
            description = "id로 특정 게시글을 조회합니다."
    )
    @GetMapping("/{id}") // 경로에 포함된 {id} 값을 변수로 사용함
    public ResponseEntity<BoardResponse> findById(@PathVariable Long id) {
        BoardResponse response = boardService.findById(id);
        return ResponseEntity.ok(response);
    }

    // 게시글 수정
    @Operation( // API 기능 요약
            summary = "게시글 수정",
            description = "id로 특정 게시글의 제목과 내용을 수정합니다."
    )
    @PutMapping("/{id}") // HTTP PUT 요청 처리: 주로 전체 데이터를 수정할 때 사용
    public ResponseEntity<BoardResponse> update(@PathVariable Long id,
                                                @RequestBody BoardUpdateRequest request) {
        BoardResponse response = boardService.update(id, request);
        return ResponseEntity.ok(response);
    }

    // 게시글 삭제
    @Operation( // API 기능 요약
            summary = "게시글 삭제",
            description = "id로 특정 게시글을 삭제합니다."
    )
    @DeleteMapping("/{id}") // HTTP DELETE 요청 처리: 데이터를 삭제할 때 사용
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boardService.delete(id);
        return ResponseEntity.noContent().build(); // 삭제 성공 시 보낼 데이터가 없으므로 204 No Content 반환
    }
}