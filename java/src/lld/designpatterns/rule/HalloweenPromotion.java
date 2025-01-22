package src.lld.designpatterns.rule;

import src.lld.vendingmachine.Item;

import java.time.Month;

public class HalloweenPromotion implements BasketRule {
    @Override
    public boolean matches(Basket basket) {
        return basket.created.getMonth()== Month.OCTOBER;
    }

    @Override
    public void apply(Basket basket) {
        basket.setDiscount(5);
        basket.addItem(new Item("Mask","Halloween mask",5));
    }
}
