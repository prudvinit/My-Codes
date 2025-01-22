package src.lld.designpatterns.decorator;

public abstract class CoffeeDecorator implements Coffee {

    Coffee coffee;

    CoffeeDecorator(Coffee coffee){
        this.coffee = coffee;
    }

    @Override
    public String getDescription(){
        return coffee.getDescription();
    }

    @Override
    public Double getCost(){
        return coffee.getCost();
    }
}
