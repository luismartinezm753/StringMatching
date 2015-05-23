package PatriciaTree;

import java.util.ArrayList;

/**
 * Created by luism on 21-05-15.
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<String> words=new ArrayList<String>();
        PatriciaTrees tree=new PatriciaTrees();
        words.add("hola");
        words.add("hoja");
        //words.add("un");
        //words.add("facasdfs");
        //words.add("faces");
        for (String word : words){
            tree.insert(tree.getRoot(), word, word, "", words.indexOf(word));
        }
        System.out.println(tree.search(tree.getRoot(),"hola","hola", ""));
    }
}

