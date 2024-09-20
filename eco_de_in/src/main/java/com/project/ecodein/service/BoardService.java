package com.project.ecodein.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.project.ecodein.dto.Board;
import com.project.ecodein.repository.BoardRepository;

@Service
public class BoardService {

	private final BoardRepository board;
	
	public BoardService (BoardRepository board) {
		this.board = board;
	}
	
	public List<Board> boardList() {
		return board.findAll ();
	}
}
