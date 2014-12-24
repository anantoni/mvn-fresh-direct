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
        <div class="container-fluid" align="center">
            <form class="form-horizontal" action="AddNewProductServlet" method="get" role="form">
            <fieldset>

            <!-- Form Name -->
            <legend>Register New Product</legend>
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_name">Product Name:</label>
                <div class="col-md-4">
                    <input autofocus class="form-control input-md" type="search" name="product_name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_description">Product Description:</label>
                <div class="col-md-4">
                    <textarea class="form-control input-md" name="product_description"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_category">Product Category:</label>
                <div class="col-md-4">
                    <select name="product_category" id="inputProductCategory" class="form-control">
                        <option value="1">Vegetable and Fruits</option>
                        <option value="2">Meat and Dairy Products</option>
                        <option value="3">Chemical Products</option>
                        <option value="4">Industrial Products</option>
                        <option value="5">Boxed/Canned Products</option>
                        <option value="6">Garden Products</option>
                        <option value="7">Household Products</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="list_price">List Price: </label>
                 <div class="col-md-4">
                    <input class="form-control input-md" type="number" name="list_price" min="1" max="5000" step="1"/>
                 </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_quantity"> Product Quantity: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" type="number" name="product_quantity" min="0" max="999" step="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_procurement_level"> Product Procurement Level: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" type="number" name="product_procurement_level" min="0" max="999" step="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_procurement_quantity"> Product Procurement Quantity: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" type="number" name="product_procurement_quantity" min="0" max="999" step="1"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="supplier_name"> Supplier: </label>
                <div class="col-md-4">
                    <select name="supplier_name" id="inputSupplier" class="form-control">
                        <c:forEach var="supplier" items="${sessionScope.userProfile.supplierList}">
                            <option value="<c:out value="${supplier.ID}"/>"><c:out value="${supplier.name}"/></option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Add New Product</button>
                </div>
            </div>
        </fieldset>
        </form>
        </div>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
