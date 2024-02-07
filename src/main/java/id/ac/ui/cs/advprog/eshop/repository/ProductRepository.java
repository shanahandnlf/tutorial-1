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

    public Product edit (Product updatedProduct){
        for (Product existingProduct : productData) {
            if (existingProduct.getProductId().equals(updatedProduct.getProductId())) {
                String newProductName = updatedProduct.getProductName();
                int newProductQuantity = updatedProduct.getProductQuantity();
                existingProduct.setProductName(newProductName);
                existingProduct.setProductQuantity(newProductQuantity);
                return updatedProduct;
            }
        }
        throw new NoSuchElementException("No product found with ID: " + updatedProduct.getProductId());
    }

    public Product delete(Product deletedProduct) {
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
