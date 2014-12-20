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
            Query for the highest and lowest order sums per product:
            <input type="submit" value="Submit"> 
        </form>
        <form action="GreaterThan10KServlet" method="get" name="search_form">
            Query for the days on which the value of all orders was more than 10,000 euros in month: of year:
            <input type="submit" value="Submit"> 
        </form>
        <form action="BestSellingProductServlet" method="get" name="search_form">
            Query for the best selling product in terms of units sold:
            <input type="submit" value="Submit">
        </form>
        <form action="MostExpensiveProductPerGroupServlet" method="get" name="search_form">
            Query for the most expensive product per group:
            <input type="submit" value="Submit">
        </form>
        <form action="NeverOrderedProductsServlet" method="get" name="search_form">
            Query for the products never ordered:
            <input type="submit" value="Submit">
        </form>
        <form action="NotOrderedInMonthOfYearServlet" method="get" name="search_form">
            Query for the products not ordered in month: of year: 
            <input type="submit" value="Submit">
        </form>
        <form action="SixDegreesOfSeparationServlet" method="get" name="search_form">
            Query the degree of seperation between supplier #1: and supplier #2
            <input type="submit" value="Submit">
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
