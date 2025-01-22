package src.lld.coupon;

import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User{

    public AdminUser(String name, int age) {
        super(name,age);
    }

    public void addCoupon(Coupon c){
        CouponManager.getInstance().createCoupon(c);
    }

    public void deleteCoupon(Coupon c){
        CouponManager.getInstance().deleteCoupon(c);
    }

    public void activate(Coupon c){
        CouponManager.getInstance().activateCoupon(c);
    }

    public void disableCoupon(Coupon c){
        CouponManager.getInstance().disableCoupon(c);
    }
}
