<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 11/17/2016
  Time: 12:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<form method="post">
    <label for="username">Login</label><input type="text" id="username" name="username" class="placeholder" placeholder="login">
    <label for="password">Password</label><input type="password" id="password" name="password" class="placeholder" placeholder="password">
    <input type="text" id="uri" name="uri" hidden value=${uri}>
    <input type="text" id="token" name="token" hidden value=${token}>
    <input type="submit" value="Login">
</form>
</body>
</html>
