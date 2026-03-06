/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CategoryDAO;



import GenericDAO.GenericDAO;
import Utils.JPAUtils;
import model.Category;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CategoryDAO extends GenericDAO<Category, Integer> {

    public CategoryDAO() {
        super(Category.class);
    }

    
    public Category findByName(String name) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT c FROM Category c WHERE c.categoryName = :name";
            TypedQuery<Category> query = em.createQuery(jpql, Category.class);
            query.setParameter("name", name);
            List<Category> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }

    public boolean hasProducts(Integer categoryId) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT COUNT(p) FROM Product p WHERE p.categoryId.categoryId = :catId";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("catId", categoryId);
            return query.getSingleResult() > 0;
        } finally {
            em.close();
        }
    }
}
