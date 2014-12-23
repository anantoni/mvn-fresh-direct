<%-- 
    Document   : statistics_and_awards
    Created on : Dec 22, 2014, 12:31:28 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Statistics and Awards</title>
    <%@ include file="../layout/header.jsp" %>
    
    <div class="container-fluid" align="center">
            <legend><h1>Statistics and Awards</h1></legend>
    <form action="StatisticsAndAwardsServlet" method="get" class="form-inline" role="form">
        <label for="popularProductsLimit" class="col-sm-4 control-label">Query for the most popular products this week:</label>
        <select type="text" class="form-control" id="popularProductsLimit" name="popularProductsLimit">
            <c:forEach begin="1" end="100" var="val">
                <option value="<c:out value="${val}"/>"><c:out value="${val}"/></option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-success">Query</button> 
    </form>
        
    <form action="StatisticsAndAwardsServlet" method="get" class="form-inline" role="form">
        <label for="popularSuppliersLimit" class="col-md-4 control-label">Query for the most popular suppliers:</label> 
        <select type="text" class="form-control" id="popularSuppliersLimit" name="popularSuppliersLimit">
            <c:forEach begin="1" end="100" var="val">
                <option value="<c:out value="${val}"/>"><c:out value="${val}"/></option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-success">Query</button> 
    </form>
    
    <form action="StatisticsAndAwardsServlet" method="get" class="form-inline" role="form">
        <label for="popularPostCodesLimit" class="col-md-4 control-label">Query for the most popular post codes:</label>
        <select type="text" class="form-control" id="popularPostCodesLimit" name="popularPostCodesLimit">
            <c:forEach begin="1" end="100" var="val">
                <option value="<c:out value="${val}"/>"><c:out value="${val}"/></option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-success">Query</button>
    </form>

    <form action="StatisticsAndAwardsServlet" method="get" class="form-inline" role="form">
        <label for="topClientsLimit" class="col-md-4 control-label">Query for the top clients:</label>
        <select type="text" class="form-control" id="topClientsLimit" name="topClientsLimit">
            <c:forEach begin="1" end="100" var="val">
                <option value="<c:out value="${val}"/>"><c:out value="${val}"/></option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-success">Query</button>
    </form>

    </div>
    <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
