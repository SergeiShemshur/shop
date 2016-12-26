package shop.sale;

import shop.model.OrderItem;
import shop.model.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductSale implements Sale {
    private Map<Product, BigDecimal> sealProducts;

    public ProductSale() {
        this.sealProducts = new HashMap<>();
    }

    public void applySale(List<OrderItem> orderItems) {
        for (OrderItem item : orderItems)
            if (sealProducts.containsKey(item.getProduct()))
                setSalePurchasePrice(item);
    }

    public void addSale(Product product, BigDecimal sale) {
        sealProducts.put(product, sale);
    }

    private void setSalePurchasePrice(OrderItem item) {
        BigDecimal purchasePrice = item.getPurchasePrice();
        BigDecimal sale = sealProducts.get(item.getProduct());
        item.setPurchasePrice(purchasePrice.multiply(sale));
    }
}
