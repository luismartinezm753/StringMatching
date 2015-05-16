import java.util.ArrayList;

/**
 * Created by luism on 16-05-15.
 */
public class SuffixTrie {
    SuffixNode root;
    public SuffixTrie(){
        root=new SuffixNode();
    }
    public boolean search(String s){
        return false;
    }
    public void insert(String s){

    }

    /**
     * Genera todos los sufijos de un string
     * @param s string
     * @return un lista con todos los sufijos de s
     */
    public ArrayList<String> generateSuffix(String s){
        ArrayList<String> suffixes=new ArrayList<String>();
        suffixes.add(s);
        for (int i = 0; i < s.length(); i++) {
            String sufix=s.substring(i,s.length());
            suffixes.add(sufix);
        }
        return suffixes;
    }
}
