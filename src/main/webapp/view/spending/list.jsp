<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/16/2021
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List Spending</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <style>
        .content {
            background-color: #EDEEF2;
            width: 100%;
            height: 700px;
        }

    </style>
</head>
<body>
<div class="nav">
    <nav class="navbar navbar-expand-lg navbar-light bg-light w-100">
        <div class="container-fluid">
            <a class="navbar-brand" href="#"
               style="border-right: 1px solid black; padding-right: 1px; text-align: center">
                <img src="/view/images/logo.jpg" alt="microsoft icon" width="60%">
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page"
                           href="/login?action=editAccountUser&id=${accountLogging.id}">Edit Account Information</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/loans">Loan</a>
                    </li>
                    </li>
                </ul>
                <ul class="navbar-nav mb-2 mb-lg-0 navbar-right">
                    <li class="nav-item ">
                        <a class="nav-link" href="/login">
                            Logout
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-person-circle" viewBox="0 0 16 16">
                                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
                                <path fill-rule="evenodd"
                                      d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
                            </svg>
                        </a>
                    </li>
                </ul>

            </div>
        </div>
    </nav>
</div>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid" style="background-color: #004165">
        <a style="color: white" class="navbar-brand" href="/login?action=showUserPage">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a style="color: white" class="nav-link" href="">Contact</a>
                </li>
                <li class="nav-item">
                    <a style="color: white" class="nav-link" href="/spending?action=create&id=${account.id}">Add Spending</a>
                </li>
                <li class="nav-item dropdown">
                    <a style="color: white" class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Sort
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="#">Sort by name</a></li>
                        <li><a class="dropdown-item" href="/spending?action=sort&id=${account.id}">Sort By Amount
                            Spending</a></li>
                        <li><a class="dropdown-item" href="#">Sort By Date</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex" method="get" style="padding-top:10px">
                <input type="hidden" name="action" value="search">
                <input type="hidden" name="id" value="${account.id}">
                <input class="form-control me-2" type="text" name="date" placeholder="Date time" aria-label="Search">
                <button class="btn btn-outline-success" type="submit" style="color: white">Search</button>
            </form>
        </div>
    </div>
</nav>




<center>
    <h1>Spending Management</h1>


</center>
<br>
<br>
<c:if test="${role == 2}">
    <center>
        <div class="col">
            <h2 class="header-table"><a style="text-decoration: none" href="/spending">List Of Spending</a> </h2>
            <div>
                <center>
                    <div align="center">
                        <table style="width: 90%" border="1" cellpadding="5" class="table table-success table-striped table-hover ">
                            <tr>
                                <th>ID</th>
                                <th>Type</th>
                                <th>Amount</th>
                                <th>Date</th>
                                <th>Description</th>
                                <th>Actions</th>
                            </tr>
                            <c:forEach var="spending" items="${listSpending}">
                                <tr>
                                    <td><c:out value="${spending.id}"/></td>
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
                    <h3>Revenue Total: <fmt:formatNumber value = "${spendingTotalUser}" type = "currency"/></h3>
                </center>
            </div>
        </div>
    </center>
</c:if>


<c:if test="${role == 1}">
    <center>
        <div class="col">
            <h2 style="padding: 0" class="header-table"><a style="text-decoration: none" href="/spending">List Of User Spending</a></h2>
            <div>
                <center>
                    <div align="center">
                        <table style="width: 90%;" border="1" cellpadding="5" class="table table-success table-striped table-hover ">
                            <tr>
                                <th>ID</th>
                                <th>Type</th>
                                <th>Amount</th>
                                <th>Date</th>
                                <th>Description</th>
                                <th>Account ID</th>
                                <th>Actions</th>
                            </tr>
                            <c:forEach var="spending" items="${listSpendingUser}">
                                <tr>
                                    <td><c:out value="${spending.id}"/></td>
                                    <td><c:out value="${spending.type}"/></td>
                                    <td><fmt:formatNumber value = "${spending.amount}" type = "currency"/></td>
                                    <td><c:out value="${spending.date}"/></td>
                                    <td><c:out value="${spending.description}"/></td>
                                    <td><c:out value="${spending.getAccount().getId()}"/></td>
                                    <td>
                                        <a href="/spending?action=edit&id=${spending.id}">Edit</a>
                                        <a href="/spending?action=delete&id=${spending.id}">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                    <h3>sSpending Total: <fmt:formatNumber value="${spendingTotalUser}" type="currency"/></h3>
                </center>
            </div>

        </div>

        <br>
        <br>

        <div class="col">
            <h2 style="padding: 0" class="header-table"><a style="text-decoration: none" href="/spending">List Of Administrator Spending</a></h2>
            <center>
                <div align="center">
                    <table style="width: 90%;" border="1" cellpadding="5" class="table table-success table-striped table-hover ">
                        <tr>
                            <th>ID</th>
                            <th>Type</th>
                            <th>Amount</th>
                            <th>Date</th>
                            <th>Description</th>
                            <th>Account ID</th>
                            <th>Actions</th>
                        </tr>
                        <c:forEach var="spending" items="${listSpending}">
                            <tr>
                                <td><c:out value="${spending.id}"/></td>
                                <td><c:out value="${spending.type}"/></td>
                                <td><fmt:formatNumber value = "${spending.amount}" type = "currency"/></td>
                                <td><c:out value="${spending.date}"/></td>
                                <td><c:out value="${spending.description}"/></td>
                                <td><c:out value="${spending.getAccount().getId()}"/></td>
                                <td>
                                    <a href="/spending?action=edit&id=${spending.id}">Edit</a>
                                    <a href="/spending?action=delete&id=${spending.id}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <h3>Spending Total: <fmt:formatNumber value = "${spendingTotalAdmin}" type = "currency"/></h3>
            </center>
        </div>
    </center>
</c:if>


<center>
    <a style="align-items: center" class="btn btn-primary" href="spending?action=create" role="button">Add New Spending</a>
</center>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>

