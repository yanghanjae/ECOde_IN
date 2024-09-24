package com.project.ecodein.dto;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {
	
	private int boardNo;
	private String board_title;
	private String board_content;
	private LocalDateTime board_date;
	private User user;
	private Admin admin;
	private String comment_content;
	private LocalDateTime comment_date;

}
