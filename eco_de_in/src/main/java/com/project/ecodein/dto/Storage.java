package com.project.ecodein.dto;

import java.time.LocalDate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
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
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="storage")
@DynamicInsert
@DynamicUpdate
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
	@ColumnDefault("'current_date()'")
	private LocalDate storage_regist;

	public Storage (String storage_name, String storage_status, String storage_site) {

		this.storage_name = storage_name;
		this.storage_status = storage_status;
		this.storage_site = storage_site;

	}
	
	
}

