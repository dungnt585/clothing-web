<%-- 
    Document   : cart
    Created on : Mar 3, 2026, 10:27:04 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Giỏ hàng của bạn</title>
    <style>
        .cart-container { width: 80%; margin: 20px auto; }
        table { width: 100%; border-collapse: collapse; }
        th, td { padding: 12px; border-bottom: 1px solid #ddd; text-align: left; }
        .btn-remove { color: red; text-decoration: none; font-weight: bold; }
        .total-section { text-align: right; margin-top: 20px; font-size: 1.2em; }
    </style>
</head>
<body>
    <div class="cart-container">
        <h1>🛒 Giỏ hàng của bạn</h1>
        
        <c:choose>
            <c:when test="${not empty sessionScope.cart}">
                <table>
                    <thead>
                        <tr>
                            <th>Sản phẩm</th>
                            <th>Phân loại</th>
                            <th>Số lượng</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${sessionScope.cart}">
                            <%-- Lưu ý: Bạn cần gửi kèm danh sách thông tin Variant từ Servlet hoặc dùng một Filter để Map ID sang Object --%>
                            <tr>
                                <td>Mã biến thể: ${item.key}</td> 
                                <td>Số lượng: ${item.value}</td>
                                <td>
                                    <form action="CartServlet" method="post" style="display:inline;">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="variantId" value="${item.key}">
                                        <button type="submit" class="btn-remove" style="border:none; background:none; cursor:pointer;">Xóa</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="total-section">
                    <a href="home" style="float: left;">← Tiếp tục mua sắm</a>
                    <button class="btn-buy" style="padding: 10px 20px;">Thanh toán ngay</button>
                </div>
            </c:when>
            <c:otherwise>
                <p>Giỏ hàng đang trống. <a href="home">Quay lại mua sắm ngay!</a></p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
