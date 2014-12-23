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
        <div class="container-fluid" align="center">
            <form  action="AuthenticateUserServlet" method="post" class="form-horizontal" role="form">
            <fieldset>

            <!-- Form Name -->
            <legend>Login</legend>    
                
            <div class="form-group">
              <label for="inputUsername" class="col-md-4 control-label">Username</label>
              <div class="col-md-4">
                <input type="text" class="form-control input-md" id="inputUsername" name="username" placeholder="Username">
                <span class="help-block"></span>
              </div>
            </div>
            <div class="form-group">
              <label for="inputPassword" class="col-md-4 control-label">Password</label>
              <div class="col-md-4">
                <input type="password" class="form-control input-md" id="inputPassword" name="password" placeholder="Password">
                <span class="help-block"></span>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-4 control-label"></label>
              <div class="col-md-4">
                <div class="checkbox">
                  <label>
                    <input type="checkbox"> Remember me
                  </label>
                </div>
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-4 control-label"></label>  
              <div class="col-md-4">
                <button type="submit" class="btn btn-success">Sign in</button>
              </div>
            </div>
          </form>
                <%
                    if (request.getAttribute("error") != null) {
                        out.println(request.getAttribute("error"));
                    }

                %>  
        </form>
        </div>
    <%@ include file="../layout/footer.jsp" %>
</body>
</html>