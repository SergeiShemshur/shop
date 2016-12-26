package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.service.CategoryService;
import shop.service.ProductService;

import java.math.BigDecimal;

@Controller
public class ProductController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @RequestMapping("/category/{id}")
    public String getProductByCategory(Model model, @PathVariable(value = "id") int id) {
        categoryService.addCategory(model);
        model.addAttribute("products", productService.getProductByCategoryId(id));
        model.addAttribute("categoryId", id);
        return "products";
    }
}
