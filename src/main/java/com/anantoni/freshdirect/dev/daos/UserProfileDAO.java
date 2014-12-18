/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.dev.daos;

import com.anantoni.freshdirect.beans.UserProfile;
import java.util.List;
import javax.sql.DataSource;
/**
 *
 * @author anantoni
 */
public interface UserProfileDAO {
    
    /** 
    * This is the method to be used to initialize
    * database resources ie. connection.
     * @param ds
    */
   public void setDataSource(DataSource ds);
   /** 
    * This is the method to be used to create
    * a record in the Student table.
     * @param username
     * @param firstname
     * @param lastname
     * @param email
     * @param town
     * @param street
     * @param postCode
     * @param creditLimit
     * @param streetNumber
     * @param currentBalance
    */
   public void create(String username, String firstname, String lastname, String email, String town, String street, 
            int streetNumber, int postCode, int creditLimit, int currentBalance); 
   /** 
    * This is the method to be used to list down
    * a record from the Student table corresponding
    * to a passed student id.
     * @param id
     * @return 
    */
   public UserProfile getUserProfile(Integer id);
   /** 
    * This is the method to be used to list down
    * all the records from the Student table.
     * @return 
    */
   public List<UserProfile> listUserProfiles();
   /** 
    * This is the method to be used to delete
    * a record from the Student table corresponding
    * to a passed student id.
     * @param id
    */
   public void delete(Integer id);
   /** 
    * This is the method to be used to update
    * a record into the Student table.
     * @param id
     * @param age
    */
   public void update(Integer id, Integer age);
    
}
