package finitebags;

import java.util.Random;

public class FiniteBags {

    ///////////////////////
    /////// TESTING ///////
    ///////////////////////
    public static FiniteBag empty() {
        return new FBEmpty();
    }

    static Random randomizer = new Random();

    public static FiniteBag randIntFB() {
        FiniteBag<Integer> tree = empty();
        int numElts = randomizer.nextInt() % 100;
        int countVal = randomizer.nextInt() % 10;
        for (int i = 0; i < numElts; i++) {
            tree = tree.addMultiple(randomizer.nextInt() % 50, countVal);
        }
        return tree;
    }

    public static void main(String[] args) {

    }

}
