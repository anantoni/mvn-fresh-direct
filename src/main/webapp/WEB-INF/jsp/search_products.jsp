<%-- 
    Document   : search_products
    Created on : Dec 14, 2014, 4:34:05 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Search</title>

        <%@ include file="../layout/header.jsp" %>
        <form class="search_form" action="" method="get" name="search_form">
        <div class="product_section">
            <label for="product_name"> Product Name: </label>
            <input autofocus type="search" name="product_name"/>
            <br>
            <label for="product_description"> Product Description: </label>
            <input type="search" name="product_description"/>
        </div>
        <div class="product_category_section">
            <label for="product_category"> Product Category: </label>
            <input type="radio" name="product_category" value="A" checked>Any
            <input type="radio" name="product_category" value="V">Vegetable and Fruits
            <input type="radio" name="product_category" value="M">Meat and Dairy Products
            <input type="radio" name="product_category" value="C">Chemical Products
            <input type="radio" name="product_category" value="I">Industrial Products
            <input type="radio" name="product_category" value="B">Boxed/Canned Products
            <input type="radio" name="product_category" value="G">Garden Products
            <input type="radio" name="product_category" value="H">Household Products
        </div>
        <div class="supplier_section">
            <label for="supplier_name"> Supplier Name: </label>
            <input type="search" name="supplier_name"/>
        </div>
            <input type="submit" value="Submit"> 
            
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
