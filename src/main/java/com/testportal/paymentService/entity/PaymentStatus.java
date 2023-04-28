package com.testportal.paymentService.entity;

import java.util.UUID;

import javax.persistence.Entity;
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
@Table(name = "payment_status")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String status;
	private String failure_reason;
	private String razor_receipt;
	private String razor_order_id;
	private UUID paymentId;
	@OneToOne
	private Payment_Order payment_Order;
}
