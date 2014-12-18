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
    private int totalCost;
    
    public Cart() {
        productMap = new HashMap();
        totalCost = 0;
    }

    public void addProduct(OrderedProduct product) {
        if (getProductMap().containsKey(product)) 
            getProductMap().put(product, getProductMap().get(product) + product.getQuantity());
        else 
            getProductMap().put(product, product.getQuantity());
        setTotalCost(totalCost + product.getPrice() * product.getQuantity());
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
    public int getTotalCost() {
        return totalCost;
    }

    /**
     * @param totalCost
     */
    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }
    
    
    
}
