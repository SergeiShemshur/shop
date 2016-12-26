package shop.model;

import java.math.BigDecimal;

public class OrderItem {
    private int id;
    private long orderId;
    private Product product;
    private BigDecimal purchasePrice;
    private BigDecimal amount;



    public OrderItem(Product product, BigDecimal amount) {
        this.product = product;
        purchasePrice = product.getPrice().multiply(amount);
        this.amount = amount;

    }


    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderItem orderItem = (OrderItem) o;
        if (product != null ? !product.equals(orderItem.product) : orderItem.product != null)
            return false;
        if (purchasePrice != null ? !purchasePrice.equals(orderItem.purchasePrice) : orderItem.purchasePrice != null)
            return false;
        return !(amount != null ? !amount.equals(orderItem.amount) : orderItem.amount != null);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmountAndRecalculatePrice(BigDecimal amount) {
        this.amount = amount;
        this.purchasePrice = product.getPrice().multiply(amount);
    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (purchasePrice != null ? purchasePrice.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", product=" + product +
                ", purchasePrice=" + purchasePrice.setScale(2,2) +
                ", amount=" + amount +
                '}';
    }
}
