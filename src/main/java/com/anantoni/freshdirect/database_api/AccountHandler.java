package com.anantoni.freshdirect.database_api;

import com.anantoni.freshdirect.beans.UserProfile;
import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Johnny, Lelouch
 */
public class AccountHandler {

    Connection SQLcon;

    public AccountHandler() throws ClassNotFoundException, SQLException {
        //Sundesi ston sql server
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/mysql?" + "user=" + "root" + "&password=" + "BlackCat13";
        Connection con = DriverManager.getConnection(connectionUrl);
        SQLcon = con;
        Statement s = con.createStatement();
        //Dhmiourgia ths bashs kai twn pinakwn ths
        s.executeUpdate("CREATE SCHEMA IF NOT EXISTS Social");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS Social.User("
                + "User_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "Username VARCHAR(40),"
                + "Password VARCHAR(40),"
                + "Email VARCHAR(40),"
                + "Create_profile INT UNSIGNED NOT NULL DEFAULT 0,"
                + "PRIMARY KEY (User_ID))");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS Social.Profile("
                + "User_ID INT UNSIGNED NOT NULL,"
                + "Firstname VARCHAR(40) NOT NULL,"
                + "Lastname VARCHAR(40) NOT NULL,"
                + "Gender VARCHAR(6),"
                + "Gender_visible INT UNSIGNED NOT NULL,"
                + "Birth_date DATE NOT NULL,"
                + "Birthdate_visible INT UNSIGNED NOT NULL,"
                + "Welcome_text VARCHAR(120),"
                + "Interests_visible INT UNSIGNED,"
                + "Wall_visible INT UNSIGNED,"
                + "Profile_picture VARCHAR(200) DEFAULT NULL,"
                + "PRIMARY KEY(User_ID))");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS Social.Friends("
                + "User_ID INT UNSIGNED NOT NULL,"
                + "Friend_ID INT UNSIGNED NOT NULL,"
                + "Pending INT UNSIGNED NOT NULL,"
                + "PRIMARY KEY (User_ID, Friend_ID))");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS Social.Interests("
                + "User_ID INT UNSIGNED NOT NULL,"
                + "Interest VARCHAR(50),"
                + "PRIMARY KEY (User_ID, Interest))");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS Social.Wall("
                + "Message_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "Message_time TIMESTAMP, "
                + "Sender_ID INT UNSIGNED NOT NULL, "
                + "Receiver_ID INT UNSIGNED NOT NULL, "
                + "Message VARCHAR(500), "
                + "PRIMARY KEY(Message_ID))");
        s.executeUpdate("CREATE TABLE IF NOT EXISTS Social.Comments("
                + "Comment_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,"
                + "Message_ID INT UNSIGNED NOT NULL,"
                + "Comment_time TIMESTAMP, "
                + "Sender_ID INT UNSIGNED NOT NULL, "
                + "Receiver_ID INT UNSIGNED NOT NULL, "
                + "Comment VARCHAR(200), "
                + "PRIMARY KEY(Comment_ID))");
        s.close();
    }

//    public friend[] search(String firstname, String lastname, int age1, int age2, String[] interests, int user_id) throws SQLException {
//        //Sunarthsh ulopoihshs tou search 
//        int i, count = 0;
//                        //Se oles tis periptwseis frontizoume na mhn dinei ws apotelesma to xrhsth pou kanei to search
//        //kai lambanoume upopsin to visibility twn dedomenwn
//        if (firstname != null && lastname == null && age1 == -1 && age2 == -1 && interests == null) {
//            //Anazhthsh me basi to firstname
//            PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname FROM Social.Profile WHERE Firstname = ? AND User_ID NOT IN (?)");
//            s.setString(1, firstname);
//            s.setInt(2, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByFirstname(firstname, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname == null && lastname != null && age1 == -1 && age2 == -1 && interests == null) {
//            //Anazhthsh me bash to lastname
//            PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname FROM Social.Profile WHERE Lastname = ? AND User_ID NOT IN (?)");
//            s.setString(1, lastname);
//            s.setInt(2, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByLastname(lastname, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//        } else if (firstname == null && lastname == null && age1 != -1 && age2 != -1 && interests == null) {
//            //Anazhthsh me bash thn hlikia
//            String upper_date, lower_date;
//            upper_date = calculateUpperDate(age1);
//            lower_date = calculateLowerDate(age2);
//            PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID NOT IN (?)"
//                    + "UNION "
//                    + "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Birth_date BETWEEN ? AND ? AND User_ID IN "
//                    + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?) AND Birthdate_visible = 1");
//            s.setString(1, lower_date);
//            s.setString(2, upper_date);
//            s.setInt(3, user_id);
//            s.setString(4, lower_date);
//            s.setString(5, upper_date);
//            s.setInt(6, user_id);
//            s.setInt(7, user_id);
//            s.setInt(8, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByAge(age1, age2, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//        } else if (firstname == null && lastname == null && age1 == -1 && age2 == -1 && interests != null) {
//            //Anazhthsh me bash ta interests
//            String query1 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query1 = query1 + ", ?";
//            }
//            query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
//            String query2 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query2 = query2 + ", ?";
//            }
//            query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?) AND Interests_visible = 1";
//            String query = query1 + " UNION " + query2;
//            PreparedStatement s = SQLcon.prepareStatement(query);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + 1, interests[i]);
//            }
//            s.setInt(interests.length + 1, user_id);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + interests.length + 2, interests[i]);
//            }
//            s.setInt(2 * interests.length + 2, user_id);
//            s.setInt(2 * interests.length + 3, user_id);
//            s.setInt(2 * interests.length + 4, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByInterests(interests, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//        } else if (firstname != null && lastname != null && age1 == -1 && age2 == -1 && interests == null) {
//            //Anazhthsh me bash ta firstname kai lastname
//            PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Lastname = ? AND User_ID NOT IN (?)");
//            s.setString(1, firstname);
//            s.setString(2, lastname);
//            s.setInt(3, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByFirstLastname(firstname, lastname, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname != null && lastname == null && age1 != -1 && age2 != -1 && interests == null) {
//            //Anazhthsh me bash to firstname kai thn hlikia
//            String upper_date, lower_date;
//            upper_date = calculateUpperDate(age1);
//            lower_date = calculateLowerDate(age2);
//            PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID NOT IN (?)"
//                    + "UNION "
//                    + "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Birth_date BETWEEN ? AND ? AND User_ID IN "
//                    + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?) AND Birthdate_visible = 1");
//            s.setString(1, firstname);
//            s.setString(2, lower_date);
//            s.setString(3, upper_date);
//            s.setInt(4, user_id);
//            s.setString(5, firstname);
//            s.setString(6, lower_date);
//            s.setString(7, upper_date);
//            s.setInt(8, user_id);
//            s.setInt(9, user_id);
//            s.setInt(10, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByFirstnameAge(firstname, age1, age2, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname != null && lastname == null && age1 == -1 && age2 == -1 && interests != null) {
//            //Anazhthsh me bash ta firstname kai interests
//            String query1, query2, query;
//            query1 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query1 = query1 + ",?";
//            }
//            query1 = query1 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Firstname = ?) "
//                    + "AND User_ID NOT IN (?) AND Interests_visible = 2";
//            query2 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query2 = query2 + ", ?";
//            }
//            query2 = query2 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Firstname = ?) "
//                    + "AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?) AND Interests_visible = 1";
//            query = query1 + " UNION " + query2;
//            PreparedStatement s = SQLcon.prepareStatement(query);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + 1, interests[i]);
//            }
//            s.setString(interests.length + 1, firstname);
//            s.setInt(interests.length + 2, user_id);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + interests.length + 3, interests[i]);
//            }
//            s.setString(2 * interests.length + 3, firstname);
//            s.setInt(2 * interests.length + 4, user_id);
//            s.setInt(2 * interests.length + 5, user_id);
//            s.setInt(2 * interests.length + 6, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByFirstnameInterests(firstname, interests, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname == null && lastname != null && age1 != -1 && age2 != -1 && interests == null) {
//            //Anazhthsh me bash to lastname kai thn hlikia
//            String upper_date, lower_date;
//            upper_date = calculateUpperDate(age1);
//            lower_date = calculateLowerDate(age2);
//            PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Lastname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID NOT IN (?)"
//                    + "UNION "
//                    + "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Lastname = ? AND Birth_date BETWEEN ? AND ? AND User_ID IN "
//                    + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?) AND Birthdate_visible = 1");
//            s.setString(1, lastname);
//            s.setString(2, lower_date);
//            s.setString(3, upper_date);
//            s.setInt(4, user_id);
//            s.setString(5, lastname);
//            s.setString(6, lower_date);
//            s.setString(7, upper_date);
//            s.setInt(8, user_id);
//            s.setInt(9, user_id);
//            s.setInt(10, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByLastnameAge(lastname, age1, age2, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname == null && lastname != null && age1 == -1 && age2 == -1 && interests != null) {
//            //Anazhthsh me bash ta lastname kai interests
//            String query1, query2, query;
//            query1 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query1 = query1 + ",?";
//            }
//            query1 = query1 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Lastname = ?) "
//                    + "AND User_ID NOT IN (?) AND Interests_visible = 2";
//            query2 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query2 = query2 + ", ?";
//            }
//            query2 = query2 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Lastname = ?) "
//                    + "AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?) AND Interests_visible = 1";
//            query = query1 + " UNION " + query2;
//            PreparedStatement s = SQLcon.prepareStatement(query);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + 1, interests[i]);
//            }
//            s.setString(interests.length + 1, lastname);
//            s.setInt(interests.length + 2, user_id);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + interests.length + 3, interests[i]);
//            }
//            s.setString(2 * interests.length + 3, lastname);
//            s.setInt(2 * interests.length + 4, user_id);
//            s.setInt(2 * interests.length + 5, user_id);
//            s.setInt(2 * interests.length + 6, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByLastnameInterests(lastname, interests, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname == null && lastname == null && age1 != -1 && age2 != -1 && interests != null) {
//            //Anazhthsh me bash thn hlikia kai ta interests
//            String upper_date, lower_date, query;
//            upper_date = calculateUpperDate(age1);
//            lower_date = calculateLowerDate(age2);
//
//            String query1 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query1 = query1 + ", ?";
//            }
//            query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
//            String query2 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Birth_date BETWEEN ? AND ? AND "
//                    + "((Birthdate_visible = 1  AND Interests_visible = 1) "
//                    + "OR (Birthdate_visible = 1  AND Interests_visible = 2) "
//                    + "OR (Birthdate_visible = 2  AND Interests_visible = 1)) "
//                    + "AND User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query2 = query2 + ", ?";
//            }
//            query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?)";
//            query = query1 + " UNION " + query2;
//
//            PreparedStatement s = SQLcon.prepareStatement(query);
//            s.setString(1, lower_date);
//            s.setString(2, upper_date);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + 3, interests[i]);
//            }
//            s.setInt(interests.length + 3, user_id);
//            s.setString(interests.length + 4, lower_date);
//            s.setString(interests.length + 5, upper_date);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + interests.length + 6, interests[i]);
//            }
//            s.setInt(2 * interests.length + 6, user_id);
//            s.setInt(2 * interests.length + 7, user_id);
//            s.setInt(2 * interests.length + 8, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByAgeInterests(age1, age2, interests, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname != null && lastname != null && age1 != -1 && age2 != -1 && interests == null) {
//            //Anazhthsh me bash to firstname, to lastname kai thn hlikia
//            String upper_date, lower_date;
//            upper_date = calculateUpperDate(age1);
//            lower_date = calculateLowerDate(age2);
//            PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Lastname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID NOT IN (?)"
//                    + "UNION "
//                    + "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Lastname = ? AND Birth_date BETWEEN ? AND ? AND User_ID IN "
//                    + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?) AND Birthdate_visible = 1");
//            s.setString(1, firstname);
//            s.setString(2, lastname);
//            s.setString(3, lower_date);
//            s.setString(4, upper_date);
//            s.setInt(5, user_id);
//            s.setString(6, firstname);
//            s.setString(7, lastname);
//            s.setString(8, lower_date);
//            s.setString(9, upper_date);
//            s.setInt(10, user_id);
//            s.setInt(11, user_id);
//            s.setInt(12, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByFirstLastnameAge(firstname, lastname, age1, age2, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname != null && lastname != null && age1 == -1 && age2 == -1 && interests != null) {
//            //Anazhthsh me bash to firstname, to lastname kai ta interests
//            String query1, query2, query;
//            query1 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query1 = query1 + ",?";
//            }
//            query1 = query1 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Firstname =? AND Lastname = ?) "
//                    + "AND User_ID NOT IN (?) AND Interests_visible = 2";
//            query2 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query2 = query2 + ", ?";
//            }
//            query2 = query2 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Firstname = ? AND Lastname = ?) "
//                    + "AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?) AND Interests_visible = 1";
//            query = query1 + " UNION " + query2;
//            PreparedStatement s = SQLcon.prepareStatement(query);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + 1, interests[i]);
//            }
//            s.setString(interests.length + 1, firstname);
//            s.setString(interests.length + 2, lastname);
//            s.setInt(interests.length + 3, user_id);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + interests.length + 4, interests[i]);
//            }
//            s.setString(2 * interests.length + 4, firstname);
//            s.setString(2 * interests.length + 5, lastname);
//            s.setInt(2 * interests.length + 6, user_id);
//            s.setInt(2 * interests.length + 7, user_id);
//            s.setInt(2 * interests.length + 8, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByFirstLastnameInterests(firstname, lastname, interests, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname != null && lastname == null && age1 != -1 && age2 != -1 && interests != null) {
//            //Anazhthsh me bash to firstname, thn hlikia kai ta interests
//            String upper_date, lower_date, query;
//            upper_date = calculateUpperDate(age1);
//            lower_date = calculateLowerDate(age2);
//
//            String query1 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query1 = query1 + ", ?";
//            }
//            query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
//            String query2 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Birth_date BETWEEN ? AND ? AND "
//                    + "((Birthdate_visible = 1  AND Interests_visible = 1) "
//                    + "OR (Birthdate_visible = 1  AND Interests_visible = 2) "
//                    + "OR (Birthdate_visible = 2  AND Interests_visible = 1)) "
//                    + "AND User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query2 = query2 + ", ?";
//            }
//            query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?)";
//            query = query1 + " UNION " + query2;
//
//            PreparedStatement s = SQLcon.prepareStatement(query);
//            s.setString(1, firstname);
//            s.setString(2, lower_date);
//            s.setString(3, upper_date);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + 4, interests[i]);
//            }
//            s.setInt(interests.length + 4, user_id);
//            s.setString(interests.length + 5, firstname);
//            s.setString(interests.length + 6, lower_date);
//            s.setString(interests.length + 7, upper_date);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + interests.length + 8, interests[i]);
//            }
//            s.setInt(2 * interests.length + 8, user_id);
//            s.setInt(2 * interests.length + 9, user_id);
//            s.setInt(2 * interests.length + 10, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByFirstnameAgeInterests(firstname, age1, age2, interests, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname == null && lastname != null && age1 != -1 && age2 != -1 && interests != null) {
//            //Anazhthsh me bash to lastname, thn hlikia kai ta interests
//            String upper_date, lower_date, query;
//            upper_date = calculateUpperDate(age1);
//            lower_date = calculateLowerDate(age2);
//
//            String query1 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Lastname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query1 = query1 + ", ?";
//            }
//            query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
//            String query2 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Lastname = ? AND Birth_date BETWEEN ? AND ? AND "
//                    + "((Birthdate_visible = 1  AND Interests_visible = 1) "
//                    + "OR (Birthdate_visible = 1  AND Interests_visible = 2) "
//                    + "OR (Birthdate_visible = 2  AND Interests_visible = 1)) "
//                    + "AND User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query2 = query2 + ", ?";
//            }
//            query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?)";
//            query = query1 + " UNION " + query2;
//
//            PreparedStatement s = SQLcon.prepareStatement(query);
//            s.setString(1, lastname);
//            s.setString(2, lower_date);
//            s.setString(3, upper_date);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + 4, interests[i]);
//            }
//            s.setInt(interests.length + 4, user_id);
//            s.setString(interests.length + 5, lastname);
//            s.setString(interests.length + 6, lower_date);
//            s.setString(interests.length + 7, upper_date);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + interests.length + 8, interests[i]);
//            }
//            s.setInt(2 * interests.length + 8, user_id);
//            s.setInt(2 * interests.length + 9, user_id);
//            s.setInt(2 * interests.length + 10, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByLastnameAgeInterests(lastname, age1, age2, interests, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else if (firstname != null && lastname != null && age1 != -1 && age2 != -1 && interests != null) {
//            //Anazhthsh me bash to firstname, to lastname, thn hlikia kai ta interests
//            String upper_date, lower_date, query;
//            upper_date = calculateUpperDate(age1);
//            lower_date = calculateLowerDate(age2);
//
//            String query1 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Lastname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query1 = query1 + ", ?";
//            }
//            query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
//            String query2 = "SELECT User_ID, Firstname, Lastname "
//                    + "FROM Social.Profile "
//                    + "WHERE Firstname = ? AND Lastname = ? AND Birth_date BETWEEN ? AND ? AND "
//                    + "((Birthdate_visible = 1  AND Interests_visible = 1) "
//                    + "OR (Birthdate_visible = 1  AND Interests_visible = 2) "
//                    + "OR (Birthdate_visible = 2  AND Interests_visible = 1)) "
//                    + "AND User_ID IN "
//                    + "(SELECT User_ID "
//                    + "FROM Social.Interests "
//                    + "WHERE Interest IN (?";
//            for (i = 0; i < interests.length - 1; i++) {
//                query2 = query2 + ", ?";
//            }
//            query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                    + "UNION "
//                    + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?)";
//            query = query1 + " UNION " + query2;
//
//            PreparedStatement s = SQLcon.prepareStatement(query);
//            s.setString(1, firstname);
//            s.setString(2, lastname);
//            s.setString(3, lower_date);
//            s.setString(4, upper_date);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + 5, interests[i]);
//            }
//            s.setInt(interests.length + 5, user_id);
//            s.setString(interests.length + 6, firstname);
//            s.setString(interests.length + 7, lastname);
//            s.setString(interests.length + 8, lower_date);
//            s.setString(interests.length + 9, upper_date);
//            for (i = 0; i < interests.length; i++) {
//                s.setString(i + interests.length + 10, interests[i]);
//            }
//            s.setInt(2 * interests.length + 10, user_id);
//            s.setInt(2 * interests.length + 11, user_id);
//            s.setInt(2 * interests.length + 12, user_id);
//            s.executeQuery();
//            ResultSet rs = s.getResultSet();
//
//            count = countPeopleByFirstLastnameAgeInterests(firstname, lastname, age1, age2, interests, user_id);
//            friend[] userResult = new friend[count];
//            for (i = 0; i < count; i++) {
//                userResult[i] = new friend();
//            }
//
//            i = 0;
//            while (rs.next()) {
//                userResult[i].setID(rs.getInt("User_ID"));
//                userResult[i].setFirstname(rs.getString("Firstname"));
//                userResult[i].setLastname(rs.getString("Lastname"));
//                i++;
//            }
//            rs.close();
//            s.close();
//
//            if (count < 1) {
//                userResult = null;
//            }
//            return userResult;
//
//        } else {
//            return null;
//        }
//    }

    public int countPeopleByFirstname(String firstname, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to firstname
        int count = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND User_ID NOT IN (?)");
        s.setString(1, firstname);
        s.setInt(2, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByLastname(String lastname, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to lastname
        int count = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM Social.Profile "
                + "WHERE Lastname = ? AND User_ID NOT IN (?)");
        s.setString(1, lastname);
        s.setInt(2, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByAge(int age1, int age2, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash thn hlikia
        int count = -1;

        String upper_date, lower_date;
        upper_date = calculateUpperDate(age1);
        lower_date = calculateLowerDate(age2);
        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM (SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID NOT IN (?)"
                + "UNION "
                + "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Birth_date BETWEEN ? AND ? AND User_ID IN "
                + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?) AND Birthdate_visible = 1) AS ID");
        s.setString(1, lower_date);
        s.setString(2, upper_date);
        s.setInt(3, user_id);
        s.setString(4, lower_date);
        s.setString(5, upper_date);
        s.setInt(6, user_id);
        s.setInt(7, user_id);
        s.setInt(8, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public String calculateUpperDate(int age) {
        //Ypologismos hmeromhnias gia thn xrhsh ws anw oriou
        GregorianCalendar calendar = new GregorianCalendar();
        int year, month, day;

        year = calendar.get(Calendar.YEAR) - age;
        if (calendar.get(Calendar.MONTH) != 12) {
            month = calendar.get(Calendar.MONTH) + 1;
        } else {
            month = 1;
        }
        day = calendar.get(Calendar.DAY_OF_MONTH);
        String upper_date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

        return upper_date;

    }

    public String calculateLowerDate(int age) {
        //Ypologismos hmeromhnias gia thn xrhsh ws katw oriou
        GregorianCalendar calendar = new GregorianCalendar();
        int year, month, day;

        year = calendar.get(Calendar.YEAR) - age - 1;
        if (calendar.get(Calendar.MONTH) != 12) {
            month = calendar.get(Calendar.MONTH) + 1;
        } else {
            month = 1;
        }
        day = calendar.get(Calendar.DAY_OF_MONTH);

        if (day == 31 || (day == 30 && (month == 4 || month == 6 || month == 9 || month == 11)) || (day == 28 && calendar.isLeapYear(year) == false) || (day == 29 && month == 2)) {
            if (month != 12) {
                month++;
            } else {
                year++;
                month = 1;
            }
            day = 1;
        } else {
            if (month == 12) {
                year++;
            }
            day++;
        }
        String lower_date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

        return lower_date;

    }

    public int countPeopleByInterests(String[] interests, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash ta interests
        int count = -1, i;

        String query1 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query1 = query1 + ", ?";
        }
        query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
        String query2 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query2 = query2 + ", ?";
        }
        query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?) AND Interests_visible = 1";
        String query = "SELECT COUNT(*) AS Total FROM (" + query1 + " UNION " + query2 + ") AS ID";
        PreparedStatement s = SQLcon.prepareStatement(query);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 1, interests[i]);
        }
        s.setInt(interests.length + 1, user_id);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + interests.length + 2, interests[i]);
        }
        s.setInt(2 * interests.length + 2, user_id);
        s.setInt(2 * interests.length + 3, user_id);
        s.setInt(2 * interests.length + 4, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();
        System.out.println(" String: " + interests);
        System.out.println(count);
        return count;
    }

    public int countPeopleByFirstLastname(String firstname, String lastname, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to firstname kai to lastname
        int count = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Lastname = ? AND User_ID NOT IN (?)");
        s.setString(1, firstname);
        s.setString(2, lastname);
        s.setInt(3, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByFirstnameAge(String firstname, int age1, int age2, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to firstname kai thn hlikia
        int count = -1;

        String upper_date, lower_date;
        upper_date = calculateUpperDate(age1);
        lower_date = calculateLowerDate(age2);

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM (SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID NOT IN (?)"
                + "UNION "
                + "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Birth_date BETWEEN ? AND ? AND User_ID IN "
                + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?) AND Birthdate_visible = 1) AS ID");
        s.setString(1, firstname);
        s.setString(2, lower_date);
        s.setString(3, upper_date);
        s.setInt(4, user_id);
        s.setString(5, firstname);
        s.setString(6, lower_date);
        s.setString(7, upper_date);
        s.setInt(8, user_id);
        s.setInt(9, user_id);
        s.setInt(10, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByFirstnameInterests(String firstname, String[] interests, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to firstname kai ta interests
        int count = -1, i;
        String query1 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query1 = query1 + ", ?";
        }
        query1 = query1 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Firstname = ?) "
                + "AND User_ID NOT IN (?) AND Interests_visible = 2";
        String query2 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query2 = query2 + ", ?";
        }
        query2 = query2 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Firstname = ?) "
                + "AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?) AND Interests_visible = 1";
        String query = "SELECT COUNT(*) AS Total FROM (" + query1 + " UNION " + query2 + ") AS ID";

        PreparedStatement s = SQLcon.prepareStatement(query);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 1, interests[i]);
        }
        s.setString(interests.length + 1, firstname);
        s.setInt(interests.length + 2, user_id);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + interests.length + 3, interests[i]);
        }
        s.setString(2 * interests.length + 3, firstname);
        s.setInt(2 * interests.length + 4, user_id);
        s.setInt(2 * interests.length + 5, user_id);
        s.setInt(2 * interests.length + 6, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByLastnameAge(String lastname, int age1, int age2, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to lastname kai thn hlikia 
        int count = -1;

        String upper_date, lower_date;
        upper_date = calculateUpperDate(age1);
        lower_date = calculateLowerDate(age2);

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM (SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Lastname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID NOT IN (?)"
                + "UNION "
                + "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Lastname = ? AND Birth_date BETWEEN ? AND ? AND User_ID IN "
                + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?) AND Birthdate_visible = 1) AS ID");
        s.setString(1, lastname);
        s.setString(2, lower_date);
        s.setString(3, upper_date);
        s.setInt(4, user_id);
        s.setString(5, lastname);
        s.setString(6, lower_date);
        s.setString(7, upper_date);
        s.setInt(8, user_id);
        s.setInt(9, user_id);
        s.setInt(10, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByLastnameInterests(String lastname, String[] interests, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to lastname kai ta interests
        int count = -1, i;
        String query1 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query1 = query1 + ", ?";
        }
        query1 = query1 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Lastname = ?) "
                + "AND User_ID NOT IN (?) AND Interests_visible = 2";
        String query2 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query2 = query2 + ", ?";
        }
        query2 = query2 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Lastname = ?) "
                + "AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?) AND Interests_visible = 1";
        String query = "SELECT COUNT(*) AS Total FROM (" + query1 + " UNION " + query2 + ") AS ID";

        PreparedStatement s = SQLcon.prepareStatement(query);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 1, interests[i]);
        }
        s.setString(interests.length + 1, lastname);
        s.setInt(interests.length + 2, user_id);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + interests.length + 3, interests[i]);
        }
        s.setString(2 * interests.length + 3, lastname);
        s.setInt(2 * interests.length + 4, user_id);
        s.setInt(2 * interests.length + 5, user_id);
        s.setInt(2 * interests.length + 6, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByAgeInterests(int age1, int age2, String[] interests, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash thn hlikia kai ta interests
        int count = -1, i;

        String upper_date, lower_date, query;
        upper_date = calculateUpperDate(age1);
        lower_date = calculateLowerDate(age2);

        String query1 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query1 = query1 + ", ?";
        }
        query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
        String query2 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Birth_date BETWEEN ? AND ? AND "
                + "((Birthdate_visible = 1  AND Interests_visible = 1) "
                + "OR (Birthdate_visible = 1  AND Interests_visible = 2) "
                + "OR (Birthdate_visible = 2  AND Interests_visible = 1)) "
                + "AND User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query2 = query2 + ", ?";
        }
        query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?)";
        query = "SELECT COUNT(*) AS Total FROM (" + query1 + " UNION " + query2 + ") AS ID";

        PreparedStatement s = SQLcon.prepareStatement(query);
        s.setString(1, lower_date);
        s.setString(2, upper_date);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 3, interests[i]);
        }
        s.setInt(interests.length + 3, user_id);
        s.setString(interests.length + 4, lower_date);
        s.setString(interests.length + 5, upper_date);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + interests.length + 6, interests[i]);
        }
        s.setInt(2 * interests.length + 6, user_id);
        s.setInt(2 * interests.length + 7, user_id);
        s.setInt(2 * interests.length + 8, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByFirstLastnameAge(String firstname, String lastname, int age1, int age2, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to firstname, to lastname kai thn hlikia
        int count = -1;

        String upper_date, lower_date;
        upper_date = calculateUpperDate(age1);
        lower_date = calculateLowerDate(age2);

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM (SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Lastname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID NOT IN (?)"
                + "UNION "
                + "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Lastname = ? AND Birth_date BETWEEN ? AND ? AND User_ID IN "
                + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?) AND Birthdate_visible = 1) AS ID");
        s.setString(1, firstname);
        s.setString(2, lastname);
        s.setString(3, lower_date);
        s.setString(4, upper_date);
        s.setInt(5, user_id);
        s.setString(6, firstname);
        s.setString(7, lastname);
        s.setString(8, lower_date);
        s.setString(9, upper_date);
        s.setInt(10, user_id);
        s.setInt(11, user_id);
        s.setInt(12, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByFirstLastnameInterests(String firstname, String lastname, String[] interests, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to firsname, to lastname kai ta interests
        int count = -1, i;
        String query1 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query1 = query1 + ", ?";
        }
        query1 = query1 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Firstname = ? AND Lastname = ?) "
                + "AND User_ID NOT IN (?) AND Interests_visible = 2";
        String query2 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query2 = query2 + ", ?";
        }
        query2 = query2 + ")) AND User_ID IN (SELECT User_ID FROM Social.Profile WHERE Firstname = ? AND Lastname = ?) "
                + "AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?) AND Interests_visible = 1";
        String query = "SELECT COUNT(*) AS Total FROM (" + query1 + " UNION " + query2 + ") AS ID";

        PreparedStatement s = SQLcon.prepareStatement(query);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 1, interests[i]);
        }
        s.setString(interests.length + 1, firstname);
        s.setString(interests.length + 2, firstname);
        s.setInt(interests.length + 3, user_id);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + interests.length + 4, interests[i]);
        }
        s.setString(2 * interests.length + 4, firstname);
        s.setString(2 * interests.length + 5, lastname);
        s.setInt(2 * interests.length + 6, user_id);
        s.setInt(2 * interests.length + 7, user_id);
        s.setInt(2 * interests.length + 8, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByFirstnameAgeInterests(String firstname, int age1, int age2, String[] interests, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to firstname, thn hlikia kai ta interests
        int count = -1, i;

        String upper_date, lower_date, query;
        upper_date = calculateUpperDate(age1);
        lower_date = calculateLowerDate(age2);

        String query1 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query1 = query1 + ", ?";
        }
        query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
        String query2 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Birth_date BETWEEN ? AND ? AND "
                + "((Birthdate_visible = 1  AND Interests_visible = 1) "
                + "OR (Birthdate_visible = 1  AND Interests_visible = 2) "
                + "OR (Birthdate_visible = 2  AND Interests_visible = 1)) "
                + "AND User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query2 = query2 + ", ?";
        }
        query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?)";
        query = "SELECT COUNT(*) AS Total FROM (" + query1 + " UNION " + query2 + ") AS ID";

        PreparedStatement s = SQLcon.prepareStatement(query);
        s.setString(1, firstname);
        s.setString(2, lower_date);
        s.setString(3, upper_date);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 4, interests[i]);
        }
        s.setInt(interests.length + 4, user_id);
        s.setString(interests.length + 5, firstname);
        s.setString(interests.length + 6, lower_date);
        s.setString(interests.length + 7, upper_date);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + interests.length + 8, interests[i]);
        }
        s.setInt(2 * interests.length + 8, user_id);
        s.setInt(2 * interests.length + 9, user_id);
        s.setInt(2 * interests.length + 10, user_id);
        s.executeQuery();
        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByLastnameAgeInterests(String lastname, int age1, int age2, String[] interests, int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to lastname, thn hlikia kai ta interests
        int count = -1, i;

        String upper_date, lower_date, query;
        upper_date = calculateUpperDate(age1);
        lower_date = calculateLowerDate(age2);

        String query1 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Lastname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query1 = query1 + ", ?";
        }
        query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
        String query2 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Lastname = ? AND Birth_date BETWEEN ? AND ? AND "
                + "((Birthdate_visible = 1  AND Interests_visible = 1) "
                + "OR (Birthdate_visible = 1  AND Interests_visible = 2) "
                + "OR (Birthdate_visible = 2  AND Interests_visible = 1)) "
                + "AND User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query2 = query2 + ", ?";
        }
        query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?)";
        query = "SELECT COUNT(*) AS Total FROM (" + query1 + " UNION " + query2 + ") AS ID";

        PreparedStatement s = SQLcon.prepareStatement(query);
        s.setString(1, lastname);
        s.setString(2, lower_date);
        s.setString(3, upper_date);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 4, interests[i]);
        }
        s.setInt(interests.length + 4, user_id);
        s.setString(interests.length + 5, lastname);
        s.setString(interests.length + 6, lower_date);
        s.setString(interests.length + 7, upper_date);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + interests.length + 8, interests[i]);
        }
        s.setInt(2 * interests.length + 8, user_id);
        s.setInt(2 * interests.length + 9, user_id);
        s.setInt(2 * interests.length + 10, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int countPeopleByFirstLastnameAgeInterests(String firstname, String lastname, int age1, int age2, String interests[], int user_id) throws SQLException {
        //Metraei tous xrhstes me bash to firstname, to lastname, thn hlikia kai ta interests
        int count = -1, i;

        String upper_date, lower_date, query;
        upper_date = calculateUpperDate(age1);
        lower_date = calculateLowerDate(age2);

        String query1 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Lastname = ? AND Birth_date BETWEEN ? AND ? AND Birthdate_visible = 2 AND User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query1 = query1 + ", ?";
        }
        query1 = query1 + ")) AND User_ID NOT IN (?) AND Interests_visible = 2";
        String query2 = "SELECT User_ID "
                + "FROM Social.Profile "
                + "WHERE Firstname = ? AND Lastname = ? AND Birth_date BETWEEN ? AND ? AND "
                + "((Birthdate_visible = 1  AND Interests_visible = 1) "
                + "OR (Birthdate_visible = 1  AND Interests_visible = 2) "
                + "OR (Birthdate_visible = 2  AND Interests_visible = 1)) "
                + "AND User_ID IN "
                + "(SELECT User_ID "
                + "FROM Social.Interests "
                + "WHERE Interest IN (?";
        for (i = 0; i < interests.length - 1; i++) {
            query2 = query2 + ", ?";
        }
        query2 = query2 + ")) AND User_ID IN (SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
                + "UNION "
                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1)AND User_ID NOT IN (?)";
        query = "SELECT COUNT(*) AS Total FROM (" + query1 + " UNION " + query2 + ") AS ID";

        PreparedStatement s = SQLcon.prepareStatement(query);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 1, interests[i]);
        }
        s.setString(1, firstname);
        s.setString(2, lastname);
        s.setString(3, lower_date);
        s.setString(4, upper_date);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + 5, interests[i]);
        }
        s.setInt(interests.length + 5, user_id);
        s.setString(interests.length + 6, firstname);
        s.setString(interests.length + 7, lastname);
        s.setString(interests.length + 8, lower_date);
        s.setString(interests.length + 9, upper_date);
        for (i = 0; i < interests.length; i++) {
            s.setString(i + interests.length + 10, interests[i]);
        }
        s.setInt(2 * interests.length + 10, user_id);
        s.setInt(2 * interests.length + 11, user_id);
        s.setInt(2 * interests.length + 12, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int checkUserExistence(int user_id) throws SQLException {
        //Elegxei thn upar3h xrhsth me basi to User_ID
        PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID FROM Social.User WHERE User_ID = ?");
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

    public int checkUsernameAvailability(String username) throws SQLException {
        //Elegxei thn dia8eshmotita tou Username
        PreparedStatement s = SQLcon.prepareStatement("SELECT Username FROM Social.User WHERE Username = ?");
        s.setString(1, username);
        s.executeQuery();
        ResultSet rs = s.getResultSet();

        if (rs.next()) {
            s.close();
            return 0;
        } else {
            s.close();
            return 1;
        }
    }

    public int checkEmailAvailability(String email) throws SQLException {
        //Elegxei thn dia8esimotita tou Email
        PreparedStatement s = SQLcon.prepareStatement("SELECT Email FROM Social.User WHERE Email = ?");
        s.setString(1, email);
        s.executeQuery();
        ResultSet rs = s.getResultSet();

        if (rs.next()) {
            s.close();
            return 0;
        } else {
            s.close();
            return 1;
        }
    }

    public int insertPrimaryData(String username, String password, String email) throws SQLException {
        //Eisagwgh stoixeiwn pinaka User
        PreparedStatement s = SQLcon.prepareStatement("INSERT INTO Social.User("
                + "Username, Password, Email)"
                + "VALUES(?, ?, ?)");
        s.setString(1, username);
        s.setString(2, password);
        s.setString(3, email);
        s.executeUpdate();
        s.close();
        s = SQLcon.prepareStatement("SELECT User_ID FROM Social.User WHERE Username = ?");
        s.setString(1, username);
        s.executeQuery();
        ResultSet rs = s.getResultSet();

        if (rs.next()) {
            int user_id = rs.getInt("User_ID");
            s.close();
            return user_id;
        } else {
            s.close();
            return -1;
        }
    }

    public UserProfile getProfileData(int user_id) throws SQLException {
        //Anakthsh dedomenwn pinaka Profile
        PreparedStatement s = SQLcon.prepareStatement("SELECT * FROM fd_schema.FULL_USER_PROFILE WHERE User_ID = ?");
        s.setInt(1, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        UserProfile userProfile = null;

        if (rs.next()) {
            userProfile = new UserProfile();
            userProfile.setUsername(rs.getString("login_name"));
            userProfile.setFirstname(rs.getString("first_name"));
            userProfile.setLastname(rs.getString("last_name"));
            userProfile.setEmail(rs.getString("email"));
            userProfile.setCreditLimit(rs.getInt("credit_limit"));
            userProfile.setCurrentBalance(rs.getInt("credit_balance"));
            userProfile.setTown(rs.getString("town"));
            userProfile.setStreet(rs.getString("street"));
            userProfile.setStreetNumber(rs.getInt("street_number"));
            userProfile.setPostCode(rs.getInt("post_code"));
        }

        s.close();
        return userProfile;
	
    }

    public int getInterests(int user_id, UserProfile profile) throws SQLException {
        //Anakthsh endiaferontwn me bash to User_ID
        int i = 1;
        int row_count = 0;
        String[] interests = null;

        PreparedStatement s = SQLcon.prepareStatement("SELECT Interest FROM Social.Interests WHERE User_ID =?");
        s.setInt(1, user_id);
        s.executeQuery();
        ResultSet rs = s.getResultSet();

        while (rs.next()) {
            row_count++;
        }

        if (row_count > 0) {

            rs.first();
            interests = new String[row_count];
            interests[0] = rs.getString("Interest");

            while (rs.next()) {
                interests[i] = rs.getString("Interest");
                i++;
            }
        } else {
            return -1;
        }


        s.close();
        rs.close();

        return row_count;
    }

    public int insertFriend(int user_id, int friend_id) throws SQLException {
        //Eisagwgh dedomenwn sto pinaka Friends
        PreparedStatement s = SQLcon.prepareStatement("INSERT INTO Social.Friends("
                + "User_ID, Friend_ID, Pending)"
                + "VALUES(?,?, 0)");
        s.setInt(1, user_id);
        s.setInt(2, friend_id);
        s.executeUpdate();
        s.close();

        if (user_id == friend_id) {
            this.acceptFriend(user_id, friend_id);
        }

        return 0;
    }

    public int acceptFriend(int user_id, int friend_id) throws SQLException {
        //Apodoxh aithmatos filias
        PreparedStatement s = SQLcon.prepareStatement("UPDATE Social.Friends "
                + "SET Pending = 1 "
                + "WHERE User_ID = ? AND Friend_ID = ?");
        s.setInt(1, user_id);
        s.setInt(2, friend_id);
        s.executeUpdate();
        s.close();

        return 0;
    }

    public int hasFriend(int user_id1, int user_id2) throws SQLException {
        //Elegxos upar3hs filou me bash ta user_id
        PreparedStatement s = SQLcon.prepareStatement("SELECT Pending FROM Social.Friends WHERE ( ( User_ID = ? AND Friend_ID = ? ) OR ( User_ID = ? AND Friend_ID = ? ) ) AND Pending = 1");
        s.setInt(1, user_id1);
        s.setInt(2, user_id2);
        s.setInt(3, user_id2);
        s.setInt(4, user_id1);
        s.executeQuery();

        ResultSet rs = s.executeQuery();

        if (rs.next()) {
            s.close();
            return 1;
        } else {
            s.close();
            return 0;
        }

    }

    public int updatePassword(String password, int user_id) throws SQLException {
        //Allagh password
        PreparedStatement s = SQLcon.prepareStatement("UPDATE Social.User "
                + "SET Password = ? "
                + "WHERE User_ID = ?");
        s.setString(1, password);
        s.setInt(2, user_id);
        s.executeUpdate();
        s.close();

        return 0;
    }

    public int updatePersonalInfo(String firstname, String lastname, String birthdate, String gender, int gender_visibility, int birthday_visibility, int interests_visibility, int wall_visibility, String welcome, int user_id) throws SQLException {
        //Ananewsh stoixeiwn pou briskontai ston pinaka Profile
        PreparedStatement s = SQLcon.prepareStatement("UPDATE Social.Profile "
                + "SET Firstname = ?, Lastname = ?, Birth_date = ?, Gender = ?, Gender_visible = ?, Birthdate_visible = ?, Interests_visible = ?, Wall_visible = ?, Welcome_text = ? "
                + "WHERE User_ID = ?");
        s.setString(1, firstname);
        s.setString(2, lastname);
        s.setString(3, birthdate);
        s.setString(4, gender);
        s.setInt(5, gender_visibility);
        s.setInt(6, birthday_visibility);
        s.setInt(7, interests_visibility);
        s.setInt(8, wall_visibility);
        s.setString(9, welcome);
        s.setInt(10, user_id);
        s.executeUpdate();
        s.close();

        return 0;
    }

    public int updateInterests(String[] interests, String[] old_interests, int user_id) throws SQLException {
        //Ananewsh endiaferontwn
        for (int i = 0; i < old_interests.length; i++) {
            boolean deleted = true;
            for (int j = 0; j < interests.length; j++) {
                if (old_interests[i].equals(interests[j])) {
                    deleted = false;
                    break;
                }
            }

            if (deleted) {
                this.removeInterest(old_interests[i], user_id);
            }

        }
        for (int i = 0; i < interests.length; i++) {
            boolean new_interest = true;
            for (int j = 0; j < old_interests.length; j++) {
                if (old_interests[j].equals(interests[i])) {
                    new_interest = false;
                    break;
                }
            }

            if (new_interest) {
                this.insertInterest(interests[i], user_id);
            }

        }

        return 0;
    }

    public int insertInterest(String interest, int user_id) throws SQLException {
        //Eisagwgh stoixeiwn ston pinaka Interests 
        PreparedStatement s = SQLcon.prepareStatement("INSERT INTO Social.Interests("
                + "User_ID, Interest)"
                + "VALUES(?, ?)");
        s.setInt(1, user_id);
        s.setString(2, interest);
        s.executeUpdate();

        s.close();
        return 0;
    }

    public int removeInterest(String interest, int user_id) throws SQLException {
        //Apomakrunsh stoixeiou apo ton pinaka Interests
        PreparedStatement s = SQLcon.prepareStatement("DELETE FROM Social.Interests WHERE User_ID =? AND Interest = ?");
        s.setInt(1, user_id);
        s.setString(2, interest);
        s.executeUpdate();
        s.close();

        return 0;
    }

    public int login(String username, String password) throws SQLException {
        //Sunarthsh gia to login
        int user_id = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID FROM Social.User WHERE Username = ? AND Password = ?");
        s.setString(1, username);
        s.setString(2, password);
        s.executeQuery();
        ResultSet rs = s.getResultSet();

        if (rs.next()) {

            user_id = rs.getInt("User_ID");
            s.close();

        }
        return user_id;

    }

    public int removeFriend(int user_id, int friend_id) throws SQLException {
        //Diagrafi filwn
        PreparedStatement s = SQLcon.prepareStatement("DELETE FROM Social.Friends "
                + "WHERE (User_ID =? AND Friend_ID = ?) "
                + "OR (User_ID =? AND Friend_ID = ?)");
        s.setInt(1, user_id);
        s.setInt(2, friend_id);
        s.setInt(3, friend_id);
        s.setInt(4, user_id);
        s.executeUpdate();
        s.close();

        return 0;
    }

    public int requestAlreadySent(int user_id, int friend_id) throws SQLException {
        //Elegxos gia to an exei hdh apostalei aithma filias
        PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID FROM Social.Friends "
                + "WHERE ((User_ID = ? AND Friend_ID = ?) "
                + "OR (User_ID = ? AND Friend_ID = ?)) AND Pending = 0");
        s.setInt(1, user_id);
        s.setInt(2, friend_id);
        s.setInt(3, friend_id);
        s.setInt(4, user_id);
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

    public int countFriends(int user_id) throws SQLException {
        //Metraei to pli8os twn filwn enos user
        int count = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total_Friends "
                + "FROM Social.Friends "
                + "WHERE ( User_ID = ? OR Friend_ID = ? ) AND Pending = 1");
        s.setInt(1, user_id);
        s.setInt(2, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total_Friends") - 1;
        }

        s.close();

        return count;
    }

//    public friend[] getFriendsList(int user_id) throws SQLException {
//        //Lista filwn enos user
//        int friends_count = this.countFriends(user_id), i = 0;
//        friend[] friendsList = new friend[friends_count];
//        for (i = 0; i < friends_count; i++) {
//            friendsList[i] = new friend();
//        }
//
//        PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname "
//                + "FROM Social.Profile "
//                + "WHERE User_ID IN "
//                + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 1 "
//                + "UNION "
//                + "SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 1) AND User_ID NOT IN (?)");
//        s.setInt(1, user_id);
//        s.setInt(2, user_id);
//        s.setInt(3, user_id);
//        s.executeQuery();
//
//        ResultSet rs = s.getResultSet();
//
//        i = 0;
//        while (rs.next()) {
//            friendsList[i].setID(rs.getInt("User_ID"));
//            friendsList[i].setFirstname(rs.getString("Firstname"));
//            friendsList[i].setLastname(rs.getString("Lastname"));
//            i++;
//
//        }
//
//        rs.close();
//        s.close();
//        if (i == 0) {
//            friendsList = null;
//        }
//        return friendsList;
//
//    }

    public int countFriendRequests(int user_id) throws SQLException {
        //Metraei ta friend requests pou exei dex8ei enas user
        int count = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total_Requests "
                + "FROM Social.Friends "
                + "WHERE User_ID = ? AND Pending = 0");
        s.setInt(1, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total_Requests");
        }
        s.close();

        return count;
    }

//    public friend[] getFriendRequestsList(int user_id) throws SQLException {
//        //Lista atomwn pou exoun kanei aithma filias pros ena user
//        int friends_count = this.countFriendRequests(user_id), i = 0;
//        friend[] friendRequestsList = new friend[friends_count];
//
//        for (i = 0; i < friends_count; i++) {
//            friendRequestsList[i] = new friend();
//        }
//
//        PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname "
//                + "FROM Social.Profile "
//                + "WHERE User_ID IN "
//                + "(SELECT Friend_ID FROM Social.Friends WHERE User_ID = ? AND Pending = 0)");
//
//        s.setInt(1, user_id);
//        s.executeQuery();
//
//        ResultSet rs = s.getResultSet();
//
//        i = 0;
//        while (rs.next()) {
//            friendRequestsList[i].setID(rs.getInt("User_ID"));
//            friendRequestsList[i].setFirstname(rs.getString("Firstname"));
//            friendRequestsList[i].setLastname(rs.getString("Lastname"));
//            i++;
//
//        }
//
//        rs.close();
//        s.close();
//
//        if (friends_count < 1) {
//            friendRequestsList = null;
//        }
//        return friendRequestsList;
//
//    }
//
//    public int countPendingFriendRequests(int user_id) throws SQLException {
//        //Pli8os atomwn stous opoious exei kanei aithma filias enas user
//        int count = -1;
//
//        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total_Requests "
//                + "FROM Social.Friends "
//                + "WHERE Friend_ID = ? AND Pending = 0");
//        s.setInt(1, user_id);
//        s.executeQuery();
//
//        ResultSet rs = s.getResultSet();
//        if (rs.next()) {
//            count = rs.getInt("Total_Requests");
//        }
//        s.close();
//
//        return count;
//    }
//
//    public friend[] getPendingFriendRequestsList(int user_id) throws SQLException {
//        //Lista atomwn sta opoia enas user exei kanei aithma filias
//        int pending_friend_requests_count = this.countPendingFriendRequests(user_id), i = 0;
//        friend[] pendingFriendRequestsList = new friend[pending_friend_requests_count];
//
//        for (i = 0; i < pending_friend_requests_count; i++) {
//            pendingFriendRequestsList[i] = new friend();
//        }
//
//        PreparedStatement s = SQLcon.prepareStatement("SELECT User_ID, Firstname, Lastname "
//                + "FROM Social.Profile "
//                + "WHERE User_ID IN "
//                + "(SELECT User_ID FROM Social.Friends WHERE Friend_ID = ? AND Pending = 0)");
//
//        s.setInt(1, user_id);
//        s.executeQuery();
//
//        ResultSet rs = s.getResultSet();
//
//        i = 0;
//        while (rs.next()) {
//            pendingFriendRequestsList[i].setID(rs.getInt("User_ID"));
//            pendingFriendRequestsList[i].setFirstname(rs.getString("Firstname"));
//            pendingFriendRequestsList[i].setLastname(rs.getString("Lastname"));
//            i++;
//
//        }
//
//        rs.close();
//        s.close();
//
//        if (pending_friend_requests_count < 1) {
//            pendingFriendRequestsList = null;
//        }
//        return pendingFriendRequestsList;
//
//    }

    public int deleteProfile(int user_id) throws SQLException {
        //Diagrafh profil xrhsth
        PreparedStatement s = SQLcon.prepareStatement("DELETE FROM Social.Comments WHERE Receiver_ID = ? OR Sender_ID = ?");
        s.setInt(1, user_id);
        s.setInt(2, user_id);
        s.executeUpdate();
        s.close();
        s = SQLcon.prepareStatement("DELETE FROM Social.Wall WHERE Receiver_ID = ? OR Sender_ID = ?");
        s.setInt(1, user_id);
        s.setInt(2, user_id);
        s.executeUpdate();
        s.close();
        s = SQLcon.prepareStatement("DELETE FROM Social.Friends WHERE User_ID = ? OR Friend_ID = ?");
        s.setInt(1, user_id);
        s.setInt(2, user_id);
        s.executeUpdate();
        s.close();
        s = SQLcon.prepareStatement("DELETE FROM Social.Interests WHERE User_ID = ?");
        s.setInt(1, user_id);
        s.executeUpdate();
        s.close();
        s = SQLcon.prepareStatement("DELETE FROM Social.Profile WHERE User_ID =?");
        s.setInt(1, user_id);
        s.executeUpdate();
        s.close();
        s = SQLcon.prepareStatement("DELETE FROM Social.User WHERE User_ID = ?");
        s.setInt(1, user_id);
        s.executeUpdate();
        s.close();

        return 0;
    }

    public int createProfileStatus(int user_id) throws SQLException {
        //Anakta thn timi tou Create_profile
        int result = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT Create_profile FROM Social.User WHERE User_ID = ? ");
        s.setInt(1, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            result = rs.getInt("Create_Profile");
        }
        return result;
    }

    public int changeCreateProfileStatus(int user_id) throws SQLException {
        //Allazei thn timi tou Create_profile apo 0 se 1 otan exei dhmiourgh8ei to profil tou xrhsth
        int result = createProfileStatus(user_id);
        if (result == 0) {
            PreparedStatement s = SQLcon.prepareStatement("UPDATE Social.User SET Create_profile = 1 WHERE User_ID = ?");
            s.setInt(1, user_id);
            s.executeUpdate();
            return 0;
        }
        return 1;
    }

    public int insertMessage(int sender_id, int receiver_id, String message) throws SQLException {
        //Eisagwgh dedomenwn ston pinaka Wall
        PreparedStatement s = SQLcon.prepareStatement("INSERT INTO Social.Wall("
                + "Sender_ID, Receiver_ID, Message)"
                + "VALUES(?, ?, ?)");
        s.setInt(1, sender_id);
        s.setInt(2, receiver_id);
        s.setString(3, message);
        s.executeUpdate();
        s.close();

        return 0;
    }

//    public message[] getMessage(int receiver_id) throws SQLException {
//        //Anakthsh dedomenwn apo ton pinaka Wall
//        int count, i;
//
//        PreparedStatement s = SQLcon.prepareStatement("SELECT Sender_ID, Receiver_ID, Message, Message_ID, Message_time FROM Social.Wall "
//                + "WHERE Receiver_ID = ? ORDER BY Message_time DESC");
//        s.setInt(1, receiver_id);
//        s.executeQuery();
//        ResultSet rs = s.getResultSet();
//
//        count = countMessages(receiver_id);
//        message[] Result = new message[count];
//        for (i = 0; i < count; i++) {
//            Result[i] = new message();
//        }
//
//        i = 0;
//        while (rs.next()) {
//            Result[i].setSenderID(rs.getInt("Sender_ID"));
//            Result[i].setReceiverID(rs.getInt("Receiver_ID"));
//            Result[i].setMessage(rs.getString("Message"));
//            Result[i].setMessageID(rs.getInt("Message_ID"));
//            Result[i].setMessageTime(rs.getString("Message_time"));
//            i++;
//        }
//        rs.close();
//        s.close();
//
//        if (count < 1) {
//            Result = null;
//        }
//        return Result;
//    }

    public int countMessages(int receiver_id) throws SQLException {
        //Anakthsh plh8ous mhnumatwn pou exei dex8ei enas xrhsths
        int count = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM Social.Wall "
                + "WHERE Receiver_ID = ?");
        s.setInt(1, receiver_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int deleteMessage(int message_id) throws SQLException {
        //Diagrafh mhnumatos
        PreparedStatement s = SQLcon.prepareStatement("DELETE FROM Social.Comments WHERE Message_ID = ?");
        s.setInt(1, message_id);
        s.executeUpdate();
        s.close();
        s = SQLcon.prepareStatement("DELETE FROM Social.Wall WHERE Message_ID = ?");
        s.setInt(1, message_id);
        s.executeUpdate();
        s.close();

        return 0;
    }

    public int insertComment(int message_id, int sender_id, int receiver_id, String comment) throws SQLException {
        //Eisagwgh dedomenwn sto pinaka Comments
        PreparedStatement s = SQLcon.prepareStatement("INSERT INTO Social.Comments("
                + "Message_ID, Sender_ID, Receiver_ID, Comment)"
                + "VALUES(?, ?, ?, ?)");
        s.setInt(1, message_id);
        s.setInt(2, sender_id);
        s.setInt(3, receiver_id);
        s.setString(4, comment);
        s.executeUpdate();
        s.close();

        return 0;
    }

//    public comment[] getComment(int message_id) throws SQLException {
//        //Anakthsh dedomenwn apo ton pinaka Comments
//        int count, i;
//
//        PreparedStatement s = SQLcon.prepareStatement("SELECT Sender_ID, Receiver_ID, Comment, Comment_ID, Comment_time FROM Social.Comments "
//                + "WHERE Message_ID = ? ORDER BY Comment_time");
//        s.setInt(1, message_id);
//        s.executeQuery();
//        ResultSet rs = s.getResultSet();
//
//        count = countComments(message_id);
//        comment[] Result = new comment[count];
//        for (i = 0; i < count; i++) {
//            Result[i] = new comment();
//        }
//
//        i = 0;
//        while (rs.next()) {
//            Result[i].setSenderID(rs.getInt("Sender_ID"));
//            Result[i].setReceiverID(rs.getInt("Receiver_ID"));
//            Result[i].setComment(rs.getString("Comment"));
//            Result[i].setCommentID(rs.getInt("Comment_ID"));
//            Result[i].setCommentTime(rs.getString("Comment_time"));
//            i++;
//        }
//        rs.close();
//        s.close();
//
//        if (count < 1) {
//            Result = null;
//        }
//        return Result;
//    }

    public int countComments(int message_id) throws SQLException {
        //Anakthsh plh8ous comments
        int count = -1;

        PreparedStatement s = SQLcon.prepareStatement("SELECT COUNT(*) AS Total "
                + "FROM Social.Comments "
                + "WHERE Message_ID = ?");
        s.setInt(1, message_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            count = rs.getInt("Total");
        }
        s.close();

        return count;
    }

    public int deleteComment(int comment_id) throws SQLException {
        //Diagrafh comments
        PreparedStatement s = SQLcon.prepareStatement("DELETE FROM Social.Comments WHERE Comment_ID = ?");
        s.setInt(1, comment_id);
        s.executeUpdate();
        s.close();

        return 0;
    }

    public String getName(int user_id) throws SQLException {
        //Anakthsh firstname kai lastname apo ton pinaka Profile me bash to User_ID
        PreparedStatement s = SQLcon.prepareStatement("SELECT Firstname,Lastname FROM Social.Profile WHERE User_ID = ?");
        String name = null;

        s.setInt(1, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            name = rs.getString("Firstname") + " " + rs.getString("Lastname");
        }

        rs.close();
        s.close();

        return name;

    }

    public int insertProfileData(int user_id, String firstname, String lastname, String gender, String birthdate, String welcome, String gender_visibility, String birthday_visibility, String interests_visibility, String wall_visibility) throws SQLException {
        //Eisagwgh dedomenwn sto pinaka Profile
        PreparedStatement s = SQLcon.prepareStatement("INSERT INTO Social.Profile("
                + "User_ID, Firstname, Lastname, Gender, Gender_visible, Birth_date, Birthdate_visible, Welcome_text, Interests_visible, Wall_visible)"
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        s.setInt(1, user_id);
        s.setString(2, firstname);
        s.setString(3, lastname);
        s.setString(4, gender);
        s.setString(5, gender_visibility);
        s.setString(6, birthdate);
        s.setString(7, birthday_visibility);
        s.setString(8, welcome);
        s.setString(9, interests_visibility);
        s.setString(10, wall_visibility);
        s.executeUpdate();
        s.close();
        insertFriend(user_id, user_id);

        return 0;
    }

    public String getProfilePicture(int user_id) throws SQLException {
        //Anakthsh tou path sto opoio brisketai h eikona tou profile
        String path = null;

        PreparedStatement s = SQLcon.prepareStatement("SELECT Profile_picture FROM Social.Profile WHERE User_ID = ?");
        s.setInt(1, user_id);
        s.executeQuery();

        ResultSet rs = s.getResultSet();
        if (rs.next()) {
            path = rs.getString("Profile_picture");
        }

        rs.close();
        s.close();

        return path;
    }

    public int updateProfilePicture(String profile_picture, int user_id) throws SQLException {
        //Ananewsh path pou brisketai h eikona tou profile
        PreparedStatement s = SQLcon.prepareStatement("UPDATE Social.Profile SET Profile_picture = ? WHERE User_ID = ?");
        s.setString(1, profile_picture);
        s.setInt(2, user_id);
        s.executeUpdate();
        s.close();

        return 0;

    }

}
