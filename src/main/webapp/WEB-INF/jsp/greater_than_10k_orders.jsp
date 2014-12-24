<%-- 
    Document   : greater_than_10k_orders
    Created on : Dec 20, 2014, 5:34:13 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Days with greater than 10k orders</title>
        <%@ include file="../layout/header.jsp" %>
        <div class="container-fluid" align="center">
            <legend><h1>Day with orders greater than 10000 &euro;</h1></legend>
        <c:forEach var="day" items="${dayList}">
            <h3><c:out value="${day}"/> <br></h3>
        </c:forEach>
        </div>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
