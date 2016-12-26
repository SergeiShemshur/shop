package shop;

import shop.discont.Discount;
import shop.discont.NoDiscount;
import shop.model.OrderItem;
import shop.model.User;
import shop.sale.NoSale;
import shop.sale.Sale;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCart {
    private List<OrderItem> orderItems;
    private Sale saleStrategy;
    private Discount discountStrategy;
    private User user;

    public ShoppingCart() {
        orderItems = new ArrayList<>();
        saleStrategy = new NoSale();
        discountStrategy = new NoDiscount();
    }

    public BigDecimal getTotalCost() {
        applySaleToOrderItem();
        BigDecimal total = getOrderItemTotalCost();
        total = getTotalCostWithDiscount(total);
        return total;
    }

    private void applySaleToOrderItem() {
        saleStrategy.applySale(orderItems);
    }

    public BigDecimal getOrderItemTotalCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems)
            total = total.add(item.getPurchasePrice());
        return total;
    }

    private BigDecimal getTotalCostWithDiscount(BigDecimal total) {
        return total.subtract(discountStrategy.applyDiscount(total));
    }

    public void setSale(Sale saleStrategy) {
        this.saleStrategy = saleStrategy;
    }

    public void setDiscount(Discount discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void removeOrderItem(OrderItem orderItem) {
        if (orderItems.contains(orderItem))
            orderItems.remove(orderItem);
    }
}
