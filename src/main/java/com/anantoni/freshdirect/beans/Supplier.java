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
    private int ID;
    private String Name;
    private String Email;
    private int totalAmountSupplied;

    

    /**
     * @return the totalAmountSupplied
     */
    public int getTotalAmountSupplied() {
        return totalAmountSupplied;
    }

    /**
     * @param totalAmountSupplied the totalAmountSupplied to set
     */
    public void setTotalAmountSupplied(int totalAmountSupplied) {
        this.totalAmountSupplied = totalAmountSupplied;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    
}
