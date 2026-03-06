<%-- 
    Document   : edit-product
    Created on : Mar 3, 2026, 7:23:29 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sửa Sản Phẩm</title>
    <style>
        .form-group { margin-bottom: 15px; }
        label { display: block; font-weight: bold; margin-bottom: 5px; }
        input[type="text"], input[type="number"], textarea, select { width: 100%; max-width: 500px; padding: 8px; }
        .btn-save { background-color: #28a745; color: white; padding: 10px 20px; border: none; cursor: pointer; }
    </style>
</head>
<body>
    <div style="max-width: 600px; margin: auto;">
        <h2>Chỉnh Sửa Sản Phẩm</h2>
        
        <form action="AdminProductServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="productId" value="${product.productId}">

            <div class="form-group">
                <label>Tên sản phẩm:</label>
                <input type="text" name="productName" value="${product.productName}" required>
            </div>

            <div class="form-group">
                <label>Giá (VNĐ):</label>
                <input type="number" name="price" value="${product.price}" step="0.01" required>
            </div>

            <div class="form-group">
                <label>Danh mục:</label>
                <select name="categoryId">
                    <c:forEach var="cat" items="${categories}">
                        <option value="${cat.categoryId}" ${product.categoryId.categoryId == cat.categoryId ? 'selected' : ''}>
                            ${cat.categoryName}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label>Mô tả:</label>
                <textarea name="description" rows="5">${product.description}</textarea>
            </div>

            <div class="form-group">
                <label>Đường dẫn ảnh hiện tại:</label>
                <input type="text" name="imagePath" value="${product.imagePath}">
            </div>

            <button type="submit" class="btn-save">Lưu Thay Đổi</button>
            <a href="AdminProductServlet?action=list" style="margin-left: 20px; color: #666;">Hủy bỏ</a>
        </form>
    </div>
</body>
</html>
