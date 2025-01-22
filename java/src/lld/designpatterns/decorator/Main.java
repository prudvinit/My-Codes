package src.lld.designpatterns.decorator;

public class Main {
    public static void main(String[] args) {
        /**Decorator is a structural design pattern , allows behavior to be added to individual objects dynamically
         * without affecting the behaviour of other objects from same class
         */
        Coffee cappucino = new CappucinoCoffee();
        Coffee iceCoffeeDecorator = new IceCoffeeDecorator(cappucino);
        System.out.println(iceCoffeeDecorator.getDescription()+" : "+iceCoffeeDecorator.getCost());
    }
}
