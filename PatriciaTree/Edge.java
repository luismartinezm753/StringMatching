package PatriciaTree;

import SuffixTrie.CompactCharSequence;

/**
 * Created by milenkotomic on 20-05-15.
 */

public class Edge {
    private CharSequence label;

    public Edge(String label) {
        this.label = new CompactCharSequence(label);
    }
    public Edge(CharSequence label){
        this.label=label;
    }

    public CharSequence getLabel() {
        return label;
    }

    public void setLabel(CompactCharSequence label) {
        this.label = label;
    }
}
