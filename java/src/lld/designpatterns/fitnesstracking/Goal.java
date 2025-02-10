package src.lld.designpatterns.fitnesstracking;

public class Goal {
    double goalValue;
    Goal(double goalValue){
        this.goalValue = goalValue;
    }

    boolean hasGoalReached(double value){
        return value>=goalValue;
    }

    double getGoalValue(){
        return this.goalValue;
    }

    double getPercentageAchieved(double value){
        return value*100d/this.goalValue;
    }
}
