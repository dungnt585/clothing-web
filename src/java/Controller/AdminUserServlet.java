/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import UserDAO.UserDAO;
import Utils.JPAUtils;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import javax.persistence.EntityManager;
import model.Roles;
import model.Users;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "AdminUserServlet", urlPatterns = {"/AdminUserServlet"})
public class AdminUserServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUserServlet at " + request.getContextPath() + "</h1>");
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
        UserDAO dao = new UserDAO();

        if ("list".equals(action)) {
            listUsers(request, response, dao);
        } else if ("edit".equals(action)) {
            showEditForm(request, response, dao);
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
        UserDAO dao = new UserDAO();
if ("logout".equals(action)) {
        
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); 
        }
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
else
         if ("create".equals(action)) {
    createUser(request, response, dao);
}
                else if ("update".equals(action)) {
            updateUser(request, response, dao);
        } else if ("lock".equals(action) || "restore".equals(action)) {
            toggleUserStatus(request, response, dao, action);
        }
    }
    
 
    private void createUser(HttpServletRequest request, HttpServletResponse response, UserDAO dao)
        throws IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password"); // Nên mã hóa password nếu có thể
    String fullName = request.getParameter("fullName");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String country = request.getParameter("country");
    int roleId = Integer.parseInt(request.getParameter("roleId"));

    // Khởi tạo đối tượng User mới
    Users newUser = new Users();
    newUser.setUsername(username);
    newUser.setPassword(password);
    newUser.setFullName(fullName);
    newUser.setEmail(email);
    newUser.setPhone(phone);
    newUser.setAddress(address);
    newUser.setCountry(country);
    newUser.setStatus(true); // Mặc định tài khoản mới là Hoạt động

 EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        Roles userRole = em.getReference(Roles.class, 2);
    newUser.setRoleId(userRole);
    em.close();
    newUser.setCreatedAt(new java.util.Date()); 
    try {
        dao.create(newUser); // Hoặc dao.create(newUser) tùy vào tên hàm trong DAO của bạn
        response.sendRedirect("AdminUserServlet?action=list");
    } catch (Exception e) {
        response.sendRedirect("admin/user/create-user.jsp?error=Error creating user: " + e.getMessage());
    }
}
    
private void listUsers(HttpServletRequest request, HttpServletResponse response, UserDAO dao)
            throws ServletException, IOException {
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
        int pageSize = 5; // Số người dùng mỗi trang

        List<Users> list = dao.findAll(page, pageSize); 
        request.setAttribute("userList", list); 
        request.setAttribute("currentPage", page); 
        request.getRequestDispatcher("admin/user/user-management.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response, UserDAO dao)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); 
        Users user = dao.findById(id);
        request.setAttribute("userEdit", user); 
        request.getRequestDispatcher("admin/user/edit-user.jsp").forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response, UserDAO dao)
        throws IOException {
    int id = Integer.parseInt(request.getParameter("id"));
    String fullName = request.getParameter("fullName"); 
    String email = request.getParameter("email"); 
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String country = request.getParameter("country");
    boolean status = Boolean.parseBoolean(request.getParameter("status")); 

    Users user = dao.findById(id);
    
    // Bảo mật: Kiểm tra lại một lần nữa ở server side để chắc chắn không sửa Admin
    if(user.getRoleId() != null && "Admin".equals(user.getRoleId().getRoleName())) {
        response.sendRedirect("AdminUserServlet?action=list&error=Cannot edit Admin account");
        return;
    }

    user.setFullName(fullName);
    user.setEmail(email);
    user.setPhone(phone);
    user.setAddress(address);
    user.setCountry(country);
    user.setStatus(status);

    dao.update(user);
    response.sendRedirect("AdminUserServlet?action=list");
}
    private void toggleUserStatus(HttpServletRequest request, HttpServletResponse response, UserDAO dao, String action)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id")); 
        Users user = dao.findById(id);
        
        // Khóa nếu action là 'lock', khôi phục nếu là 'restore'
        user.setStatus("restore".equals(action)); 
        
        dao.update(user);
        response.sendRedirect("AdminUserServlet?action=list");
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
