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
        <div class="container-fluid" align="center">
        <form class="form-horizontal" action="SearchProductServlet" method="get">
            <fieldset>

            <!-- Form Name -->
            <legend>Search Products</legend>

            <div class="form-group">
                <label class="col-md-4 control-label" for="product_name"> Product Name: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" type="search" name="product_name"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_description"> Product Description: </label>
                <div class="col-md-4">
                    <textarea class="form-control" name="product_description"/> </textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="product_category"> Product Category: </label>
                <div class="col-md-4">
                <select name="product_category" id="inputProductCategory" class="form-control">
                    <option value="">Any</option>
                    <option value="V">Vegetable and Fruits</option>
                    <option value="M">Meat and Dairy Products</option>
                    <option value="C">Chemical Products</option>
                    <option value="I">Industrial Products</option>
                    <option value="B">Boxed/Canned Products</option>
                    <option value="G">Garden Products</option>
                    <option value="H">Household Products</option>
                </select>
                </div>
            </div>
        
           
            <c:if test="${sessionScope.userProfile != null}">
            <div class="form-group">
                <label class="col-md-4 control-label" for="inputSupplier"> Supplier Name: </label>
                <div class="col-md-4">
                <select name="supplier_name" id="inputSupplier" class="form-control">
                    <option value="">Any</option>
                    <c:forEach var="supplier" items="${sessionScope.userProfile.supplierList}">
                    <option value="<c:out value="${supplier.ID}"/>"><c:out value="${supplier.name}"/></option>
                    </c:forEach>
                </select>
                </div>
            </div>
            </c:if>
            <div class="form-group">    
                <label class="col-md-4 control-label" for="order_by"> Order by: </label>
                <div class="col-md-4">
                <select name="order_by" id="inputSupplier" class="form-control">
                    <option value="1">Price</option>
                    <option value="2">Product Name</option>
                    <option value="3">Supplier Name</option>
                </select>
                </div>
            </div>
            <div class="form-group">    
                <label class="col-md-4 control-label" for="ordering"> Ordering: </label>
                <div class="col-md-4">
                <select name="ordering" id="inputSupplier" class="form-control">
                    <option value="1">Descending</option>
                    <option value="2">Ascending</option>
                </select>
                </div>
            </div>           
            
            <button type="submit" class="btn btn-success">Search</button>
            </fieldset>
        </form>
        </div>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
