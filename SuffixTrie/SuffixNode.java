package SuffixTrie;
import PatriciaTree.Node;
/**
 * Created by luism on 16-05-15.
 */

/**
 * Son los nodos del suffixTrie, guardan la referencia de los arcos donde se guarda un caracter y los hijos del nodo
 */
public class SuffixNode extends Node{

    private boolean mark;
    public SuffixNode(){
        super();
        mark=false;
    }

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

}
