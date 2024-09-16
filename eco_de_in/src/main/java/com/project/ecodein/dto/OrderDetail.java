package com.project.ecodein.dto;

import jakarta.persistence.*;
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

    @Id
	@ManyToOne
	@JoinColumn(referencedColumnName = "item_no", nullable = false)
	private Item item_no;

    @Id
	@ManyToOne
	@JoinColumn(referencedColumnName = "order_no", nullable = false)
	private Ordering order_no;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
}
