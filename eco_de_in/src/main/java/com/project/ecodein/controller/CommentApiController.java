package com.project.ecodein.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.ecodein.dto.AddCommentRequest;
import com.project.ecodein.dto.UpdateCommentRequest;
import com.project.ecodein.entity.Board;
import com.project.ecodein.entity.Comment;
import com.project.ecodein.service.CommentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping ("/board")
@RestController
public class CommentApiController {

	private final CommentService commentService;

	// 댓글 생성
	@PostMapping ("/comment")
	public ResponseEntity<Comment> save (@PathVariable int id,
		@RequestBody AddCommentRequest request,
		Principal principal) {

		Comment savedComment = commentService.save (id, request, principal.getName ());

		return ResponseEntity.status (HttpStatus.CREATED)
			.body (savedComment);

	}

	// 댓글 읽어오기
	@GetMapping ("/{id}/comment")
	public List<Comment> read (@PathVariable int id) {

		return commentService.findAll (id);

	}

	// 댓글 업데이트
	@PutMapping ({ "/{boardNo}/comment/{id}" })
	public ResponseEntity<Long> update (@PathVariable Board boardNo,
		@PathVariable Long id,
		@RequestBody UpdateCommentRequest dto) {

		commentService.update (boardNo, id, dto);
		return ResponseEntity.ok (id);

	}

	// 댓글 삭제
	@DeleteMapping ("/{boardNo}/comment/{id}")
	public ResponseEntity<Long> delete (@PathVariable Board boardNo, @PathVariable Long id) {

		commentService.delete (boardNo, id);
		return ResponseEntity.ok (id);

	}

}
