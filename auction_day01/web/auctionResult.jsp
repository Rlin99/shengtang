<%@ page import="java.util.List" %>
<%@ page import="club.banyuan.entity.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="club.banyuan.entity.Auction" %>
<%@ page import="club.banyuan.service.AuctionServiceDao" %>
<%@ page import="club.banyuan.service.Impl.AuctionServiceDaoImpl" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: edz
  Date: 2020/7/9
  Time: 3:10 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/common.css" rel="stylesheet" type="text/css" />
    <link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wrap">
    <!-- main begin-->
    <div class="sale">
        <h1 class="lf">拍卖结束的商品</h1>
        <div class="right rulse">当前用户是：<span class="blue strong"><a href="#" title="张三">张三</a></span></div>
        <div class="cl"></div>
    </div>
    <div class="items">
        <ul class="rows even strong">
            <li>名称</li>
            <li>开始时间</li>
            <li>结束时间</li>
            <li>起拍价</li>
            <li class="list-wd">成交价</li>
            <li class="borderno">买家</li>
        </ul>
        <%
            List<Product> productList = new ArrayList<>();
            productList = (ArrayList)session.getAttribute("productListIsSelled");
            AuctionServiceDao auctionServiceDao = new AuctionServiceDaoImpl();
            for (Product product : productList) {
                Auction auction = new Auction();
                auction = auctionServiceDao.getProductHighPrice(product.getId());
        %>
        <ul class="rows">
            <li><a href="国书" title=""><%=product.getName()%></a></li>
            <li><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(product.getStartTime())%></li>
            <li><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(product.getFinishTime())%></li>
            <li><%=product.getStartPrice()%></li>
            <li class="list-wd"><%=auction.getPrice()%></li>
            <li class="borderno red"><a href="#"><%=auction.getUesrName()%></a></li>
        </ul>
        <%
            }
        %>

    </div>
    <h1>拍卖中的商品</h1>
    <div class="items records">
        <ul class="rows even strong rowh">
            <li>名称</li>
            <li>开始时间</li>
            <li>结束时间</li>
            <li>起拍价</li>
            <li class="borderno record">出价记录</li>
            <div class="cl"></div>
        </ul>

        <%
            List<Product> productList1 = new ArrayList<>();
            productList1 = (ArrayList)session.getAttribute("productListNotSelled");
            for (Product product : productList1) {
                List<Auction> auctionList = new ArrayList<>();
                auctionList = auctionServiceDao.getByProductId(product.getId());
        %>
        <ul class="rows">
            <li><a href="国书" title=""><%=product.getName()%></a></li>
            <li><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(product.getStartTime())%></li>
            <li><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(product.getFinishTime())%></li>
            <li><%=product.getStartPrice()%></li>
            <li class="borderno blue record">
                <%
                    for (Auction auction : auctionList) {
                %>
                <p><%=auction.getUesrName()+" "+auction.getPrice()%>元</p>
                <%
                    }
                %>
            </li>
            <div class="cl"></div>
        </ul>
        <%
            }

        %>


    </div>
    <!-- main end-->
</div>
</body>
</html>
