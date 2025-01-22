package src.lld.designpatterns.chainofresponsibility;

public class Request {
    private String priority;

    public Request(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
