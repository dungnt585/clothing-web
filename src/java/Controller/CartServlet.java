/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/CartServlet"})
public class CartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.getRequestDispatcher("cart/cart.jsp").forward(request, response);
    }

    /**
     * doPost: Dùng để xử lý các thao tác Thay đổi dữ liệu: Add, Update, Remove
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        // Lấy giỏ hàng từ session hoặc tạo mới nếu chưa có
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
        }

        try {
            if (action != null) {
                switch (action) {
                    case "add":
                        // variantId lấy từ input ẩn đã chọn qua script
                        int variantIdAdd = Integer.parseInt(request.getParameter("variantId"));
                        int quantityAdd = Integer.parseInt(request.getParameter("quantity"));

                        if (cart.containsKey(variantIdAdd)) {
                            cart.put(variantIdAdd, cart.get(variantIdAdd) + quantityAdd);
                        } else {
                            cart.put(variantIdAdd, quantityAdd);
                        }
                        break;

                    case "update":
                        int variantIdUp = Integer.parseInt(request.getParameter("variantId"));
                        int quantityUp = Integer.parseInt(request.getParameter("quantity"));
                        if (quantityUp > 0) {
                            cart.put(variantIdUp, quantityUp);
                        } else {
                            cart.remove(variantIdUp);
                        }
                        break;

                    case "remove":
                        int variantIdRem = Integer.parseInt(request.getParameter("variantId"));
                        cart.remove(variantIdRem);
                        break;
                }
            }

            // Lưu lại giỏ hàng vào session sau khi thay đổi
            session.setAttribute("cart", cart);
            
            // Sau khi thực hiện lệnh POST, nên redirect để tránh việc user F5 bị gửi lại form
            response.sendRedirect("home"); 

        } catch (Exception e) {
            // Hiển thị lỗi nếu người dùng chưa chọn đủ Size/Màu khiến variantId bị null 
            session.setAttribute("errorMsg", "Vui lòng chọn đầy đủ Size và Màu sắc!");
            response.sendRedirect("home");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
