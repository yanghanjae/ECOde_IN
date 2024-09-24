package com.project.ecodein.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.ecodein.common.Pagenation;
import com.project.ecodein.common.PagingButtonInfo;
import com.project.ecodein.dto.BoardDTO;
import com.project.ecodein.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private final BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	// 상세페이지
	@GetMapping("/{board_no}")
	public String findBoardByNo(@PathVariable int board_no, Model model) {
		
		BoardDTO board = boardService.findBoardByNo(board_no);
		model.addAttribute("board", board);
		return "board/boardDetail";
	}

	// 목록
	@GetMapping("/board")
	public String findBoardList(@PageableDefault Pageable pageable,Model model) {
		
		Page<BoardDTO> boardList = boardService.findBoardList(pageable);
		
		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(boardList);
		
		model.addAttribute("paging", paging);
		model.addAttribute("boardList", boardList);
		
		return "board/board";
	}
	
	// 등록
	@GetMapping("/add")
	public void addPage() {}
	
	@PostMapping("/add")
	public String addNewBoard(BoardDTO newBoard) {
		
		boardService.addNewBoard(newBoard);
		
		return "redirect:/board/board";
	}
	
	// 수정
	@GetMapping("/update")
	public void updatePage() {
	}
	
	@PostMapping("/update")
	public String updateBoard(BoardDTO updateBoard) {
		
		boardService.updateBoard(updateBoard);
		
		return "redirect:/board/" + updateBoard.getBoardNo();
	}
	
	// 삭제
	@GetMapping("/delete")
	public void deletePage() {}
	
	@PostMapping("/delete")
	public String deleteBoard(@RequestParam Integer board_no) {
		
		boardService.deleteBoard(board_no);
		
		return "redirect:/board/board";
	}
	
	
	
}
