package src.lld.parkinglot;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    
    protected List<ParkingSpot> parkingSpots;
    private String licensePlate;
    private Size size;
    private int spotsNeeded;
    
    public Vehicle(String licensePlate, int spotsNeeded){
        this.licensePlate = licensePlate;
        parkingSpots = new ArrayList();
        this.spotsNeeded = spotsNeeded;
    }

    public int getSpotsNeeded(){
        return spotsNeeded;
    }

    public String getLicensePlate(){
        return this.licensePlate;
    }

    protected abstract Size getSize();

    public void park(ParkingSpot parkingSpot){
        parkingSpots.add(parkingSpot);
    }
    public void unpark(){
        parkingSpots.clear();
    }
    
    public List<ParkingSpot> getParkingSpots(){
        return this.parkingSpots;
                
    }

    abstract boolean canFitInSpot(ParkingSpot spot);

    abstract void print();
}
