import org.junit.Test;
import shop.discont.CouponDiscount;
import shop.discont.Discount;
import java.math.BigDecimal;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CouponDiscountTest {

    @Test
    public void testCouponSizeReturn() {
        Discount constantDiscount = new CouponDiscount(new BigDecimal(10));
        assertThat(constantDiscount.applyDiscount(new BigDecimal(100)), is(new BigDecimal(10)));
    }
}
