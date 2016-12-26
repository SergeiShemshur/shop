<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>


        .shopping-cart {
            width: 100%;
        }

        .shopping-cart td, .shopping-cart th {
            border: 1px solid #ccc;
            padding: 20px;
        }

        .product-image img {
            width: 100px;
        }

        .product-quantity input {
            width: 45px;
        }

        th:empty, td:empty {
            border: 0 !important;
        }


    </style>
    <spring:url value="/resources/core/css/hello.css" var="coreCss"/>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.css" rel="stylesheet">
    <link href="${bootstrapCss}" rel="stylesheet"/>
    <link href="${coreCss}" rel="stylesheet"/>
</head>
<body style="background-image: url(http://cdn.fabulousblogging.com/wp-content/uploads/2012/10/fabric_plaid2.jpg?bcbabf)">
<jsp:include page="navbar.jsp"/>

<%--<div class="container">
    <div class="row">
        <div class="col-lg-1"></div>
        <div class="col-lg-8">
            <h1 class="text-center">Shopping Cart</h1>
            <div >
                <table class='shopping-cart table' >
                    <tr>
                        <th></th>
                        <th>Product</th>
                        <th>Price Per</th>
                        <th>Amount</th>
                        <th>Totals</th>
                    </tr>

                    <c:forEach items="${orderItems}" var="item">
                       <tr>
                           <td class='product-removal'>
                               <button class='remove-product'>
                                   <a href="/removeProductFromShoppingCart?id=${item.product.id}&amount=${item.amount}">&times; </a>
                               </button>
                           </td>
                           <td>${item.product.modelName}</td>
                           <td>${item.product.price.setScale(2,2)}</td>
                           <td><a href="/productAmountMinus?id=${item.product.id}&amount=${item.amount}">
                               <i class="fa fa-minus"></i></a>  ${item.amount}
                               <a href="/productAmountPlus?id=${item.product.id}&amount=${item.amount}"> <i class="fa fa-plus"></i></a></td>
                           <td class = "price">${item.purchasePrice.setScale(2,2)}</td>

                       </tr>
                    </c:forEach>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>Total price </td>
                        <td id ="total"> ${total}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-lg-3"></div>
    </div>
</div>--%>
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-10 col-md-offset-1">
            <c:choose>
                <c:when test="${sessionScope.products == null or sessionScope.products.size() == 0}">
              <div class="container">
                  <div class="row">
                      <div class="col-md-4 col-lg-4 col-xs -4"></div>
                      <div class="col-md-4 col-lg-4 col-xs -4">
                          <p style="font-size: 30px" class="text-center">
                              <i class="fa fa-shopping-cart fa-4x"></i> is empty.
                      </p>
                          <a href="/">
                          <button type="button" class="btn btn-success btn-lg btn-block">Go shopping</button>
                          </a>
                      </div>
                      <div class="col-md-4 col-lg-4 col-xs -4"></div>
                  </div>
              </div>
                </c:when>
                <c:otherwise>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>Product</th>
                            <th>Amount</th>
                            <th class="text-center">Price</th>
                            <th class="text-center">Total</th>
                            <th> </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orderItems}" var="item">
                            <tr>
                                <td class="col-sm-8 col-md-6">
                                    <div class="media">
                                        <a class="thumbnail pull-left" href="#">
                                            <img class="media-object"
                                                 src="http://icons.iconarchive.com/icons/custom-icon-design/flatastic-2/72/product-icon.png"
                                                 style="width: 72px; height: 72px;">
                                        </a>
                                        <div class="media-body">
                                            <h4 class="media-heading" style="padding-left: 17px;">${item.product.modelName}</h4>
                                        </div>
                                    </div>
                                </td>
                                <td><a href="/productAmountMinus?id=${item.product.id}&amount=${item.amount}">
                                    <i class="fa fa-minus"></i></a> ${item.amount}
                                    <a href="/productAmountPlus?id=${item.product.id}&amount=${item.amount}">
                                        <i class="fa fa-plus"></i></a>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center"><strong>${item.product.price.setScale(2,2)}</strong>
                                </td>
                                <td class="col-sm-1 col-md-1 text-center"><strong
                                        class="price">${item.purchasePrice.setScale(2,2)}</strong></td>
                                <td class="col-sm-1 col-md-1">
                                    <a href="/removeProductFromShoppingCart?id=${item.product.id}&amount=${item.amount}">
                                        <button type="button" class="btn btn-danger">
                                            <span class="glyphicon glyphicon-remove"></span> Remove
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td><h3>Total</h3></td>
                            <td class="text-right"><h3><strong id="total"></strong></h3></td>
                        </tr>
                        <tr>
                            <td>  </td>
                            <td>  </td>
                            <td>  </td>
                            <td>
                                <button type="button" class="btn btn-default">
                                    <a href="/">

                                        <span class="glyphicon glyphicon-shopping-cart"></span> Continue Shopping
                                    </a>
                                </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal"
                                        data-dismiss="modal">
                                    Checkout <span class="glyphicon glyphicon-play"></span>

                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Confirm the order</h4>
                                </div>
                                <div class="modal-body">
                                    <form action="/buy" method="post">

                                        <div class="panel panel-default">
                                            <div class="panel-body">
                                                <p class="text-center" style="    font-size: 20px; color: green;">Please enter personal information</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="exampleInputName">Name</label>
                                            <input min="1" required type="text" name="name" class="form-control" id="exampleInputName" placeholder="Enter name">
                                        </div>
                                        <div class="form-group">
                                            <label for="inputPhone">Phone</label>
                                            <input min="5" required type="text" name="phone" class="form-control" id="inputPhone" placeholder="Enter phone number">
                                        </div>
                                        <button type="submit" class="btn btn-default left">Confirm</button>
                                    </form>

                                </div>
                                <div class="modal-footer">
                                    <div class="btn-group btn-group-justified" role="group" aria-label="group button">
                                        <div class="btn-group" role="group">
                                            <button type="button" class="btn btn-default" data-dismiss="modal" role="button">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>




<script>
    var total = 0;
    var price = document.getElementsByClassName("price");
    for (var i = 0; i < price.length; i++) {
        total += parseFloat(price[i].innerText);
    }
    document.getElementById("total").innerText = '$' + total;
</script>

<spring:url value="/resources/core/js/hello.js" var="coreJs"/>
<spring:url value="/resources/core/js/jquery-2.1.4.min.js" var="jQuery"/>
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${jQuery}"></script>
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
</body>
</html>

<%--
<c:choose>
    <c:when test="${not empty msg}">
        <p> not empty </p>
    </c:when>
    <c:otherwise>
        <p>empty</p>
    </c:otherwise>
</c:choose>--%>
