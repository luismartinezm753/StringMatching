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
        //words.add("hole");
        words.add("holame");
        //words.add("un");
        //words.add("hole");
        //words.add("facasdfs");
        //words.add("faces");
        for (String word : words){
            tree.insert(tree.getRoot(), word, word, "", words.indexOf(word));
        }
        System.out.println(tree.search(tree.getRoot(),"hola","hola",""));
        System.out.println(tree.search(tree.getRoot(),"hoja","hoja",""));
        System.out.println(tree.search(tree.getRoot(),"holame","holame",""));
        //System.out.println(tree.search(tree.getRoot(),"hole","hole",""));
        //System.out.println(tree.search(tree.getRoot(),"faces","faces",""));
    }
}

