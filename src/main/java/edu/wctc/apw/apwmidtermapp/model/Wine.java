/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.model;

import java.util.Objects;

/**
 *
 * @author andre_000
 */
public class Wine {
    private int productId;
    private String productName;
    private double productPrice;
    private String imageURL;

    public Wine() {
    }

    public Wine(int productId, String productName, double productPrice, String imageURL) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.imageURL = imageURL;
    }

    public final int getProductId() {
        return productId;
    }

    public final void setProductId(int productId) {
        this.productId = productId;
    }

    public final String getProductName() {
        return productName;
    }

    public final void setProductName(String productName) {
        this.productName = productName;
    }

    public final double getProductPrice() {
        return productPrice;
    }

    public final void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public final String getImageURL() {
        return imageURL;
    }

    public final void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.productId;
        hash = 41 * hash + Objects.hashCode(this.productName);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.productPrice) ^ (Double.doubleToLongBits(this.productPrice) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.imageURL);
        return hash;
    }

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

    @Override
    public final  String toString() {
        return "Wine{" + "productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice + ", imageURL=" + imageURL + '}';
    }
    
    
    
    
}
