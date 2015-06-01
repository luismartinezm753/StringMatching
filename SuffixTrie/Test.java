package SuffixTrie;

import java.util.ArrayList;

/**
 * Created by luism on 01-06-15.
 */
public class Test {
    public static void main(String[] args) {
        SuffixTrie trie=new SuffixTrie();
        ArrayList<String> words = trie.generateSuffix("banana");
        int i=0;
        for (String word : words){
            trie.insert(trie.getRoot(),word, word, "", i);
            i++;
        }
    }
}
