package shop.discont;

import java.math.BigDecimal;

public class AmountDiscount implements Discount {
    private int amount;
    private BigDecimal discount;

    public AmountDiscount(int amount, BigDecimal discount) {
        this.amount = amount;
        this.discount = discount;
    }

    public BigDecimal applyDiscount(BigDecimal price) {
        if (price.intValue() > amount)
            return price.multiply(discount);
            return price;
    }
}
