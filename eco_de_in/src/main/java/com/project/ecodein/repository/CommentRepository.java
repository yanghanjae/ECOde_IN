package com.project.ecodein.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.ecodein.entity.Board;
import com.project.ecodein.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentByBoardNoOrderById (Board board);
    Optional<Comment> findByBoardNoAndId (Board boardNo, Long id);
    List<Comment> getCommentByBoardNoOrderById(Board board);
    Optional<Comment> findByBoardNoAndId(Board boardNo, Long id);
}
