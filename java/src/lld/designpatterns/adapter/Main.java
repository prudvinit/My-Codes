package src.lld.designpatterns.adapter;

public class Main {

    static void clientMethod(Target target){
        target.targetMethod();
    }
    public static void main(String[] args) {
        Source source = new SourceConcrete();
        Target target = new SourceToTargetAdapter(source);
        clientMethod(target);
    }
}
