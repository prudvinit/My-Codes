package src.lld.parkinglot;

public class Car extends Vehicle {
    public Car(String licensePlate) {
        super(licensePlate,1);
    }

    @Override
    protected Size getSize() {
        return Size.COMPACT;
    }

    @Override
    boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize()==Size.COMPACT || spot.getSize()==Size.LARGE;
    }

    @Override
    void print() {
        System.out.println("Car - "+getLicensePlate());
    }
}
