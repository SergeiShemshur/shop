<%@ page import="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Shop</title>
    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.css" rel="stylesheet">
<style>
    .shadow {
        background: beige; /* Цвет фона */
        box-shadow: 0 0 10px rgba(0,0,0,0.5); /* Параметры тени */
        padding: 10px;
    }
</style>
</head>
<body style="background-image: url(http://cdn.fabulousblogging.com/wp-content/uploads/2012/10/fabric_plaid2.jpg?bcbabf)">
<jsp:include page="navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-xs-3 col-sm-3 col-md-2 col-lg-3 sidebar">
            <ul class="nav nav-sidebar">
                <c:forEach items="${categories}" var="category">
                    <li><a href="/category/${category.id}"><c:out value="${category.title}"/></a></li>
                </c:forEach>
            </ul>
        </div>

        <div  class="col-xs-9 col-sm-9 col-md-10 col-lg-9 ">
            <c:forEach items="${categories}" var="category">
                <div class="col-lg-3 col-md-3 col-sm-4 col-xs-6 ">
                    <a href="/category/${category.id}">
                    <div class="thumbnail shadow">
                        <img src="http://www.slideteam.net/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/p/r/product_word_with_target_dart_and_arrow_showing_business_and_marketing_target_stock_photo_Slide01.jpg" class="img-rounded">
                        <div class="text-center"><c:out value="${category.title}"/></div>
                    </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<spring:url value="/resources/core/js/hello.js" var="coreJs"/>
<spring:url value="/resources/core/js/jquery-2.1.4.min.js" var="jQuery"/>
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${jQuery}"></script>
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>


