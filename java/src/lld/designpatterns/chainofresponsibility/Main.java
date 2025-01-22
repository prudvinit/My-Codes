package src.lld.designpatterns.chainofresponsibility;

public class Main {
    public static void main(String[] args) {

        /**
         * Chain of responsibility is a behavioral design pattern. It allows an object to be pass a request along a chain of handlers.
         * Each handler can either process the request , or pass it along the chain
         */
        Handler l3Support = new L3Support();
        Handler l2Support = new L2Support(l3Support);
        Handler l1Support = new L1Support(l2Support);

        l1Support.handleRequest(new Request("SEV2"));

    }
}
