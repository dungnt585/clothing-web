<%-- 
    Document   : product-management
    Created on : Mar 3, 2026, 7:23:02 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Sản Phẩm</title>
    <style>
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f4f4f4; }
        .btn { padding: 6px 12px; text-decoration: none; border-radius: 4px; color: white; display: inline-block; }
        .btn-view { background-color: #17a2b8; }
        .btn-edit { background-color: #ffc107; color: black; }
        .btn-delete { background-color: #dc3545; }
        .btn-add { background-color: #28a745; margin-bottom: 20px; }
        .pagination { margin-top: 20px; text-align: center; }
    </style>
</head>
<body>
    <h2>Danh Sách Sản Phẩm</h2>

    <a href="AdminProductServlet?action=create" class="btn btn-add">+ Thêm Sản Phẩm Mới</a>
    <a href="${pageContext.request.contextPath}/admin/dashboard.jsp" class="btn" style="background-color: #6c757d;">Quay về Dashboard</a>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Ảnh</th>
                <th>Tên Sản Phẩm</th>
                <th>Giá</th>
                <th>Danh Mục</th>
                <th>Thao Tác</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${productList}">
                <tr>
                    <td>${p.productId}</td>
                    <td><img src="${p.imagePath}" width="50" height="50" alt="No image"></td>
                    <td>${p.productName}</td>
                    <td>${p.price} VNĐ</td>
                    <td>${p.categoryId.categoryName}</td>
                    <td>
                        <a href="AdminProductServlet?action=view&id=${p.productId}" class="btn btn-view">Xem</a>
                        <a href="AdminProductServlet?action=edit&id=${p.productId}" class="btn btn-edit">Sửa</a>
                        <a href="AdminProductServlet?action=delete&id=${p.productId}" 
                           class="btn btn-delete" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div style="text-align: center; margin: 20px;">
    <%-- Nút TRƯỚC: Vô hiệu hóa nếu đang ở trang 1 --%>
    <c:choose>
        <c:when test="${currentPage > 1}">
            <a href="home?page=${currentPage - 1}">Trước</a>
        </c:when>
        <c:otherwise>
            <span style="color: gray; cursor: not-allowed;">Trước</span>
        </c:otherwise>
    </c:choose>

    <span> Trang ${currentPage} / ${totalPages} </span>

    <%-- Nút SAU: Vô hiệu hóa nếu đang ở trang cuối cùng --%>
    <c:choose>
        <c:when test="${currentPage < totalPages}">
            <a href="home?page=${currentPage + 1}">Sau</a>
        </c:when>
        <c:otherwise>
            <span style="color: gray; cursor: not-allowed;">Sau</span>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>