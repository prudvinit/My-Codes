package src.lld.parkinglot;

public class Bus extends Vehicle {

    public Bus(String licensePlate) {
        super(licensePlate,5);
    }

    @Override
    protected Size getSize() {
        return Size.LARGE;
    }

    @Override
    boolean canFitInSpot(ParkingSpot spot) {
        return spot.getSize()==Size.LARGE;
    }

    @Override
    void print() {
        System.out.println("Bus - "+getLicensePlate());
    }
}
