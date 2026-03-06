/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */


package GenericDAO;

import java.util.List;





public interface IGenericDAO<T, K> {
    void create(T entity);
    void update(T entity);
    void delete(K id);
    T findById(K id);
    List<T> findAll();
    List<T> findAll(int page, int pageSize); // Hỗ trợ phân trang chuyên nghiệp
}