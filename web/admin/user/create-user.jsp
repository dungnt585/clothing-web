<%-- 
    Document   : create-user
    Created on : Mar 3, 2026, 6:27:44 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm Người Dùng Mới</title>
    <style>
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], input[type="password"], select { 
            width: 100%; max-width: 400px; padding: 8px; box-sizing: border-box; 
        }
        .btn-save { background-color: #28a745; color: white; padding: 10px 20px; border: none; cursor: pointer; border-radius: 4px; }
        .btn-back { background-color: #6c757d; color: white; padding: 10px 20px; text-decoration: none; border-radius: 4px; margin-left: 10px; }
    </style>
</head>
<body>
    <h2>Thêm Người Dùng Mới</h2>

    <form action="${pageContext.request.contextPath}/AdminUserServlet" method="post">
        <input type="hidden" name="action" value="create">

        <div class="form-group">
            <label>Tên đăng nhập:</label>
            <input type="text" name="username" required placeholder="Nhập tên đăng nhập">
        </div>

        <div class="form-group">
            <label>Mật khẩu:</label>
            <input type="password" name="password" required placeholder="Nhập mật khẩu">
        </div>

        <div class="form-group">
            <label>Họ tên:</label>
            <input type="text" name="fullName" required placeholder="Nhập họ và tên">
        </div>

        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" required placeholder="example@mail.com">
        </div>

        <div class="form-group">
            <label>Số điện thoại:</label>
            <input type="text" name="phone" placeholder="Nhập số điện thoại">
        </div>

        <div class="form-group">
            <label>Địa chỉ:</label>
            <input type="text" name="address" placeholder="Nhập địa chỉ">
        </div>

        <div class="form-group">
            <label>Quốc gia:</label>
            <input type="text" name="country" placeholder="Nhập quốc gia">
        </div>

      <input type="hidden" name="roleId" value="2">

        <div style="margin-top: 20px;">
            <button type="submit" class="btn-save">Tạo người dùng</button>
            <a href="AdminUserServlet?action=list" class="btn-back">Hủy bỏ</a>
        </div>
    </form>
</body>
</html>