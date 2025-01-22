package src.lld.vendingmachine;

public class DefaultState implements State {

    VendingMachine machine;

    DefaultState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void insertCash(double cash) {
        machine.setBufferCash(machine.getBufferCash()+cash);
        machine.setCurrentState(machine.coinInsertedState);
    }

    @Override
    public void pressButton(int aisle) {
        System.out.println("Can't select product. Please add cash first");
    }

    @Override
    public void dispense() {
        System.out.println("Can't dispense. Please add cash first");
    }
}
