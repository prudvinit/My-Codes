package src.lld.designpatterns.strategy;

public class CashPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paying "+amount+" in cash.");
    }
}
