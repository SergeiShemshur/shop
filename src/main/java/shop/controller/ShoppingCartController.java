package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.db.dao.OrderDao;
import shop.db.dao.OrderItemDao;
import shop.model.Order;
import shop.model.OrderItem;
import shop.model.Product;
import shop.model.User;
import shop.service.ProductService;
import shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/shoppingCart")
    public String showShoppingCart(HttpSession session, Model model) {
        List<OrderItem> productList = new ArrayList<>();
        Object obj = session.getAttribute("products");
        if (obj != null)
            productList = (List<OrderItem>) obj;
        model.addAttribute("orderItems", productList);
        return "shoppingCart";
    }


    @RequestMapping(value = "/addProductToShoppingCart", method = RequestMethod.GET)
    public String addToShoppingCart(HttpSession session, HttpServletRequest request,
                                    @RequestParam("id") int productId,
                                    @RequestParam("amount") int amount) {
        Product buyProduct = productService.getProductById(productId);
        Object obj = session.getAttribute("products");

        if (obj == null) {
            ArrayList<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(new OrderItem(buyProduct, new BigDecimal(amount)));
            session.setAttribute("products", orderItems);
        } else {
            addProductToShoppingCart((List) obj,buyProduct,amount);
        }
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/removeProductFromShoppingCart")
    public String removeProductFromShoppingCart(@RequestParam("id") int productId,
                                                @RequestParam("amount") int amount,
                                                HttpSession session, HttpServletRequest request) {
        Object obj = session.getAttribute("products");
        if (obj != null) {
            List<OrderItem> orderItems = (List) obj;
            int remove = getOrderItemListId(orderItems, productId, amount);
            orderItems.remove(remove);
        }
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/productAmountPlus")
    public String productAmountPlus(@RequestParam("id") int productId,
                                    @RequestParam("amount") int amount,
                                    HttpSession session, HttpServletRequest request) {
        Object obj = session.getAttribute("products");
        if (obj != null) {
            List<OrderItem> orderItems = (List) obj;
            int change = getOrderItemListId(orderItems, productId, amount);
            orderItems.get(change).setAmountAndRecalculatePrice(orderItems.get(change).getAmount().add(new BigDecimal(1)));
        }

        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/productAmountMinus")
    public String productAmountMinus(@RequestParam("id") int productId,
                                     @RequestParam("amount") int amount,
                                     HttpSession session, HttpServletRequest request) {
        Object obj = session.getAttribute("products");
        if (obj != null) {
            List<OrderItem> orderItems = (List) obj;
            int change = getOrderItemListId(orderItems, productId, amount);
            if (orderItems.get(change).getAmount().intValue() == 1) {
                orderItems.remove(change);
            } else
                orderItems.get(change).setAmountAndRecalculatePrice(orderItems.get(change).getAmount().subtract(new BigDecimal(1)));
        }
        return "redirect:" + request.getHeader("Referer");
    }

    private int getOrderItemListId(List<OrderItem> orderItems, int productId, int amount) {
        int id = Integer.MAX_VALUE;
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem item = orderItems.get(i);
            if (item.getProduct().getId() == productId && item.getAmount().intValue() == amount) {
                id = i;
                break;
            }
        }
        return id;
    }

    @RequestMapping(value = "/buy")
    public String buy(@RequestParam("name") String name,
                      @RequestParam("phone") String phone,
                      HttpSession session) {
        User user = userService.getSavedUser(name, phone);

        Order order = new Order(user.getId());
        OrderDao orderDao = new OrderDao();
        long orderId = orderDao.saveAndGetId(order);

        Object obj = session.getAttribute("products");
        List<OrderItem> orderItems = (List) obj;
        OrderItemDao dao = new OrderItemDao();
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(orderId);
            dao.save(orderItem);
        }
        session.removeAttribute("products");
        return "success";
    }

    public void addProductToShoppingCart(List<OrderItem> orderItems, Product buyProduct, int amount) {
        if (isProductInShoppingCart(orderItems, buyProduct.getId())) {
            addAmountToProduct(orderItems, buyProduct.getId(), amount);
        } else orderItems.add(new OrderItem(buyProduct, new BigDecimal(amount)));
    }

    private void addAmountToProduct(List<OrderItem> orderItems, int productId, int amount) {
        orderItems.stream().filter(item -> item.getProduct().getId() == productId).forEach(item -> {
            item.setAmountAndRecalculatePrice(item.getAmount().add(new BigDecimal(amount)));
        });
    }

    private boolean isProductInShoppingCart(List<OrderItem> orderItems, int productId) {
        for (OrderItem item : orderItems)
            if (item.getProduct().getId() == productId)
                return true;
        return false;
    }
}
