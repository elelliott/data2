package finitebags;

import java.util.Random;
import sequence.*;

public class FiniteBags {

    ///////////////////////
    /////// TESTING ///////
    ///////////////////////
    public static FiniteBag empty() {
        return new FBEmpty();
    }

    static Random randomizer = new Random();

    public static FiniteBag<Integer> randIntFB() {
        FiniteBag<Integer> tree = empty();
        int numElts = randomizer.nextInt() % 100;
        int countVal = randomizer.nextInt(5);
        for (int i = 0; i < numElts; i++) {
            tree = tree.addMultiple(randomizer.nextInt() % 50, countVal);
        }
        return tree;
    }

    public static String randomString() {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        StringBuffer str = new StringBuffer();
        int length = randomizer.nextInt() % 10;
        for (int i = 0; i < length; i++) {
            str.append(alpha.charAt(randomizer.nextInt(alpha.length())));
        }
        return str.toString();
    }

    public static FiniteBag<String> randStrFB() {
        FiniteBag<String> tree = empty();
        int numElts = randomizer.nextInt() % 100;
        int countVal = randomizer.nextInt() % 5;
        for (int i = 0; i < numElts; i++) {
            tree = tree.addMultiple(randomString(), countVal);
        }
        return tree;
    }

    //////////////////////////////////////////////
    /////// POLYMORPHIC FINITE SET TESTING ///////
    //////////////////////////////////////////////
    // for all x y s, x.union(y).subset(s) = x.subset(s) && y.subset(s)
    public static void checkUnionSubset(FiniteBag x, FiniteBag y, FiniteBag s) {
        for (int i = 0; i < 15; i++) {
            boolean answer = (x.union(y).subset(s) == (x.subset(s) && y.subset(s)));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all x y, max(x.cardinality(), y.cardinality()) <= 
    //           x.union(y).cardinality() <= x.cardinality() + y.cardinality()
    public static void checkUnionCard(FiniteBag x, FiniteBag y) {
        for (int i = 0; i < 15; i++) {
            int xlength = x.cardinality();
            int ylength = y.cardinality();
            boolean answer = ((Math.max(xlength, ylength) <= (x.union(y).cardinality()))
                    && (x.union(y).cardinality() <= (xlength + ylength)));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all t u x, t.inter(u).member(x) = t.member(x) && u.member(x)
    public static void checkMemberInterInt(FiniteBag<Integer> t, FiniteBag<Integer> u) {
        for (int i = 0; i < 15; i++) {
            // x % 50 to mirror element value restriction in randFB methods
            int x = randomizer.nextInt() % 50;
            boolean answer = (t.inter(u).member(x) == (t.member(x) && u.member(x)));
            System.out.println(answer + " should be " + true);
        }
    }

    public static void checkMemberInterString(FiniteBag<String> t, FiniteBag<String> u) {
        for (int i = 0; i < 15; i++) {
            // x % 50 to mirror element value restriction in randFB methods
            String x = randomString();
            boolean answer = (t.inter(u).member(x) == (t.member(x) && u.member(x)));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all t x y, t.add(x).member(y) = true <-> x = y \/ t.member(y) = true
    public static void checkMemberAddInt(FiniteBag<Integer> t) {
        for (int i = 0; i < 15; i++) {
            int x = randomizer.nextInt() % 50;
            int y = randomizer.nextInt() % 50;
            boolean answer = (t.add(x).member(y) == ((x == y) || t.member(y)));
            System.out.println(answer + " should be " + true);
        }
    }

    public static void checkMemberAddString(FiniteBag<String> t) {
        for (int i = 0; i < 15; i++) {
            String x = randomString();
            String y = randomString();
            boolean answer = (t.add(x).member(y) == ((x.equals(y)) || t.member(y)));
            System.out.println(answer + " should be " + true);
        }
    }

    // t.union(s).member(x) = true <-> s.member(x) = true \/ t.member(x) = true
    public static void checkMemberUnionInt(FiniteBag<Integer> s, FiniteBag<Integer> t) {
        for (int i = 0; i < 15; i++) {
            int x = randomizer.nextInt() % 50;
            boolean answer = (t.union(s).member(x) == (s.member(x) || t.member(x)));
            System.out.println(answer + " should be " + true);
        }
    }

    public static void checkMemberUnionString(FiniteBag<String> s, FiniteBag<String> t) {
        for (int i = 0; i < 15; i++) {
            String x = randomString();
            boolean answer = (t.union(s).member(x) == (s.member(x) || t.member(x)));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all t u, t.subset(u) = true <-> t.equal(u) || 
    //           t.diff(u).cardinality() == u.cardinality() - t.cardinality()
    public static void checkSubDiff(FiniteBag t, FiniteBag u) {
        for (int i = 0; i < 15; i++) {
            boolean answer = (t.subset(u) == (t.equal(u)
                    || t.diff(u).cardinality() == u.cardinality() - t.cardinality()));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all t u, t.subset(u) = true <-> t.inter(u).equal(t) = true
    public static void checkSubInter(FiniteBag t, FiniteBag u) {
        for (int i = 0; i < 15; i++) {
            boolean answer = (t.subset(u) == t.inter(u).equal(t));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all t u x, t.add(x).subset(u.removeAll(x)) = false
    public static void checkSubRemoveInt(FiniteBag<Integer> t, FiniteBag<Integer> u) {
        for (int i = 0; i < 15; i++) {
            int x = randomizer.nextInt() % 50;
            boolean answer = t.add(x).subset(u.removeAll(x));
            System.out.println(answer + " should be " + false);
        }
    }

    public static void checkSubRemoveString(FiniteBag<String> t, FiniteBag<String> u) {
        for (int i = 0; i < 15; i++) {
            String x = randomString();
            boolean answer = t.add(x).subset(u.removeAll(x));
            System.out.println(answer + " should be " + false);
        }
    }

    // for all t u, t.union(u).equal(t) && t.union(u).equal(u) <-> t.equal(u)
    public static void checkUnionEqual(FiniteBag t, FiniteBag u) {
        for (int i = 0; i < 15; i++) {
            boolean answer = ((t.union(u).equal(t) && t.union(u).equal(u))
                    == t.equal(u));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all t u, t.equal(u) = true <-> t.subset(u) && u.subset(t)
    public static void checkEqualSubset(FiniteBag t, FiniteBag u) {
        for (int i = 0; i < 15; i++) {
            boolean answer = (t.equal(u) == (t.subset(u) && u.subset(t)));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all t u, t.equal(u) = true <-> t.union(u).equal(t.inter(u))
    public static void checkUnionInter(FiniteBag t, FiniteBag u) {
        for (int i = 0; i < 15; i++) {
            boolean answer = (t.equal(u) == t.union(u).equal(t.inter(u)));
            System.out.println(answer + " should be " + true);
        }
    }

    // for all t, t.diff(t) = empty
    public static void checkDiffEmpty(FiniteBag t) {
        for (int i = 0; i < 15; i++) {
            boolean answer = t.diff(t).isEmpty();
            System.out.println(answer + " should be " + true);
        }
    }

    ////////////////////////////////////////////
    /////// POLYMORPHIC MULTISET TESTING ///////
    ////////////////////////////////////////////
    // for some bag t, elt x and count c, c + t.countElt(x)
    //     == t.addMultiple(x, c).countElt(x)
    public static void checkAddMultiInt(FiniteBag<Integer> t) {
        for (int i = 0; i < 15; i++) {
            int countVal = randomizer.nextInt(5);
            int x = randomizer.nextInt() % 50;
            boolean answer = t.addMultiple(x, countVal).countElt(x)
                    == countVal + t.countElt(x);
            System.out.println(answer + " should be " + true);
        }
    }

    public static void checkAddMultiStr(FiniteBag<String> t) {
        for (int i = 0; i < 15; i++) {
            int countVal = randomizer.nextInt(5);
            String x = randomString();
            boolean answer = t.addMultiple(x, countVal).countElt(x)
                    == countVal + t.countElt(x);
            System.out.println(answer + " should be " + true);
        }
    }

    // for bag t, elt x, count c, t.countElt(x) - c == 
    //     t.removeMultiple(x, c).countElt(X)
    public static void checkRemoveMultiInt(FiniteBag<Integer> t) {
        for (int i = 0; i < 15; i++) {
            int x = randomizer.nextInt() % 50;
            int countVal = 0;
            if (t.countElt(x) > 0) {
                countVal = randomizer.nextInt(t.countElt(x));
            }
            boolean answer = t.removeMultiple(x, countVal).countElt(x)
                    == t.countElt(x) - countVal;
            System.out.println(answer + " should be " + true);
        }
    }

    public static void checkRemoveMultiStr(FiniteBag<String> t) {
        for (int i = 0; i < 15; i++) {
            String x = randomString();
            int countVal = 0;
            if (t.countElt(x) > 0) {
                countVal = randomizer.nextInt(t.countElt(x));
            }
            boolean answer = t.removeMultiple(x, countVal).countElt(x)
                    == t.countElt(x) - countVal;
            System.out.println(answer + " should be " + true);
        }
    }

    // for bag t and elt x, t.removeAll(x).countElt(x) == 0
    public static void checkRemoveAllInt(FiniteBag<Integer> t) {
        for (int i = 0; i < 15; i++) {
            int x = randomizer.nextInt() % 50;
            boolean answer = t.removeAll(x).countElt(x) == 0;
            System.out.println(answer + " should be " + true);
        }
    }

    public static void checkRemoveAllStr(FiniteBag<String> t) {
        for (int i = 0; i < 15; i++) {
            String x = randomString();
            boolean answer = t.removeAll(x).countElt(x) == 0;
            System.out.println(answer + " should be " + true);
        }
    }

    // for bags t and u, elt x, counts c1 and c2,
    // c2 - (c2 - c1) == t.addMultiple(x, c1).inter(u.addMultiple(x, c2).countElt(x)
    public static void checkInterMultiInt(FiniteBag<Integer> t, FiniteBag<Integer> u) {
        for (int i = 0; i < 15; i++) {
            int x = randomizer.nextInt() % 50;
            int countValA = randomizer.nextInt(5);
            int countValB = randomizer.nextInt(5) + 5;
            boolean answer = t.addMultiple(x, countValA).inter(u.addMultiple(x,
                    countValB)).countElt(x) == countValB - (countValB - countValA);
            System.out.println(answer + " should be " + true);
        }
    }

    public static void checkInterMultiStr(FiniteBag<String> t, FiniteBag<String> u) {
        for (int i = 0; i < 15; i++) {
            String x = randomString();
            int countValA = randomizer.nextInt(5);
            int countValB = randomizer.nextInt(5) + 5;
            boolean answer = t.addMultiple(x, countValA).inter(u.addMultiple(x,
                    countValB)).countElt(x) == countValB - (countValB - countValA);
            System.out.println(answer + " should be " + true);
        }
    }

    ////////////////////////////////
    /////// SEQUENCE TESTING ///////
    ////////////////////////////////
    public static void summingTest() {
        for (int i = 0; i < 15; i++) {
            FiniteBag<Integer> tree = empty();
            int x = randomizer.nextInt() % 50;
            int y = randomizer.nextInt() % 50;
            int z = randomizer.nextInt() % 50;
            tree = tree.add(x).add(y).add(z);
            Sequence<Integer> seq = tree.seq();

            int sum = 0;
            while (!seq.isEmpty()) {
                sum += seq.here();
                seq = seq.next();
            }

            boolean answer = x + y + z == sum;
            System.out.println(answer + " should be " + true);
        }
    }

    public static void stringingTest() {
        for (int i = 0; i < 15; i++) {
            FiniteBag<String> tree = empty();
            String x = randomString();
            String y = randomString();
            String z = randomString();
            tree = tree.add(x).add(y).add(z);
            Sequence<String> seq = tree.seq();

            StringBuffer buf = new StringBuffer();
            while (!seq.isEmpty()) {
                buf.append(seq.here());
                seq = seq.next();
            }

            boolean answer = (x + y + z).equals(buf.toString());
            System.out.println(answer + " should be " + true);
        }
    }

    public static void main(String[] args) {
        ///////////////////////////////////
        // Polymorphic FiniteSet testing //
        ///////////////////////////////////

        System.out.println("Diff & isEmpty check:");
        checkDiffEmpty(randIntFB());
        checkDiffEmpty(randStrFB());

        System.out.println("Equal & Subset check:");
        checkEqualSubset(randIntFB(), randIntFB());
        checkEqualSubset(randStrFB(), randStrFB());

        System.out.println("Member & Add check:");
        checkMemberAddInt(randIntFB());
        checkMemberAddString(randStrFB());

        System.out.println("Member & Inter check:");
        checkMemberInterInt(randIntFB(), randIntFB());
        checkMemberInterString(randStrFB(), randStrFB());

        System.out.println("Member & Union check:");
        checkMemberUnionInt(randIntFB(), randIntFB());
        checkMemberUnionString(randStrFB(), randStrFB());

        System.out.println("Subset & Diff check:");
        checkSubDiff(randIntFB(), randIntFB());
        checkSubDiff(randStrFB(), randStrFB());

        System.out.println("Subset & Inter check:");
        checkSubInter(randIntFB(), randIntFB());
        checkSubInter(randStrFB(), randStrFB());

        System.out.println("Subset & Remove check:");
        checkSubRemoveInt(randIntFB(), randIntFB());
        checkSubRemoveString(randStrFB(), randStrFB());

        System.out.println("Union & Cardinality check:");
        checkUnionCard(randIntFB(), randIntFB());
        checkUnionCard(randStrFB(), randStrFB());

        System.out.println("Union & Equal check:");
        checkUnionEqual(randIntFB(), randIntFB());
        checkUnionEqual(randStrFB(), randStrFB());

        System.out.println("Union & Inter check:");
        checkUnionInter(randIntFB(), randIntFB());
        checkUnionInter(randStrFB(), randStrFB());

        System.out.println("Union & Subset check:");
        checkUnionSubset(randIntFB(), randIntFB(), randIntFB());
        checkUnionSubset(randStrFB(), randStrFB(), randStrFB());

        //////////////////////////////////
        // Polymorphic MultiSet testing //
        //////////////////////////////////
        System.out.println("\n\nPolymorphic Multiset Testing:");

        System.out.println("Add Multi check:");
        checkAddMultiInt(randIntFB());
        checkAddMultiStr(randStrFB());

        System.out.println("Remove Multi check:");
        checkRemoveMultiInt(randIntFB());
        checkRemoveMultiStr(randStrFB());

        System.out.println("Remove All check:");
        checkRemoveAllInt(randIntFB());
        checkRemoveAllStr(randStrFB());

        System.out.println("Inter & Add check:");
        checkInterMultiInt(randIntFB(), randIntFB());
        checkInterMultiStr(randStrFB(), randStrFB());

        //////////////////////
        // Sequence testing //
        //////////////////////
        System.out.println("\n\nSequence Testing:");

        System.out.println("Summing test:");
        summingTest();

        System.out.println("Stringing test:");
        stringingTest();
    }

}
