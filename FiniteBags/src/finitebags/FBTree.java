package finitebags;

public class FBTree<D extends Iterable> implements FiniteBag<D> {
    D root;
    FiniteBag<D> left, right;

    FBTree(D root, FiniteBag<D> left, FiniteBag<D> right) {
        this.root = root;
        this.left = left;
        this.right = right;
    }
    
    public FiniteBag<D> empty() {
        return new FBEmpty();
    }
    
    
    // IMPLEMENT
    public int cardinality() {
        return -1;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    // IMPLEMENT
    public boolean member(D elt) {
        return true;
    }
    
    // IMPLEMENT
    public FiniteBag<D> add(D elt) {
        return this;
    }
    
    // IMPLEMENT
    public FiniteBag<D> remove(D elt) {
        return this;
    }
    
    // IMPLEMENT
    public boolean equal(FiniteBag<D> b) {
        return false;
    }
    
}
