/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.beans;

import java.util.List;
/**
 *
 * @author Lelouch
 */
public class UserProfile implements java.io.Serializable {
    private int userID;
    private String role;
    private String username;
    private String firstname;
    private String lastname; 
    private String email;
    private String street;
    private int streetNumber;
    private String town;
    private int postCode;
    private int creditLimit;
    private int currentBalance;
    private List<Long> cardList;
    private List<Supplier> supplierList;
    
        
    public UserProfile() {

    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the streetNumber
     */
    public int getStreetNumber() {
        return streetNumber;
    }

    /**
     * @param streetNumber the streetNumber to set
     */
    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    /**
     * @return the town
     */
    public String getTown() {
        return town;
    }

    /**
     * @param town the town to set
     */
    public void setTown(String town) {
        this.town = town;
    }

    /**
     * @return the postCode
     */
    public int getPostCode() {
        return postCode;
    }

    /**
     * @param postCode the postCode to set
     */
    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    /**
     * @return the creditLimit
     */
    public int getCreditLimit() {
        return creditLimit;
    }

    /**
     * @param creditLimit the creditLimit to set
     */
    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * @return the currentBalance
     */
    public int getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param currentBalance the currentBalance to set
     */
    public void setCurrentBalance(int currentBalance) {
        this.currentBalance = currentBalance;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the cardList
     */
    public List<Long> getCardList() {
        return cardList;
    }

    /**
     * @param cardList the cardList to set
     */
    public void setCardList(List<Long> cardList) {
        this.cardList = cardList;
    }

    /**
     * @return the supplierList
     */
    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    /**
     * @param supplierList the supplierList to set
     */
    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }
}