package src.lld.coupon;

import java.util.ArrayList;
import java.util.List;

public class CouponManager {

    private static CouponManager mgmt;

    private List<Coupon> coupons;
    private List<Voucher> vouchers;


    private CouponManager() {
        coupons = new ArrayList();
        vouchers = new ArrayList();
    }

    static CouponManager getInstance() {
        if (mgmt == null) {
            mgmt = new CouponManager();
        }
        return mgmt;
    }

    void createCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }

    void deleteCoupon(Coupon coupon){
        this.coupons.remove(coupon);
    }

    void disableCoupon(Coupon coupon){
        coupon.disable();
    }

    void activateCoupon(Coupon coupon){
        coupon.activate();
    }

    void showCoupons() {
        for (Coupon c : coupons) {
            if(c.isActive())
            c.display();
        }
    }

    public boolean redeem(CustomerUser user, Coupon coupon){
        return coupon.redeem(user);
    }
}
