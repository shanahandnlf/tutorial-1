package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private List<Product> products;
    private Map<String, String> paymentData;
    private Order order;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();

        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        Order order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");

    }


    @Test
    public void testCreatePaymentWithVoucher() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment newPayment = new Payment("1", "voucherCode", paymentData, order);
        assertEquals("1", newPayment.getId());
        assertEquals("voucherCode", newPayment.getMethod());
        assertEquals(paymentData, newPayment.getPaymentData());
        assertSame(payment.getOrder(), order);
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), newPayment.getStatus());
        paymentData.clear();
    }

    @Test
    public void testCreatePaymentWithVoucherAndStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment newPayment = new Payment("1", "voucherCode", paymentData, order, OrderStatus.SUCCESS.getValue());
        assertEquals("1", newPayment.getId());
        assertEquals("voucherCode", newPayment.getMethod());
        assertEquals(paymentData, newPayment.getPaymentData());
        assertSame(payment.getOrder(), order);
        assertEquals(OrderStatus.SUCCESS.getValue(), newPayment.getStatus());
        paymentData.clear();
    }

    @Test
    public void testCreatePaymentWithBankInfo() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "23122003");

        Payment newPayment = new Payment("1", "bankTransfer", paymentData, order);
        assertEquals("1", newPayment.getId());
        assertEquals("bankTransfer", newPayment.getMethod());
        assertEquals(paymentData, newPayment.getPaymentData());
        assertSame(payment.getOrder(), order);
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), newPayment.getStatus());
        paymentData.clear();
    }

    @Test
    public void testCreatePaymentWithBankInfoAndStatus() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "23122003");

        Payment newPayment = new Payment("1", "bankTransfer", paymentData, order, OrderStatus.SUCCESS.getValue());
        assertEquals("1", newPayment.getId());
        assertEquals("bankTransfer", newPayment.getMethod());
        assertEquals(paymentData, newPayment.getPaymentData());
        assertSame(payment.getOrder(), order);
        assertEquals(OrderStatus.SUCCESS.getValue(), newPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("1", "voucherCode", new HashMap<>(), order));
    }

    @Test
    void testCreatePaymentWithInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> new Payment("1", "voucherCode", paymentData, order, "Invalid"));
    }

    @Test
    void testSetStatusWithVoucher() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment newPayment = new Payment("1", "voucherCode", paymentData, order);
        newPayment.setStatus(OrderStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertEquals(OrderStatus.SUCCESS.getValue(), newPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusWithBankInfo() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "23122003");

        Payment newPayment = new Payment("1", "bankTransfer", paymentData, order);
        newPayment.setStatus(OrderStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertEquals(OrderStatus.SUCCESS.getValue(), newPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment newPayment = new Payment("1", "voucherCode", paymentData, order);
        assertThrows(IllegalArgumentException.class, () -> newPayment.setStatus("INVALID_STATUS"));
        paymentData.clear();
    }

    @Test
    void testSetStatusWithEmptyPaymentData() {
        assertThrows(IllegalArgumentException.class, () -> new Payment("1", "voucherCode", new HashMap<>(), order));
    }
}


