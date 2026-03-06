/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserDAO;


import GenericDAO.GenericDAO;
import Utils.JPAUtils;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Users;

public class UserDAO extends GenericDAO<Users, Integer> implements IUserDAO {

    public UserDAO() {
        super(Users.class);
    }

    @Override
    public Users findByUsername(String username) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        try {
            // Sử dụng NamedQuery đã được định nghĩa sẵn trong model Users 
            TypedQuery<Users> query = em.createNamedQuery("Users.findByUsername", Users.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Trả về null nếu không tìm thấy hoặc có lỗi
        } finally {
            em.close();
        }
    }
@Override
    public void deleteStatus(int id) {
       EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager(); 
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Users user = em.find(Users.class, id);
            if (user != null) {
//                // Tùy chọn 1: Xóa vĩnh viễn

                
//                 Tùy chọn 2 (Khuyên dùng): Chuyển status = false để ẩn User (Soft Delete)
                 user.setStatus(false);
                 em.merge(user);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
 
    
    @Override
    public Users login(String username, String password) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM Users u WHERE u.username = :user AND u.password = :pass AND u.status = true";
            TypedQuery<Users> query = em.createQuery(jpql, Users.class);
            query.setParameter("user", username);
            query.setParameter("pass", password);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}