package com.testportal.paymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testportal.paymentService.entity.Payment_Order;

public interface PaymentOrderRepo extends JpaRepository<Payment_Order, Long> {

}
