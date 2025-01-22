package src.lld.designpatterns.decorator;

import java.util.Collection;

public class CappucinoCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Nice hot cappucino.";
    }

    @Override
    public Double getCost() {
        return 5d;
    }
}
