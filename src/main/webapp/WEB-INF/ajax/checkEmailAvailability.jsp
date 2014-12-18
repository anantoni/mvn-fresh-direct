<%-- 
    Document   : checkEmailAvailability
    Created on : 27 Ιουν 2011, 9:34:10 μμ
    Author     : anantoni
--%>

<%@page import="controller.DatabaseManager" %>
<%@page import="java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
        if ( request.getParameter("email") == null ) 
                out.println( "<checkUsernameAvailability><status>FAIL</status><error>BAD_REQUEST</error></checkUsernameAvailability>");
        
        else {
                String email = request.getParameter("email");
                DatabaseManager dbManager = new DatabaseManager();
            
                try {
                
                    if ( dbManager.checkEmailAvailability( email ) == true )
                        out.println( "<checkUsernameAvailability><status>SUCCESS</status></checkUsernameAvailability>" );
                    else 
                        out.println( "<checkUsernameAvailability><status>FAIL</status><error>ALREADY_EXISTS</error></checkUsernameAvailability>" );

                } catch ( SQLException ex ) {
                        out.println( "<checkEmailAvailability><status>FAIL</status><error>DATABASE_ERROR</error></checkEmailAvailability>" );
                }

        }
%>
