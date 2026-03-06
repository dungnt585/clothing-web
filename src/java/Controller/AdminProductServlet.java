/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import CategoryDAO.CategoryDAO;
import ProductDAO.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "AdminProductServlet", urlPatterns = {"/AdminProductServlet"})
public class AdminProductServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminProductServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminProductServlet at " + request.getContextPath() + "</h1>");
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
    String action = request.getParameter("action");
        ProductDAO dao = new ProductDAO();

        if (action == null || "list".equals(action)) {
            listProducts(request, response, dao);
        }
        else if ("view".equals(action)) {

        }
            else if ("edit".equals(action)) {
            showEditForm(request, response, dao);
        } else if ("delete".equals(action)) {
            deleteProduct(request, response, dao);
        } else if ("create".equals(action)) {
            // Hiển thị form tạo mới (cần lấy list category để chọn)
            request.setAttribute("categories", new CategoryDAO().findAll());
            request.getRequestDispatcher("admin/product/create-product.jsp").forward(request, response);
        }
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
       String action = request.getParameter("action");
        ProductDAO dao = new ProductDAO();

        if ("insert".equals(action)) {
            insertProduct(request, response, dao);
        } else if ("update".equals(action)) {
            updateProduct(request, response, dao);
        }
        else if ("view".equals(action)) {
    showProductDetails(request, response, dao);
}
    }
    
    private void showProductDetails(HttpServletRequest request, HttpServletResponse response, ProductDAO dao)
        throws ServletException, IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    Product product = dao.findById(id); 
    
    // Gửi đối tượng sản phẩm sang trang chi tiết
    request.setAttribute("p", product);
    request.getRequestDispatcher("admin/product/view-product.jsp").forward(request, response);
}
private void listProducts(HttpServletRequest request, HttpServletResponse response, ProductDAO dao)
            throws ServletException, IOException {
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int pageSize = 10;
        List<Product> list = dao.findAll(page, pageSize); 
        request.setAttribute("productList", list);
        request.setAttribute("currentPage", page);
        request.getRequestDispatcher("admin/product/product-management.jsp").forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response, ProductDAO dao)
            throws IOException {
        String name = request.getParameter("productName");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String desc = request.getParameter("description");
        int catId = Integer.parseInt(request.getParameter("categoryId"));

        Product p = new Product();
        p.setProductName(name);
        p.setPrice(price);
        p.setDescription(desc);
        p.setCategoryId(new CategoryDAO().findById(catId));
        p.setCreatedAt(new java.util.Date());

        dao.create(p); 
        response.sendRedirect("AdminProductServlet?action=list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, ProductDAO dao)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = dao.findById(id); 
        request.setAttribute("product", existingProduct);
        request.setAttribute("categories", new CategoryDAO().findAll());
        request.getRequestDispatcher("admin/product/edit-product.jsp").forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response, ProductDAO dao)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("productId"));
        Product p = dao.findById(id); 
        p.setProductName(request.getParameter("productName"));
        p.setPrice(new BigDecimal(request.getParameter("price")));
        p.setDescription(request.getParameter("description"));
        p.setCategoryId(new CategoryDAO().findById(Integer.parseInt(request.getParameter("categoryId"))));

        dao.update(p); 
        response.sendRedirect("AdminProductServlet?action=list");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response, ProductDAO dao)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.delete(id); 
        response.sendRedirect("AdminProductServlet?action=list");
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
