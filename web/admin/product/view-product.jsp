<%-- 
    Document   : view-product
    Created on : Mar 3, 2026, 7:19:42 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi Tiết Sản Phẩm</title>
    <style>
        .detail-container { max-width: 800px; margin: 20px auto; font-family: Arial, sans-serif; }
        .info-row { margin-bottom: 10px; border-bottom: 1px solid #eee; padding-bottom: 5px; }
        .label { font-weight: bold; width: 150px; display: inline-block; }
        .variant-table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        .variant-table th, .variant-table td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        .btn-back { display: inline-block; margin-top: 20px; padding: 10px 15px; background: #6c757d; color: white; text-decoration: none; border-radius: 4px; }
    </style>
</head>
<body>
    <div class="detail-container">
        <h2>Thông Tin Sản Phẩm: ${p.productName}</h2>

        <div class="info-row"><span class="label">ID:</span> ${p.productId}</div>
        <div class="info-row"><span class="label">Tên sản phẩm:</span> ${p.productName}</div>
        <div class="info-row"><span class="label">Giá:</span> ${p.price} VNĐ</div>
        <div class="info-row"><span class="label">Danh mục:</span> ${p.categoryId.categoryName}</div>
        <div class="info-row"><span class="label">Mô tả:</span> <p>${p.description}</p></div>
        <div class="info-row"><span class="label">Ngày tạo:</span> ${p.createdAt}</div>

        <h3>Danh sách các biến thể (Size & Color)</h3>
        <table class="variant-table">
            <thead>
                <tr>
                    <th>Kích thước (Size)</th>
                    <th>Màu sắc (Color)</th>
                    <th>Số lượng tồn (Stock)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="v" items="${p.productVariantsCollection}">
                    <tr>
                        <td>${v.sizeId.sizeName}</td>
                        <td>${v.colorId.colorName}</td>
                        <td>${v.stock}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty p.productVariantsCollection}">
                    <tr><td colspan="3">Sản phẩm này chưa có biến thể nào.</td></tr>
                </c:if>
            </tbody>
        </table>

        <a href="AdminProductServlet?action=list" class="btn-back">Quay lại danh sách</a>
    </div>
</body>
</html>
