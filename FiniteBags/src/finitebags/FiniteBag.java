package finitebags;

import sequence.*;

public interface FiniteBag<D extends Comparable> extends Sequenced<D> {
    
    // METHODS
    /* Sequence<D> seq(); */
    FiniteBag<D> empty();
    int cardinality();
    int countElt(D elt);
    boolean isEmpty();
    boolean member(D elt);
    boolean equal(FiniteBag<D> b);
    boolean subset(FiniteBag<D> b);
    FiniteBag<D> add(D elt);
    FiniteBag<D> addMultiple(D elt, int count);
    FiniteBag<D> addInner(D elt, int count);
    FiniteBag<D> blacken();
    FiniteBag<D> remove(D elt);
    FiniteBag<D> removeMultiple(D elt, int count);
    FiniteBag<D> removeAll(D elt);
    FiniteBag<D> union(FiniteBag<D> b);
    FiniteBag<D> inter(FiniteBag<D> b);
    FiniteBag<D> diff(FiniteBag<D> b);
    
}
