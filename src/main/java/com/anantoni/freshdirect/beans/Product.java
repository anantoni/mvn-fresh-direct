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
public class Product implements java.io.Serializable {
    private int productID;
    private String name;
    private String description;
    private char productGroup;
    private int listPrice;
    private int availableQuantity;
    private int procurementLevel;
    private int procurementQuantity;
    private int orderSum;
    private int minOrderSum;
    private int maxOrderSum;
    private boolean procurementLevelReached;

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the productGroup
     */
    public char getProductGroup() {
        return productGroup;
    }

    /**
     * @param productGroup the productGroup to set
     */
    public void setProductGroup(char productGroup) {
        this.productGroup = productGroup;
    }

    /**
     * @return the listPrice
     */
    public int getListPrice() {
        return listPrice;
    }

    /**
     * @param listPrice the listPrice to set
     */
    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }

    /**
     * @return the availableQuantity
     */
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * @param availableQuantity the availableQuantity to set
     */
    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    /**
     * @return the procurementLevel
     */
    public int getProcurementLevel() {
        return procurementLevel;
    }

    /**
     * @param procurementLevel the procurementLevel to set
     */
    public void setProcurementLevel(int procurementLevel) {
        this.procurementLevel = procurementLevel;
    }

    /**
     * @return the procurementQuantity
     */
    public int getProcurementQuantity() {
        return procurementQuantity;
    }

    /**
     * @param procurementQuantity the procurementQuantity to set
     */
    public void setProcurementQuantity(int procurementQuantity) {
        this.procurementQuantity = procurementQuantity;
    }

    /**
     * @return the orderSum
     */
    public int getOrderSum() {
        return orderSum;
    }

    /**
     * @param orderSum the orderSum to set
     */
    public void setOrderSum(int orderSum) {
        this.orderSum = orderSum;
    }

    /**
     * @return the procurementLevelReached
     */
    public boolean getProcurementLevelReached() {
        return procurementLevelReached;
    }

    /**
     * @param procurementLevelReached the procurementLevelReached to set
     */
    public void setProcurementLevelReached(boolean procurementLevelReached) {
        this.procurementLevelReached = procurementLevelReached;
    }
    
    /**
     * @param procurementLevelReached the procurementLevelReached to set
     */
    public void setProcurementLevelReached(int procurementLevelReached) {
        this.procurementLevelReached = procurementLevelReached != 0;
    }

    /**
     * @return the minOrderSum
     */
    public int getMinOrderSum() {
        return minOrderSum;
    }

    /**
     * @param minOrderSum the minOrderSum to set
     */
    public void setMinOrderSum(int minOrderSum) {
        this.minOrderSum = minOrderSum;
    }

    /**
     * @return the maxOrderSum
     */
    public int getMaxOrderSum() {
        return maxOrderSum;
    }

    /**
     * @param maxOrderSum the maxOrderSum to set
     */
    public void setMaxOrderSum(int maxOrderSum) {
        this.maxOrderSum = maxOrderSum;
    }
    
    
    
    
}
