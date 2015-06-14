package SuffixTrie;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by luism on 01-06-15.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        SuffixTrie trie=new SuffixTrie();
        ArrayList<CompactCharSequence> words = trie.generateSuffix("banana and naranja with apple");
        int i=0;
        for (CompactCharSequence word : words){
            trie.insert(trie.getRoot(),word, word,new CompactCharSequence(""), i);
            i++;
        }
        boolean r=trie.searchTrie(trie.getRoot(),new CompactCharSequence("banana"),new CompactCharSequence("banana"), new CompactCharSequence(""));
        boolean r1=trie.searchTrie(trie.getRoot(),new CompactCharSequence("naranja"),new CompactCharSequence("naranja"), new CompactCharSequence(""));
        boolean r2=trie.searchTrie(trie.getRoot(),new CompactCharSequence("apple"),new CompactCharSequence("apple"), new CompactCharSequence(""));
        System.out.println(r);
        System.out.println(r1);
        System.out.println(r2);
        CompactCharSequence s=new CompactCharSequence("asdf");
        CompactCharSequence s1 = (CompactCharSequence) s.subSequence(0, 2);
        System.out.println(s1);
        System.out.println(s1.append("12"));
        //System.out.println("word: "+new CompactCharSequence("banana$")+" Result: "+trie.searchTrie(trie.getRoot(),new CompactCharSequence("banana$"),new CompactCharSequence("banana$"), new CompactCharSequence("")));
        //System.out.println("word: "+new CompactCharSequence("potatoe$")+" Result: "+trie.searchTrie(trie.getRoot(),new CompactCharSequence("potatoe$"),new CompactCharSequence("potatoe$"), new CompactCharSequence("")));
        //System.out.println("word: "+new CompactCharSequence("e$")+" Result: "+trie.searchTrie(trie.getRoot(),new CompactCharSequence("e$"),new CompactCharSequence("e$"), new CompactCharSequence("")));
    }
}
