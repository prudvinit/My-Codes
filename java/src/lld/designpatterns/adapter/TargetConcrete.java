package src.lld.designpatterns.adapter;


public class TargetConcrete implements Target {

    @Override
    public void targetMethod() {
        System.out.println("Target concrete method");
    }
}
