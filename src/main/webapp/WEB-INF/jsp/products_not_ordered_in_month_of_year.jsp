<%-- 
    Document   : products_not_ordered_in_month_of_year
    Created on : Dec 20, 2014, 6:57:11 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products not Ordered in Month of Year</title>
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