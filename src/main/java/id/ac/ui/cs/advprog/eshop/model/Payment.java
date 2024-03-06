package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {

    private String id;
    private PaymentMethod method;
    private Order order;
    private Map<String, String> paymentData;
    private PaymentStatus status;

    public Payment(String id, PaymentMethod method, Map<String, String> paymentData, Order order) {
        this(id, method, paymentData, order, PaymentStatus.WAITING_PAYMENT);
    }

    public Payment(String id, PaymentMethod method, Map<String, String> paymentData, Order order, PaymentStatus status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    private void setOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        this.order = order;
    }

    public void setStatus(PaymentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        this.status = status;
    }

    protected void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }
        this.paymentData = paymentData;
    }
}