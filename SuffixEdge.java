/**
 * Created by luism on 16-05-15.
 */

/**
 * Son los arcos del suffixTrie, son los que almacenan un caracter en especifico.
 */
public class SuffixEdge {
    private char s;

    public SuffixEdge(char s){
        this.s=s;
    }
    public char getS() {
        return s;
    }

    public void setS(char s) {
        this.s = s;
    }
}
