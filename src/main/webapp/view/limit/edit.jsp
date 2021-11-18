<%--
  Created by IntelliJ IDEA.
  User: HieuDao
  Date: 17-Nov-21
  Time: 12:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Limit Management Application</title>
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




<div align="center">
    <form method="post" action="limit" style="width: 50%;">
            <caption>
                <h2>Edit Limit</h2>
            </caption>
            <c:if test="${limit != null}">
                <input type="hidden" name="id" value="<c:out value='${limit.id}' />"/>
            </c:if>

        <div class="input-group mb-3">
            <span class="input-group-text" >Amount:</span>
            <input name="amount" id="amount"  type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="<fmt:formatNumber value = "${limit.amount}" type = 'currency'/>">
        </div>


        <button type="submit" class="btn btn-primary">Save</button>
        </form>
</div>

</body>
</html>