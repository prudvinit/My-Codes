package src.lld.designpatterns.adapter;

public class SourceToTargetAdapter implements Target {

    Source source;

    SourceToTargetAdapter(Source source){
        this.source = source;
    }

    @Override
    public void targetMethod() {
        source.sourceMethod();
    }
}
