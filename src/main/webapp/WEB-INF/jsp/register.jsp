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
        
        <div class="container-fluid" align="center">
            <form class="form-horizontal" action="createAccountServlet" method="post">
            <fieldset>

            <!-- Form Name -->
            <legend>Create Account</legend>
            
            <div class="form-group">
                Already have an account? <a href="login.html">Log in</a>
            </div>
            <div class="form-group">
                <label for="username" class="col-md-4 control-label"> Username: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" name="username" autofocus="autofocus" autocomplete="off" maxlength="40" type="text"/>
                    <span class="help-block" id="registerUsernameError"><c:out value="${error}"/></span>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-md-4 control-label"> Password: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" name="password" autocomplete="off" maxlength="40" type="password"/>
                    <span class="help-block" id="registerPasswordError"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="passwordconfirm" class="col-md-4 control-label"> Confirm Password: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" name="passwordconfirm" autocomplete="off" maxlength="40" type="password"/>
                    <span class="help-block" id="registerPasswordConfirmError"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="email" class="col-md-4 control-label"> E-mail: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" placeholder="e.g. your.email@domain.com" name="email" autocomplete="off" maxlength="40" type="text"/>
                    <span class="help-block" id="registerEmailError"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="firstname" class="col-md-4 control-label"> Firstname: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" placeholder="e.g. Anastasios" name="firstname" autocomplete="off" maxlength="40" type="text"/>
                    <span class="help-block" id="registerFirstnameError"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="lastname" class="col-md-4 control-label"> Lastname: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" placeholder="e.g. Antoniadis" name="lastname" autocomplete="off" maxlength="40" type="text"/>
                    <span class="help-block" id="registerLastnameError"></span>
                </div>
            </div>            
            <div class="form-group">
                <label for="town" class="col-md-4 control-label"> Town: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" placeholder="e.g. Kamatero" name="town" autocomplete="off" maxlength="40" type="text"/>
                    <span class="help-block" id="registerTownError">
                </div>
            </div>
            <div class="form-group">
                <label for="street" class="col-md-4 control-label"> Street: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" placeholder="e.g. Paioniou" name="street" autocomplete="off" maxlength="40" type="text"/>
                    <span class="help-block" id="registerStreetError">
                </div>
            </div>
            <div class="form-group">
                <label for="streetnumber" class="col-md-4 control-label"> Street Number: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" placeholder="e.g. 6" name="streetnumber" autocomplete="off" maxlength="40" type="text"/>
                    <span class="help-block" id="registerStreetNumberError"></span>
                </div>
            </div>
            <div class="form-group">
                <label for="postcode" class="col-md-4 control-label"> Post Code: </label>
                <div class="col-md-4">
                    <input class="form-control input-md" placeholder="e.g. 13451" name="postcode" autocomplete="off" maxlength="40" type="text"/>
                    <span id="registerPostCodeError"></span>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" ></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-primary"> Create Account </button>
                </div>
            </div>
            </fieldset>
            </form>
            <%
                if (request.getParameter("error") != null) {
                    out.println(request.getParameter("error"));
                }

            %>  
        </div>
        <%@ include file="../layout/footer.jsp" %>
</body>
</html>