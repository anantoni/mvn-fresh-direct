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
        
    <form action="OrderSumMinMaxServlet" method="post" class="form-inline" role="form">
        <label class="col-sm-4 control-label">Query for the highest and lowest order sums per product</label>
        <button type="submit" class="btn btn-default">Query</button> 
    </form>
        
    <form action="GreaterThan10KServlet" method="get" class="form-inline" role="form">
        <label for="inputDate1" class="col-sm-4 control-label">Query for the days on which the value of all orders was more than 10,000&euro; in</label> 
        <input type="text" type="text" value="12/2014" class="form-control datepicker" name="date" id="inputDate1">
        <button type="submit" class="btn btn-default">Query</button> 
    </form>
    
    <form action="BestSellingProductServlet" method="post" class="form-inline" role="form">
        <label class="col-sm-4 control-label">Query for the best selling product in terms of units sold</label>
        <button type="submit" class="btn btn-default">Query</button>
    </form>

    <form action="MostExpensiveProductsPerGroupServlet" method="post" class="form-inline" role="form">
        <label class="col-sm-4 control-label">Query for the most expensive product per group</label>
        <button type="submit" class="btn btn-default">Query</button>
    </form>

    <form action="NeverOrderedProductsServlet" method="post" class="form-inline" role="form">
        <label class="col-sm-4 control-label">Query for the products never ordered</label>
        <button type="submit" class="btn btn-default">Query</button>
    </form>
    
    <form action="ProductsNotOrderedInMonthOfYearServlet" method="get" class="form-inline" role="form">
        <label for="inputDate2" class="col-sm-4 control-label">Query for the products not ordered in</label> 
        <input type="text" type="text"  value="12/2014" class="form-control datepicker" name="date" id="inputDate2">
        <button type="submit" class="btn btn-default">Query</button>
    </form>

    <form action="DegreeOfSeparationBetweenTwoSuppliersServlet" method="get" class="form-inline" role="form">
        <label for="inputSupplier1" class="col-sm-4 control-label">Query the degree of separation between supplier #1 and supplier #2</label>
        <input type="text" class="form-control" id="inputSupplier1" name="supplier1" placeholder="Supplier #1">
        <input type="text" class="form-control" id="inputSupplier2" name="supplier2" placeholder="Supplier #2">
        <button type="submit" class="btn btn-default">Query</button>
    </form>
    <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
