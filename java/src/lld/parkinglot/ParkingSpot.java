package src.lld.parkinglot;

public class ParkingSpot {
    protected Vehicle vehicle;
    protected Level level;
    private Size size;
    private int row,spotNumber;

    public ParkingSpot(Level level, int row, int spotNumber, Size size) {
        this.level = level;
        this.size = size;
        this.row = row;
        this.spotNumber = spotNumber;
    }

    public ParkingSpot(Size size){
        this.size = size;
    }

    public Size getSize(){
        return this.size;
    }

    public boolean isAvailable(){
        return vehicle == null;
    }

    public boolean canFitVehicle(Vehicle v){
        return isAvailable()&&v.canFitInSpot(this);
    }

    public boolean park(Vehicle v){
        if(!canFitVehicle(v))return false;
        vehicle = v;
        vehicle.park(this);
        return true;
    }

    public int getRow(){
        return this.row;
    }

    public void unpark(){
        this.vehicle = null;
    }

    public String toString(){
        return (vehicle!=null?vehicle.getLicensePlate():"__")+" ";
    }

}
