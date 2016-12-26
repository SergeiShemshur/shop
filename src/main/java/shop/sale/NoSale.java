package shop.sale;

import shop.model.OrderItem;
import java.util.List;

public class NoSale implements Sale {

    @Override
    public void applySale(List<OrderItem> products) {
    }
}
