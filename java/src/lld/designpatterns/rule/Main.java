package src.lld.designpatterns.rule;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<BasketRule> rules = new ArrayList();
        rules.add(new HalloweenPromotion());
        Basket basket = new Basket();
        rules.stream().filter(x->x.matches(basket)).forEach(x->x.apply(basket));
        rules.stream().filter(x->x.matches(basket)).findFirst().get();
    }
}
