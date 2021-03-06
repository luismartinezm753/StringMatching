package PatriciaTree;

import SuffixTrie.CompactCharSequence;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by luism on 21-05-15.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        PatriciaTrees tree = new PatriciaTrees();

        //String text="qwertasdsf$ qwertyfg$ qwertyu$ qwer$ qwerz$ qwer$ qwer$ mjkl$";
        //String text="qwertasdsf qwertyfg qwertyu qwer qwerz qwer qwer mjkl";
        //String[] tokens = text.split(" ");
        ArrayList<String> words = new ArrayList<String>();
        words.add("hola$");
        words.add("hoja$");
        words.add("hole$");
        words.add("holame$");
        words.add("un$");
        words.add("faces$");
        words.add("hole$");
        words.add("and$");
        words.add("ande$");
        words.add("and$");
        String s = "see my about my column";
        String[] splits = s.split(" ");
        for (String s1 : splits) {
            words.add(s1+"$");
        }

        int i=0;
        for (String word : words) {
            System.out.println("insertando: " + word);
            tree.insert(tree.getRoot(), new CompactCharSequence(word), new CompactCharSequence(word), new CompactCharSequence(""), i);
            i++;
        }
        for (String s3 : words){
            System.out.println(s3 + ": " + tree.search(tree.getRoot(), new CompactCharSequence(s3), new CompactCharSequence(s3), new CompactCharSequence("")));
        }
        System.out.println(words.size());
    }
}

