package src.lld.vendingmachine;

public class VendingMachineApp {
    public static void main(String[] args) {

        VendingMachine machine = VendingMachine.getInstance();
        machine.load(new Item("Coke","Cold beverage",5),1);
        machine.insertCash(10);
        machine.pressButton(1);
    }
}
