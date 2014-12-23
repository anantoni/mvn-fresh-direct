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
        <div class="container-fluid" align="center">
        <c:if test="${sessionScope.userProfile!= null}">
            Name: <c:out value="${userProfile.username}"/><br>
            Email: <c:out value="${userProfile.email}" />
            
            
            <c:forEach var="product" items="${userProfile.salesHistory}">
                <fmt:formatDate type="date" value="${product.date}"/> <c:out value="${product.name}"/> <c:out value="${product.orderQuantity}"/><br>
            </c:forEach>
        </c:if>
        </div>
        
        <%@ include file="../layout/footer.jsp" %>        
    </body>
</html>