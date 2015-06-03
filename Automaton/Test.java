package Automaton;

/**
 * Created by milenkotomic on 02-06-15.
 */
public class Test {
    public static void main(String[] args) {
        String s = "Blatter did not say when the election would be held but said it should before the next World Congress".toLowerCase();
        Automaton a = new Automaton("the");
        System.out.println(a.search(s));
    }
}
