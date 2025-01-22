package src.lld.vendingmachine;

public class VendingMachine {
    Inventory inventory;
    private double totalCash;
    private static VendingMachine machine;
    public State currentState,defaultState,coinInsertedState,productSelectedState;
    private double bufferCash;
    private int selectedAisle;

    private VendingMachine(){
        totalCash = 0;
        bufferCash = 0;
        defaultState = new DefaultState(this);
        coinInsertedState = new CoinInsertedState(this);
        productSelectedState = new ProductSelectedState(this);
        currentState = defaultState;
        inventory = new Inventory(2);
    }

    public double getBufferCash(){
        return bufferCash;
    }

    public void setCurrentState(State state){
        this.currentState = state;
    }

    public void setBufferCash(double cash){
        this.bufferCash = cash;
    }

    public static VendingMachine getInstance(){
        if(machine==null){
            machine = new VendingMachine();
        }
        return machine;
    }

    public void load(Item item, int quantity){
        inventory.load(item,quantity);
    }

    public void insertCash(double cash){
        currentState.insertCash(cash);
    }

    public void pressButton(int aisle){
        currentState.pressButton(aisle);
        currentState.dispense();
    }

    public int getSelectedAisle(){
        return this.selectedAisle;
    }

    public void setSelectedAisle(int aisle){
        this.selectedAisle = aisle;
    }

    public double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(double totalCash) {
        this.totalCash = totalCash;
    }
}
