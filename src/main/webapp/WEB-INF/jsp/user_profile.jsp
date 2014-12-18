<%-- 
    Document   : user_profile
    Created on : 1 Αυγ 2011, 12:23:30 πμ
    Author     : anantoni
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <%@ include file="../layout/header.jsp" %>
    
    <div id="mainContainer" align="center">
        Name: <c:out value="${userProfile.username}"/>
        Email: <c:out value="${userProfile.email}" />
    </div>
    <%@ include file="../layout/footer.jsp" %>        
    </body>
</html>