/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.beans;

/**
 *
 * @author anantoni
 */
public class OrderedProduct implements java.io.Serializable {
    private int productID;
    private String name;
    private int quantity;
    private int price;

    /**
     * @return the productID
     */
    public int getProductID() {
        return productID;
    }

    /**
     * @param productID the productID to set
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public boolean equals(Object other){
    boolean result;
    if((other == null) || (getClass() != other.getClass())){
        result = false;
    } // end if
    else{
        OrderedProduct otherProduct = (OrderedProduct)other;
        result = (hashCode() == other.hashCode());
    } // end else

    return result;
} // end equals

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.productID;
        return hash;
    }
}
