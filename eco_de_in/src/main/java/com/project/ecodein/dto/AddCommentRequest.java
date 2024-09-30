package com.project.ecodein.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.project.ecodein.entity.Board;
import com.project.ecodein.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddCommentRequest {

	private Long id;
	private String comment;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private Admin admin;
	private Board boardNo;
	
	public Comment toEntity() {//생성자를 사용해 객체 생성
        return Comment.builder()
                .comment(comment)
                .createdDate(createdDate)
                .modifiedDate(modifiedDate)
                .admin(admin)
                .boardNo(boardNo)
                .build();
    }

}
