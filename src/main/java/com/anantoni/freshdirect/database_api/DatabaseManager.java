/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.database_api;

import com.anantoni.freshdirect.beans.UserProfile;
import com.anantoni.freshdirect.beans.Product;
import com.anantoni.freshdirect.beans.Supplier;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anantoni
 */
public class DatabaseManager {

    private Connection SQLcon;

    public DatabaseManager() throws ClassNotFoundException, SQLException {
        //Connect to sql server
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/mysql?" + "user=" + "root" + "&password=" + "BlackCat13";
        Connection con = DriverManager.getConnection(connectionUrl);
        SQLcon = con;
        SQLcon.createStatement().execute("USE fd_schema;");
    }
    
    public DatabaseManager(int connection_type) throws ClassNotFoundException, SQLException {
        //Connect to sql server
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/mysql?" + "user=" + "root" + "&password=" + "BlackCat13";
        Connection con = DriverManager.getConnection(connectionUrl);
        SQLcon = con;
        SQLcon.createStatement().execute("USE fd_schema;");
    }

    public int checkUserExistence(int user_id) throws SQLException {
        //Check for user existence based on user id
        PreparedStatement s = getSQLcon().prepareStatement("SELECT user_id FROM USERS WHERE user_id = ?");
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
        PreparedStatement s = getSQLcon().prepareStatement("SELECT login_name FROM USERS WHERE login_name = ?");
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
        PreparedStatement s = getSQLcon().prepareStatement("SELECT email FROM USERS WHERE email = ?");
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
    
    public List<Supplier> getSuppliers() throws SQLException {
        List<Supplier> supplierList = new ArrayList<>();
        
        PreparedStatement s = getSQLcon().prepareStatement("SELECT * FROM SUPPLIERS GROUP BY supplier_id");
        
        ResultSet rs = s.executeQuery();
        
        while (rs.next()) {
            Supplier supplier = new Supplier();
            supplier.setID(rs.getInt("supplier_id"));
            supplier.setName(rs.getString("supplier_name"));
            supplier.setEmail(rs.getString("email"));
            supplierList.add(supplier);
        }
        
        return supplierList;
    }

    public int createAccount(String username, String password, String email, String firstname, 
            String lastname, String town, String street, String streetNumber, String postCode) 
            throws SQLException 
    {
        //Check if town exists in TOWN
        PreparedStatement s = getSQLcon().prepareStatement("SELECT town_id FROM TOWNS WHERE town_name = ?");
        
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
            s = getSQLcon().prepareStatement("INSERT INTO TOWNS("
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
        s = getSQLcon().prepareStatement("SELECT street_id FROM STREETS WHERE street_name = ?");
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
            s = getSQLcon().prepareStatement("INSERT INTO STREETS("
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
        s = getSQLcon().prepareStatement("SELECT address_id FROM ADDRESSES WHERE town_id = ? AND street_id = ?" +
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
            s = getSQLcon().prepareStatement("INSERT INTO ADDRESSES("
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
        s = getSQLcon().prepareStatement("INSERT INTO USERS("
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
        PreparedStatement s = getSQLcon().prepareStatement("SELECT * FROM FULL_USER_PROFILE WHERE user_id = ?");
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

        PreparedStatement s = getSQLcon().prepareStatement("SELECT * FROM FULL_USER_PROFILE WHERE login_name = ? AND password = ?");
        s.setString(1, username);
        s.setString(2, password);
        ResultSet rs = s.executeQuery();

        if (rs.next()) {
            userProfile = new UserProfile();
            int userID = rs.getInt("user_id");
            userProfile.setUserID(userID);
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
            s = getSQLcon().prepareStatement("SELECT * FROM MANAGERS WHERE user_id = ?");
            s.setInt(1, userID);
            rs = s.executeQuery();
            if (rs.next())
                userProfile.setRole("manager");
            else
                userProfile.setRole("customer");
            rs.close();
            s.close();
            
            s = getSQLcon().prepareStatement("SELECT DISTINCT(cc.credit_card_number) AS credit_card_number FROM USERS AS u, ORDERS AS o, ORDERED_WITH_CREDIT_CARD AS owcc, CREDIT_CARDS AS cc" +
            " WHERE u.user_id = ?" +
            " AND o.customer_id = u.user_id" +
            " AND o.order_id = owcc.order_id" +
            " AND owcc.credit_card_id = cc.credit_card_id");
            s.setInt(1, userID);
            rs = s.executeQuery();
            
            List<Long> cardList = new ArrayList<>();
            while (rs.next()) {
                cardList.add(rs.getLong("credit_card_number"));
                userProfile.setCardList(cardList);
            }
        }
        
        
        return userProfile;

    }
    
    public List<Product> getProductsByCategory(char category) throws SQLException {
        
        PreparedStatement s = getSQLcon().prepareStatement("SELECT "
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
    
    public boolean checkout(UserProfile userProfile, String[] pIDs, String[] pQuantities, String[] pListPrices, int totalCost, String creditCardNumber) throws SQLException {
        int userID = userProfile.getUserID();
        int orderID = -1;
        getSQLcon().setAutoCommit(false);
        PreparedStatement s = getSQLcon().prepareStatement("SELECT credit_limit, current_balance FROM USERS WHERE user_id = ?");
        s.setInt(1, userID);
        ResultSet rs = s.executeQuery();
              
        if (rs.next()) {
            int creditLimit = rs.getInt("credit_limit");
            int currentBalance = rs.getInt("current_balance");
            rs.close();
            s.close();
            
            if (currentBalance + totalCost <= creditLimit) {
                s = getSQLcon().prepareStatement("UPDATE USERS SET current_balance = ? WHERE user_id = ?");
                s.setInt(1, currentBalance + totalCost);
                s.setInt(2, userID);
                s.executeUpdate();
                
                s = getSQLcon().prepareStatement("INSERT INTO ORDERS(order_date, customer_id, total_cost) VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
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
            s = getSQLcon().prepareStatement("SELECT available_quantity, procurement_level FROM PRODUCTS WHERE product_id = ?");
            s.setInt(1, productID);
            rs = s.executeQuery();
            
            if (rs.next()) {
                int availableQuantity = rs.getInt("available_quantity");
                int procurementLevel = rs.getInt("procurement_level");
                if (productQuantity <= availableQuantity) {
                    if (availableQuantity - productQuantity <= procurementLevel)
                        s = getSQLcon().prepareStatement("UPDATE PRODUCTS SET available_quantity = ?, procurement_level_reached = 1 WHERE product_id = ?");
                    else
                        s = getSQLcon().prepareStatement("UPDATE PRODUCTS SET available_quantity = ?, procurement_level_reached = 0 WHERE product_id = ?");
                    s.setInt(1, availableQuantity - productQuantity);
                    s.setInt(2, productID);
                    s.executeUpdate();
                    
                    s = getSQLcon().prepareStatement("INSERT INTO ORDER_DETAILS(order_id, product_id, order_quantity, order_sum) VALUES(?, ?, ?, ?)");
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
        //Check if credit card number exists in CREDIT_CARDS table
        s = getSQLcon().prepareStatement("SELECT credit_card_id FROM CREDIT_CARDS WHERE credit_card_number = ?");
        s.setLong(1, Long.parseLong(creditCardNumber));
        rs = s.executeQuery();
        
        int creditCardID = -1;
        if (rs.next()) {
            creditCardID = rs.getInt("credit_card_id");
            s.close();
        } else {
            s.close();
        }
        
        //If not exists insert to CREDITS_CARDS table
        if (creditCardID == -1) {
            s = getSQLcon().prepareStatement("INSERT INTO CREDIT_CARDS("
                    + "credit_card_number)"
                    + "VALUES(?)", Statement.RETURN_GENERATED_KEYS);
            s.setLong(1, Long.parseLong(creditCardNumber));
            s.executeUpdate();
            rs = s.getGeneratedKeys();

            if (rs.next()) {
                creditCardID = rs.getInt(1);
                s.close();
            } else {
                s.close();
            }
        }
        
        s = getSQLcon().prepareStatement("INSERT INTO ORDERED_WITH_CREDIT_CARD(order_id, credit_card_id) VALUES(?, ?)");
        s.setInt(1, orderID);
        s.setInt(2, creditCardID);
        s.executeUpdate();
        
        getSQLcon().commit();
        getSQLcon().setAutoCommit(true);
        return true;
    }
    
    public List<Product> bestSellingProduct() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.bestSellingProducts()}");
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
        rs.close();
        cs.close();
        
        return productList;
    }
    
    public List<Product> neverOrderedProducts() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.neverOrderedProducts()}");
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
        rs.close();
        cs.close();
        
        return productList;
    }
    
    public List<Product> mostExpensiveProductsPerGroup() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.mostExpensiveProductPerGroup()}");
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
        rs.close();
        cs.close();
        
        return productList;
    }

    public List<Product> orderSumMinMaxPerProduct() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.orderSumMinMaxPerProduct()}");
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
        rs.close();
        cs.close();
        
        return productList;
    }
    
    public List<Integer> daysGreaterThan10k(String month, String year) throws SQLException {
        List<Integer> dayList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.daysGreaterThan10k(?, ?)}");
        cs.setInt(1, Integer.parseInt(month));
        cs.setInt(2, Integer.parseInt(year));
        ResultSet rs = cs.executeQuery();

        while (rs.next()) 
            dayList.add(rs.getInt("found_day"));
        
        rs.close();
        cs.close();
        
        return dayList;
    }
    
    public List<Product> productsNotOrderedInMonthOfYear(String month, String year) throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.productsNotOrderedInMonthOfYear(?, ?)}");
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
        rs.close();
        cs.close();
        
        return productList;
    }
    
    public int sixDegreesOfSeparation(int supplier1, int supplier2) throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.sixDegreesOfSeparation(?, ?, ?)}");
        cs.setInt(1, supplier1);
        cs.setInt(2, supplier2);
        cs.registerOutParameter(3, java.sql.Types.INTEGER);
        ResultSet rs = cs.executeQuery();

        int degree = cs.getInt(3);
        rs.close();
        cs.close();
        
        return degree;
    }
    
    public List<Product> searchProducts(String product_name, String product_description, String product_group_name, String supplier_name, String order_by, String ordering) throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.searchProducts(?, ?, ?, ?, ?)}");
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
        
        if (supplier_name != null && supplier_name.equals(""))
            cs.setString(4, null);
        else
            cs.setString(4, supplier_name);
        
        String order_choice = "";
        switch (order_by) {
            case "1":
                order_choice += " ORDER BY p.list_price";
                break;
            case "2":
                order_choice += " ORDER BY p.name";
                break;
            case "3":
                order_choice += " ORDER BY s.supplier_name";
                break;
        }
        
        switch (ordering) {
            case "1":
                order_choice += " DESC";
                break;
            case "2":
                order_choice += " ASC";
                break;
        }
        cs.setString(5, order_choice);
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
        rs.close();
        cs.close();
        
        return productList;
    }
    
    public List<Product> mostPopularProducts(int limit) throws SQLException{
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.mostPopularProducts(?, ?)}");
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        cs.setDate(1, new java.sql.Date(System.currentTimeMillis()));
        cs.setInt(2, limit);
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
        rs.close();
        cs.close();
        
        return productList;
    }
    
    public List<Supplier> mostPopularSuppliers(int limit) throws SQLException {
        List<Supplier> supplierList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.mostPopularSuppliers(?)}");
        cs.setInt(1, limit);
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) {
            Supplier supplier = new Supplier();
            supplier.setID(rs.getInt("supplier_id"));
            supplier.setName(rs.getString("supplier_name"));
            supplier.setEmail(rs.getString("email"));
            supplier.setTotalAmountSupplied(rs.getInt("total_amount_supplied"));
            supplierList.add(supplier);
        }
        return supplierList;
    }
    
    public List<Integer> mostPopularPostCodes(int limit) throws SQLException {
        List<Integer> postCodeList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.mostPopularPostCodes(?)}");
        cs.setInt(1, limit);
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) {
            postCodeList.add(rs.getInt("post_code"));
        }
        return postCodeList;
    }
    
    public List<UserProfile> topClients(int limit) throws SQLException {
        List<UserProfile> clientList = new ArrayList<>();
        
        CallableStatement cs = getSQLcon().prepareCall("{call fd_schema.topClients(?)}");
        cs.setInt(1, limit);
        ResultSet rs = cs.executeQuery();
        
        while (rs.next()) {
            UserProfile client = new UserProfile();
            client.setUserID(rs.getInt("customer_id"));
            client.setTotalAmountSpent(rs.getInt("total_cost"));
            clientList.add(client);
        }
        return clientList;
    }
    
    public List<Product> suggestProducts(int product_id) throws SQLException{
        List<Product> productList = new ArrayList<>();
        
        CallableStatement cs = SQLcon.prepareCall("{call fd_schema.suggestProducts(?)}");
        cs.setInt(1, product_id);
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
        rs.close();
        cs.close();
        
        return productList;
    }
    
    public boolean insertNewProduct(Product product, int supplierID) throws SQLException {
        PreparedStatement s = getSQLcon().prepareStatement("INSERT INTO PRODUCTS(" +
                "name, description, product_group, list_price, available_quantity, procurement_level, procurement_quantity, procurement_level_reached)"+
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        s.setString(1, product.getName());
        s.setString(2, product.getDescription());
        s.setInt(3, product.getProductGroupID());
        s.setInt(4, product.getListPrice());
        s.setInt(5, product.getAvailableQuantity());
        s.setInt(6, product.getProcurementLevel());
        s.setInt(7, product.getProcurementQuantity());
        s.setInt(8, 0);
        
        s.executeUpdate();
        ResultSet rs = s.getGeneratedKeys();
        
        if (rs.next()) {
            int productID = rs.getInt(1);
            rs.close();
            s.close();
            s = getSQLcon().prepareStatement("SELECT * FROM SUPPLIERS WHERE supplier_id = ? LIMIT 1");
            s.setInt(1, supplierID);
            rs = s.executeQuery();
            
            if (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setID(supplierID);
                supplier.setEmail(rs.getString("email"));
                supplier.setName(rs.getString("supplier_name"));
                
                rs.close();
                s.close();

                s = getSQLcon().prepareStatement("INSERT INTO SUPPLIERS(" + 
                        "supplier_id, product_id, supplier_name, email, supplied_quantity) " +
                        "VALUES(?, ?, ?, ?, ?)");
                s.setInt(1, supplierID);
                s.setInt(2, productID);
                s.setString(3, supplier.getName());
                s.setString(4, supplier.getEmail());
                s.setInt(5, product.getAvailableQuantity());
                s.executeUpdate();
            }
            else {
                rs.close();
                s.close();
            }            
        }
        else {
            rs.close();
            s.close();
            return false;
        }
        rs.close();
        s.close();
        return true;
    }
    
    public List<Product> getSalesHistory(int userID) throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        PreparedStatement s = getSQLcon().prepareStatement(
                 "SELECT o.order_date AS order_date, p.name AS product_name, od.order_quantity AS order_quantity" +
                 " FROM ORDERS AS o, ORDER_DETAILS AS od, PRODUCTS AS p" +
                 " WHERE o.order_id = od.order_id AND od.product_id = p.product_id AND o.customer_id = ?");
        
        s.setInt(1, userID);
        ResultSet rs = s.executeQuery();
        
        while (rs.next()) {
            Product product = new Product();
            product.setDate(rs.getTimestamp("order_date"));
            product.setName(rs.getString("product_name"));
            product.setOrderQuantity(rs.getInt("order_quantity"));
            productList.add(product);
        }
        
        return productList;
    }
    
    public List<Product> getProductsOnShortage() throws SQLException {
        List<Product> productList = new ArrayList<>();
        
        PreparedStatement s = getSQLcon().prepareStatement(
            "SELECT p.product_id AS product_id, p.name AS product_name, s.supplier_id, s.supplier_name, p.procurement_quantity" +
            " FROM PRODUCTS AS p, SUPPLIERS AS s" +
            " WHERE p.procurement_level_reached = 1" +
            " AND p.product_id = s.product_id" );
        ResultSet rs = s.executeQuery();
        
        int prevProductID = -1;
        Product product = new Product();
        while (rs.next()) {
            if (prevProductID != rs.getInt("product_id")) {
                product = new Product();
                product.setProductID(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setProcurementQuantity(rs.getInt("procurement_quantity"));
                prevProductID = rs.getInt("product_id");
                productList.add(product);
            }
            Supplier supplier = new Supplier();
            supplier.setID(rs.getInt("supplier_id"));
            supplier.setName(rs.getString("supplier_name"));
            product.addSupplier(supplier);
            
        }
        
        return productList;
    }
    
    public void reorder(String[] pIDs, String[] pQuantities, String[] pSuppliers) throws SQLException {
        for (int i = 0; i < pIDs.length; i++) {
            PreparedStatement s = getSQLcon().prepareStatement("SELECT available_quantity, procurement_level FROM PRODUCTS WHERE product_id = ?");
            s.setInt(1, Integer.parseInt(pIDs[i]));
            ResultSet rs = s.executeQuery();
            
            if (rs.next()) {
                int currentQuantity = rs.getInt("available_quantity");
                int procurementLevel = rs.getInt("procurement_level");
                int orderQuantity = Integer.parseInt(pQuantities[i]);
                
                if (currentQuantity + orderQuantity <= procurementLevel) 
                    s = getSQLcon().prepareStatement("UPDATE PRODUCTS SET available_quantity = ?, procurement_level_reached = 1 WHERE product_id = ?");
                else 
                    s = getSQLcon().prepareStatement("UPDATE PRODUCTS SET available_quantity = ?, procurement_level_reached = 0 WHERE product_id = ?");
                
                s.setInt(1, currentQuantity + orderQuantity);
                s.setInt(2, Integer.parseInt(pIDs[i]));
                s.executeUpdate();
                
                s = getSQLcon().prepareStatement("SELECT supplied_quantity FROM SUPPLIERS WHERE supplier_id = ? AND product_id = ?");
                s.setInt(1, Integer.parseInt(pSuppliers[i]));
                s.setInt(2, Integer.parseInt(pIDs[i]));
                rs = s.executeQuery();

                if (rs.next()) {
                    int suppliedQuantity = rs.getInt("supplied_quantity");
                    s = getSQLcon().prepareStatement("UPDATE SUPPLIERS SET supplied_quantity = ? WHERE supplier_id = ? AND product_id = ?");
                    s.setInt(1, suppliedQuantity + orderQuantity);
                    s.setInt(2, Integer.parseInt(pSuppliers[i]));
                    s.setInt(3, Integer.parseInt(pIDs[i]));
                    s.executeUpdate();
                }
            }
            
        }
    }

    /**
     * @return the SQLcon
     */
    public Connection getSQLcon() {
        return SQLcon;
    }

    /**
     * @param SQLcon the SQLcon to set
     */
    public void setSQLcon(Connection SQLcon) {
        this.SQLcon = SQLcon;
    }
    
    
}