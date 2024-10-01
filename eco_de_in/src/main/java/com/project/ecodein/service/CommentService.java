package com.project.ecodein.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.project.ecodein.dto.AddCommentRequest;
import com.project.ecodein.dto.Admin;
import com.project.ecodein.dto.UpdateCommentRequest;
import com.project.ecodein.entity.Board;
import com.project.ecodein.entity.Comment;
import com.project.ecodein.repository.AdminRepository;
import com.project.ecodein.repository.BoardRepository;
import com.project.ecodein.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdminRepository adminRepository;
    private final BoardRepository boardRepository;

    //댓글 추가 메서드
    public Comment save(int id, AddCommentRequest request, String adminName) {
        Optional<Admin> adminOptional = adminRepository.findByAdminId(adminName);
        Admin admin;
        if (adminOptional.isPresent()) { // Optional이 값으로 채워져 있는지 확인
            admin = adminOptional.get(); // User 객체 추출
        } else {
            System.out.println("사용자가 존재하지 않습니다: " + adminName);
            return null;
        }
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다. " + id));

        request.setAdmin(admin);
        request.setBoardNo(board);

        return commentRepository.save(request.toEntity());
    }

    //댓글을 읽어온다.
    @Transactional(readOnly = true)
    public List<Comment> findAll(int id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 존재하지 않습니다. id: " + id));
        List<Comment> comments = board.getComments();
        return comments;
    }

    //댓글 업데이트
    @Transactional
    public void update(Board boardNo, Long id, UpdateCommentRequest dto) {
        Comment comment = commentRepository.findByBoardNoAndId(boardNo, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));

        comment.update(dto.getComment());
    }

    //댓글 삭제
    @Transactional
    public void delete(Board boardNo, Long id) {
        Comment comment = commentRepository.findByBoardNoAndId(boardNo, id).orElseThrow(() ->
                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));

        commentRepository.delete(comment);
    }

}