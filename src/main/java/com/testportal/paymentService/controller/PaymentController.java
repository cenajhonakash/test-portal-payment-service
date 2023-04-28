package com.testportal.paymentService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testportal.paymentService.dto.PaymentOrderDto;
import com.testportal.paymentService.service.PaymentService;

@RestController
@RequestMapping("/v1/admin")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	/*
	 * jwt protected endpoint
	 */
	@PostMapping("/create-order")
	public ResponseEntity<PaymentOrderDto> createOrder(@RequestBody PaymentOrderDto data, @RequestParam(required = false) Long userId) {
		return new ResponseEntity<PaymentOrderDto>(paymentService.createOrder(data, userId), HttpStatus.OK);
	}

	/*
	 * API available for user & admin
	 */
	@GetMapping("/getPaymentDetails/{userID}")
	public List<PaymentOrderDto> getAllAttemptedPaymentsForUser(@RequestParam(name = "status", required = false) String status) {
		return null;
	}

	/*
	 * API only available for super admin
	 */
	@GetMapping("/getPaymentDetails")
	public List<PaymentOrderDto> getAllAttemptedPayments(@RequestParam(name = "status", required = false) String status) {
		return null;
	}

	/*
	 * jwt protected endpoint
	 */
	@PutMapping("/update-order-status/{razorOID}")
	public ResponseEntity<?> updateOrder(@PathVariable(name = "razorOID") String razorOID) {
		return null;
	}
}
