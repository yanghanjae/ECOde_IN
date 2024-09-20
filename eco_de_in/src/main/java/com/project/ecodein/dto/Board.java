package com.project.ecodein.dto;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "board")
public class Board {

	@Id
	@Column (name = "board_no", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int board_no;

	@Column (name = "board_title", nullable = false)
	private String board_title;

	@Column (name = "board_content", nullable = false)
	private String board_content;

	@Column (name = "board_date", nullable = false)
	private LocalDateTime board_date;

	@ManyToOne
	@JoinColumn (name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn (name = "admin_id",  referencedColumnName = "admin_id", nullable = true)
	private Admin admin;

	@Column (name = "comment_content", nullable = true)
	private String comment_content;

	@Column (name = "comment_date", nullable = true)
	private LocalDateTime comment_date;

}
