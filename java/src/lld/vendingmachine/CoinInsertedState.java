package src.lld.vendingmachine;

public class CoinInsertedState implements State {

    VendingMachine machine;

    CoinInsertedState(VendingMachine machine){
        this.machine = machine;
    }

    @Override
    public void insertCash(double cash) {
        machine.setBufferCash(machine.getBufferCash()+cash);
    }

    @Override
    public void pressButton(int aisle) {
        Item item = machine.inventory.getProductAt(aisle);
        if(item==null){
            System.out.println("No product exists at the aisle");
            System.out.println("Please collect your cash : "+machine.getBufferCash());
            machine.setBufferCash(0);
        }
        else{
            if(item.price>machine.getBufferCash()){
                System.out.println("Not enough cash. Please collect your cash : "+machine.getBufferCash());
                machine.setBufferCash(0);
            }
            else {
                machine.setSelectedAisle(aisle);
                machine.setCurrentState(machine.productSelectedState);
            }
        }
    }

    @Override
    public void dispense() {
        System.out.println("Can't dispense. Please select the product first.");
    }
}
