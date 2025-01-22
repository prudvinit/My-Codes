package src.lld.coupon;

public class GreatIndian extends Coupon {


    public GreatIndian(String name, int expiration, int totalAvailable, int perUser) {
        super(name, expiration,totalAvailable,perUser);
    }

    @Override
    protected boolean isApplicable(CustomerUser user) {
        return redeemed<totalAvailable&&user.age>=18 && user.getCartValue()>=10000;
    }


    @Override
    protected void display() {
        System.out.println("Great indian sale coupon, applicable for all adults with cart value 10000. Eligible for first 1000 users. Hurry");
    }
}
