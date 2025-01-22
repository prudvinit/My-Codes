package src.lld.vendingmachine;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {
    Map<Item,Integer> inventory;
    private double totalCash;
    private static VendingMachine machine;

    private VendingMachine(){
        inventory = new HashMap();
        totalCash = 0;
    }

    public static VendingMachine getInstance(){
        if(machine==null){
            machine = new VendingMachine();
        }
        return machine;
    }

    public void show(){
        for(Item item : inventory.keySet()){
            if(inventory.getOrDefault(item,0)>0) {
                System.out.println("Item : " + item.name + ", Price : " + item.price + ", " + item.description + " ");
            }
        }
    }

    public void load(Item item, int quantity){
        inventory.put(item,inventory.getOrDefault(item,0)+1);
    }

    public boolean purchase(Item i, double cash){
        if(inventory.getOrDefault(i,0)==0){
            System.out.println("No item in stock!");
            return false;
        }
        else if(cash<i.price){
            System.out.println("Insufficient cash. Please insert "+i.price);
            return false;
        }
        inventory.put(i,inventory.get(i)-1);
        totalCash+=i.price;
        System.out.println("Please collect your item "+i.name);
        if(cash>i.price){
            System.out.println("Please collect your change "+(cash-i.price)+"$");
        }
        return true;
    }
}
