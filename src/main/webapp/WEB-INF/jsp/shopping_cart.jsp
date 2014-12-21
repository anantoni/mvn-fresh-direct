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
        <form action="CheckoutServlet" class="form-inline" role="form" method="post">
            <c:forEach var="entry" items="${sessionScope.shoppingCart.productMap}">
                <c:out value="${entry.key.name}"/> <br>
                <c:out value="${entry.key.price} x ${entry.value} = ${entry.key.price * entry.value}"/> <br>
                <input type="hidden" name="product_names" value="<c:out value="${entry.key.name}"/>">
                <input type="hidden" name="product_ids" value="<c:out value="${entry.key.productID}"/>">
                <input type="hidden" name="product_list_prices" value="<c:out value="${entry.key.price}"/>">
                <input type="hidden" name="product_quantities" value="<c:out value="${entry.value}"/>">
            </c:forEach>
                Total Cost: <c:out value="${sessionScope.shoppingCart.totalCost}"/> <br>
            <c:if test="${sessionScope.userProfile != null}">
                <div class="form-group">
                    <label for="oldCreditCard">Order with new credit card:</label>
                    <select name="old_credit_card" id="oldCreditCard" class="form-control">
                    <c:forEach var="credit_card" items="${sessionScope.userProfile.cardList}">
                        <option><c:out value="${credit_card}"/></option>
                    </c:forEach>
                    </select>
                
                    <label for="newCreditCard">Order with new credit card:</label> 
                    <input type="text" class="form-control" id="newCreditCard" name="new_credit_card">
                </div>
                <button type="submit" class="btn btn-default">Checkout</button>
            </c:if>
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
