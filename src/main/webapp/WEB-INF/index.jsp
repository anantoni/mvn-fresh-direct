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

        <div id="mainContainer">

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