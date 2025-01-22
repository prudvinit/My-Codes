package src.lld.designpatterns.rule;

public interface IRule<T> {
    boolean matches(T t);
    void apply(T t);
}
