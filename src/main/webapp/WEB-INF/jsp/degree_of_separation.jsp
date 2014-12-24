<%-- 
    Document   : degree_of_separation
    Created on : Dec 21, 2014, 7:15:21 PM
    Author     : anantoni
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <%@ include file="../layout/header.jsp" %>
        
        <div class="container-fluid" align="center">
            <legend><h1>Six Degrees of Separation</h1></legend>
            <h3><c:out value="${degreeOfSeparation}"/></h3>
        </div>
        <%@ include file="../layout/footer.jsp" %>        
    </body>
</html>
