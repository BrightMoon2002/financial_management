<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 17/11/2021
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<html>
<head>
    <title>Create Account</title>
</head>
<style>
    @-webkit-keyframes my {
        0% { color: #0af822; }
        50% { color: #fff;  }
        100% { color: #0af822;  }
    }
    @-moz-keyframes my {
        0% { color: #0af822;  }
        50% { color: #fff;  }
        100% { color: #0af822;  }
    }
    @-o-keyframes my {
        0% { color: #0af822; }
        50% { color: #fff; }
        100% { color: #0af822;  }
    }
    @keyframes my {
        0% { color: #0af822;  }
        50% { color: #fff;  }
        100% { color: #0af822;  }
    }
    .test {
        background: #ffffff;
        font-size:24px;
        font-weight:bold;
        -webkit-animation: my 700ms infinite;
        -moz-animation: my 700ms infinite;
        -o-animation: my 700ms infinite;
        animation: my 700ms infinite;
    }
</style>
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
<center>
    <h1><a href="/login" style="border: none; color: #dc3545; text-decoration: none">LOGIN</a></h1>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5" class="table table-info table-hover table-striped">
            <tr>
                <td colspan="2" style="text-align: center"><h4>Create New Account</h4></td>
            </tr>
            <tr>
                <th>Username:</th>
                <p>
                    <c:if test="${message1!= null}">
                        <span>${message1}</span>
                    </c:if>
                </p>
                <td><input type="text" name="username" placeholder="Nhập tên đăng nhập" size="45"></td>
            </tr>
            <tr>
                <th>Password:</th>
                <td><input type="text" name="password" placeholder="Nhập mật khẩu" size="45"></td>
            </tr>
            <tr>
                <th>Name:</th>
                <td><input type="text" name="name" placeholder="Nhập tên" size="45"></td>
            </tr>
            <tr>
                <th>Date Of Birth:</th>
                <td><input type="date" name="dob" placeholder="Nhập năm sinh" size="45"></td>
            </tr>
            <tr>
                <th>Email:</th>
                <td><input type="text" name="email" placeholder="Nhập email" size="45"></td>
            </tr>
            <tr>
                <th>Address:</th>
                <td><input type="text" name="address" placeholder="Nhập địa chỉ" size="45"></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" class="btn btn-outline-info"/>
                </td>
            </tr>
        </table>
    </form>
    <p class="test">
        <c:if test="${message!= null}">
            <span>${message}</span>
        </c:if>
    </p>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>

</html>
