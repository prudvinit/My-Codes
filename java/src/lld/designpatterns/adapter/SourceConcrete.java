package src.lld.designpatterns.adapter;

public class SourceConcrete implements Source {
    @Override
    public void sourceMethod() {
        System.out.println("Source method");
    }
}
