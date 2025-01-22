package src.lld.designpatterns.chainofresponsibility;

public class L1Support implements Handler {

    Handler next;

    public L1Support(Handler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(Request level) {
        if(level.getPriority().equalsIgnoreCase("SEV1")){
            System.out.println("Handled by L1 Support");
        }
        else if (next!=null){
            next.handleRequest(level);
        }
    }
}
