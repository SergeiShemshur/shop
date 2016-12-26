package shop.discont;

import java.math.BigDecimal;

public class CouponDiscount implements Discount {
    private final BigDecimal COUPON_sIZE;

    public CouponDiscount(BigDecimal couponSize) {
        this.COUPON_sIZE = couponSize;
    }

    public BigDecimal applyDiscount(BigDecimal price) {
        return COUPON_sIZE;
    }

}
