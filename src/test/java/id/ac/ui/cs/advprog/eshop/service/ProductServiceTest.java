package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository).create(product);
    }

    @Test
    void findAll() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        Iterator<Product> productIterator = products.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> allProducts = productService.findAll();

        assertEquals(products.size(), allProducts.size());
        verify(productRepository).findAll();
    }

    @Test
    void findById() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(product);

        Product foundProduct = productService.findById(productId);

        assertEquals(product, foundProduct);
        verify(productRepository).findById(productId);
    }

    @Test
    void edit() {
        Product product = new Product();
        when(productRepository.edit(product)).thenReturn(product);

        Product editedProduct = productService.edit(product);

        assertEquals(product, editedProduct);
        verify(productRepository).edit(product);
    }

    @Test
    void delete() {
        Product product = new Product();
        when(productRepository.delete(product)).thenReturn(product);

        Product deletedProduct = productService.delete(product);

        assertEquals(product, deletedProduct);
        verify(productRepository).delete(product);
    }
}
