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
        <title>Create Account</title>
        <%@ include file="../layout/header.jsp" %>
    
        <div width="100%" align="center"> Already have an account? <a href="login.html">Log in</a></div>
        <form action="createAccountServlet" method="post">

        <label for="username"> Username: </label><input name="username" autofocus="autofocus" autocomplete="off" maxlength="40" type="text"/><span id="registerUsernameError"></span><br>
        <label for="password"> Password: </label><input name="password" autocomplete="off" maxlength="40" type="password"/><span id="registerPasswordError"></span><br>
        <label for="passwordconfirm"> Confirm Password: </label><input name="passwordconfirm" autocomplete="off" maxlength="40" type="password"/><span id="registerPasswordConfirmError"></span><br>
        <label for="email"> E-mail: </label><input placeholder="e.g. your.email@domain.com" name="email" autocomplete="off" maxlength="40" type="text"/><span id="registerEmailError"></span><br>
        <label for="firstname"> Firstname: </label><input placeholder="e.g. Anastasios" name="firstname" autocomplete="off" maxlength="40" type="text"/><span id="registerFirstnameError"></span><br>
        <label for="lastname"> Lastname: </label><input placeholder="e.g. Antoniadis" name="lastname" autocomplete="off" maxlength="40" type="text"/><span id="registerLastnameError"></span><br>
        <label for="town"> Town: </label><input placeholder="e.g. Kamatero" name="town" autocomplete="off" maxlength="40" type="text"/><span id="registerTownError"></span><br>
        <label for="street"> Street: </label><input placeholder="e.g. Paioniou" name="street" autocomplete="off" maxlength="40" type="text"/><span id="registerStreetError"></span><br>
        <label for="streetnumber"> Street Number: </label><input placeholder="e.g. 6" name="streetnumber" autocomplete="off" maxlength="40" type="text"/><span id="registerStreetNumberError"></span><br>
        <label for="postcode"> Post Code: </label><input placeholder="e.g. 13451" name="postcode" autocomplete="off" maxlength="40" type="text"/><span id="registerPostCodeError"></span><br>
        <input type="submit"> Create Account </input>

        </form>
        <br>
        <%
            if (request.getParameter("error") != null) {
                out.println(request.getParameter("error"));
            }

        %>  

        <%@ include file="../layout/footer.jsp" %>
</body>
</html>