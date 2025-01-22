package src.lld.designpatterns.strategy;

import src.lld.vendingmachine.Item;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    Payment payment;

    private List<Item> items;

    ShoppingCart(){
        items = new ArrayList();
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
