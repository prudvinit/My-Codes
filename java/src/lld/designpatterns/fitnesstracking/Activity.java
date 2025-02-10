package src.lld.designpatterns.fitnesstracking;

public abstract class Activity{

    protected int value;

    private Goal goal;

    Activity(){

    }

    Activity(Goal goal){
        this.goal = goal;
    }

    double totalValue(){
        return this.value;
    }

    public Goal getGoal(){
        return this.goal;
    }

    void addGoal(double goalValue){
        this.goal = new Goal(goalValue) ;
    }

}
