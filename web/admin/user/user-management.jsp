<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Người Dùng</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f4f4f4; }
        .btn { padding: 5px 10px; text-decoration: none; border-radius: 4px; border: none; cursor: pointer; }
        .btn-edit { background-color: #ffc107; color: black; }
        .btn-delete { background-color: #dc3545; color: white; }
        .status-active { color: green; font-weight: bold; }
        .status-locked { color: red; font-weight: bold; }
    </style>
</head>
<body>
    <h2>Danh Sách Người Dùng</h2>
    <p style="color: red;">${error}</p>
    <a href="admin/user/create-user.jsp" class="btn" style="background-color: #28a745; color: white;">+ Thêm người dùng mới</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Điện thoại</th>
                <th>Địa chỉ</th>
                <th>Quốc gia</th>
                <th>Vai trò</th>
                <th>Trạng thái</th>
                <th>Thao tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="u" items="${userList}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.fullName}</td>
                    <td>${u.email}</td>
                    <td>${u.phone}</td>
                    <td>${u.address}</td>
                    <td>${u.country}</td>
                    <td>${u.roleId.roleName}</td>
                    <td>
                        <span class="${u.status ? 'status-active' : 'status-locked'}">
                            ${u.status ? "Đang hoạt động" : "Đã khóa"}
                        </span>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${u.roleId.roleName == 'Admin'}">
                                <span style="color: gray; font-style: italic;">Không thể sửa Admin</span>
                            </c:when>
                            <c:otherwise>
                                <a href="${pageContext.request.contextPath}/AdminUserServlet?action=edit&id=${u.id}" class="btn btn-edit">Sửa</a>
                                <c:choose>
                                    <c:when test="${u.status}">
                                        <form action="${pageContext.request.contextPath}/AdminUserServlet" method="post" style="display:inline;" onsubmit="return confirm('Khóa tài khoản này?')">
                                            <input type="hidden" name="action" value="lock">
                                            <input type="hidden" name="id" value="${u.id}">
                                            <button type="submit" class="btn btn-delete">Khóa</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="${pageContext.request.contextPath}/AdminUserServlet" method="post" style="display:inline;" onsubmit="return confirm('Khôi phục tài khoản này?')">
                                            <input type="hidden" name="action" value="restore">
                                            <input type="hidden" name="id" value="${u.id}">
                                            <button type="submit" class="btn btn-edit" style="background-color: #17a2b8; color: white;">Khôi phục</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
<div class="action-bar" style="margin-bottom: 20px;">
  
    <a href="${pageContext.request.contextPath}/adminHome" class="btn" style="background-color: #6c757d; color: white; margin-left: 10px;">Quay về AdminHome</a>
</div>
</body>
</html>