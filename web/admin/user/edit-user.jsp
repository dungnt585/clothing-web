<%-- 
    Document   : edit-user
    Created on : Feb 28, 2026, 11:48:26?AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chỉnh Sửa Người Dùng</title>
    <style>
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"], select { width: 300px; padding: 8px; }
        .btn-save { background-color: #28a745; color: white; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <h2>Chỉnh Sửa Thông Tin Người Dùng</h2>

    <form action="${pageContext.request.contextPath}/AdminUserServlet" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${userEdit.id}">

        <div class="form-group">
            <label>Tên đăng nhập:</label>
            <input type="text" value="${userEdit.username}" disabled>
        </div>

        <div class="form-group">
            <label>Họ tên:</label>
            <input type="text" name="fullName" value="${userEdit.fullName}" required>
        </div>

        <div class="form-group">
            <label>Email:</label>
            <input type="email" name="email" value="${userEdit.email}" required>
        </div>

        <div class="form-group">
            <label>Số điện thoại:</label>
            <input type="text" name="phone" value="${userEdit.phone}">
        </div>

        <div class="form-group">
            <label>Địa chỉ:</label>
            <input type="text" name="address" value="${userEdit.address}">
        </div>

        <div class="form-group">
            <label>Quốc gia:</label>
            <input type="text" name="country" value="${userEdit.country}">
        </div>

        <div class="form-group">
            <label>Trạng thái:</label>
            <select name="status">
                <option value="true" ${userEdit.status ? 'selected' : ''}>Hoạt động</option>
                <option value="false" ${!userEdit.status ? 'selected' : ''}>Khóa</option>
            </select>
        </div>

        <button type="submit" class="btn-save">Lưu thay đổi</button>
        <a href="${pageContext.request.contextPath}/AdminUserServlet?action=list">Hủy bỏ</a>
    </form>
</body>
</html>
