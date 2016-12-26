package shop.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.service.CategoryService;

@Controller
public class MainController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/")
    public String getCategory(Model model){
        categoryService.addCategory(model);
        return "index";
    }
}
