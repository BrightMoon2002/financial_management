<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 16/11/2021
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quản Lý Tài Chính</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
.header-table {
    position: relative;
    padding: 0 10%;
    max-width: 640px;
    margin-left: auto;
    margin-right: auto;
    color: black;
    text-shadow: 1px 1px 4px rgba(0, 0, 0, 0.5);
    font-family: "Montserrat", sans-serif;
}

th {
    text-align: center;
}

td {
    text-align: center;
}


    </style>


</head>
<body>

<div class="nav">
    <nav class="navbar navbar-expand-lg navbar-light bg-light w-100">
        <div class="container-fluid">
            <!------Logo------->
            <a class="navbar-brand" href="#" style="border-right: 1px solid black; padding-right: 1px; text-align: center">
                <img src="view/images/logo.jpg" alt="logo" width="60%" >
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!--------Log Out----------->
            <ul class="navbar-nav mb-2 mb-lg-0 navbar-right">
                <li class="nav-item ">
                    <a class="nav-link" href="/login?action=logoutAccount">
                        Logout
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                        </svg>
                    </a>
                </li>
            </ul>
        </div>
    </nav>
</div>


<!-----Nav Bar----->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid" style="background-color: #004165">

        <!------Home-------->

        <a style="color: white" class="navbar-brand" href="/login?action=showUserPage">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>




        <div class="collapse navbar-collapse" >
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">


                <!----------Edit Account Information------------>
                <li class="nav-item">
                    <a style="color: white" class="nav-link active" aria-current="page" href="/login?action=editAccountUser&id=${accountLogging.id}">Edit Account Information</a>
                </li>


                <!----------Loan Management------------>
                <li class="nav-item">
                    <a style="color: white" class="nav-link active" aria-current="page" href="/loans">Loan Management</a>
                </li>
                </li>
            </ul>
        </div>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                <!--------Revenue Management---------->
                <li class="nav-item">
                    <a style="color: white" class="nav-link" href="/revenue">Revenue Management</a>
                </li>

                <!--------Spending Management---------->
                <li class="nav-item">
                    <a style="color: white" class="nav-link" href="/spending">Spending Management</a>
                </li>
            </ul>


            <!--------Search Management---------->

        </div>
    </div>
</nav>
<div class="container-fluid">
    <center>
        <div style="margin-top: 100px" class="row">
            <div class="col">
                <h2 class="header-table"><a style="text-decoration: none" href="/revenue">Revenue</a> </h2>
                <div>
                    <center>
                            <div align="center">
                                <table border="1" cellpadding="5" class="table table-success table-striped table-hover ">
                                    <tr>
                                        <th>ID</th>
                                        <th>Type</th>
                                        <th>Amount</th>
                                        <th>Date</th>
                                        <th>Description</th>
                                        <th>Actions</th>
                                    </tr>
                                    <c:forEach var="revenue" items="${listRevenue}">
                                        <tr>
                                            <td><c:out value="${revenue.id}"/></td>
                                            <td><c:out value="${revenue.type}"/></td>
                                            <td><fmt:formatNumber value = "${revenue.amount}" type = "currency"/></td>
                                            <td><c:out value="${revenue.date}"/></td>
                                            <td><c:out value="${revenue.description}"/></td>
                                            <td>
                                                <a href="/revenue?action=edit&id=${revenue.id}">Edit</a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </table>
                            </div>
                            <h3>Revenue Total: <fmt:formatNumber value = "${revenueTotalUser}" type = "currency"/></h3>
                        </center>
                </div>

            </div>
            <div class="col">
                <h2 class="header-table"><a style="text-decoration: none" href="/spending">Spending</a> </h2>
                <div>
                    <center>
                        <div align="center">
                            <table border="1" cellpadding="5" class="table table-success table-striped table-hover ">
                    <tr>
                        <th>ID</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="spending" items="${spendings}">
                        <tr>
                            <td><c:out value="${spending.id}"></c:out></td>
                            <td><c:out value="${spending.type}"/></td>
                            <td><fmt:formatNumber value = "${spending.amount}" type = "currency"/></td>
                            <td><c:out value="${spending.date}"/></td>
                            <td><c:out value="${spending.description}"/></td>
                            <td>
                                <a href="/spending?action=edit&id=${spending.id}">Edit</a>
                            </td>
                        </tr>
                    </c:forEach>
                            </table>
                        </div>
                        <h3>Spending Total: <fmt:formatNumber value = "${spendingTotalAmount}" type = "currency"/></h3>
                    </center>
                </div>
            </div>
            <div class="col">
                <h3 style="text-decoration: underline; font-style: italic; color: #6610f2">Account Balance: <fmt:formatNumber value = "${accountBalance}" type = "currency"/></h3>
                <div>
                    <h2 class="header-table"><a style="text-decoration: none" href="/spending">Friend List</a> </h2>
                    <table border="1" cellpadding="5" class="table table-success table-striped table-hover ">
                        <tr>
                            <th>Name</th>
                            <th>Username</th>
                        </tr>
                        <c:forEach var="friend" items="${friendList}">
                            <tr>
                                <td><c:out value="${friend.name}"/></td>
                                <td><c:out value="${friend.username}"/></td>
                                <td><a href="/spending?action=sendId&id=${friend.id}">Transfer</a></td>
                            </tr>
                        </c:forEach>

                    </table>
                </div>
            </div>


        </div>
    </center>

</div>


</body>
</html>