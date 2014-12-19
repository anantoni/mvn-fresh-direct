<%-- 
    Document   : queries
    Created on : Dec 18, 2014, 1:57:58 AM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Query the Store Database</title>
        <%@ include file="../layout/header.jsp" %>
    
        <form action="HighLowOrderSumsServlet" method="get" name="search_form">
            <input type="submit" value="Submit"> 
        </form>
        <form action="GreaterThan10KServlet" method="get" name="search_form">
            <input type="submit" value="Submit"> 
        </form>
        <form action="BestSellingProductServlet" method="get" name="search_form">
            <input type="submit" value="Submit">
        </form>
        <form action="MostExpensiveProductPerGroupServlet" method="get" name="search_form">
            <input type="submit" value="Submit">
        </form>
        <form action="NeverOrderedServlet" method="get" name="search_form">
            <input type="submit" value="Submit">
        </form>
        <form action="NotOrderedInMonthOfYearServlet" method="get" name="search_form">
            <input type="submit" value="Submit">
        </form>
        <form action="SixDegreesOfSeparationServlet" method="get" name="search_form">
            <input type="submit" value="Submit">
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
