package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.db.dao.ProductDao;
import shop.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public List getProductByCategoryId(int id) {
        List<Product> allProducts = productDao.findAll();
        List<Product> categoryProducts = new ArrayList<>();
        allProducts.forEach(product -> {
            if (product.getCategory() == id) {
                categoryProducts.add(product);
            }
        });
        return categoryProducts;
    }

    public Product getProductById(int id){
       return productDao.findById(id);
    }

}
