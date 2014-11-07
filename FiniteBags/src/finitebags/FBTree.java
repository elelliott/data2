package finitebags;
import sequence.*;

public class FBTree<D extends Comparable> implements FiniteBag<D>, Sequence<D> {

    D root;
    int count;
    char color;
    FiniteBag<D> left, right;

    FBTree(char color, D root, FiniteBag<D> left, FiniteBag<D> right) {
        this(color, root, 1, left, right);
    }

    protected FBTree(char color, D root, int count,
            FiniteBag<D> left, FiniteBag<D> right) {
        this.root = root;
        this.count = count;
        this.left = left;
        this.right = right;
    }

    public FiniteBag<D> empty() {
        return new FBEmpty();
    }

    public int cardinality() {
        return 1 + this.left.cardinality() + this.right.cardinality();
    }

    public int countElt(D elt) {
        if (this.root.equals(elt)) {
            return this.count;
        } else if (this.left.member(elt)) {
            return this.left.countElt(elt);
        } else {
            return this.right.countElt(elt);
        }
    }

    public char getColor() {
        return this.color;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean member(D elt) {
        if (this.root.equals(elt)) {
            return true;
        } else if (this.root.compareTo(elt) > 0) {
            return this.left.member(elt);
        } else {
            return this.right.member(elt);
        }
    }

    public boolean equal(FiniteBag b) {
        return this.subset(b) && b.subset(this);
    }

    public boolean subset(FiniteBag b) {
        return b.member(this.root) && this.left.subset(b) && this.right.subset(b);
    }

    public FiniteBag<D> add(D elt) {
        return this.addMultiple(elt, 1);
    }

    public FBTree addInner(D elt, int count) {
        if (this.root.equals(elt)) {
            return new FBTree(this.color, this.root, count,
                    this.left, this.right);
        } else if (this.root.compareTo(elt) > 0) {
            return balance(this.color, this.root, this.count,
                    this.left.addInner(elt, count), this.right);
        } else {
            return balance(this.color, this.root, this.count,
                    this.left, this.right.addInner(elt, count));
        }
    }

    public FiniteBag<D> blacken() {
        return new FBTree('b', this.root, this.count, this.left, this.right);
    }

    public FBTree balance(char color, D elt, int count,
            FiniteBag left, FiniteBag right) {
        // Case: T(B, k, v, T(R, k, v, T(R, k, v, l, r), r), r)
        if (color == 'b'
                && left instanceof FBTree
                && ((FBTree) left).getColor() == 'r'
                && ((FBTree) left).left instanceof FBTree
                && ((FBTree) ((FBTree) left).left).getColor() == 'r') {

            FBTree aL = (FBTree) left;
            // left.left
            FBTree bL = (FBTree) aL.left;

            return new FBTree('r', aL.root, aL.count,
                    new FBTree('b', bL.root, bL.count, bL.left, bL.right),
                    new FBTree('b', elt, count, aL.right, right));

        } else if (color == 'b'
                && left instanceof FBTree
                && ((FBTree) left).getColor() == 'r'
                && ((FBTree) left).left instanceof FBTree
                && ((FBTree) ((FBTree) left).right).getColor() == 'r') {

            FBTree aL = (FBTree) left;
            // left.left
            FBTree bL = (FBTree) aL.left;
            // left.right
            FBTree bR = (FBTree) aL.right;

            return new FBTree('r', bR.root, bR.count,
                    new FBTree('b', aL.root, aL.count, bL.left, bR.left),
                    new FBTree('b', elt, count, bR.right, right));

        } else if (color == 'b'
                && right instanceof FBTree
                && ((FBTree) right).getColor() == 'r'
                && ((FBTree) right).left instanceof FBTree
                && ((FBTree) ((FBTree) right).left).getColor() == 'r') {

            FBTree aR = (FBTree) right;
            // right.left
            FBTree bL = (FBTree) aR.left;
            // right.right
            FBTree bR = (FBTree) aR.right;

            return new FBTree('r', bL.root, bL.count,
                    new FBTree('b', elt, count, left, bL.left),
                    new FBTree('b', aR.root, aR.count, bL.right, bR));

        } else if (color == 'b'
                && right instanceof FBTree
                && ((FBTree) right).getColor() == 'r'
                && ((FBTree) right).right instanceof FBTree
                && ((FBTree) ((FBTree) right).right).getColor() == 'r') {

            FBTree aR = (FBTree) right;
            // right.left
            FBTree bL = (FBTree) aR.left;
            // right.right
            FBTree bR = (FBTree) aR.right;

            return new FBTree('r', aR.root, aR.count,
                    new FBTree('b', elt, count, left, bL.left),
                    new FBTree('b', bR.root, bR.count, bR.left, bR.right));

        } else {
            return new FBTree(color, elt, count, left, right);
        }
    }

    public FiniteBag<D> addMultiple(D elt, int count) {
        return this.addInner(elt, count).blacken();
    }

    public FiniteBag<D> remove(D elt) {
        return this.removeMultiple(elt, 1);
    }

    public FiniteBag<D> removeMultiple(D elt, int count) {
        if (this.member(elt)) {
            if (this.root.equals(elt)) {
                if (this.countElt(elt) - count > 0) {
                    return this.left.union(this.right).addMultiple(elt,
                            this.countElt(elt) - count);
                } else {
                    return this.left.union(this.right);
                }
            } else if (this.root.compareTo(elt) > 0) {
                return new FBTree(this.color, this.root,
                        this.left.removeMultiple(elt, count), this.right);
            } else {
                return new FBTree(this.color, this.root,
                        this.left, this.right.removeMultiple(elt, count));
            }
        } else {
            return this;
        }
    }

    public FiniteBag<D> removeAll(D elt) {
        return this.removeMultiple(elt, this.countElt(elt));
    }

    public FiniteBag<D> union(FiniteBag b) {
        return this.left.union(this.right).union(b).addMultiple(this.root,
                this.countElt(this.root));
    }

    public FiniteBag<D> inter(FiniteBag b) {
        if (b.member(this.root)) {
            return new FBTree(this.color, this.root, this.count,
                    this.left.inter(b), this.right.inter(b));
        } else {
            return this.left.inter(b).union(this.right.inter(b));
        }
    }

    public FiniteBag<D> diff(FiniteBag b) {
        if (b.member(this.root)) {
            return this.left.union(this.right).diff(b.removeMultiple(this.root,
                    b.countElt(this.root)));
        } else {
            return this.left.union(this.right).diff(b);
        }
    }
    
    // Sequence stuff
    
    public Sequence seq() {
        return this;
    }
    
    public D here() {
        return this.root;
    }
    
    public Sequence next() {
        return new SeqCat(this.left.seq(), this.right.seq());
    }

}
