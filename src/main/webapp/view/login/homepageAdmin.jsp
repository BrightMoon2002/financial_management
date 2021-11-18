<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 17/11/2021
  Time: 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">

</head>
<style>
    .footer-basic {
        padding:40px 0;
        background-color:#ffffff;
        color:#4b4c4d;
    }

    .footer-basic ul {
        padding:0;
        list-style:none;
        text-align:center;
        font-size:18px;
        line-height:1.6;
        margin-bottom:0;
    }

    .footer-basic li {
        padding:0 10px;
    }

    .footer-basic ul a {
        color:inherit;
        text-decoration:none;
        opacity:0.8;
    }

    .footer-basic ul a:hover {
        opacity:1;
    }

    .footer-basic .social {
        text-align:center;
        padding-bottom:25px;
    }

    .footer-basic .social > a {
        font-size:24px;
        width:40px;
        height:40px;
        line-height:40px;
        display:inline-block;
        text-align:center;
        border-radius:50%;
        border:1px solid #ccc;
        margin:0 8px;
        color:inherit;
        opacity:0.75;
    }

    .footer-basic .social > a:hover {
        opacity:0.9;
    }

    .footer-basic .copyright {
        margin-top:15px;
        text-align:center;
        font-size:13px;
        color:#aaa;
        margin-bottom:0;
    }
</style>
<body>
<h1><a href="/spending?id=${account.id}">aaa</a></h1>
<table>
    <c:forEach items="${accountList}" var="a">
        <tr>
            <td>${a.username}</td>
        </tr>
    </c:forEach>
</table>
<a href="revenue?action=revenue&id=${accountLogin.id}">List All Revenues</a>
<a href="/spending">List All Spending</a>
<h2><a href="/login?action=showAccountList">Show Account List</a></h2>
<h1><a href="/login?action=logoutAccount">Logout</a></h1>
<hr>
</body>
<div class="footer-basic">
    <footer>
        <div class="social" >
            <a href="#" style="padding-top: 7px"><i class="icon ion-social-instagram"></i></a>
            <a href="#" style="padding-top: 7px"><i class="icon ion-social-snapchat"></i></a>
            <a href="#" style="padding-top: 7px"><i class="icon ion-social-twitter"></i></a>
            <a href="#" style="padding-top: 7px"><i class="icon ion-social-facebook"></i></a>
        </div>
        <ul class="list-inline">
            <li class="list-inline-item"><a href="#">Home</a></li>
            <li class="list-inline-item"><a href="#">Services</a></li>
            <li class="list-inline-item"><a href="#">About</a></li>
            <li class="list-inline-item"><a href="#">Terms</a></li>
            <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
        </ul>
        <p class="copyright">Company Financial Company © 2021</p>
    </footer>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
</html>
