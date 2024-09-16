package com.project.ecodein.dto;

import java.sql.Date;
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
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user")
public class User {
	
	@Id
	@Column(name="user_id", nullable=false)
	private String user_id;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="buyer_code", nullable = false)
	private Buyer buyer_code;

	@Column(name="user_password", nullable = false)
	private String user_password;
	
	@Column(name="user_name", nullable = false)
	private String user_name;

	@Column(name="user_date", nullable = false)
	private Date user_date;

	@Column(name = "user_email", nullable = false)
	private String user_email;

}

