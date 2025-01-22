package src.lld.vendingmachine;

public class Item {
    protected String name,description;
    protected double price;

    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
