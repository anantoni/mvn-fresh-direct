<%-- 
    Document   : register_new_product
    Created on : Dec 14, 2014, 5:23:07 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register New Product</title>
        <%@ include file="../layout/header.jsp" %>
        <form class="new_product_form" action="" method="get" name="search_form">
        <div class="new_product_section">
            <label for="product_name"> Product Name: </label>
            <input autofocus type="text" name="product_name"/>
            <br>
            <label for="product_description"> Product Description: </label>
            <input type="textarea" name="product_description" maxlength="512"/>
            <br>
            <label for="product_category"> Product Category: </label>
            <input type="radio" name="product_category" value="V" checked>Vegetable and Fruits
            <input type="radio" name="product_category" value="M">Meat and Dairy Products
            <input type="radio" name="product_category" value="C">Chemical Products
            <input type="radio" name="product_category" value="I">Industrial Products
            <input type="radio" name="product_category" value="B">Boxed/Canned Products
            <input type="radio" name="product_category" value="G">Garden Products
            <input type="radio" name="product_category" value="H">Household Products
            <br>
            <label for="product_quantity"> Product Quantity: </label>
            <input type="number" name="product_quantity" min="0" max="999" step="1"/>
            <br>
            <label for="product_quantity"> Product Procurement Level: </label>
            <input type="number" name="product_procurment_level" min="0" max="999" step="1"/>
            <br>
            <label for="product_quantity"> Product Procurement Quantity: </label>
            <input type="number" name="product_procurement_quantity" min="0" max="999" step="1"/>
            <br>
            <label for="supplier_name"> Supplier Name: </label>
            <input type="search" name="supplier_name"/>     
            <br>
            <input type="submit" value="Submit">            
        </div>
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
