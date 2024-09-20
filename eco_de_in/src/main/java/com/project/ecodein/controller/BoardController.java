package com.project.ecodein.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.project.ecodein.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 목록
	@GetMapping("")
	public String board(Model model) {
		model.addAttribute ("boardList", boardService.boardList());
		return "board/board";
	}
	
	// 글쓰기
	@GetMapping("/add")
	public String add() {
		return "board/add";
	}
	
	// 수정
	@GetMapping("/update")
	public String update() {
		return "board/update";
	}
	
	// 상세페이지
	@GetMapping("/{board_no}")
	public String boardDetail(Model model) {
		return "board/boardDetail";
	}
	
	
}
