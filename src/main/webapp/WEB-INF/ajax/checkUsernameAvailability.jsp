             <%-- 
    Document   : checkUsernameAvailability
    Created on : 25 Ιουν 2011, 7:05:17 μμ
    Author     : Lelouch
--%>

<%@page import="controller.DatabaseManager" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    if (request.getParameter("username") == null) {
        out.println("<checkUsernameAvailability><status>FAIL</status><error>BAD_REQUEST</error></checkUsernameAvailability>");
    } else {

        String username = request.getParameter("username");
        DatabaseManager dbManager = new DatabaseManager();

        try {

            if (dbManager.checkUsernameAvailability(username) == true) {
                out.println("<checkUsernameAvailability><status>SUCCESS</status></checkUsernameAvailability>");
            } else {
                out.println("<checkUsernameAvailability><status>FAIL</status><error>ALREADY_EXISTS</error></checkUsernameAvailability>");
            }

        } catch (SQLException ex) {
            out.println("<checkUsernameAvailability><status>FAIL</status><error>DATABASE_ERROR" + ex.toString() + "</error></checkUsernameAvailability>");
        }

    }
%>