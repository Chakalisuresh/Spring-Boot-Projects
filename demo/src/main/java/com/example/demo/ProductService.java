package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    //createProduc Method
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
   //GetProduct Method
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = getProduct(productId);

        if (existingProduct != null) {
            // Update the existing product with the new values
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            // ... other fields

            // Save the updated product
            return productRepository.save(existingProduct);
        } else {
            // Handle the case where the product is not found, you might throw an exception or return null
            return null;
        }
    }
    
    //GetAll Method
     public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
     
   //Delete Method
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

