<%-- 
    Document   : register
    Created on : 24 Ιουν 2011, 12:18:20 πμ
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <%@ include file="../layout/header.jsp" %>
    <div id="mainContainer" align="center">        

        <div id="registerPanel">

            <div id="panelHeader" align="center"> 
                <h2> Create Account </h2>
                <hr>
            </div>
            <div width="100%" align="center"> Already have an account? <a href="login.html">Log in</a></div>
            <form action="createAccountServlet" method="post">

            <label class="registerLabel" for="username"> Username: </label><input name="username" id="registerUsername" class="registerInput" autofocus="autofocus" autocomplete="off" maxlength="40" type="text"/><span id="registerUsernameError"></span><br>
            <label class="registerLabel" for="password"> Password: </label><input name="password" id="registerPassword" class="registerInput" autocomplete="off" maxlength="40" type="password"/><span id="registerPasswordError"></span><br>
            <label class="registerLabel" for="passwordconfirm"> Confirm Password: </label><input name="passwordconfirm" id="registerPasswordConfirm" autocomplete="off" class="registerInput" maxlength="40" type="password"/><span id="registerPasswordConfirmError"></span><br>
            <label class="registerLabel" for="email"> E-mail: </label><input placeholder="e.g. your.email@domain.com" name="email" id="registerEmail" class="registerInput" autocomplete="off" maxlength="40" type="text"/><span id="registerEmailError"></span><br>
            <label class="registerLabel" for="firstname"> Firstname: </label><input placeholder="e.g. Anastasios" name="firstname" id="registerFirstname" class="registerInput" autocomplete="off" maxlength="40" type="text"/><span id="registerFirstnameError"></span><br>
            <label class="registerLabel" for="lastname"> Lastname: </label><input placeholder="e.g. Antoniadis" name="lastname" id="registerLastname" class="registerInput" autocomplete="off" maxlength="40" type="text"/><span id="registerLastnameError"></span><br>
            <label class="registerLabel" for="town"> Town: </label><input placeholder="e.g. Kamatero" name="town" id="registerTown" class="registerInput" autocomplete="off" maxlength="40" type="text"/><span id="registerTownError"></span><br>
            <label class="registerLabel" for="street"> Street: </label><input placeholder="e.g. Paioniou" name="street" id="registerStreet" class="registerInput" autocomplete="off" maxlength="40" type="text"/><span id="registerStreetError"></span><br>
            <label class="registerLabel" for="streetnumber"> Street Number: </label><input placeholder="e.g. 6" name="streetnumber" id="registerStreetNumber" class="registerInput" autocomplete="off" maxlength="40" type="text"/><span id="registerStreetNumberError"></span><br>
            <label class="registerLabel" for="postcode"> Post Code: </label><input placeholder="e.g. 13451" name="postcode" id="registerPostCode" class="registerInput" autocomplete="off" maxlength="40" type="text"/><span id="registerPostCodeError"></span><br>
            <div id="buttonSection1" class="buttonSection" style="text-align: center; width: 100%;"> <button id="registerButton"  type="submit"> <span> Create Account </span> </button> </div>

            </form>
            <br>
            <div id="registrationResult" style="color:red; text-align: center; width: 100%;"> 
                <%
                    if (request.getParameter("error") != null) {
                        out.println(request.getParameter("error"));
                    }

                %>  
            </div> 
        </div>

    </div>
    <%@ include file="../layout/footer.jsp" %>
</body>
</html>