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
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <style>
    body, html {
      height: 100%;
      margin: 0;
    }

    .bg {
      /* The image used */
      background-image: url("view/images/background_index.png");
      background-position: center;
      background-repeat:no-repeat;
      background-size: cover;
      width: 100vw;
      height: 100vh;
    }

    .quote {
      position: absolute;
      top: 50%;
      left: 50%;
      max-width: 1200px;
      min-width: 500px;
      text-align: center;
    }

    a {
      color: white;
      text-decoration: none;
    }

    a:hover {
      color: white;
    }
  </style>
</head>
<body>

  <div class="bg">
    <div class="quote" >
      <figure class="text-center">
        <blockquote class="blockquote">
          <p style="font-size: 40px; color: #bfbbbb">Tiền bạc cũng như đàn bà: muốn giữ nó thì phải săn sóc nó, bằng không nó đi tạo hạnh phúc cho người khác.</p>
        </blockquote>
        <figcaption class="blockquote-footer" style="font-size: 30px; color: #bbb7b7">
          E. Bourdet <cite title="Source Title">Source Title</cite>
        </figcaption>
      </figure>
      <button  style="color: white; border-color: white; font-size: 20px; position: absolute; font-weight: bold" type="button" class="btn btn-outline-primary"><a href="/login">Login</a></button>
    </div>
</div>



  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
