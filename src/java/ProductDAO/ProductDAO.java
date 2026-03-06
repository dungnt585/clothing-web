/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProductDAO;

import GenericDAO.GenericDAO;
import Utils.JPAUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Product;

/**
 *
 * @author LENOVO
 */
public class ProductDAO extends GenericDAO<Product, Integer> implements IProductDAO {

    public ProductDAO() {
        // Truyền class Product vào GenericDAO để định nghĩa kiểu thực thể
        super(Product.class);
    }

    @Override
    public List<Product> findByName(String name) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        try {
            // Sử dụng NamedQuery đã được định nghĩa sẵn trong class Product
            TypedQuery<Product> query = em.createNamedQuery("Product.findByProductName", Product.class); 
            query.setParameter("productName", "%" + name + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findByCategory(Integer categoryId) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        try {
            // Truy vấn JPQL để lọc sản phẩm theo ID danh mục
            String jpql = "SELECT p FROM Product p WHERE p.categoryId.categoryId = :catId";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("catId", categoryId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

   
    public long countAll() {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT COUNT(p) FROM Product p";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Product> findLatestProducts(int limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
