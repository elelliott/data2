package finitebags;

public class FBEmpty<D extends Iterable> implements FiniteBag<D> {
    
    FBEmpty() {  }
    
    public FiniteBag<D> empty() {
        return new FBEmpty();
    }
    
    public int cardinality() {
        return 0;
    }
    
    public boolean isEmpty() {
        return true;
    }
    
    public boolean member(D elt) {
        return false;
    }
    
    public FiniteBag<D> add(D elt) {
        return new FBTree(elt, new FBEmpty(), new FBEmpty());
    }
    
    public FiniteBag<D> remove(D elt) {
        return this;
    }
    
    public boolean equal(FiniteBag b) {
        if (b.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}