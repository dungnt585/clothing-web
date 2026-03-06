/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GenericDAO;



import Utils.JPAUtils;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

    public class GenericDAO<T, K> implements IGenericDAO<T, K> {
        
    protected Class<T> entityClass;
 
    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public void create(T entity) {
      EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager(); 
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(entity);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    @Override
    public void delete(K id) {
       EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager(); 
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            T entity = em.find(entityClass, id); 
            if (entity != null) {
                em.remove(entity);
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /* 3. FINDBYID: Tìm kiếm một thực thể cụ thể */
    @Override
    public T findById(K id) {
       EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager(); 
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    /* 4. FINDALL: Lấy toàn bộ danh sách (không phân trang) */
    @Override
    public List<T> findAll() {
 EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();     
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    /* 5. FINDALL: Lấy danh sách có phân trang (Bạn đã viết) */
    @Override
    public List<T> findAll(int page, int pageSize) {
     EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager(); 
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    // Thực thi các hàm update, delete, findById tương tự...
@Override
    public void update(T entity) {
    EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager(); 
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(entity); // Hợp nhất các thay đổi vào Database
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
