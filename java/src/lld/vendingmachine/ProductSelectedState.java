package src.lld.vendingmachine;

public class ProductSelectedState implements State {

    VendingMachine machine;

    ProductSelectedState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void insertCash(double cash) {
        System.out.println("Product dispensing. Can't accept cash now");
    }

    @Override
    public void pressButton(int aisle) {
        System.out.println("Product already selected. Dispensing now");
    }

    @Override
    public void dispense() {
        machine.setTotalCash(machine.getTotalCash()+machine.getBufferCash());
        machine.setBufferCash(0);
        machine.inventory.dispense(machine.getSelectedAisle());
    }
}
