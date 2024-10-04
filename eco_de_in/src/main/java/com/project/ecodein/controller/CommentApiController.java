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

	// 댓글 읽어오기
	@GetMapping("/{id}/comments")
	public List<Comment> read(@PathVariable int id) {
		return commentService.findAll(id);
	}

}
