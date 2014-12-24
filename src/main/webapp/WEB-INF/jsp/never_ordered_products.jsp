<%-- 
    Document   : never_ordered_products
    Created on : Dec 19, 2014, 5:50:57 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Never Ordered Products</title>
        <%@ include file="../layout/header.jsp" %>
         <div class="container-fluid" align="center">

            <legend><h1>Never Ordered Products</h1></legend>
            <c:forEach var="product" items="${productList}">
            <div style="border: 2px solid; border-radius: 25px; padding: 10px" class="col-md-2">    
                <c:out value="${product.name}"/> <br>
                <c:out value="${product.listPrice}"/>&euro; <br>
                <c:if test="${sessionScope.userProfile!= null}">
                <form action="AddToCartServlet" method="get">
                    
                    <input type="hidden" name="product_name" value="<c:out value="${product.name}"/>">
                    <input type="hidden" name="product_id" value="<c:out value="${product.productID}"/>">
                    <input type="hidden" name="product_list_price" value="<c:out value="${product.listPrice}"/>">
                    <input type="number" class="form-control" name="product_quantity" min="0" max="<c:out value="${product.availableQuantity}"/>" step="1">
                    <button type="submit" class="btn btn-success">Add to Cart</button>
                    <br>
                    
                </form>
                </c:if>
            </div>    
            </c:forEach>
         </div>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
