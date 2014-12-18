<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<link href="<c:url value="/resources/css/layout.css" />" rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.1.min.js" />"></script>

</head>
<body>
    <div id="header">

    <div id="logo"> <a style="color: white; text-decoration: none;" href="index.html"> Online Grocery Store </a> </div>
    <c:if test="${sessionScope.username != null}">
        <c:out value="${sessionScope.username}"/> 
    </c:if>
    </div>

    <nav>
        <ul>
            <li><a href="index.html">Home</a></li>
            <c:if test="${sessionScope.username == null}">
            <li><a href="login.html">Login</a></li>
                <li><a href="register.html">Register</a></li>
            </c:if>
            <li><a href="search_products.html"> Search Products <span class="caret"></span></a></li>
            <li><a href="browse_categories.html"> Browse Categories <span class="caret"></span></a></li>
            <li><a href="queries.html"> Query the Store Database <span class="caret"></span></a></li>
                        
            <c:if test="${sessionScope.username != null}">
                <li><a href="shopping_cart.html"> Shopping Cart (
                    <c:if test="${sessionScope.shopping_cart != null}">
                        <c:out value="${sessionScope.shopping_cart.totalPrice}"/> 
                    </c:if>
                )<span class="caret"></span></a></li>
                <li><a href="new_product.html"> Register New Product <span class="caret"></span></a></li>
                <li><a href="arrival.html"> Register Products Arrival <span class="caret"></span></a></li>
                <li><a href="logout.html">Logout</a></li>
            </c:if>
        </ul>
    </nav>
