/**
 * Created by luism on 16-05-15.
 */

/**
 * Son los arcos del suffixTrie, son los que almacenan un caracter en especifico.
 */
public class SuffixEdge {
    private String s;

    public SuffixEdge(String s){
        this.s=s;
    }
    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
