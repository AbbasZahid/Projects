/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.macfayden.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abbas Zahid
 */
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
@SessionScoped
public class Product implements Serializable{
    
    @XmlElement(required=true)
    private String productId;
    @XmlElement(required=true)
    private String name;
    @XmlElement(required=true)
    private String image;
    @XmlElement(required=true)
    private BigDecimal price;

    public Product(String productId, String name, String image, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public Product() {
        List<Product> products;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }  
}
