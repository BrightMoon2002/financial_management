<%--
  Created by IntelliJ IDEA.
  User: duynguyen
  Date: 15/11/2021
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<%--  <meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">--%>
  <style>
    *{
      margin:0;
      padding: 0;
      box-sizing: border-box;
    }
    a{
      text-decoration: none;
    }
    header{
      background-image:url("view/images/background_index.png");
      background-position: center;
      background-repeat:no-repeat;
      background-size: cover;
      width: 100vw;
      height: 100vh;
    }
    .header-text{
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%,-50%);
      max-width: 700px;
      min-width: 500px;
      text-align: center;
      color: white;
    }
  </style>
</head>
<body>

<header>
  <div class="bg-image">
  </div>
  <div class="header-text">
    <h1>Liều Thì Ăn Nhiều Không Nhiều Thì Ăn Ít</h1>
    <p>Duy - Hiếu - Lập - Long</p>
    <button id="address-frorm"><a href="/login">Loggin</a></button>
  </div>
</header>

<%--  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>--%>
</body>
</html>
