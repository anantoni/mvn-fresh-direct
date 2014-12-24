<%-- 
    Document   : statistics_and_awards_results
    Created on : Dec 22, 2014, 2:23:50 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <%@ include file="../layout/header.jsp" %>
        <div class="container-fluid" align="center">
            
        <c:if test="${productList != null}">
            <legend><h1>Most Popular Products</h1></legend>
            <c:forEach var="product" items="${productList}">
            <div style="border: 2px solid; border-radius: 25px; padding: 10px" class="col-md-2">    
                <c:out value="${product.name}"/> <br>
                <c:out value="${product.listPrice}"/>&euro; <br>
            </div>
            </c:forEach>
        </c:if>
        <c:if test="${supplierList != null}">        
            <legend><h1>Most Popular Suppliers</h1></legend>
            <div class="col-md-6 col-lg-6 center-block" style="margin-top:10px;">
                <table class="table table-user-information">
                    <tbody> 
                        <thead>
                            <tr><td>Supplier Name</td><td>Total Amount Supplied</td></tr>
                        </thead>
                        <c:forEach var="supplier" items="${supplierList}">
                            <tr>
                                <td><c:out value="${supplier.name}"/></td>
                                <td><c:out value="${supplier.totalAmountSupplied}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>    
                </table>
            </div>      
        </c:if>
        <c:if test="${postCodeList != null}">
            <legend><h1>Most Popular Post Codes</h1></legend>
            <div class="col-md-6 col-lg-6 center-block" style="margin-top:10px;">
                <table class="table table-user-information">
                    <tbody>
                        <thead>
                        <tr><td>Post Code</td></tr>
                        </thead>
                        <c:forEach var="postCode" items="${postCodeList}">
                            <tr>
                                <td><c:out value="${postCode}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>    
                </table>
            </div>      
        </c:if>
        <c:if test="${clientList != null}">        
            <legend><h1>Top Clients</h1></legend>
            <div class="col-md-6 col-lg-6 center-block" style="margin-top:10px;">
                <table class="table table-user-information">
                    <tbody>
                    <thead>
                    <tr><td>Client ID</td><td>Total Amount Spent</td></tr>
                    </thead>
                    <c:forEach var="client" items="${clientList}">
                        <tr>
                            <td><c:out value="${client.userID}"/></td>
                            <td><c:out value="${client.totalAmountSpent}"/>&euro;</td>
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
