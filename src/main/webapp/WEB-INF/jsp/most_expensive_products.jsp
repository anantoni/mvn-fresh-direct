<%-- 
    Document   : most_expensive_products
    Created on : Dec 19, 2014, 8:07:47 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Most Expensive Product Per Group</title>
        <%@ include file="../layout/header.jsp" %>
        
        <c:forEach var="product" items="${productList}">
            <c:out value="${product.name}"/> <br>
            <c:out value="${product.listPrice}"/> <br>
            <form action="AddToCartServlet" method="get">
                <c:if test="${sessionScope.userProfile!= null}">
                    <input type="hidden" name="product_name" value="<c:out value="${product.name}"/>">
                    <input type="hidden" name="product_id" value="<c:out value="${product.productID}"/>">
                    <input type="hidden" name="product_list_price" value="<c:out value="${product.listPrice}"/>">
                    <input type="number" name="product_quantity" min="0" max="<c:out value="${product.availableQuantity}"/>" step="1">
                    <input type="submit" value="Add to Cart">
                </c:if>
            </form>
        </c:forEach>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
