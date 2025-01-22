package src.lld.designpatterns.chainofresponsibility;

public class L3Support implements Handler {

    public L3Support() {
    }

    @Override
    public void handleRequest(Request level) {
        if(level.getPriority().equalsIgnoreCase("SEV3")){
            System.out.println("Handled by L3 Support");
        }
        else{
            System.out.println("Invalid sev level");
        }
    }
}
