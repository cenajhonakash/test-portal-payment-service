package com.testportal.paymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testportal.paymentService.entity.PaymentStatus;

public interface PaymentStatusRepo extends JpaRepository<PaymentStatus, Long> {

}
