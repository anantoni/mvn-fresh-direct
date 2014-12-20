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
        
        <c:forEach var="day" items="${dayList}">
            <c:out value="${day}"/> <br>
        </c:forEach>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
