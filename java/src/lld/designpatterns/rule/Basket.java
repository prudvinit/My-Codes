package src.lld.designpatterns.rule;

import src.lld.vendingmachine.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    LocalDateTime created;
    List<Item> itemList;
    private double discount;
    Basket(){
        itemList = new ArrayList();
    }

    void addItem(Item item){
        itemList.add(item);
    }

    void setDiscount(double discount){
        this.discount = discount;
    }
}
