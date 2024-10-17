package com.project.ecodein.dto;

import java.time.LocalDateTime;
import java.util.List;
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
	private String boardTitle;
	private String boardContent;
	private LocalDateTime boardDate;
	private User user;


}
