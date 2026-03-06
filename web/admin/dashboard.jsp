<%-- 
    Document   : dashboard
    Created on : Feb 28, 2026, 11:07:24 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hệ Thống Quản Trị - Fashion Store</title>
    <style>
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 0; background-color: #f4f7f6; }
        .sidebar { width: 250px; height: 100vh; background: #2c3e50; color: white; position: fixed; padding-top: 20px; }
        .sidebar h2 { text-align: center; font-size: 20px; border-bottom: 1px solid #34495e; padding-bottom: 20px; }
        .sidebar a { display: block; color: #bdc3c7; padding: 15px 25px; text-decoration: none; transition: 0.3s; }
        .sidebar a:hover { background: #34495e; color: white; }
        
        .main-content { margin-left: 250px; padding: 20px; }
        .header { background: white; padding: 15px 30px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        
        .dashboard-grid { display: grid; grid-template-columns: repeat(auto-fit, minmax(250px, 1fr)); gap: 20px; margin-top: 30px; }
        .card { background: white; padding: 30px; border-radius: 10px; text-align: center; box-shadow: 0 4px 6px rgba(0,0,0,0.05); transition: 0.3s; text-decoration: none; color: #333; }
        .card:hover { transform: translateY(-5px); box-shadow: 0 10px 15px rgba(0,0,0,0.1); }
        .card h3 { margin: 10px 0; color: #2c3e50; }
        .card p { color: #7f8c8d; }
        
        .logout-btn { background: #e74c3c; color: white; border: none; padding: 8px 15px; border-radius: 5px; cursor: pointer; }
    </style>
</head>
<body>
    <%-- Kiểm tra quyền Admin [cite: 63, 64] --%>
    <%
        model.Users user = (model.Users) session.getAttribute("user");
        if (user == null || !user.getRoleId().getRoleName().equalsIgnoreCase("Admin")) { 
            response.sendRedirect("../login.jsp");
            return;
        }
    %>

    <div class="sidebar">
        <h2>FASHION ADMIN</h2>
        <a href="adminHome">🏠 Dashboard</a>
        <a href="${pageContext.request.contextPath}/AdminUserServlet?action=list">👤 Quản Lý Người Dùng</a>
        <a href="${pageContext.request.contextPath}/AdminUserServlet?action=list">📦 Quản Lý Sản Phẩm</a>
        <a href="${pageContext.request.contextPath}/AdminUserServlett?action=list">🛒 Quản Lý Đơn Hàng</a>
        <a href="${pageContext.request.contextPath}/AdminUserServlet">📊 Thống Kê</a>
    </div>

    <div class="main-content">
       <div class="header">
    <h3>Chào mừng, <%= user.getFullName() %>!</h3>
    <form action="${pageContext.request.contextPath}/AdminUserServlet" method="post" style="margin: 0;">
        <input type="hidden" name="action" value="logout"/>
        <button type="submit" class="logout-btn">Đăng xuất</button>
    </form>
</div>

        <div class="dashboard-grid">
            <%-- Thẻ Quản lý người dùng [cite: 65] --%>
            <a href="${pageContext.request.contextPath}/AdminUserServlet?action=list" class="card">
                <h3>👤 Người Dùng</h3>
                <p>Quản lý tài khoản và phân quyền khách hàng.</p>
            </a>

            <%-- Thẻ Quản lý sản phẩm [cite: 66] --%>
            <a href="${pageContext.request.contextPath}/AdminProductServlet?action=list" class="card">
                <h3>📦 Sản Phẩm</h3>
                <p>Cập nhật kho hàng, size và màu sắc sản phẩm.</p>
            </a>

            <%-- Thẻ Quản lý đơn hàng [cite: 66] --%>
            <a href="${pageContext.request.contextPath}/AdminUserServlet?action=list" class="card">
                <h3>🛒 Đơn Hàng</h3>
                <p>Theo dõi trạng thái và lịch sử giao dịch.</p>
            </a>

            <%-- Thẻ Báo cáo [cite: 67] --%>
            <a href="${pageContext.request.contextPath}/AdminUserServlet" class="card">
                <h3>📊 Báo Cáo</h3>
                <p>Xem doanh thu và thống kê bán hàng hàng tháng.</p>
            </a>
        </div>
    </div>
</body>
</html>