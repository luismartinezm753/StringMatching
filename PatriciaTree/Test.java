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
        words.add("hoja");
        words.add("hole");
        words.add("holame");
        words.add("un");
        words.add("faces");
        words.add("hole");
        String s = "see my about my column";
        String[] splits = s.split(" ");
        for (String s1 : splits) {
            words.add(s1);
        }
        int i=0;
        for (String word : words) {
            tree.insert(tree.getRoot(), word, word, "", i);
            i++;
        }
        for (String s3 : words){
            System.out.println(s3 + ": " + tree.search(tree.getRoot(), s3, s3, ""));
        }
        for (String s2 : splits) {
            System.out.println(s2 + ": " + tree.search(tree.getRoot(), s2, s2, ""));
        }
    }
}

