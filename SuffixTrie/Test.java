package SuffixTrie;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by luism on 01-06-15.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        SuffixTrie trie=new SuffixTrie();
        ArrayList<CompactCharSequence> words = trie.generateSuffix("banana and potatoe");
        int i=0;
        for (CompactCharSequence word : words){
            trie.insert(trie.getRoot(),word, word,new CompactCharSequence(""), i);
            i++;
        }
        System.out.println("word: "+new CompactCharSequence("Banana$")+" Result: "+trie.search(trie.getRoot(),new CompactCharSequence("Banana$"),new CompactCharSequence("Banana$"), new CompactCharSequence("")));
        System.out.println("word: "+new CompactCharSequence("potatoe$")+" Result: "+trie.search(trie.getRoot(),new CompactCharSequence("potatoe$"),new CompactCharSequence("potatoe$"), new CompactCharSequence("")));
        System.out.println("word: "+new CompactCharSequence("e$")+" Result: "+trie.search(trie.getRoot(),new CompactCharSequence("e$"),new CompactCharSequence("e$"), new CompactCharSequence("")));
    }
}
