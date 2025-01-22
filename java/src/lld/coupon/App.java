package src.lld.coupon;

public class App {
    public static void main(String[] args) {

        AdminUser adminUser = new AdminUser("Prudvi",29);
        GreatIndian greatIndian = new GreatIndian("GREAT",10000,100,1);
        adminUser.addCoupon(greatIndian);

        CustomerUser customerUser = new CustomerUser("Naveen",27,0);
        customerUser.addToCart(10000d);
        customerUser.showCoupons();
        customerUser.redeem(greatIndian);
        customerUser.redeem(greatIndian);
    }
}
