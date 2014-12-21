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
public class Supplier implements java.io.Serializable {
    private int supplierID;
    private String supplierName;
    private String supplierEmail;

    /**
     * @return the supplierID
     */
    public int getSupplierID() {
        return supplierID;
    }

    /**
     * @param supplierID the supplierID to set
     */
    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    /**
     * @return the supplierName
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * @param supplierName the supplierName to set
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the supplierEmail
     */
    public String getSupplierEmail() {
        return supplierEmail;
    }

    /**
     * @param supplierEmail the supplierEmail to set
     */
    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }
    
    
}
