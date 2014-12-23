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
        <form class="form-inline" action="SearchProductServlet" method="get">
            <label class="control-label" for="product_name"> Product Name: </label>
            <input autofocus class="form-control"  type="search" name="product_name"/>
            <br>
            <label class="control-label" for="product_description"> Product Description: </label>
            <input type="search" class="form-control" name="product_description"/>
            <br>
            <label class="control-label" for="product_category"> Product Category: </label>
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
        
            <br>
            <c:if test="${sessionScope.userProfile != null}">
                <label class="control-label" for="inputSupplier"> Supplier Name: </label>
                <select name="supplier_name" id="inputSupplier" class="form-control">
                    <option value="">Any</option>
                <c:forEach var="supplier" items="${sessionScope.userProfile.supplierList}">
                    <option value="<c:out value="${supplier.ID}"/>"><c:out value="${supplier.name}"/></option>
                </c:forEach>
                </select>
                
            </c:if>
                <br>
            <button type="submit" class="btn btn-default">Search</button>
            
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
