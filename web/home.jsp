<%-- 
    Document   : home
    Created on : Feb 28, 2026, 1:22:03 PM
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cửa Hàng Thời Trang - Trang Chủ</title>
    <style>
        .header { display: flex; justify-content: space-between; padding: 20px; background: #333; color: white; }
        .product-grid { display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; padding: 20px; }
        .product-card { border: 1px solid #ddd; padding: 10px; text-align: center; border-radius: 8px; }
        .product-card img { max-width: 100%; height: 200px; object-fit: cover; }
        .btn-buy { background: #28a745; color: white; padding: 8px 15px; border: none; border-radius: 5px; cursor: pointer; }
        .auth-links a { color: white; text-decoration: none; margin-left: 15px; }
    
    .selection-group { margin-bottom: 15px; }
.selection-title { font-size: 0.9em; font-weight: bold; margin-bottom: 8px; display: block; }
.btn-group { display: flex; flex-wrap: wrap; gap: 10px; }

/* Ẩn input radio gốc */
.option-item input { display: none; }

/* Tạo giao diện nút bấm */
.option-label {
    padding: 6px 15px;
    border: 1px solid #ddd;
    border-radius: 20px;
    cursor: pointer;
    font-size: 0.85em;
    transition: all 0.3s;
    background: white;
}

/* Khi được chọn */
.option-item input:checked + .option-label {
    background-color: #007bff;
    color: white;
    border-color: #007bff;
}

/* Khi hết hàng */
.option-item input:disabled + .option-label {
    background-color: #f8f9fa;
    color: #ccc;
    cursor: not-allowed;
    text-decoration: line-through;
}
    </style>
</head>
<body>
    <div class="header">
        <h1>Fashion Store</h1>
       
        <div class="auth-links">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <span>Chào, ${sessionScope.user.fullName}!</span> <a href="CustomerServlet?action=profile">Tài khoản</a>
                    <form action="UserServlet" method="post" style="display:inline;">
                        <a href="${pageContext.request.contextPath}/OrderHistory" style="color: #ffc107;">📦 Đơn hàng của tôi</a>
                        <input type="hidden" name="action" value="logout">
                        <button type="submit" style="background:none; border:none; color:white; cursor:pointer;">Đăng xuất</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <a href="login.jsp">Đăng nhập</a>
                     <a href="register.jsp">Đăng ki</a>
                </c:otherwise>
              
            </c:choose>
        </div>
    </div>
<div class="search-section" style="text-align: center; margin: 30px 0;">
    <form action="${pageContext.request.contextPath}/home" method="get" style="display: inline-flex; gap: 10px;">
        <input type="text" name="keyword" placeholder="Bạn đang tìm gì?..." 
               value="${param.keyword}" 
               style="padding: 10px 20px; width: 300px; border: 1px solid #ddd; border-radius: 25px; outline: none;">
        <button type="submit" class="btn-buy" style="border-radius: 25px; padding: 10px 25px;">
            🔍 Tìm kiếm
        </button>
    </form>
</div>
   <a href="${pageContext.request.contextPath}/cart/cart.jsp" style="position: relative;">
            🛒 Giỏ hàng 
            <c:if test="${not empty sessionScope.cart}">
                <span style="background: red; color: white; border-radius: 50%; padding: 2px 6px; font-size: 12px;">
                    ${sessionScope.cart.size()}
                </span>
            </c:if>
        </a>
<%-- 2. Menu Danh mục (Category Menu) --%>
<div class="category-menu" style="margin: 10px 20px; border-bottom: 1px solid #ddd; padding-bottom: 10px;">
    <strong>Danh mục: </strong>
    <a href="home?action=list" style="margin-right: 15px;">Tất cả</a>
    <a href="home?action=category&cid=1" style="margin-right: 15px;">  Ao nam</a>
    <a href="home?action=category&cid=2" style="margin-right: 15px;">Ao nu</a>
      <a href="home?action=category&cid=3" style="margin-right: 15px;">Quan nam</a>
        <a href="home?action=category&cid=4" style="margin-right: 15px;">Quan nu</a>
          <a href="home?action=category&cid=5" style="margin-right: 15px;">Vay Dam</a>
             <a href="home?action=category&cid=6" style="margin-right: 15px;">Phu kien</a>
    <%-- Bạn có thể dùng c:forEach để duyệt danh sách Category từ DB --%>
</div>

   <c:if test="${not empty sessionScope.errorMsg}">
    <div style="color: white; background: #dc3545; padding: 10px; text-align: center; margin-bottom: 15px; border-radius: 5px;">
        ${sessionScope.errorMsg}
    </div>
    <c:remove var="errorMsg" scope="session"/>
</c:if>

<div class="product-grid">
    <c:forEach var="p" items="${productList}">
        <div class="product-card" style="border: 1px solid #ddd; padding: 15px; border-radius: 10px; display: flex; flex-direction: column; height: 100%;">
            <%-- 1. Ảnh sản phẩm --%>
            <img src="${p.imagePath != null ? p.imagePath : 'https://via.placeholder.com/200'}" 
                 alt="${p.productName}" style="width: 100%; height: 200px; object-fit: cover; border-radius: 5px;">
            
            <%-- 2. Tên và Giá --%>
            <h3 style="margin: 10px 0; font-size: 1.1em;">${p.productName}</h3>
            <p style="color: #d9534f; font-weight: bold; font-size: 1.1em; margin: 5px 0;">${p.price} VNĐ</p>
            
            <%-- 3. Mô tả sản phẩm (Hiển thị khi người dùng nhìn vào thẻ) --%>
            <div style="background: #f9f9f9; padding: 8px; border-radius: 5px; margin: 10px 0; min-height: 60px;">
                <strong style="font-size: 0.8em; color: #333;">Mô tả:</strong>
                <p style="font-size: 0.85em; color: #666; margin: 5px 0; display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden;">
                    ${p.description}
                </p>
            </div>

            <form action="${pageContext.request.contextPath}/CartServlet" method="post" class="product-form">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="productId" value="${p.productId}">
    
    <input type="hidden" name="variantId" id="selectedVariant-${p.productId}" required>

    <div class="selection-group">
        <span class="selection-title">Kích cỡ:</span>
        <div class="btn-group">
            <c:forEach var="v" items="${p.productVariantsCollection}">
                <label class="option-item">
                    <input type="radio" name="sizeGroup-${p.productId}" 
                           value="${v.sizeId.sizeId}" 
                           onclick="updateVariant('${p.productId}', '${v.sizeId.sizeId}', '${v.colorId.colorId}', '${v.variantId}')">
                    <span class="option-label">${v.sizeId.sizeName}</span>
                </label>
            </c:forEach>
        </div>
    </div>

    <div class="selection-group">
        <span class="selection-title">Màu sắc:</span>
        <div class="btn-group">
            <c:forEach var="v" items="${p.productVariantsCollection}">
                <label class="option-item">
                    <input type="radio" name="colorGroup-${p.productId}" 
                           value="${v.colorId.colorId}"
                           onclick="updateVariant('${p.productId}', '${v.sizeId.sizeId}', '${v.colorId.colorId}', '${v.variantId}')">
                    <span class="option-label">${v.colorId.colorName}</span>
                </label>
            </c:forEach>
        </div>
    </div>

    <div style="display: flex; gap: 10px; align-items: center;">
        <input type="number" name="quantity" value="1" min="1" style="width: 60px; padding: 8px; border-radius: 5px; border: 1px solid #ddd;">
        <button type="submit" class="btn-buy" style="flex-grow: 1;">Thêm vào giỏ</button>
    </div>
</form>
        </div>
    </c:forEach>
</div>

<!--   <div style="text-align: center; margin: 20px;">
        <c:if test="${currentPage > 1}">
            <a href="home?page=${currentPage - 1}">Trước</a>
        </c:if>
        <span> Trang ${currentPage} </span>
        <a href="home?page=${currentPage + 1}">Sau</a>
    </div>-->
    
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
<script>
function updateVariant(productId, sizeId, colorId, variantId) {
    // Gán ID của biến thể vào input ẩn để gửi lên Servlet
    document.getElementById('selectedVariant-' + productId).value = variantId;
    
    // Bạn có thể viết thêm logic ở đây để làm mờ các nút không khả dụng
    console.log("Đã chọn sản phẩm " + productId + " với Variant: " + variantId);
}
</script>
