package shop.discont;


import java.math.BigDecimal;

public class NoDiscount implements  Discount {
    @Override
    public BigDecimal applyDiscount(BigDecimal price) {
        return new BigDecimal(0);
    }
}
