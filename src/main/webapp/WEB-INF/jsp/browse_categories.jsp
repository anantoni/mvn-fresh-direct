<%-- 
    Document   : browse_categories
    Created on : Dec 15, 2014, 12:59:27 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browse Categories</title>
        
        <%@ include file="../layout/header.jsp" %>
        <form class="browse_categories_form" action="BrowseCategoriesServlet" method="get" name="search_form">
        <div class="product_category_section">
            <label for="product_category"> Product Category: </label>
            <input type="radio" name="product_category" value="V" checked>Vegetable and Fruits
            <input type="radio" name="product_category" value="M">Meat and Dairy Products
            <input type="radio" name="product_category" value="C">Chemical Products
            <input type="radio" name="product_category" value="I">Industrial Products
            <input type="radio" name="product_category" value="B">Boxed/Canned Products
            <input type="radio" name="product_category" value="G">Garden Products
            <input type="radio" name="product_category" value="H">Household Products
            <br>
            <input type="submit" value="Submit"> 
        </div>
        
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
