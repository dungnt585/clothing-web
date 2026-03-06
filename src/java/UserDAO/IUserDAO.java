/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package UserDAO;


import GenericDAO.IGenericDAO;
import model.Users;

public interface IUserDAO extends IGenericDAO<Users, Integer> {
    // Tìm kiếm người dùng qua tên đăng nhập (phục vụ đăng nhập)
    Users findByUsername(String username);
    
    // Kiểm tra đăng nhập
    Users login(String username, String password);
    public void deleteStatus(int id);
    
 
}