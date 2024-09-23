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
@Table(name="storage")
public class Storage {
	
	@Id
	@Column(name="storage_no", nullable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storage_no;
	
	@Column(name="storage_name", nullable = false)
	private String storage_name;

	@Column(name="storage_status", nullable = false)
	private String storage_status;
	
	@Column(name="storage_site", nullable = false)
	private String storage_site;

	@Column(name="storage_regist", nullable = false)
	private Date storage_regist;
}

