package src.lld.parkinglot;

public class MotorCycle extends Vehicle {

    public MotorCycle(String licensePlate) {
        super(licensePlate,1);
    }

    @Override
    protected Size getSize() {
        return Size.SMALL;
    }

    @Override
    boolean canFitInSpot(ParkingSpot spot) {
        return true;
    }

    @Override
    void print() {
        System.out.println("Motor Cycle - "+getLicensePlate());
    }
}
