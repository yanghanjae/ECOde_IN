package com.project.ecodein.service;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import com.project.ecodein.dto.BoardDTO;
import com.project.ecodein.entity.Board;
import com.project.ecodein.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Service
public class BoardService {

	private final BoardRepository boardRepository;
	private final ModelMapper modelMapper;
	
	public BoardService (BoardRepository boardRepository, ModelMapper modelMapper) {
		this.boardRepository = boardRepository;
		this.modelMapper = modelMapper;
	}
	
	public BoardDTO findBoardByNo(int board_no) {
		
		Board board = boardRepository.findById(board_no).orElseThrow(IllegalArgumentException::new);
		// modelMapper를 이용하여 엔티티를 DTO로 변환하여 반환
		return modelMapper.map(board, BoardDTO.class);
	}
	
	public Page<BoardDTO> findBoardList(Pageable pageable) {
		
		/* page 파라미터가 Pageable의 number 값으로 넘어오는데 해당 값이 조회시에는 인덱스 기준이 되어야 해서 -1 처리가 필요하다. */
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
					pageable.getPageSize(),
					Sort.by("boardNo").descending());
	
		Page<Board> boardList = boardRepository.findAll(pageable);
		return boardList.map(board -> modelMapper.map(board, BoardDTO.class));
	}
	
	// save
	@Transactional
	public void addNewBoard(BoardDTO newBoard) {
		boardRepository.save(modelMapper.map(newBoard, Board.class));
	}
	
	@Transactional
	public void updateBoard(BoardDTO updateBoard) {
		
		Board foundBoard = boardRepository.findById(updateBoard.getBoardNo()).orElseThrow(IllegalArgumentException::new);
		foundBoard.setBoardTitle(updateBoard.getBoard_title());
		foundBoard.setBoardContent(updateBoard.getBoard_content());
		foundBoard.setBoardDate(LocalDateTime.now());
	}
	
	@Transactional
	public void deleteBoard(Integer board_no) {
		
		boardRepository.deleteById(board_no);
	}
	
	
	

}
