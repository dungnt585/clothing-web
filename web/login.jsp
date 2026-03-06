<%-- 
    Document   : login
    Created on : Feb 28, 2026, 11:00:07 AM
    Author     : LENOVO
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>LOGIN</h2>

<form action="Login" method="post">
    <input type="hidden" name="action" value="login"/>

    <label>Username:</label>
    <input type="text" name="username" required/>
    <br/><br/>

    <label>Password:</label>
    <input type="password" name="password" required/>
    <br/><br/>

    <button type="submit">Login</button>
    
    <a href="register.jsp" > Đăng Kí </a>
</form>

<p style="color:red;">
    ${error}
</p>

</body>
</html>