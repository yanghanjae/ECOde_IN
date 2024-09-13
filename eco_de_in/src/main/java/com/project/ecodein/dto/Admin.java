package com.project.ecodein.dto;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table (name = "admin")
public class Admin {

	@Id
	@Column (name = "admin_id", nullable = false)
	private String admin_id;

	@Column (name = "admin_password", nullable = false)
	private String admin_password;

	@Column (name = "admin_name", nullable = false)
	private String admin_name;

	@Column (name = "admin_date", nullable = false)
	private Date admin_date;

	@Column (name = "admin_recognize", nullable = false)
	private boolean admin_recognize;

}
