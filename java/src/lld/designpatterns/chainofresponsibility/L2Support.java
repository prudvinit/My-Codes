package src.lld.designpatterns.chainofresponsibility;

public class L2Support implements Handler {

    Handler next;

    public L2Support(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(Request level) {
        if(level.getPriority().equalsIgnoreCase("SEV2")){
            System.out.println("Handled by L2 Support");
        }
        else if (next!=null){
            next.handleRequest(level);
        }
    }
}
