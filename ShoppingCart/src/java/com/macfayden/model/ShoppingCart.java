/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.macfayden.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@XmlRootElement(name="shoppingCart")
@XmlAccessorType(XmlAccessType.FIELD)
@SessionScoped
public class ShoppingCart implements Serializable{
    
    @XmlElement
    private List<CommerceItem> items ;
    
    @XmlElement
    private BigDecimal amount;

    public ShoppingCart() {
        
    }
    
    public List<CommerceItem> getItems() {
        if(items == null){
            this.items = new ArrayList<CommerceItem>();
        }
        return items;
    }

    public void setItems(List<CommerceItem> items) {
        this.items = items;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
