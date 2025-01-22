package src.lld.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private Level levels[];
    public ParkingLot(Level levels[]){
        this.levels = levels;
    }

    public List<ParkingSpot> park(Vehicle vehicle){
        for(Level level : levels){
            if(level.available(vehicle)){
                 return level.park(vehicle);
            }
        }
        return null;
    }

    public void unpark(Vehicle v){
        levels[v.getParkingSpots().get(0).level.floor()].unpark(v);
    }

    public void display(){
        for(Level level : levels){
            System.out.println(level);
        }
    }
}
