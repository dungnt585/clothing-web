/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Login;

import UserDAO.UserDAO;
import Utils.JPAUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import model.Roles;
import model.Users;


@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
    request.getRequestDispatcher("register.jsp").forward(request, response);
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
        register(request, response);
            // 1. Lấy dữ liệu từ form

       }
       private void register(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 try{      String email = request.getParameter("email");
            String fullname = request.getParameter("fullName");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserDAO dao = new UserDAO();
            
            // 2. Kiểm tra trùng username
            Users existingUser = dao.findByUsername(username);
            if (existingUser != null) {
                request.setAttribute("error", "Tên đăng nhập '" + username + "' đã tồn tại!");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // 3. Tạo đối tượng User mới và gán giá trị mặc định
            Users newUser = new Users();
            newUser.setEmail(email);
            newUser.setFullName(fullname);
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setStatus(true); // Hoạt động mặc định
            
            // Gán Role mặc định là 'User' (ID = 2 theo DB của bạn)
   EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        Roles userRole = em.getReference(Roles.class, 2);
    newUser.setRoleId(userRole);
    em.close();
    newUser.setCreatedAt(new java.util.Date()); 
            // 4. Lưu vào Database
            dao.create(newUser);

            // 5. Thông báo thành công
            request.setAttribute("message", "Đăng ký thành công! Bạn có thể đăng nhập ngay.");
            request.getRequestDispatcher("register.jsp").forward(request, response);

                 } catch (Exception e) {
            request.setAttribute("error", "Lỗi hệ thống: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
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
