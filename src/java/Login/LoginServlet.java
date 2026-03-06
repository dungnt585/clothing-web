/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Login;

import UserDAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Users;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
    model.Users user = (model.Users) session.getAttribute("user");

    // 1. Neu chua dang nhap thi ve trang login
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

   
    String roleName = user.getRoleId().getRoleName();

    if (roleName.equalsIgnoreCase("Admin")) {
        // Dieu huong den thu muc admin/dashboard.jsp
     response.sendRedirect("adminHome");
    } else {
        // Dieu huong den trang chu cua khach hang
        response.sendRedirect("home.jsp");
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

        if ("login".equals(action)) {
            login(request, response);
        }
        if ("logout".equals(action)) {
    HttpSession session = request.getSession(false);
    if (session != null) {
        session.invalidate(); // Xoa toan bo session
    }
    response.sendRedirect("login.jsp");
    }
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    UserDAO dao = new UserDAO();
    // Đổi checkLogin thành login cho khớp với UserDAO
    Users user = dao.login(username, password); 

    if (user != null) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Lấy RoleName chính xác từ quan hệ ManyToOne
        String roleName = user.getRoleId().getRoleName(); 

        if (roleName.equalsIgnoreCase("Admin")) {
            response.sendRedirect("adminHome");
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    } else {
        request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
