package SuffixTrie;

/**
 * Created by luism on 29-05-15.
 */
public class SuffixLinks {

    private SuffixNode init;
    private SuffixNode end;
    public SuffixLinks(SuffixNode init,SuffixNode end){
        this.init=init;
        this.end=end;
    }
    public SuffixNode getInit() {
        return init;
    }

    public void setInit(SuffixNode init) {
        this.init = init;
    }
    public SuffixNode getEnd() {
        return end;
    }

    public void setEnd(SuffixNode end) {
        this.end = end;
    }
}
