package shop.sale;

import shop.model.OrderItem;
import shop.model.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GiftSale implements Sale {
    private Map<Product, Product> giftProducts;

    public GiftSale() {
        this.giftProducts = new HashMap<>();
    }

    public void applySale(List<OrderItem> orderItems) {
        List<OrderItem> gifts = new ArrayList<>();
        for (OrderItem item : orderItems) {
            if (giftProducts.containsKey(item.getProduct())) {
                gifts.add(getGift(item));
            }
        }
        orderItems.addAll(gifts);
    }

    private OrderItem getGift(OrderItem item) {
        Product product = item.getProduct();
        Product gift = giftProducts.get(product);
        OrderItem orderItemGift = new OrderItem(gift, new BigDecimal(1));
        orderItemGift.setPurchasePrice(new BigDecimal(0));
        return orderItemGift;
    }

    public void addProductAndGift(Product product, Product gift) {
        giftProducts.put(product, gift);
    }
}
