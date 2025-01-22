package src.lld.parkinglot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Level {
    ParkingSpot[] motorSpots,compactSpots,largeSpots;
    private int floor;
    private final int SPOTS_PER_ROW = 10;
    private int remainingSpots;
    public Level(int floor, int m, int c, int l){
        this.floor = floor;
        motorSpots = new ParkingSpot[m];
        compactSpots = new ParkingSpot[c];
        largeSpots = new ParkingSpot[l];
        remainingSpots = m+c+l;
        init();
    }
    private void init(){
        for(int i=0;i<motorSpots.length;i++){
            motorSpots[i] = new ParkingSpot(this,i/SPOTS_PER_ROW,i-(i/SPOTS_PER_ROW)*SPOTS_PER_ROW,Size.SMALL);
        }
        for(int i=0;i<compactSpots.length;i++){
            compactSpots[i] = new ParkingSpot(this,i/SPOTS_PER_ROW,i-(i/SPOTS_PER_ROW)*SPOTS_PER_ROW,Size.COMPACT);
        }
        for(int i=0;i<largeSpots.length;i++){
            largeSpots[i] = new ParkingSpot(this,i/SPOTS_PER_ROW,i-(i/SPOTS_PER_ROW)*SPOTS_PER_ROW,Size.LARGE);
        }
    }

    private List<ParkingSpot> getAvailableSpots(ParkingSpot[] spots, Vehicle v){
        for(int i=0;i<spots.length;i++){
            boolean success = true;
            Set<Integer> rows = new HashSet();
            if(i+v.getSpotsNeeded()-1>=spots.length)continue;
            for(int j=i;j<i+v.getSpotsNeeded();j++){
                if(!spots[j].canFitVehicle(v)){
                    success = false;
                    break;
                }
                else{
                    rows.add(spots[j].getRow());
                }
            }
            success = success && rows.size()==1;
            if(success){
                List<ParkingSpot> ans = new ArrayList();
                for(int k=0;k<v.getSpotsNeeded();k++){
                    ans.add(spots[i+k]);
                }
                return ans;
            }
        }
        return null;
    }

    private List<ParkingSpot> getAvailableSpots(Vehicle v){
        List<ParkingSpot> spots = getAvailableSpots(motorSpots,v);
        if(spots!=null)return spots;
        spots = getAvailableSpots(compactSpots,v);
        if(spots!=null)return spots;
        spots = getAvailableSpots(largeSpots,v);
        return spots;
    }

    private boolean isAvailable(ParkingSpot[] spots, Vehicle v){
        return getAvailableSpots(spots,v)!=null;
    }

    public boolean available(Vehicle v){
        return isAvailable(motorSpots,v) || isAvailable(compactSpots,v) || isAvailable(largeSpots,v);
    }

    public List<ParkingSpot> park(Vehicle v){
        if(!available(v))return null;
        List<ParkingSpot> available = getAvailableSpots(v);
        for(ParkingSpot spot : available){
            spot.park(v);
        }
        remainingSpots = remainingSpots-available.size();
        return available;
    }

    public void unpark(Vehicle v){
        for(ParkingSpot spot : v.getParkingSpots()){
            spot.unpark();
        }
        remainingSpots += v.getParkingSpots().size();
        v.unpark();
    }

    public int floor(){
        return floor;
    }

    @Override
    public String toString(){
        String ans = "MOTOR\n";
        int c = 0;
        for(ParkingSpot spot : motorSpots){
            if(c!=0&&c%SPOTS_PER_ROW==0){
                ans = ans+"\n";
            }
            ans = ans+" "+spot.toString();
            c++;
        }
        ans = ans+"\nCOMPACT\n";
        c = 0;
        for(ParkingSpot spot : compactSpots){
            if(c!=0&&c%SPOTS_PER_ROW==0){
                ans = ans+"\n";
            }
            ans = ans+" "+spot.toString();
            c++;
        }
        ans = ans+"\nLARGE\n";
        c = 0;
        for(ParkingSpot spot : largeSpots){
            if(c!=0&&c%SPOTS_PER_ROW==0){
                ans = ans+"\n";
            }
            ans = ans+" "+spot.toString();
            c++;
        }

        return ans;
    }
}
