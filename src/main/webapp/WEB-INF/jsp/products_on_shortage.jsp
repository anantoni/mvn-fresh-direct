<%-- 
    Document   : products_on_shortage
    Created on : Dec 23, 2014, 1:11:16 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products on Shortage</title>
        <%@ include file="../layout/header.jsp" %>
        <form action="ReorderServlet" method="post" role="form">
        Products on Shortage:
        <c:forEach var="product" items="${productList}">
            <c:out value="${product.name}"/> <br>
            <input type="hidden" name="product_id" value="<c:out value="${product.productID}"/>">
            <input class="form-control" type="number" name="product_quantity" min="0" max="999" value="<c:out value="${product.procurementQuantity}"/>" step="1">
            <select name="supplier_name" id="inputSupplier" class="form-control">
                <c:forEach var="supplier" items="${product.supplierList}">
                    <option value="<c:out value="${supplier.ID}"/>"><c:out value="${supplier.name}"/></option>
                </c:forEach>
            </select> 
        </c:forEach>
        <button type="submit" class="btn btn-default">Reorder All</button>    
        </form>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>

