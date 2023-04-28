package com.testportal.paymentService.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payment_order")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Payment_Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID orderId;

	private String razorOID;
	private Double amount;
	private String receipt;
	private String order_status;
	private String paymentID;
	private LocalDateTime order_date;

	@Column(length = 200)
	private String remarks;

	private Long userId;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "payment_Order")
	private PaymentStatus paymentStatus;
}
