<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 14/11/2021
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete Books</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

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
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
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
<fieldset style="width: 25%;">
    <lengend>loan DELETE</lengend>
    <p>DO YOU WANT TO DELETE THIS LOAN</p>
    <form method="post">
        <p>Name: ${loanDelete.getAccount().getName()}</p>
        <p>Amount: <fmt:formatNumber value = "${loanDelete.getAmount()}" type = "currency"/></p>

        <p>startOfLoan: ${loanDelete.getStartOfLoan()}</p>
        <p>EndOfLoan: ${loanDelete.getEndOfLoan()}</p>
        <p>Interest: ${loanDelete.getInterest().getName()}</p>
        <p>status: ${loanDelete.getStatus().getName()}</p>
        <button type="submit">Submit</button>
        <button type="button">
            <a href="/loans">Back</a>
        </button>

    </form>
</fieldset>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
