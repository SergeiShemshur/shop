<%@ page import="java.util.List" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product</title>

    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.css" rel="stylesheet">
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
    <style>
        .clicked{
            background-color: gainsboro;
        }
        .shadow {
            background: white; /* Цвет фона */
            box-shadow: 0 0 10px rgba(0,0,0,0.5); /* Параметры тени */
            padding: 10px;
        }
    </style>
</head>
<body style="background-image: url(http://cdn.fabulousblogging.com/wp-content/uploads/2012/10/fabric_plaid2.jpg?bcbabf)">
<jsp:include page="navbar.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-sm-3 col-md-2 col-xs-3 col-lg-3 sidebar" style="height: 1100px">
            <ul class="nav nav-sidebar">
                <c:forEach items="${categories}" var="category">
                    <li class="${categoryId == category.id ? 'clicked':''}"><a href="/category/${category.id}"><c:out value="${category.title}"/></a></li>
                </c:forEach>
            </ul>
        </div>
        <c:forEach items="${products}" var="product">
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6 ">
                <div class="thumbnail shadow" style="background-image: url(http://cdn.fabulousblogging.com/wp-content/uploads/2012/10/neutral.png?bcbabf)">
                    <img src="http://www.privaledge.net/pricing-strategy/wp-content/uploads/2013/09/13986273-new-product.jpg" class="img-thumbnail">
                    <h1 class="text-center"> ${product.modelName}</h1>
                    <p >
                    <form class="text-center" style="display: inline;" method="get" action="/addProductToShoppingCart/">
                         <p style="display: inline">Price = ${product.price.setScale(2,2)}</p>
                        <input hidden name="id" value="${product.id}">
                        <input  type="number" style="width: 60px;" required min="1" name="amount">
                        <button type="submit">Buy</button>
                    </form>
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<spring:url value="/resources/core/js/hello.js" var="coreJs"/>
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
<spring:url value="/resources/core/js/bootstrap.min.js" var="jQuery"/>


<script src="${jQuery}"></script>
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>


</body>
</html>


