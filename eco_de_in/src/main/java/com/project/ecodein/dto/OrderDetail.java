package com.project.ecodein.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "order_detail")
public class OrderDetail {
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "item_no", nullable = false)
	private int item_no;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "order_no", nullable = false)
	private int order_no;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
}
