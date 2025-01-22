package src.lld.designpatterns.observer;

import java.util.ArrayList;
import java.util.List;

public class StockPriceNotifier implements Subject{

    List<Observer> observerList;
    private int price;

    StockPriceNotifier(){
        observerList = new ArrayList();
    }

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    public void updatePrice(int price){
        if(price>this.price){
            this.price = price;
            notifyObservers();
        }
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer : observerList){
            observer.notify(this.price);
        }
    }
}
