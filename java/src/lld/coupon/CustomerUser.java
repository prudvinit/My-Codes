package src.lld.coupon;

import java.util.ArrayList;
import java.util.List;

public class CustomerUser extends User{
    private String name;
    double cartValue;
    private List<Coupon> coupons;

    public CustomerUser(String name, int age, double cartValue) {
        super(name,age);
        this.cartValue = cartValue;
        coupons = new ArrayList();
    }

    public void addToCart(double val){
        cartValue+=val;
    }

    public void removeFromCart(double val){
        cartValue-=val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCartValue() {
        return cartValue;
    }

    public void setCartValue(double cartValue) {
        this.cartValue = cartValue;
    }

    public void redeem(Coupon c){
        if(CouponManager.getInstance().redeem(this,c)){
            System.out.println("Coupon redeemed succesfully!");
            coupons.add(c);
        }
        else{
            System.out.println("Redemption failed");
        }
    }
}
