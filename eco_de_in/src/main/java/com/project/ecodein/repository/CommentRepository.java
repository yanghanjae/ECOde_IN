package com.project.ecodein.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.project.ecodein.dto.Comment;
import com.project.ecodein.entity.Board;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public List<Comment> findByBoardNo(@Param("boardNo") Board boardNo);
}
