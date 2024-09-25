package com.project.ecodein.dto;

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
@Table(name = "item")
public class Item {
	@Id
	@Column(name = "item_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int item_no;
	
	@Column(name = "item_name", nullable = false)
	private String item_name;
	
	@Column(name = "item_price", nullable = true)
	private Integer item_price;
	
	@Column(name = "is_material", nullable = false)
	private Boolean is_material;
	
	@Column(name = "item_image", nullable = false)
	private String item_image;
	
}
