<%-- 
    Document   : index
    Created on : 23 Ιουν 2011, 11:49:50 μμ
    Author     : Lelouch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Grocery Store</title>
        <%@ include file="../layout/header.jsp" %>
        <div class="container-fluid" align="center">
            <legend><h1>Welcome to Fresh Direct</h1></legend>
            <h2>MDE515 - Fall 2014</h2>
            <h3>Implementation by Anastasios Antoniadis - M1381</h3>
            
            <p>This site was implemented using the Spring Framework, Twitter Bootstrap<br> 
                and JSP/Java Servlets using the JSTL library. The project management <br>
                and comprehension tool used for this project is Apache Maven</p>
            <div id="error" style="color:red; text-align: center; width: 100%;"> 
                <%  if (request.getAttribute("error") != null) {
                        out.println(request.getAttribute("error"));
                    }
                %>
            </div>
        </div>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>