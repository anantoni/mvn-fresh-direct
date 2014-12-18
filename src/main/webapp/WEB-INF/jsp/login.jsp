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

        
    <div id="mainContainer" align="center">        

        <div id="loginPanel" >

            <div id="panelHeader" align="center"> 
                <h2> Log in </h2>
                <hr>
            </div>
            <form action="AuthenticateUserServlet" method="post"/>
                <label class="loginLabel" for="username"> Username: </label><input name="username" id="loginUsername" class="loginInput"  autofocus="autofocus" autocomplete="off" type="text"/><span id="loginUsernameError"></span><br>
                <label class="loginLabel" for="password"> Password: </label><input name="password" id="loginPassword" class="loginInput" autocomplete="off" type="password"/><span id="loginPasswordError"></span><br>

                <br>
                <div id="buttonSection" style="text-align: center; width: 100%;"> <button id="loginButton"> <span> Log in </span> </button> </div>
                <div id="loginResult" style="color: red; text-align: center; width: 100%;"> 
                    <%
                        if (request.getAttribute("error") != null) {
                            out.println(request.getAttribute("error"));
                        }

                    %>  

                </div> 
            </form>
        </div>
    </div>
    <%@ include file="../layout/footer.jsp" %>
</body>
</html>