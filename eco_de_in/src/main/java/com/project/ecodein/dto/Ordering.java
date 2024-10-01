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
@Table(name = "ordering")
public class Ordering {

	@Id
	@Column(name = "order_no", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderNo;
	
	@ManyToOne
	@JoinColumn(name = "buyer_code", referencedColumnName = "buyer_code", nullable = false)
	private Buyer buyer_code;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private User user_id;
	
	@Column(name = "order_date", nullable = false)
	private Date order_date;
	
	@Column(name = "due_date", nullable = false)
	private Date due_date;
	
	@Column(name = "is_delivery", nullable = false)
	//private Boolean is_delivery;
	private Boolean isDelivery;
}
