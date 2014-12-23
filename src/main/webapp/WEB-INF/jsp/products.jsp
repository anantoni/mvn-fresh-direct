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
        Suggestions:
        <c:forEach var="product" items="${suggestionList}">
            <c:out value="${product.name}"/> <br>
            <c:out value="${product.listPrice}"/> <br>
            <form action="AddToCartServlet" method="get">
                <c:if test="${sessionScope.userProfile!= null}">
                    <input type="hidden" name="product_name" value="<c:out value="${product.name}"/>">
                    <input type="hidden" name="product_id" value="<c:out value="${product.productID}"/>">
                    <input type="hidden" name="product_list_price" value="<c:out value="${product.listPrice}"/>">
                    <input type="number" name="product_quantity" min="0" max="<c:out value="${product.availableQuantity}"/>" step="1">
                    <button type="submit" class="btn btn-default">Add to Cart</button>
                </c:if>
            </form>
        </c:forEach>
        <c:remove var="suggestionList"/> 
        
        Products:
        <c:forEach var="product" items="${productList}">
            <c:out value="${product.name}"/> <br>
            <c:out value="${product.listPrice}"/> <br>
            <form action="AddToCartServlet" method="get">
                <c:if test="${sessionScope.userProfile!= null}">
                    <input type="hidden" name="product_name" value="<c:out value="${product.name}"/>">
                    <input type="hidden" name="product_id" value="<c:out value="${product.productID}"/>">
                    <input type="hidden" name="product_list_price" value="<c:out value="${product.listPrice}"/>">
                    <input type="number" name="product_quantity" min="0" max="<c:out value="${product.availableQuantity}"/>" step="1">
                    <button type="submit" class="btn btn-default">Add to Cart</button>
                </c:if>
            </form>
        </c:forEach>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
