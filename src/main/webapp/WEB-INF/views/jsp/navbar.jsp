<%@ page import="java.util.List" %>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Flower Shop</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li></li>
            <li></li>
            <li style="">
                <a href="/shoppingCart" >
                <p style="padding: 4px;color: green;border: 1px green solid"><i
                      class="fa fa-shopping-cart fa-2x"></i>
                    <%
                    Object products = session.getAttribute("products");
                    if (products == null)
                        out.print(" 0 ");
                    else {
                        List sell = (List) products;
                        out.print(" " + sell.size());
                    }
                %>
                </p>
            </a>
            </li>
        </ul>
    </div>
</nav>
