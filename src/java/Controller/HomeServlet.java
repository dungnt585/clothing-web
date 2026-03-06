/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import ProductDAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException {ProductDAO productDAO = new ProductDAO();
        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");
        String categoryIdRaw = request.getParameter("cid");
        // 1. Xử lý phân trang [cite: 68]
        int page = 1;
        int pageSize = 8; // Số sản phẩm hiển thị trên 1 trang
        String pageRaw = request.getParameter("page");
        if (pageRaw != null && !pageRaw.isEmpty()) {
            page = Integer.parseInt(pageRaw);
        }

        List<Product> list;
        long totalProducts;

        // 2. Logic lấy dữ liệu dựa trên hành động của người dùng
        if (keyword != null && !keyword.isEmpty()) {
           
            list = productDAO.findByName(keyword); // Bạn cần viết hàm này trong ProductDAO
            totalProducts = list.size();
        } else if ("category".equals(action) && categoryIdRaw != null) {
            // Lọc theo danh mục [cite: 56, 57]
            int cid = Integer.parseInt(categoryIdRaw);
            list = productDAO.findByCategory(cid); // Bạn cần viết hàm này trong ProductDAO
            totalProducts = list.size();
        } else {
            // Hiển thị mặc định có phân trang
            list = productDAO.findAll(page, pageSize);
            totalProducts = productDAO.countAll(); 
        }

        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);


        request.setAttribute("productList", list); 
        request.setAttribute("currentPage", page); 
        request.setAttribute("totalPages", totalPages); 
        
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
