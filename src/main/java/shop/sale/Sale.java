package shop.sale;

import shop.model.OrderItem;
import shop.model.Product;

import java.util.List;
import java.util.Map;

public interface Sale {

    void applySale(List<OrderItem> products);

}
