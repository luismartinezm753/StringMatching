package PatriciaTree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by luism on 21-05-15.
 */
public class Test {
    public static void main(String[] args) {
        PatriciaTrees tree = new PatriciaTrees();
        String text="qwertasdsf qwertyfg qwertyu qwerty qwer qwerz qwer qwer mjkl";
        String[] tokens = text.split(" ");
        int i=0;
        for (String word : tokens) {
            tree.insert(tree.getRoot(), word, word, "", i);
            i++;
        }
        for (String s3 : tokens){
            System.out.println(s3 + ": " + tree.search(tree.getRoot(), s3, s3, ""));
        }
    }
}

