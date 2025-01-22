package src.lld.designpatterns.strategy;

public class UPIPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paying "+amount+" via UPI");
    }
}
