package shop.discont;

import java.math.BigDecimal;

public interface Discount {

    BigDecimal applyDiscount(BigDecimal price);
}



