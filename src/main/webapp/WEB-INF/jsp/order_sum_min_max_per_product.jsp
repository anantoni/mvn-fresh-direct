<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Highest and Lowest Order Sum Per Product</title>
        <%@ include file="../layout/header.jsp" %>
        
        <div class="container-fluid" align="center">
            <legend><h1>Products</h1></legend>
        <c:forEach var="product" items="${productList}">
        <div style="border: 2px solid; border-radius: 25px; padding: 10px" class="col-md-2">
            <c:out value="${product.name}"/> <br>
            Price: <c:out value="${product.listPrice}"/>&euro;<br>
            Lowest Sum: <c:out value="${product.minOrderSum}"/> <br>
            Highest Sum: <c:out value="${product.maxOrderSum}"/> <br>
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
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>