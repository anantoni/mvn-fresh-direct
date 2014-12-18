/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.beans;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author anantoni
 */
public class Cart implements java.io.Serializable {
    private Map<OrderedProduct, Integer> productMap;
    private double totalPrice;
    
    public Cart() {
        productMap = new HashMap();
        totalPrice = 0.00;
    }

    public void addProduct(OrderedProduct product) {
        if (getProductMap().containsKey(product)) 
            getProductMap().put(product, getProductMap().get(product) + product.getQuantity());
        else 
            getProductMap().put(product, product.getQuantity());
        setTotalPrice(getTotalPrice() + product.getPrice() * product.getQuantity());
    }

    /**
     * @return the productMap
     */
    public Map<OrderedProduct, Integer> getProductMap() {
        return productMap;
    }

    /**
     * @param productMap the productMap to set
     */
    public void setProductMap(Map<OrderedProduct, Integer> productMap) {
        this.productMap = productMap;
    }

    /**
     * @return the totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
    
}
