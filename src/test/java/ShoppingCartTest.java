import org.junit.Before;
import org.junit.Test;
import shop.ShoppingCart;
import shop.discont.*;
import shop.sale.*;
import shop.model.OrderItem;
import shop.model.Product;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertThat;


public class ShoppingCartTest {
    ShoppingCart shoppingCart;
    Product phone;

    @Before
    public void initShoppingCard() {
        shoppingCart = new ShoppingCart();
        phone = new Product(1, "cx70", new BigDecimal(100));
        OrderItem item = new OrderItem(phone, new BigDecimal(2));
        shoppingCart.addOrderItem(item);
    }

    @Test
    public void testTotalCostWithOutSaleAndDiscount() {
        shoppingCart.setSale(new NoSale());
        shoppingCart.setDiscount(new NoDiscount());
        assertThat(shoppingCart.getTotalCost(), is(new BigDecimal(200)));
    }

    @Test
    public void testTotalCostWithProductSale() {
        ProductSale productSale = new ProductSale();
        productSale.addSale(phone, new BigDecimal(0.5));
        shoppingCart.setSale(productSale);
        assertThat(shoppingCart.getTotalCost(), closeTo(new BigDecimal(100), new BigDecimal(0.01)));
    }

    @Test
    public void testTotalCostWithGiftSale() {
        GiftSale giftSale = new GiftSale();
        Product gift = new Product(2, "cover to phone", new BigDecimal(10));
        giftSale.addProductAndGift(phone, gift);
        shoppingCart.setSale(giftSale);
        assertThat(shoppingCart.getTotalCost(), is(new BigDecimal(200)));
        assertThat(shoppingCart.getOrderItems().size(), is(2));
    }

    @Test
    public void testTotalCostWithAmountDiscount() {
        AmountDiscount amountDiscount = new AmountDiscount(100, new BigDecimal(0.5));
        shoppingCart.setDiscount(amountDiscount);
        assertThat(shoppingCart.getTotalCost(), closeTo(new BigDecimal(100), new BigDecimal(0.01)));
    }

    @Test
    public void testTotalCostWithCouponDiscount() {
        CouponDiscount couponDiscount = new CouponDiscount(new BigDecimal(10));
        shoppingCart.setDiscount(couponDiscount);
        assertThat(shoppingCart.getTotalCost(), closeTo(new BigDecimal(190), new BigDecimal(0.01)));
    }

    @Test
    public void testOrderItemWithCouponDiscountAndGiftSale() {
        shoppingCart.setDiscount(new CouponDiscount(new BigDecimal(10)));
        shoppingCart.setSale(new GiftSale());
        assertThat(shoppingCart.getTotalCost(), closeTo(new BigDecimal(190), new BigDecimal(0.01)));
    }

    @Test
    public void testTotalOrderItemCost() {
        assertThat(shoppingCart.getTotalCost(), closeTo(new BigDecimal(200), new BigDecimal(0.01)));
    }

    @Test
    public void testAddOrderItemToShoppingCard() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Product(6, "5530", new BigDecimal(100));
        OrderItem item = new OrderItem(product, new BigDecimal(2));
        shoppingCart.addOrderItem(item);
        assertThat(shoppingCart.getOrderItems(), hasItem(item));
        assertThat(shoppingCart.getOrderItems().size(), is(1));
    }

    @Test
    public void testRemoveOrderItemFromShoppingCard() {
        ShoppingCart shoppingCart = new ShoppingCart();
        Product product = new Product(2, "5530", new BigDecimal(100));
        OrderItem item = new OrderItem(product, new BigDecimal(2));
        shoppingCart.addOrderItem(item);
        assertThat(shoppingCart.getOrderItems(), hasItem(item));
        assertThat(shoppingCart.getOrderItems().size(), is(1));
        shoppingCart.removeOrderItem(item);
        assertThat(shoppingCart.getOrderItems().size(), is(0));
    }
}
