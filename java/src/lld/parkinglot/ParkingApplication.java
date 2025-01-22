package src.lld.parkinglot;

public class ParkingApplication {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(new Level[]{new Level(0,5,10,17)});
//        parkingLot.display();
        Vehicle bus1 = new Bus("B1");

        parkingLot.park(bus1);
        for(int i=0;i<11;i++){
            parkingLot.park(new Car("C"+i));
        }
        parkingLot.unpark(bus1);
        parkingLot.park(new Car("c11"));
        parkingLot.display();
    }
}
