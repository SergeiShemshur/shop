package shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import shop.db.dao.CategoryDao;
import shop.model.Category;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    public void addCategory(Model model){
        List<Category> categories = categoryDao.findAll();
        model.addAttribute("categories", categories);
    }
}
