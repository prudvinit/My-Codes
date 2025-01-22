package src.lld.vendingmachine;

public class VendingMachineApp {
    public static void main(String[] args) {
        Item coke = new Item("Coke","Cold beverage",40);
        VendingMachine.getInstance().load(coke,10);
        VendingMachine.getInstance().show();
        Customer user = new Customer("Prudvi",18,1000);
        user.purchase(coke,45);
    }
}
