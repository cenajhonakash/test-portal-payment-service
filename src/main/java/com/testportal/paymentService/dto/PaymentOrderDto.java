package com.testportal.paymentService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentOrderDto {

	private Long myOrderID;
	private String razorOID;
	private String receipt;
	private String order_status;
	private String paymentID;
	private LocalDateTime order_date;
	private Double amount;
	private String remarks;
}
