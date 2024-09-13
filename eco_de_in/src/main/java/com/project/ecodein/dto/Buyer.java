package com.project.ecodein.dto;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table (name = "buyer")
public class Buyer {

	@Id
	@Column (name = "buyer_code", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long buyer_code;

	@Column (name = "buyer_name", nullable = false)
	private String buyer_name;

	@Column (name = "buyer_number", nullable = false)
	private String buyer_number;

	@Column (name = "buyer_agent", nullable = false)
	private String buyer_agent;

	@Column (name = "buyer_tel", nullable = false)
	private String buyer_tel;

	@Column (name = "buyer_address", nullable = false)
	private String buyer_address;

	@Column (name = "buyer_regist_date", nullable = false)
	private LocalDateTime buyer_regist_date;

	@Column (name = "buyer_status", nullable = false)
	private boolean buyer_status;

	@Column (name = "buyer_secure_code", nullable = false)
	private String buyer_secure_code;

}
