package src.lld.designpatterns.observer;

public class EmailObserver implements Observer {

    private Subject subject;

    public EmailObserver(Subject subject){
        this.subject = subject;
        subject.register(this);
    }

    @Override
    public void notify(int payload) {
        System.out.println("Stock price updated to "+payload+" sending an email.");
    }
}
