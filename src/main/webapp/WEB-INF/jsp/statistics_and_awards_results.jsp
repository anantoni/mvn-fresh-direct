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
        <div class="container-fluid" align="center">
            
        <c:if test="${productList != null}">
            <legend><h1>Most Popular Products</h1></legend>
            <c:forEach var="product" items="${productList}">
            <div style="border: 2px solid; border-radius: 25px; padding: 10px" class="col-md-2">    
                <c:out value="${product.name}"/> <br>
                <c:out value="${product.listPrice}"/>&euro; <br>
            </div>
            </c:forEach>
        </c:if>
        <c:if test="${supplierList != null}">        
            <legend><h1>Most Popular Suppliers</h1></legend>
            <c:forEach var="supplier" items="${supplierList}">
                <h4><c:out value="${supplier.name}"/></h4> <br>
                <h4><c:out value="${supplier.totalAmountSupplied}"/></h4> <br>
            </c:forEach>
        </c:if>
        <c:if test="${postCodeList != null}">
            <legend><h1>Most Popular Post Codes</h1></legend>
            <c:forEach var="postCode" items="${postCodeList}">
                <h4><c:out value="${postCode}"/></h4><br>
            </c:forEach>
        </c:if>
        <c:if test="${clientList != null}">        
            <legend><h1>Top Clients</h1></legend>
            <c:forEach var="client" items="${clientList}">
                <h4><c:out value="${client.userID}"/></h4> <br>
                <h4><c:out value="${client.username}"/></h4> <br>
            </c:forEach>
        </c:if>
        </div>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
