package src.lld.vendingmachine;

public interface State {
    void insertCash(double cash);
    void pressButton(int aisle);
    void dispense();
}
