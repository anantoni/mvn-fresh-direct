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
                <div class=" col-md-9 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td>Username:</td>
                        <td><c:out value="${userProfile.username}"/></td>
                      </tr>
                      <tr>
                        <td>Firstname:</td>
                        <td><c:out value="${userProfile.firstname}"/></td>
                      </tr>
                      <tr>
                        <td>Lastname:</td>
                        <td><c:out value="${userProfile.lastname}"/></td>
                      </tr>
                      <tr>
                        <td>Email:</td>
                        <td><c:out value="${userProfile.email}"/></td>
                      </tr>
                      <tr>
                        <td>Home Address:</td>
                        <td><c:out value="${userProfile.street}"/> <c:out value="${userProfile.streetNumber}"/></td>
                      </tr>
                      <tr>
                        <td>Post Code:</td>
                        <td><c:out value="${userProfile.postCode}"/></td>
                      </tr>
                    </tbody>
                  </table>
                  <a href="shopping_cart.html" class="btn btn-primary">My Shopping Cart</a>
                </div>
                <div class=" col-md-9 col-lg-9" style="margin-top:10px;">
                    <table class="table table-user-information">
                        <tbody>    
                        <c:forEach var="product" items="${userProfile.salesHistory}">
                        <tr>
                            <td><fmt:formatDate type="date" value="${product.date}"/></td>
                            <td><c:out value="${product.name}"/> </td>
                            <td><c:out value="${product.orderQuantity}"/></td>
                        </tr>
                        </c:forEach>
                        </tbody>    
                    </table>
                </div>
            </c:if>
        </div>
        
        <%@ include file="../layout/footer.jsp" %>        
    </body>
</html>