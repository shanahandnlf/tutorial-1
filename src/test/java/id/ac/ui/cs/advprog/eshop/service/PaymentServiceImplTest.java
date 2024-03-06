package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    List<Payment> payments;
    List<Product> products;
    List<Order> orders;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Sumeter");
        orders.add(order2);

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234566273C");
        Payment payment1 = new Payment("4074c620-013b-4414-b085-08f7b08940payment1", "VOUCHER", orders.get(0), paymentData, PaymentStatus.WAITING_PAYMENT.getValue());
        payments.add(payment1);

        paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "23122003");
        Payment payment2 = new Payment("ec556e96-10a5-4d47-a068-d45c6fca71c0", "BANK", orders.get(0), paymentData, PaymentStatus.WAITING_PAYMENT.getValue());
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment1 = payments.get(0);
        doReturn(payment1).when(paymentRepository).save(payment1);
        payment1 = paymentService.addPayment(payment1.getOrder(), "VOUCHER", payment1.getPaymentData());

        Payment payment2 = payments.get(1);
        doReturn(payment2).when(paymentRepository).save(payment2);
        payment2 = paymentService.addPayment(payment2.getOrder(), "BANK", payment2.getPaymentData());

        doReturn(payment1).when(paymentRepository).findById(payment1.getId());
        Payment findResult = paymentService.getPayment(payment1.getId());

        assertEquals(payment1.getId(), findResult.getId());
        assertEquals(payment1.getMethod(), findResult.getMethod());
        assertEquals(payment1.getStatus(), findResult.getStatus());

        doReturn(payment2).when(paymentRepository).findById(payment2.getId());
        findResult = paymentService.getPayment(payment2.getId());

        assertEquals(payment2.getId(), findResult.getId());
        assertEquals(payment2.getMethod(), findResult.getMethod());
        assertEquals(payment2.getStatus(), findResult.getStatus());
    }

    @Test
    void testSetStatusSuccessful() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234566273C");
        Payment payment = new Payment("1111", "VOUCHER", orders.get(0), paymentData);

        assertEquals(PaymentStatus.WAITING_PAYMENT.getValue(), payment.getStatus());
        paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testUpdateOrderStatusWhenPaymentSuccess() {
        Order order = new Order(UUID.randomUUID().toString(), products, 1708560000L, "Bambang Sugeni");
        Payment payment = new Payment(UUID.randomUUID().toString(), "BANK", order, new HashMap<>(), PaymentStatus.PENDING.getValue());

        paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testSetStatusFailed() {
        assertThrows(IllegalArgumentException.class, () ->
                paymentService.setStatus(payments.get(0), "INVALID")
        );
    }

    @Test
    void testUpdateOrderStatusWhenPaymentRejected() {
        Order order = new Order(UUID.randomUUID().toString(), products, 1708560000L, "James Sudrajat");
        Payment payment = new Payment(UUID.randomUUID().toString(), "BANK", order, new HashMap<>(), PaymentStatus.WAITING_PAYMENT.getValue());

        paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(OrderStatus.FAILED.getValue(), payment.getOrder().getStatus());
    }

    @Test
    void testGetPaymentIfFound() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment paymentFound = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), paymentFound.getId());
        assertEquals("VOUCHER", paymentFound.getMethod());
        assertEquals(payment.getStatus(), paymentFound.getStatus());
    }

    @Test
    void testGetPaymentIfNotFound() {
        doReturn(null).when(paymentRepository).findById("invalid");

        Payment payment = paymentService.getPayment("invalid");
        assertNull(payment);
    }

    @Test
    void testGetAllPayment() {
        doReturn(payments).when(paymentRepository).getAllPayments();
        List<Payment> resultPayments = paymentService.getAllPayment();

        assertNotNull(resultPayments);
        assertEquals(payments.size(), resultPayments.size());
        assertTrue(resultPayments.containsAll(payments));
    }
}
