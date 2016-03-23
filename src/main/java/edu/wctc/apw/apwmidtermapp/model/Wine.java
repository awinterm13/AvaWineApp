/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import java.util.Objects;

/**
 * This class represents a Wine. 
 * @author andre_000
 */
public class Wine {
    private int productId;
    private String productName;
    private double productPrice;
    private String imageURL;
    
    /**
     * default constructor
     */
    public Wine() {
    }
    /**
     * constructor that takes all parameters, used for testing.
     * @param productId Integer
     * @param productName String
     * @param productPrice Double
     * @param imageURL  String
     */
    public Wine(int productId, String productName, double productPrice, String imageURL) {
        if( productId < 0 || productName == null || productName.isEmpty() ||
                 productPrice < 0 || imageURL == null || imageURL.isEmpty()){
            throw new IllegalArgumentException();
        }
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageURL = imageURL;
    }
    /**
     * getter for ProductId
     * @return Integer
     */
    public final int getProductId() {
        return productId;
    }
    /**
     * Setter for productId must be more then 0
     * @param productId 
     */
    public final void setProductId(int productId) {
        if(productId < 0){
            throw new IllegalArgumentException();
        }
        this.productId = productId;
    }
    
    /**
     * Getter for ProductName
     * @return String
     */
    public final String getProductName() {
        return productName;
    }
    /**
     * setter for productName Name may not be empty or null
     * @param productName 
     */
    public final void setProductName(String productName) {
        if(productName.isEmpty() || productName == null){
            throw new IllegalArgumentException();
        }
        this.productName = productName;
    }
    /**
     * getter for productPrice
     * @return double
     */
    public final double getProductPrice() {
        return productPrice;
    }
    /**
     * setter for productPrice must be more then 0
     * @param productPrice 
     */
    public final void setProductPrice(double productPrice) {
        if(productPrice < 0){
             throw new IllegalArgumentException();
        }
        this.productPrice = productPrice;
    }
    /**
     * Getter for imageURL
     * @return String
     */
    public final String getImageURL() {
        return imageURL;
    }
    /**
     * Setter for imageURL must not be null or empty
     * @param imageURL 
     */
    public final void setImageURL(String imageURL) {
        if(imageURL.isEmpty() || imageURL == null){
            throw new IllegalArgumentException();
        }
        this.imageURL = imageURL;
    }
    /**
     * hashcode
     * @return 
     */
    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.productId;
        hash = 41 * hash + Objects.hashCode(this.productName);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.productPrice) ^ (Double.doubleToLongBits(this.productPrice) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.imageURL);
        return hash;
    }
    /**
     * overriden equals.
     * @param obj
     * @return 
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Wine other = (Wine) obj;
        if (this.productId != other.productId) {
            return false;
        }
        if (Double.doubleToLongBits(this.productPrice) != Double.doubleToLongBits(other.productPrice)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.imageURL, other.imageURL)) {
            return false;
        }
        return true;
    }
    /**
     * overriden toString
     * @return returns string
     */
    @Override
    public final  String toString() {
        return "Wine{" + "productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice + ", imageURL=" + imageURL + '}';
    }
    
    
    
    
}
