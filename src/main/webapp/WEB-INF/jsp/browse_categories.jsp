<%-- 
    Document   : browse_categories
    Created on : Dec 15, 2014, 12:59:27 PM
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Browse Categories</title>
        
        <%@ include file="../layout/header.jsp" %>
        <div class="container-fluid" align="center">
            <form class="form-horizontal" action="BrowseCategoriesServlet" method="get" role="form">
            <fieldset>

            <!-- Form Name -->
            <legend>Form Name</legend>
            <div class="form-group">
                <label for="product_category" class="col-md-4 control-label"> Product Category: </label>
                <div class="col-md-4">
                    <select name="product_category" class="form-control">
                        <option value="V" checked>Vegetable and Fruits</option>
                        <option value="M">Meat and Dairy Products</option>
                        <option value="C">Chemical Products</option>
                        <option value="I">Industrial Products</option>
                        <option value="B">Boxed/Canned Products</option>
                        <option value="G">Garden Products</option>
                        <option value="H">Household Products</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <button type="submit" value="Submit" class="btn btn-success">Browse</button>
                </div>
            </div>
            </fieldset>
            </form>
        </div>
        <%@ include file="../layout/footer.jsp" %>
    </body>
</html>
