<%-- 
    Document   : statistics_and_awards_results
    Created on : Dec 22, 2014, 2:23:50 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <%@ include file="../layout/header.jsp" %>    
        <c:if test="${productList != null}">
            Most Popular Products:
            <c:forEach var="product" items="${productList}">
                <c:out value="${product.name}"/> <br>
                <c:out value="${product.listPrice}"/> <br>
            </c:forEach>
        </c:if>
        <c:if test="${supplierList != null}">        
            Most Popular Suppliers:
            <c:forEach var="supplier" items="${supplierList}">
                <c:out value="${supplier.name}"/> <br>
                <c:out value="${supplier.totalAmountSupplied}"/> <br>
            </c:forEach>
        </c:if>
        <c:if test="${postCodeList != null}">
            Most Popular Post Codes:
            <c:forEach var="postCode" items="${postCodeList}">
                <c:out value="${postCode}"/> <br>
            </c:forEach>
        </c:if>
        <c:if test="${clientList != null}">        
            Top Clients:
            <c:forEach var="client" items="${clientList}">
                <c:out value="${client.userID}"/> <br>
                <c:out value="${client.username}"/> <br>
            </c:forEach>
        </c:if>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
