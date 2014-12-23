<%-- 
    Document   : products
    Created on : Dec 15, 2014, 2:14:29 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <%@ include file="../layout/header.jsp" %>
        <div class="container-fluid" align="center">
        <c:if test="${suggestionList!= null}">
            <legend><h1>Suggestions</h1></legend>>
        <c:forEach var="product" items="${suggestionList}">
        <div style="border: 2px solid; border-radius: 25px; padding: 10px" class="col-md-2">
            <c:out value="${product.name}"/> <br>
            <c:out value="${product.listPrice}"/> <br>
            <form action="AddToCartServlet" method="get">
                <c:if test="${sessionScope.userProfile!= null}">
                    <input type="hidden" name="product_name" value="<c:out value="${product.name}"/>">
                    <input type="hidden" name="product_id" value="<c:out value="${product.productID}"/>">
                    <input type="hidden" name="product_list_price" value="<c:out value="${product.listPrice}"/>">
                    <input type="number" class="form-control" name="product_quantity" min="0" max="<c:out value="${product.availableQuantity}"/>" step="1">
                    <button type="submit" class="btn btn-success">Add to Cart</button>
                    <br>
                </c:if>
            </form>
        </div>
        </c:forEach>
        </c:if>
        <c:remove var="suggestionList"/> 
        
        <c:if test="${productList!= null}">
            <legend><h1>Products</h1></legend>
        <c:forEach var="product" items="${productList}">
        <div style="border: 2px solid; border-radius: 25px; padding: 10px" class="col-md-2">    
            <c:out value="${product.name}"/> <br>
            <c:out value="${product.listPrice}"/> <br>
            <form action="AddToCartServlet" method="get">
                <c:if test="${sessionScope.userProfile!= null}">
                    <input type="hidden" name="product_name" value="<c:out value="${product.name}"/>">
                    <input type="hidden" name="product_id" value="<c:out value="${product.productID}"/>">
                    <input type="hidden" name="product_list_price" value="<c:out value="${product.listPrice}"/>">
                    <input type="number"class="form-control" name="product_quantity" min="0" max="<c:out value="${product.availableQuantity}"/>" step="1">
                    <button type="submit" class="btn btn-success">Add to Cart</button>
                    <br>
                </c:if>
            </form>
        </div>
        </c:forEach>
        </c:if>
        </div>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
