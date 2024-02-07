package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Product edit (Product product){
        Product existingProduct = findById(product.getProductId());
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductQuantity(product.getProductQuantity());
        return existingProduct;
    }

    public Product delete(Product product) {
        Product deletedProduct = findById(product.getProductId());
        productData.remove(deletedProduct);
        return deletedProduct;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        throw new NoSuchElementException("No product with ID: " + productId);
    }


}
