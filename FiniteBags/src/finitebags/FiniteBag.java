package finitebags;

public interface FiniteBag<D extends Iterable> {
    
    // METHODS
    FiniteBag<D> empty();
    int cardinality();
    boolean isEmpty();
    boolean member(D elt);
    FiniteBag<D> add(D elt);
    FiniteBag<D> remove(D elt);
    boolean equal(FiniteBag<D> b);
    
}
