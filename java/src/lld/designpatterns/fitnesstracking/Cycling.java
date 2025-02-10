package src.lld.designpatterns.fitnesstracking;

public class Cycling extends Activity {
    Cycling(){
    }

    void takeARide(int dist){
        this.value+=dist;
    }

    public String toString(){
        return "Cycled "+this.value+" kms";
    }
}
