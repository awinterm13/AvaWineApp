/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.apw.apwmidtermapp.entity;

import edu.wctc.apw.apwmidtermapp.exception.ParameterMissingException;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A Generated Entity Class that represents Wine Objects
 * @author andre_000
 */
@Entity
@Table(name = "wine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wine.findAll", query = "SELECT w FROM Wine w"),
    @NamedQuery(name = "Wine.findByWineId", query = "SELECT w FROM Wine w WHERE w.wineId = :wineId"),
    @NamedQuery(name = "Wine.findByWineName", query = "SELECT w FROM Wine w WHERE w.wineName = :wineName"),
    @NamedQuery(name = "Wine.findByWinePrice", query = "SELECT w FROM Wine w WHERE w.winePrice = :winePrice"),
    @NamedQuery(name = "Wine.findByImageUrl", query = "SELECT w FROM Wine w WHERE w.imageUrl = :imageUrl")})
public class Wine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "wine_id")
    private Integer wineId;
    @Size(max = 255)
    @Column(name = "wine_name")
    private String wineName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "wine_price")
    private Double winePrice;
    @Size(max = 255)
    @Column(name = "image_url")
    private String imageUrl;
/**
 * Default Constructor
 */
    public Wine() {
    }
/**
 * Constructor that takes an Integer as a parameter for the WineId.
 * @param wineId 
 */
    public Wine(Integer wineId) {
        if(wineId == null){
             throw new ParameterMissingException();
        }
        this.wineId = wineId;
    }
/**
 * Getter for wineId
 * @return Integer
 */
    public Integer getWineId() {
        return wineId;
    }
/**
 * Setter for wineId takes a Integer as a id parameter
 * @param wineId 
 */
    public void setWineId(Integer wineId) {
        if(wineId == null){
             throw new ParameterMissingException();
        }
        this.wineId = wineId;
    }
/**
 * Getter for wineName
 * @return String
 */
    public String getWineName() {
        return wineName;
    }
/**
 * Setter for Wine name, takes a String and sets it as the wineName
 * @param wineName 
 */
    public void setWineName(String wineName) {
        if(wineName.isEmpty()){
           
             throw new ParameterMissingException();
        
        }
        this.wineName = wineName;
    }
/**
 * Getter for winePrice
 * @return Double
 */
    public Double getWinePrice() {
        return winePrice;
    }
/**
 * Setter for winePrice takes a double as param and sets it as winePrice
 * @param winePrice 
 */
    public void setWinePrice(Double winePrice) {
        if(winePrice == null){
            
             throw new ParameterMissingException();
 
        }
        this.winePrice = winePrice;
    }
/**
 * Getter for ImageUrl
 * @return String
 */
    public String getImageUrl() {
        return imageUrl;
    }
/**
 * Setter for ImageUrl takes a String and sets it as imageUrl
 * @param imageUrl 
 */
    public void setImageUrl(String imageUrl) {
        if(imageUrl.isEmpty()){
             throw new ParameterMissingException();
        }
        this.imageUrl = imageUrl;
    }
/**
 * Override of hashcode for equals method
 * @return 
 */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wineId != null ? wineId.hashCode() : 0);
        return hash;
    }
/**
 * Override of equals for comparison.
 * @param object
 * @return 
 */
    @Override
    public boolean equals(Object object) {
        if(object == null){
             throw new ParameterMissingException();
        }
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wine)) {
            return false;
        }
        Wine other = (Wine) object;
        if ((this.wineId == null && other.wineId != null) || (this.wineId != null && !this.wineId.equals(other.wineId))) {
            return false;
        }
        return true;
    }
/**
 * override of toString to provide useful data.
 * @return 
 */
    @Override
    public String toString() {
        return "edu.wctc.apw.apwmidtermapp.model.Wine[ wineId=" + wineId + " ]";
    }
    
}
