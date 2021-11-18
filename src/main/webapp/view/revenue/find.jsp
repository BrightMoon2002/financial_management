<%--
  Created by IntelliJ IDEA.
  User: HieuDao
  Date: 16-Nov-21
  Time: 11:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Revenue Management Application</title>
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
            <form class="d-flex" method="post" action="/revenue?action=findById" style="padding-top:10px">
                <input class="form-control me-2" type="text" name="id" id="id" placeholder="Revenue ID" aria-label="Search">
                <button class="btn btn-outline-success" type="submit" style="color: white">Find</button>
            </form>

        </div>
    </div>
</nav>




<center>
    <h1>Revenue Management</h1>

    <c:if test="${requestScope['message'] != null}">
        <p>${requestScope['message']}</p>
    </c:if>

    <c:if test="${requestScope['result'] == true}">
        <center>
            <div class="col">
                <h2 class="header-table"><a style="text-decoration: none" >Finding Result</a> </h2>
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
                            </table>
                        </div>
                    </center>
                </div>
            </div>
        </center>
    </c:if>
</center>
</body>
</html>

