package sequence;

public interface Sequence<D> {
    public D here();
    public boolean isEmpty();
    public Sequence next();
}