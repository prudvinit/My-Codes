package src.lld.vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private double cash;
    private List<Item> items;
    public Customer(String name, int age, double cash) {
        super(name, age);
        this.cash = cash;
        this.items = new ArrayList();
    }

    public void purchase(Item item, double price){
        if(cash>=price&&VendingMachine.getInstance().purchase(item,price)){
            items.add(item);
            cash-=price;
            System.out.println("Remaining balance is : "+cash);
        }
        else{
            System.out.println("Not enough balance");
        }
    }
}
