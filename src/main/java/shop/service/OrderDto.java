package shop.service;

import shop.model.OrderItem;
import shop.model.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    List<OrderItem> orderItems;
    User user;
    String status;
    String purchaseTime;

    public OrderDto(User user, String status) {
        this.user = user;
        orderItems = new ArrayList<>();
        this.status = status;
    }

    public BigDecimal getTotalPrice(){
        BigDecimal total = new BigDecimal(0);
        for(OrderItem item : orderItems){
            total = total.add(item.getPurchasePrice());
        }
        return total;
    }
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }


    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderItems=" + orderItems +
                ", user=" + user +
                '}';
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
