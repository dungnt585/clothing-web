<%-- 
    Document   : register
    Created on : Feb 28, 2026, 8:30:56 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h2>REGISTER</h2>
    <c:if test="${not empty error}">
        <p style "color: red;">${error}</p>
    </c:if>
     <c:if test="${not empty message}">
        <p style "color: green;">${message}</p>
        
    </c:if>
       <form action="${pageContext.request.contextPath}/Register" method="post">
    <input type="hidden" name="action" value="create"/>
    <label>Email:</label>
    <input type="text" name="email" required>
    <br/><br/>
    <label>FullName:</label>
    <input type="text" name="fullName" required>
    <br/><br/>
    <label>Username:</label>
    <input type="text" name="username" required/>
    <br/><br/>

    <label>Password:</label>
    <input type="password" name="password" required/>
    <br/><br/>

    <button type="submit">Register</button>
    <p><a href="login.jsp">Nhan vao day de dang nhap</a></p>
</form>
        
        
    </body>
</html>
