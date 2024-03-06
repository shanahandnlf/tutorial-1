package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.List;
import java.util.Map;

public interface PaymentService {
    Payment addPayment(Order order, PaymentMethod method, Map<String, String> paymentData);
    void setStatus(Payment payment, PaymentStatus status);
    Payment getPayment(String id);
    List<Payment> getAllPayment();
}
