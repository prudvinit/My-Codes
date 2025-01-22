package src.lld.designpatterns.strategy;

public class Main {
    public static void main(String[] args) {
        /**
         * Strategy is a behavioral design pattern that defines a family of algorithms/options, encapsulates each one and makes
         * them interchangeable. This allows algorithms to vary independent of Clients that use it.
         */
        ShoppingCart cart = new ShoppingCart();
        cart.setPayment(new CashPayment());
        cart.setPayment(new UPIPayment());
    }
}
