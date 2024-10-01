package com.project.ecodein.dto;

import java.time.LocalDateTime;
import com.project.ecodein.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CommentResponse {
	private Long id;
    private String comment;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
//    private String admin_name;
//    private String admin_Id;
	private String admin;
	private int boardNo;

    /* Entity -> Dto*/
    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.boardNo = comment.getBoardNo().getBoardNo();
//        this.admin_name = comment.getAdmin().getAdmin_name();
//        this.admin_Id = comment.getAdmin().getAdmin_id();
        this.admin = comment.getAdmin().getAdmin_id();
    }
}
