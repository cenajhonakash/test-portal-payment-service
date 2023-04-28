package com.testportal.paymentService.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.testportal.paymentService.controller.PaymentController;
import com.testportal.paymentService.dto.PaymentOrderDto;
import com.testportal.paymentService.dto.PaymentStatusDto;
import com.testportal.paymentService.entity.Payment_Order;
import com.testportal.paymentService.repository.PaymentOrderRepo;
import com.testportal.paymentService.repository.PaymentStatusRepo;

@Service
public class PaymentService {

	private static String CURRENCY = "INR";
	private static String RAZOR_RECEIPT_NAME = "receipt";
	private static String RAZOR_ORDER_ID = "id";
	private static String PAYMENT_INITITATED_STATUS = "Created";
	private static String SUCCESS_PAYMENT = "SUCCESS";
	private static String FALIED_PAYMENT = "FAILED";

	private Logger log = LoggerFactory.getLogger(PaymentController.class);

	@Value("${razorPay_Model.key_Id}")
	String key_Id;
	@Value("${razorPay_Model.secret_key}")
	String secret_key;

	@Autowired
	private PaymentOrderRepo paymentOrderRepo;
	@Autowired
	private PaymentStatusRepo paymentStatusRepo;
	@Autowired
	private Random random;
	@Autowired
	private ModelMapper mapper;

	@Transactional(propagation = Propagation.REQUIRED)
	public PaymentOrderDto createOrder(PaymentOrderDto makePaymentDto, Long userId) {

		String methodname = "createOrder()";
		log.info(methodname + " called");
		boolean flagPayment = false;
		PaymentStatusDto status = null;
		PaymentOrderDto paymentOrder = null;
		Order order = null;
		try {
			var client = new RazorpayClient(key_Id.substring(1, key_Id.length() - 1), secret_key.substring(1, secret_key.length() - 1));

			JSONObject obj = new JSONObject();
			obj.put("amount", (makePaymentDto.getAmount()) * 100);
			obj.put("currency", CURRENCY);
			obj.put("receipt", ("txn_" + random.nextInt() + "_" + java.time.LocalDate.now()));
			order = client.Orders.create(obj);

			Payment_Order payment_Order = Payment_Order.builder().amount(makePaymentDto.getAmount()).receipt(order.get(RAZOR_RECEIPT_NAME)).razorOID(order.get(RAZOR_ORDER_ID))
					.order_date(LocalDateTime.now()).order_status(PAYMENT_INITITATED_STATUS).userId(userId).remarks(makePaymentDto.getRemarks()).build();

			Payment_Order paymentRes = paymentOrderRepo.saveAndFlush(payment_Order);

			flagPayment = true;

			status = PaymentStatusDto.builder().status_cd(SUCCESS_PAYMENT).razor_receipt(order.get(RAZOR_RECEIPT_NAME)).razor_order_id(order.get(RAZOR_ORDER_ID))
					.paymentId(paymentRes.getOrderId()).build();
			paymentOrder = this.mapper.map(paymentRes, PaymentOrderDto.class);
			log.info("Order created successfully for amount: {}", makePaymentDto.getAmount());

		} catch (RazorpayException e) {
			log.info("Exception occured during order creation with error: {}", e);
			// status = PaymentStatusDto.builder().status_cd(FALIED_PAYMENT).failure_reason(e.toString()).build();
//			paymentStatusRepo.saveAndFlush(PaymentStatus.builder().failure_reason(e.getMessage()).status(FALIED_PAYMENT).razor_receipt(order.get(RAZOR_RECEIPT_NAME))
//					.razor_order_id(order.get(RAZOR_ORDER_ID)).paymentId(status.getPaymentId()).build());
		} finally {
//			if (!flagPayment && null != order) {
//				paymentStatusRepo.saveAndFlush(PaymentStatus.builder().failure_reason(paymentOrder.get).status(FALIED_PAYMENT).razor_receipt(order.get(RAZOR_RECEIPT_NAME))
//						.razor_order_id(order.get(RAZOR_ORDER_ID)).paymentId(status.getPaymentId()).build());
//			}
		}

		return paymentOrder;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public PaymentOrderDto updateOrderStatus(String status) {
		return null;

	}
}
