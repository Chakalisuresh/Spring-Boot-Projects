package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private String review;
    private int rating;

    // Constructors (default and parameterized)

    public ProductReview() {
        // Default constructor
    }

    public ProductReview(Product product, String review, int rating) {
        this.product = product;
        this.review = review;
        this.rating = rating;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    // toString method for better logging and debugging

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", product=" + product +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}


