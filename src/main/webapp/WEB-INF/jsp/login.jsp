<%-- 
    Document   : register
    Created on : 24 Ιουν 2011, 12:17:56 πμ
    Author     : Lelouch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="../layout/header.jsp" %>
        <title>Login Page</title>

        <form action="AuthenticateUserServlet" method="post"/>
            <label for="username"> Username: </label><input name="username" autofocus="autofocus" autocomplete="off" type="text"/><span id="loginUsernameError"></span><br>
            <label for="password"> Password: </label><input name="password" autocomplete="off" type="password"/><span id="loginPasswordError"></span><br>

            <br>
            <button id="loginButton"> <span> Log in </span> </button> 
                <%
                    if (request.getAttribute("error") != null) {
                        out.println(request.getAttribute("error"));
                    }

                %>  
        </form>
    <%@ include file="../layout/footer.jsp" %>
</body>
</html>