import org.junit.Before;
import org.junit.Test;
import shop.model.OrderItem;
import shop.model.Product;
import shop.sale.ProductSale;
import shop.sale.Sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;

public class ProductSaleTest {
    List<OrderItem> orderItems;
    ProductSale productSale;

    @Before
    public void createOrderItemAndAddProductWhichHaveSale() {
        orderItems = new ArrayList<>();
        Product phone = new Product(1, "o8s1", new BigDecimal(100));
        orderItems.add(new OrderItem(phone, new BigDecimal(1)));
        productSale = new ProductSale();
        productSale.addSale(phone, new BigDecimal(0.5));
    }

    @Test
    public void testApplyingSaleToProduct() {
        productSale.applySale(orderItems);
        assertThat(orderItems.get(0).getPurchasePrice(), closeTo(new BigDecimal(50), new BigDecimal(0.01)));
    }

}
