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
        <form class="form-horizontal" action="AddNewProductServlet" method="get" role="form">
        <div class="form-group">
            <label class="col-sm-4 control-label" for="product_name">Product Name:</label>
            <input autofocus class="form-control" type="search" name="product_name"/>
            
            <label class="col-sm-4 control-label" for="product_description">Product Description:</label>
            <input type="search" class="form-control" name="product_description"/>
            
            <label class="col-sm-4 control-label" for="product_category">Product Category:</label>
            <select name="product_category" id="inputProductCategory" class="form-control">
                <option value="1">Vegetable and Fruits</option>
                <option value="2">Meat and Dairy Products</option>
                <option value="3">Chemical Products</option>
                <option value="4">Industrial Products</option>
                <option value="5">Boxed/Canned Products</option>
                <option value="6">Garden Products</option>
                <option value="7">Household Products</option>
            </select>
       
            <label class="col-sm-4 control-label" for="list_price">List Price: </label>
            <input class="form-control" type="number" name="list_price" min="1" max="5000" step="1"/>
            
            <label class="col-sm-4 control-label" for="product_quantity"> Product Quantity: </label>
            <input class="form-control" type="number" name="product_quantity" min="0" max="999" step="1"/>
            
            <label class="col-sm-4 control-label" for="product_procurement_level"> Product Procurement Level: </label>
            <input class="form-control" type="number" name="product_procurement_level" min="0" max="999" step="1"/>
            
            <label class="col-sm-4 control-label" for="product_procurement_quantity"> Product Procurement Quantity: </label>
            <input class="form-control" type="number" name="product_procurement_quantity" min="0" max="999" step="1"/>
            <label class="col-sm-4 control-label" for="supplier_name"> Supplier: </label>
            <select name="supplier_name" id="inputSupplier" class="form-control">
                <c:forEach var="supplier" items="${sessionScope.userProfile.supplierList}">
                    <option value="<c:out value="${supplier.ID}"/>"><c:out value="${supplier.name}"/></option>
                </c:forEach>
            </select>     
            <button type="submit" class="btn btn-default">Add New Product</button>            
        </div>
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
