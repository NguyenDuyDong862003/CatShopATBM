<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #343a40;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .error-container {
            text-align: center;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        .error-code {
            font-size: 100px;
            font-weight: bold;
            color: #dc3545;
            margin: 0;
        }
        .error-message {
            font-size: 20px;
            margin: 10px 0 20px;
        }
        .error-details {
            font-size: 14px;
            color: #6c757d;
            margin-bottom: 20px;
        }
        .error-actions a {
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            padding: 10px 20px;
            border-radius: 5px;
            font-size: 16px;
            margin: 0 10px;
        }
        .error-actions a:hover {
            background-color: #0056b3;
        }
        .dropdown {
            position: relative;
        }

        .dropdown-toggle {
            white-space: nowrap;
        }

        .dropdown-toggle::after {
            display: inline-block;
            margin-left: 0.255em;
            vertical-align: 0.255em;
            content: "";
            border-top: 0.3em solid;
            border-right: 0.3em solid transparent;
            border-bottom: 0;
            border-left: 0.3em solid transparent;
        }

        .dropdown-toggle:empty::after {
            margin-left: 0;
        }

        .dropdown-menu {
            position: absolute;
            top: 100%;
            left: 0;
            z-index: 1000;
            display: none;
            min-width: 12rem;
            padding: 0.5rem 0;
            margin: 0.125rem 0 0;
            font-size: 0.875rem;
            color: #293240;
            text-align: left;
            list-style: none;
            background-color: #00BFFF;
            background-clip: padding-box;
            border: 0 solid rgba(0, 0, 0, 0.15);
            border-radius: 4px;
        }

        .dropdown-menu-left {
            right: auto;
            left: 0;
        }

        .dropdown-item {
            display: block;
            width: 100%;
            padding: 0.65rem 1.5rem;
            clear: both;
            font-weight: 400;
            color: #111;
            text-align: inherit;
            white-space: nowrap;
            background-color: transparent;
            border: 0;
        }

        .dropdown-item:hover, .dropdown-item:focus {
            color: #00BFFF;
            text-decoration: none;
            background-color: #e3e1fc;
        }

        .dropdown-item.active, .dropdown-item:active {
            color: #00BFFF;
            text-decoration: none;
            background-color: #e3e1fc;
        }

        .dropdown-item.disabled, .dropdown-item:disabled {
            color: #6c757d;
            pointer-events: none;
            background-color: transparent;
        }

        .dropdown-menu.show {
            display: block;
        }

        .dropdown-header {
            display: block;
            padding: 0.5rem 1.5rem;
            margin-bottom: 0;
            font-size: 0.76563rem;
            color: #6c757d;
            white-space: nowrap;
        }

        .dropdown-item-text {
            display: block;
            padding: 0.65rem 1.5rem;
            color: #293240;
        }

        .dropdown-toggle.arrow-none:after {
            display: none;
        }

        .pagination {
            display: flex;
            justify-content: center;
        }
        .pagination ul{
            display: flex;
            flex-wrap: wrap;
            background: #00BFFF;
            padding: 8px;
            border-radius: 50px;
        }
        .pagination ul li{
            color: #fff;
            list-style: none;
            line-height: 45px;
            text-align: center;
            font-size: 18px;
            font-weight: 500;
            cursor: pointer;
            user-select: none;
            transition: all 0.3s ease;
        }
        .pagination ul li.numb{
            list-style: none;
            height: 45px;
            width: 45px;
            margin: 0 3px;
            line-height: 45px;
            border-radius: 50%;
        }
        .pagination ul li.numb.first{
            margin: 0px 3px 0 -5px;
        }
        .pagination ul li.numb.last{
            margin: 0px -5px 0 3px;
        }
        .pagination ul li.dots{
            font-size: 22px;
            cursor: default;
        }
        .pagination ul li.btn{
            padding: 0 20px;
            border-radius: 50px;
        }
        .pagination li.active,
        .pagination ul li.numb:hover,
        .pagination ul li:first-child:hover,
        .pagination ul li:last-child:hover{
            color: #00BFFF;
            background: #fff;
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="error-code">
        <%= request.getAttribute("javax.servlet.error.status_code") != null ? request.getAttribute("javax.servlet.error.status_code") : "Error" %>
    </div>
    <div class="error-message">
        Oops! Something went wrong.
    </div>
    <div class="error-details">
    </div>
    <div class="error-actions">
        <a href="index.jsp">Go to Home</a>
        <a href="Contact.jsp">Contact Support</a>
    </div>
</div>
<div class="col-lg-9 col-md-7">
    <%--     <div class="product__discount">
             <div class="section-title product__discount__title">
                 <h2>Giảm Giá</h2>
             </div>
             <div class="row">
                 <div class="product__discount__slider owl-carousel">
                     <%
                         List<Product> listSale = ProductService.getInstance().listProductSale();
                         for (Product pd : listSale) {
                     %>
                     <div class="col-lg-4">
                         <div class="product__discount__item">
                             <div class="product__discount__item__pic set-bg"
                                  data-setbg="<%=pd.getImage()%>">
                                 <div class="product__discount__percent"><%=pd.getPromotionalPrice()%>%</div>
                                 <ul class="product__item__pic__hover">
                                     <%
                                         if (user != null) {
                                             Product product = new ProductDAO().getProductDetail(pd.getProductId());
                                     %>
                                     <%if (Integer.parseInt(product.getQuantity()) > 0) {%>
                                     <li><a class="add-wishlist" id="addWishlist-<%=pd.getProductId()%>"><i
                                             class="fa fa-heart"></i></a></li>
                                     <li><a class="shopnow2" id="addCart-<%=pd.getProductId()%>"><i
                                             class="fa fa-shopping-cart"></i></a></li>
                                     <%}%>
                                     <%
                                     } else {%>
                                     <li><a class="add-wishlist" href="login.jsp"><i class="fa fa-heart"></i></a>
                                     </li>
                                     <li><a class="shopnow2" href="login.jsp"><i
                                             class="fa fa-shopping-cart"></i></a></li>
                                     <% }
                                     %>
                                 </ul>
                             </div>
                             <div class="product__discount__item__text">
                                 <h5>
                                     <a href="product-details.jsp?id=<%=pd.getProductId()%>"><%=pd.getProductName()%>
                                     </a></h5>
                                 <div class="product__item__price"><%=format.format(pd.getPrice() - (pd.getPrice() * pd.getPromotionalPrice() / 100))%>
                                     đ<span><%=pd.getPrice()%>đ</span></div>
                             </div>
                         </div>
                     </div>
                     <% }%>

                 </div>
             </div>
         </div>--%>
<%--        <div class="row">--%>
<%--            <!-- [ feather-icon ] start -->--%>
<%--            <div class="col-sm-12">--%>
<%--                <div class="card">--%>
<%--                    <div class="card-header">--%>
<%--                        <h5>Feather Icon</h5>--%>
<%--                        <p>Use svg icon with <code>&lt;i data-feather="&lt;&lt; Copyed code &gt;&gt;"&gt;</code> in you html code</p>--%>
<%--                    </div>--%>
<%--                    <div class="card-body text-center">--%>
<%--                        <div class="row justify-content-center">--%>
<%--                            <div class="col-sm-6">--%>
<%--                                <input type="text" id="icon-search" class="form-control mb-4" placeholder="search . . ">--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="i-main" id="icon-wrapper"></div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
</body>
</html>
