package src.lld.designpatterns.decorator;

import java.lang.annotation.Inherited;

public class IceCoffeeDecorator extends CoffeeDecorator {
    IceCoffeeDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription(){
        return "Iced "+super.getDescription();
    }

    @Override
    public Double getCost(){
        return 0.5d+super.getCost();
    }
}
