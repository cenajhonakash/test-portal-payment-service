package com.testportal.paymentService.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PaymentStatusDto {

	private Long Id;
	private String status_cd;
	private String failure_reason;
	private PaymentOrderDto makePaymentDto;
	private String razor_receipt;
	private String razor_order_id;
	private UUID paymentId;
}
