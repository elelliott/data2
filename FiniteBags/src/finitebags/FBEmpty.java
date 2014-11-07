package finitebags;
import sequence.*;

public class FBEmpty<D extends Comparable> implements FiniteBag<D>, Sequence<D> {
    
    
    FBEmpty() { }
    
    public FiniteBag<D> empty() {
        return new FBEmpty();
    }
    
    public int cardinality() {
        return 0;
    }
    
    public int countElt(D elt) {
        return 0;
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public boolean member(D elt) {
        return false;
    }

    public boolean equal(FiniteBag b) {
        if (b.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean subset(FiniteBag b) {
        return true;
    }
    
    public FiniteBag<D> add(D elt) {
        return this.addMultiple(elt, 1);
    }
    
    public FiniteBag<D> addMultiple(D elt, int count) {
        return this.addInner(elt, count).blacken();
    }
    
    public FiniteBag<D> blacken() {
        return this;
    }
    
    public FiniteBag<D> addInner(D elt, int count) {
        return new FBTree('r', elt, count, empty(), empty());
    }
    
    public FiniteBag<D> remove(D elt) {
        return this;
    }
    
    public FiniteBag<D> removeMultiple(D elt, int count) {
        return this;
    }
    
    public FiniteBag<D> union(FiniteBag b) {
        return b;
    }
    
    public FiniteBag<D> inter(FiniteBag b) {
        return empty();
    }
    
    public FiniteBag<D> diff(FiniteBag b) {
        return b;
    }
    
    // Sequence stuff
    
    public Sequence seq() {
        return this;
    }
    
    public D here() {
        return null;
    }
    
    public Sequence next() {
        return this;
    }
}