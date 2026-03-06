/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ProductDAO;

import java.util.List;
import model.Product;

/**
 *
 * @author LENOVO
 */
public interface IProductDAO {
    List<Product> findByName(String name);
    
    // Lấy sản phẩm theo danh mục
    List<Product> findByCategory(Integer categoryId);
    
    // Lấy các sản phẩm mới nhất
    List<Product> findLatestProducts(int limit);
}
