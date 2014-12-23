<%-- 
    Document   : queries
    Created on : Dec 18, 2014, 1:57:58 AM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Query the Store Database</title>
        <%@ include file="../layout/header.jsp" %>
     <div class="container-fluid" align="center">    
        <form action="OrderSumMinMaxServlet" method="post" class="form-horizontal" role="form">
            <fieldset>
                <legend>Query the Store Database</legend>    
            <div class="form-group">
                <label class="col-md-4 control-label">Query for the highest and lowest order sums per product</label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Query</button> 
                </div>
            </div>
            </fieldset>
        </form>
        <br>
        
        <form action="GreaterThan10KServlet" method="get" class="form-horizontal" role="form">
            <fieldset>
            <div class="form-group">
                <label for="inputDate1" class="col-md-4 control-label">Query for the days on which the value of all orders was more than 10,000&euro; in</label>
                <div class="col-md-4">
                    <input type="text" type="text" value="12/2014" class="form-control datepicker input-md" name="date" id="inputDate1">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Query</button>  
                </div>
            </div>
            </fieldset>
        </form>
        <br>
        <form action="BestSellingProductServlet" method="post" class="form-horizontal" role="form">
            <fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label">Query for the best selling product in terms of units sold</label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Query</button>
                </div>
            </div>
            </fieldset>
        </form>
        <br>
        <form action="MostExpensiveProductsPerGroupServlet" method="post" class="form-horizontal" role="form">
            <fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label">Query for the most expensive product per group</label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Query</button>
                </div>
            </div>
            </fieldset>
        </form>
        <br>
        <form action="NeverOrderedProductsServlet" method="post" class="form-horizontal" role="form">
            <fieldset>
            <div class="form-group">
                <label class="col-md-4 control-label">Query for the products never ordered</label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Query</button>
                </div>
            </div>
            </fieldset>
        </form>
        <br>
        <form action="ProductsNotOrderedInMonthOfYearServlet" method="get" class="form-horizontal" role="form">
            <fieldset>
            <div class="form-group">
                <label for="inputDate2" class="col-md-4 control-label">Query for the products not ordered in</label>
                <div class="col-md-4">
                    <input type="text" type="text"  value="12/2014" class="form-control datepicker" name="date" id="inputDate2">
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Query</button>
                </div>
            </div>
            </fieldset>
        </form>
        <br>
        <c:if test="${sessionScope.userProfile != null}">
        <form action="DegreeOfSeparationBetweenTwoSuppliersServlet" method="get" class="form-horizontal" role="form">
            <fieldset>
            <div class="form-group">
                    <label class="col-md-4 control-label" for="inputSupplier">Query the degree of separation between supplier #1 </label>
                    <div class="col-md-4">
                        <select type="text" class="form-control" id="inputSupplier1" name="supplier1" placeholder="Supplier #1">
                        <c:forEach var="supplier" items="${sessionScope.userProfile.supplierList}">
                            <option value="<c:out value="${supplier.ID}"/>"><c:out value="${supplier.name}"/></option>
                        </c:forEach>
                        </select>
                    </div>
            </div>
            <div class="form-group">
                    <label class="col-md-4 control-label" for="inputSupplier"> and supplier #2 </label>
                    <div class="col-md-4">
                        <select type="text" class="form-control" id="inputSupplier2" name="supplier2" placeholder="Supplier #2">
                        <c:forEach var="supplier" items="${sessionScope.userProfile.supplierList}">
                            <option value="<c:out value="${supplier.ID}"/>"><c:out value="${supplier.name}"/></option>
                        </c:forEach>
                        </select>
                    </div>
               
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success">Query</button>
                </div>
            </div>
            </fieldset>
        </form>
         </c:if>
     </div>
    <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
