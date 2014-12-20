<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-2.1.1.min.js" />"></script>
    
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


    <a href="index.html"> Online Grocery Store </a>
    
  <nav class="navbar navbar-default" role="navigation">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="index.html">Home</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        
        <c:if test="${sessionScope.userProfile == null}">
            <li><a href="login.html">Login</a></li>
                <li><a href="register.html">Register</a></li>
        </c:if>
                
        <c:if test="${sessionScope.userProfile != null}">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> <c:out value="${sessionScope.userProfile.username}"/> <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="user_profile.html">Profile</a></li>
            <li><a href="new_product.html">Register New Product</a></li>
            <li><a href="arrival.html">Register Products Arrival</a></li>
            <li><a href="statistics_and_awards.html">Statistics & Awards</a></li>
            <li class="divider"></li>
            <li><a href="logout.html">Logout</a></li>
          </ul>
        </li>
        </c:if>
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"> Browse Store <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="search_products.html"> Search Products</a></li>
            <li><a href="browse_categories.html">Browse Categories</a></li>
            <li><a href="queries.html">Query the Store Database</a></li>
          </ul>
        </li>
        
        <c:if test="${sessionScope.userProfile != null}">
            <li><a href="shopping_cart.html"> Shopping Cart (
                <c:if test="${sessionScope.shoppingCart != null}">
                    <c:out value="${sessionScope.shoppingCart.totalCost}"/> 
                </c:if>
            )</a></li>
        </c:if>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
