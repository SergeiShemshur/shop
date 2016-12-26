package shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shop.db.dao.OrderDtoDao;
import shop.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    @Autowired
    OrderDtoDao orderDtoDao;
    @Autowired
    OrderService orderService;

    @RequestMapping("/admin")
    public String getAdminPage(Model model) {
        model.addAttribute("orderDto", orderDtoDao.findAll());
        return "admin";
    }

    @RequestMapping("admin/setStatusDone")
    public String setStatusDone(@RequestParam("id") int orderId, HttpServletRequest request) {
        orderService.setOrderStatusDone(orderId);
        return "redirect:" + request.getHeader("Referer");
    }
}
