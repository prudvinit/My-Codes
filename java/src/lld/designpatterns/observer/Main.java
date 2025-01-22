package src.lld.designpatterns.observer;

public class Main {
    public static void main(String[] args) {
        StockPriceNotifier notifier = new StockPriceNotifier();
        Observer observer = new EmailObserver(notifier);
        notifier.updatePrice(-9);
        notifier.updatePrice(100);
        notifier.updatePrice(100);
        notifier.unregister(observer);
        notifier.updatePrice(101);

    }
}
