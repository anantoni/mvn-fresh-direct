/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.database_api;

import com.anantoni.freshdirect.beans.UserProfile;
import com.anantoni.freshdirect.beans.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author anantoni
 */
public class DatabaseManager {

    Connection SQLcon;

    public DatabaseManager() throws ClassNotFoundException, SQLException {
        //Connect to sql server
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/mysql?" + "user=" + "root" + "&password=" + "BlackCat13";
        Connection con = DriverManager.getConnection(connectionUrl);
        SQLcon = con;
        SQLcon.createStatement().execute("USE fd_schema;");
    }

    public int checkUserExistence(int user_id) throws SQLException {
        //Check for user existence based on user id
        PreparedStatement s = SQLcon.prepareStatement("SELECT user_id FROM USERS WHERE user_id = ?");
        s.setInt(1, user_id);
        s.executeQuery();
        ResultSet rs = s.getResultSet();

        if (rs.next()) {
            s.close();
            return 1;
        } else {
            s.close();
            return 0;
        }
    }

    public boolean checkUsernameAvailability(String username) throws SQLException {
        //Check username availability
        PreparedStatement s = SQLcon.prepareStatement("SELECT login_name FROM USERS WHERE login_name = ?");
        s.setString(1, username);
        s.executeQuery();
        ResultSet rs = s.getResultSet();

        if (rs.next()) {
            s.close();
            return false;
        } else {
            s.close();
            return true;
        }
    }

    public boolean checkEmailAvailability(String email) throws SQLException {
        //Check email availability
        PreparedStatement s = SQLcon.prepareStatement("SELECT email FROM USERS WHERE email = ?");
        s.setString(1, email);
        s.executeQuery();
        ResultSet rs = s.getResultSet();

        if (rs.next()) {
            s.close();
            return false;
        } else {
            s.close();
            return true;
        }
    }

    public int createAccount(String username, String password, String email, String firstname, 
            String lastname, String town, String street, String streetNumber, String postCode) 
            throws SQLException 
    {
        //Check if town exists in TOWN
        PreparedStatement s = SQLcon.prepareStatement("SELECT town_id FROM TOWNS WHERE town_name = ?");
        
        s.setString(1, town);
        s.executeQuery();
        ResultSet rs = s.getResultSet();
        
        int townID = -1;
        if (rs.next()) {
            townID = rs.getInt("town_id");
            s.close();
        } else {
            s.close();
        }
        
        //If not exists insert to TOWN table
        if (townID == -1) {
            s = SQLcon.prepareStatement("INSERT INTO TOWNS("
                    + "town_name)"
                    + "VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            s.setString(1, town);
            s.executeUpdate();
            rs = s.getGeneratedKeys();

            if (rs.next()) {
                townID = rs.getInt(1);
                s.close();
            } else {
                s.close();
            }
        }
        
        //Check if street exists in STREET
        s = SQLcon.prepareStatement("SELECT street_id FROM STREETS WHERE street_name = ?");
        s.setString(1, street);
        s.executeQuery();
        rs = s.getResultSet();
        
        int streetID = -1;
        if (rs.next()) {
            streetID = rs.getInt("street_id");
            s.close();
        } else {
            s.close();
        }
        
        //If not exists insert to STREET table
        if (streetID == -1) {
            s = SQLcon.prepareStatement("INSERT INTO STREETS("
                    + "street_name)"
                    + "VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            s.setString(1, street);
            s.executeUpdate();
            rs = s.getGeneratedKeys();

            if (rs.next()) {
                streetID = rs.getInt(1);
                s.close();
            } else {
                s.close();
            }
        }
        
        //Check if address combination exists in ADDRESS table
        s = SQLcon.prepareStatement("SELECT address_id FROM ADDRESSES WHERE town_id = ? AND street_id = ?" +
                " AND street_number = ? AND post_code = ?");
        
        s.setInt(1, townID);
        s.setInt(2, streetID);
        s.setInt(3, Integer.parseInt(streetNumber));
        s.setInt(4, Integer.parseInt(postCode));        
        s.executeQuery();
        rs = s.getResultSet();
        
        int addressID = -1;
        if (rs.next()) {
            addressID = rs.getInt("address_id");
            s.close();
        } else {
            s.close();
        }
        
        //If not exists insert to ADDRESS table
        if (addressID == -1) {
            s = SQLcon.prepareStatement("INSERT INTO ADDRESSES("
                    + "town_id, street_id, street_number, post_code)"
                    + "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            s.setInt(1, townID);
            s.setInt(2, streetID);
            s.setInt(3, Integer.parseInt(streetNumber));
            s.setInt(4, Integer.parseInt(postCode));
            s.executeUpdate();
            rs = s.getGeneratedKeys();

            if (rs.next()) {
                addressID = rs.getInt(1);
                s.close();
            } else {
                s.close();
            }
        }
        
        //Insert user to USER table
        s = SQLcon.prepareStatement("INSERT INTO USERS("
                + "login_name, password, email, first_name, last_name, address_id, credit_limit, current_balance)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        
        s.setString(1, username);
        s.setString(2, password);
        s.setString(3, email);
        s.setString(4, firstname);
        s.setString(5, lastname);
        s.setInt(6, addressID);
        s.setInt(7, 2000);
        s.setInt(8, 0);
        s.executeUpdate();
        rs = s.getGeneratedKeys();
        
        if (rs.next()) {
            int userID = rs.getInt(1);
            s.close();
            return userID;
        } else {
            s.close();
            return -1;
        }
    }
    
    public UserProfile getProfileData(int user_id) throws SQLException {
        PreparedStatement s = SQLcon.prepareStatement("SELECT * FROM FULL_USER_PROFILE WHERE user_id = ?");
        s.setInt(1, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        UserProfile userProfile = null;

        if (rs.next()) {
            userProfile = new UserProfile();
            userProfile.setUserID(rs.getInt("user_id"));
            userProfile.setUsername(rs.getString("login_name"));
            userProfile.setFirstname(rs.getString("first_name"));
            userProfile.setLastname(rs.getString("last_name"));
            userProfile.setEmail(rs.getString("email"));
            userProfile.setCreditLimit(rs.getInt("credit_limit"));
            userProfile.setCurrentBalance(rs.getInt("current_balance"));
            userProfile.setTown(rs.getString("town_name"));
            userProfile.setStreet(rs.getString("street_name"));
            userProfile.setStreetNumber(rs.getInt("street_number"));
            userProfile.setPostCode(rs.getInt("post_code"));
        }

        s.close();
        return userProfile;
	
    }
    
    public UserProfile login(String username, String password) throws SQLException {
        UserProfile userProfile = null;

        PreparedStatement s = SQLcon.prepareStatement("SELECT * FROM FULL_USER_PROFILE WHERE login_name = ? AND password = ?");
        s.setString(1, username);
        s.setString(2, password);
        ResultSet rs = s.executeQuery();

        if (rs.next()) {
            userProfile = new UserProfile();
            userProfile.setUserID(rs.getInt("user_id"));
            userProfile.setUsername(rs.getString("login_name"));
            userProfile.setFirstname(rs.getString("first_name"));
            userProfile.setLastname(rs.getString("last_name"));
            userProfile.setEmail(rs.getString("email"));
            userProfile.setCreditLimit(rs.getInt("credit_limit"));
            userProfile.setCurrentBalance(rs.getInt("current_balance"));
            userProfile.setTown(rs.getString("town_name"));
            userProfile.setStreet(rs.getString("street_name"));
            userProfile.setStreetNumber(rs.getInt("street_number"));
            userProfile.setPostCode(rs.getInt("post_code"));
            rs.close();
            s.close();
            s = SQLcon.prepareStatement("SELECT * FROM MANAGERS WHERE user_id = ?");
            s.setInt(1, userProfile.getUserID());
            rs = s.executeQuery();
            if (rs.next())
                userProfile.setRole("manager");
            else
                userProfile.setRole("customer");
        }
        
        return userProfile;

    }
    
    public List<Product> getProductsByCategory(char category) throws SQLException {
        
        PreparedStatement s = SQLcon.prepareStatement("SELECT "
        + "product_id, p.name, description, product_group, list_price, available_quantity, procurement_level, procurement_quantity "
        + "FROM PRODUCTS AS p, PRODUCT_GROUPS AS pg "
        + "WHERE p.product_group = pg.product_group_id AND pg.name = ?");
                
        s.setString(1, String.valueOf(category));
        ResultSet rs = s.executeQuery();
        
        List<Product> productsList = new ArrayList<>();
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("product_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setProductGroup(rs.getString("product_group").charAt(0));
            product.setListPrice(rs.getInt("list_price"));
            product.setAvailableQuantity(rs.getInt("available_quantity"));
            product.setProcurementLevel(rs.getInt("procurement_level"));
            product.setProcurementQuantity(rs.getInt("procurement_quantity"));
            productsList.add(product);
        }
        return productsList;
    }
    
    public boolean checkout(UserProfile userProfile, String[] pIDs, String[] pQuantities, String[] pListPrices, int totalCost) throws SQLException {
        int userID = userProfile.getUserID();
        int orderID = -1;
        SQLcon.setAutoCommit(false);
        PreparedStatement s = SQLcon.prepareStatement("SELECT credit_limit, current_balance FROM USERS WHERE user_id = ?");
        s.setInt(1, userID);
        ResultSet rs = s.executeQuery();
              
        if (rs.next()) {
            int creditLimit = rs.getInt("credit_limit");
            int currentBalance = rs.getInt("current_balance");
            rs.close();
            s.close();
            
            if (currentBalance + totalCost <= creditLimit) {
                s = SQLcon.prepareStatement("UPDATE USERS SET current_balance = ? WHERE user_id = ?");
                s.setInt(1, currentBalance + totalCost);
                s.setInt(2, userID);
                s.executeUpdate();
                
                s = SQLcon.prepareStatement("INSERT INTO ORDERS(order_date, customer_id, total_cost) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                s.setDate(1, new java.sql.Date(System.currentTimeMillis()));
                s.setInt(2, userID);
                s.setInt(3, totalCost);
                s.executeUpdate();
                rs = s.getGeneratedKeys();

                if (rs.next()) {
                    orderID = rs.getInt(1);
                    rs.close();
                    s.close();
                } else {
                    rs.close();
                    s.close();
                    return false;
                }
            }
            else {
                rs.close();
                s.close();
                return false;
            }
        }
        
        for (int i = 0; i < pIDs.length; i++) {
            int productID = Integer.parseInt(pIDs[i]);
            int productQuantity = Integer.parseInt(pQuantities[i]);
            int productPrice = Integer.parseInt(pListPrices[i]);
            s = SQLcon.prepareStatement("SELECT available_quantity, procurement_level FROM PRODUCTS WHERE product_id = ?");
            s.setInt(1, productID);
            rs = s.executeQuery();
            
            if (rs.next()) {
                int availableQuantity = rs.getInt("available_quantity");
                int procurementLevel = rs.getInt("procurement_level");
                if (productQuantity <= availableQuantity) {
                    if (availableQuantity - productQuantity <= procurementLevel)
                        s = SQLcon.prepareStatement("UPDATE PRODUCTS SET available_quantity = ?, procurement_level_reached = 1 WHERE product_id = ?");
                    else
                        s = SQLcon.prepareStatement("UPDATE PRODUCTS SET available_quantity = ?, procurement_level_reached = 0 WHERE product_id = ?");
                    s.setInt(1, availableQuantity - productQuantity);
                    s.setInt(2, productID);
                    s.executeUpdate();
                    
                    s = SQLcon.prepareStatement("INSERT INTO ORDER_DETAILS(order_id, product_id, order_quantity, order_sum) VALUES(?, ?, ?, ?)");
                    s.setInt(1, orderID);
                    s.setInt(2, productID);
                    s.setInt(3, productQuantity);
                    s.setInt(4, productQuantity * productPrice);
                    s.executeUpdate();
                }
                else {
                    rs.close();
                    s.close();
                    return false;
                }                
            }
            else {
                rs.close();
                s.close();
                return false;
            }
        }
        SQLcon.commit();
        return true;
    }
    
    public List<Product> bestSellingProduct() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.bestSellingProducts()}");
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("product_id"));
            product.setName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setListPrice(rs.getInt("list_price"));
            product.setAvailableQuantity(rs.getInt("available_quantity"));
            product.setProductGroup(rs.getString("product_group").charAt(0));
            product.setProcurementLevel(rs.getInt("procurement_level"));
            product.setProcurementQuantity(rs.getInt("procurement_quantity"));
            product.setProcurementLevelReached(rs.getInt("procurement_level_reached"));
            product.setOrderSum(rs.getInt("order_sum"));
            productList.add(product);
        }
        return productList;
    }
    
    public List<Product> neverOrderedProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.neverOrderedProducts()}");
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("product_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setListPrice(rs.getInt("list_price"));
            product.setAvailableQuantity(rs.getInt("available_quantity"));
            product.setProcurementLevel(rs.getInt("procurement_level"));
            product.setProcurementQuantity(rs.getInt("procurement_quantity"));
            product.setProcurementLevelReached(rs.getInt("procurement_level_reached"));
            productList.add(product);
        }
        return productList;
    }
    
    public List<Product> mostExpensiveProductsPerGroup() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.mostExpensiveProductPerGroup()}");
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("product_id"));
            product.setName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setListPrice(rs.getInt("list_price"));
            product.setAvailableQuantity(rs.getInt("available_quantity"));
            product.setProcurementLevel(rs.getInt("procurement_level"));
            product.setProcurementQuantity(rs.getInt("procurement_quantity"));
            product.setProcurementLevelReached(rs.getInt("procurement_level_reached"));
            productList.add(product);
        }
        return productList;
    }

    public List<Product> orderSumMinMaxPerProduct() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.orderSumMinMaxPerProduct()}");
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("product_id"));
            product.setName(rs.getString("product_name"));
            product.setDescription(rs.getString("description"));
            product.setListPrice(rs.getInt("list_price"));
            product.setAvailableQuantity(rs.getInt("available_quantity"));
            product.setProcurementLevel(rs.getInt("procurement_level"));
            product.setProcurementQuantity(rs.getInt("procurement_quantity"));
            product.setProcurementLevelReached(rs.getInt("procurement_level_reached"));
            product.setMinOrderSum(rs.getInt("min_order_sum"));
            product.setMaxOrderSum(rs.getInt("max_order_sum"));
            productList.add(product);
        }
        return productList;
    }
    
    public List<Integer> daysGreaterThan10k(String month, String year) throws SQLException {
        List<Integer> dayList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.daysGreaterThan10k(?, ?)}");
        cs.setInt(1, Integer.parseInt(month));
        cs.setInt(2, Integer.parseInt(year));
        ResultSet rs = cs.executeQuery();

        while (rs.next()) 
            dayList.add(rs.getInt("found_day"));

        return dayList;
    }
    
    public List<Product> productsNotOrderedInMonthOfYear(String month, String year) throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.productsNotOrderedInMonthOfYear(?, ?)}");
        cs.setInt(1, Integer.parseInt(month));
        cs.setInt(2, Integer.parseInt(year));
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("product_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setListPrice(rs.getInt("list_price"));
            product.setAvailableQuantity(rs.getInt("available_quantity"));
            product.setProcurementLevel(rs.getInt("procurement_level"));
            product.setProcurementQuantity(rs.getInt("procurement_quantity"));
            product.setProcurementLevelReached(rs.getInt("procurement_level_reached"));
            productList.add(product);
        }
        return productList;
    }
    
    public List<Product> sixDegreesOfSeparation(String supplier1, String supplier2) throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.productsNotOrderedInMonthOfYear(?, ?)}");
        cs.setInt(1, Integer.parseInt(supplier1));
        cs.setInt(2, Integer.parseInt(supplier2));
        ResultSet rs = cs.executeQuery();

        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("product_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setListPrice(rs.getInt("list_price"));
            product.setAvailableQuantity(rs.getInt("available_quantity"));
            product.setProcurementLevel(rs.getInt("procurement_level"));
            product.setProcurementQuantity(rs.getInt("procurement_quantity"));
            product.setProcurementLevelReached(rs.getInt("procurement_level_reached"));
            productList.add(product);
        }
        return productList;
    }
    
    public List<Product> searchProduct(String product_name, String product_description, String product_group_name, String supplier_name) throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.searchProduct(?, ?, ?, ?)}");
        if (product_name.equals(""))
            cs.setObject(1, null);
        else
            cs.setString(1, product_name);
        
        if (product_description.equals(""))
            cs.setObject(2, null);
        else
            cs.setString(2, product_description);
        
        if (product_group_name.equals("") || product_group_name.equals("A"))
            cs.setObject(3, null);
        else
            cs.setString(3, product_group_name);
        
        if (supplier_name.equals(""))
            cs.setString(4, null);
        else
            cs.setString(4, supplier_name);
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) {
            Product product = new Product();
            product.setProductID(rs.getInt("product_id"));
            product.setName(rs.getString("name"));
            product.setDescription(rs.getString("description"));
            product.setListPrice(rs.getInt("list_price"));
            product.setAvailableQuantity(rs.getInt("available_quantity"));
            product.setProcurementLevel(rs.getInt("procurement_level"));
            product.setProcurementQuantity(rs.getInt("procurement_quantity"));
            product.setProcurementLevelReached(rs.getInt("procurement_level_reached"));
            productList.add(product);
        }
        
        return productList;
    }
}