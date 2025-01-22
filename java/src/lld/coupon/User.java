package src.lld.coupon;

import java.util.ArrayList;
import java.util.List;

public abstract class User {
    private String name;
    protected int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void showCoupons(){
        CouponManager.getInstance().showCoupons();
    }
}
