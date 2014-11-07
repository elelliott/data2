package sequence;

public class SeqCat<D> implements Sequence<D> {
    Sequence<D> left;
    Sequence<D> right;
    
    public SeqCat(Sequence<D> l, Sequence<D> r) {
        this.left = l;
        this.right = r;
    }
    
    public D here() {
        if (!this.left.isEmpty()) {
            return this.left.here();
        } else {
            return this.right.here();
        }
    }
    
    public Sequence next() {
        if (!this.left.isEmpty()) {
            return new SeqCat(this.left.next(), this.right);
        } else {
            return this.right.next();
        }
    }
    
    public boolean isEmpty() {
        return this.left.isEmpty() && this.right.isEmpty();
    }
    
    
}