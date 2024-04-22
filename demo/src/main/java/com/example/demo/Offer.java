package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private Long productId;
    private String offerDescription;
    private double discount;

    // Constructors (default and parameterized)

    public Offer() {
        // Default constructor
    }

    public Offer(Long productId, String offerDescription, double discount) {
        this.productId = productId;
        this.offerDescription = offerDescription;
        this.discount = discount;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getOfferDescription() {
        return offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    // toString method for better logging and debugging

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", productId=" + productId +
                ", offerDescription='" + offerDescription + '\'' +
                ", discount=" + discount +
                '}';
    }
}

