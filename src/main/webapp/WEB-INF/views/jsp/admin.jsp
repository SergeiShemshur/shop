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
    .btn-group {
        white-space: nowrap;
    }
    .btn-group .btn {
        float: none;
        display: inline-block;
    }
    .btn + .dropdown-toggle {
        margin-left: -4px;
    }

    @media screen and (max-width: 1400px) {
        .table-responsive {
            border: 1px solid #ddd;
            margin-bottom: 15px;
            overflow-x: auto;
            overflow-y: auto;
            width: 100%;
        }
        .table-responsive > .table {
            margin-bottom: 0;
        }
        .table-responsive > .table > thead > tr > th, .table-responsive > .table > tbody > tr > th, .table-responsive > .table > tfoot > tr > th, .table-responsive > .table > thead > tr > td, .table-responsive > .table > tbody > tr > td, .table-responsive > .table > tfoot > tr > td {
            white-space: nowrap;
        }

    }

    p.text-center.bg-info {
        font-size: 50px;
    }
</style>
</head>
<body style="background-image: url(http://cdn.fabulousblogging.com/wp-content/uploads/2012/10/fabric_plaid2.jpg?bcbabf)">
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Shop</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li></li>
            <li></li>
            <li><a href="/shoppingCart">
                <p style="padding-right: 100px;color: green;">
                </p>
            </a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <div class="row">
       <div class="col-md-2"></div>
       <div class="col-md-8">
           <div class="row">
               <div class="col-lg -12 col-md-12 col-sm-12">
                   <p class="text-center bg-info">Shopping orders</p>
               </div>
           </div>
           <div>
               <table class="table tablesorter" id="myTable">
                   <thead>
                   <tr>
                       <th>Id Order</th>
                       <th>Products</th>
                       <th>User name</th>
                       <th>User phone</th>
                       <th>Purchase time</th>
                       <th>Total</th>
                       <th>Status</th>
                   </tr>
                   </thead>
                   <tbody>
                    <c:forEach items="${orderDto}" var="entry">
                   <tr>
                       <td>${entry.key}</td>
                       <td><div class="btn-group">
                           <button type="button" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">
                               <span class="caret"></span>
                               <span class="sr-only">Toggle Dropdown</span>
                           </button>

                           <ul class="dropdown-menu" role="menu">
                               <li>
                                   <div>
                                       <table class="table">
                                           <thead>
                                           <tr>
                                               <th>Model</th>
                                               <th>Price</th>
                                               <th>Amount</th>
                                               <th>Purchase Price</th>
                                           </tr>
                                           </thead>
                                           <tbody>
                                           <c:forEach items="${entry.value.orderItems}" var="item">
                                         <tr>
                                             <td class="text-center">${item.product.modelName}</td>
                                             <td class="text-center">${item.product.price.setScale(2,2)}</td>
                                             <td class="text-center">${item.amount} </td>
                                             <td class="text-center">${item.purchasePrice.setScale(2,2)}</td>
                                         </tr>
                                         </c:forEach>
                                           </tbody>
                                       </table>

                                   </div>
                               </li>

                           </ul>
                       </div></td>
                       <td>${entry.value.user.name}</td>
                       <td>${entry.value.user.phone}</td>
                       <td>${entry.value.purchaseTime}</td>
                       <td>${entry.value.getTotalPrice().setScale(2,2)}</td>
                       <td> <p>${entry.value.status}
                       <c:if  test="${entry.value.status == 'new'}">
                           <a href="admin/setStatusDone?id=${entry.key}">
                           <button type="button" class="btn btn-success">done</button>
                           </a>
                       </c:if>
                       </td>
                   </tr>
                    </c:forEach>
                   </tbody>
               </table>
           </div>
       </div>
       <div class="col-md-2"></div>
    </div>
</div>
<spring:url value="/resources/core/js/hello.js" var="coreJs"/>
<spring:url value="/resources/core/js/jquery-2.1.4.min.js" var="jQuery"/>
<spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${jQuery}"></script>
<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.25.5/js/jquery.tablesorter.js"></script>
<script>
    $(document).ready(function()
            {
                $("#myTable").tablesorter();

                    $("#myTable").tablesorter( {sortList: [[0,0], [1,0]]} );

            }

    );
</script>
</body>
</html>


