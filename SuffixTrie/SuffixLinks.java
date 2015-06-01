package SuffixTrie;

import PatriciaTree.Node;

/**
 * Created by luism on 29-05-15.
 */
public class SuffixLinks {

    private Node init;
    private Node end;
    public SuffixLinks(Node init, Node end){
        this.init=init;
        this.end=end;
    }
    public Node getInit() {
        return init;
    }

    public void setInit(Node init) {
        this.init = init;
    }
    public Node getEnd() {
        return end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }
}
