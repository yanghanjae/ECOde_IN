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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_no", nullable = false)
    private int detail_no;

	@ManyToOne
	@JoinColumn(name = "item_no", referencedColumnName = "item_no", nullable = false)
	private Item item;

	@ManyToOne
	@JoinColumn(name = "order_no", referencedColumnName = "order_no", nullable = false)
	private Ordering order;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
}
