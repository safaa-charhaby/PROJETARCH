<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./style/style.css">
    <script src="https://kit.fontawesome.com/f6816dd194.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="login-page">
        <h1>Login</h1>
        <form action="logincontroler" method="POST">
            <input type="text" name="username" name="username" placeholder="USERNAME">
            <input type="password" name="password" id="password" placeholder="Enter Password...">
            <button type="submit" class="btn">Login</button>

        </form>
    </div>
</body>
</html>