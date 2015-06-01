package PatriciaTree;

import java.util.ArrayList;

/**
 * Created by luism on 21-05-15.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<String>();
        PatriciaTrees tree = new PatriciaTrees();
        words.add("hola");
        words.add("hola");
        words.add("hoja");
        words.add("hole");
        words.add("holame");
        words.add("holame");
        //words.add("un");
        //words.add("hole");
        //words.add("facasdfs");
        words.add("faces");
        String s = "looked down to see my editor had sent back comments about my column";
        String[] splits = s.split(" ");
        for (String s1 : splits) {
            words.add(s1);
        }
        for (String word : words) {
            tree.insert(tree.getRoot(), word, word, "", words.indexOf(word));
        }
        System.out.println(tree.search(tree.getRoot(), "hola", "hola", ""));
        System.out.println(tree.search(tree.getRoot(), "hoja", "hoja", ""));
        System.out.println(tree.search(tree.getRoot(), "holame", "holame", ""));
        System.out.println(tree.search(tree.getRoot(), "hol", "hol", ""));
        System.out.println(tree.search(tree.getRoot(), "hole", "hole", ""));
        System.out.println(tree.search(tree.getRoot(), "faces", "faces", ""));
        for (String s2 : splits) {
            System.out.println(s2 + ": " + tree.search(tree.getRoot(), s2, s2, ""));
        }
    }
}

