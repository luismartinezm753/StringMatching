import java.lang.String;

/**
 * Created by luism on 16-05-15.
 */
public class PatriciaTrees {
    private Node root;
    public PatriciaTrees(){
        root=new Node();
    }
    public void insert(Node nodo, String s){

    }
    public boolean search(Node nodo, String s){

    }
    public void reinsert(String s, String ,Node nodo, int index, int position){
        Edge edge=nodo.getEdge(index);
        Node child=nodo.getChild(index);
        String label=edge.getLabel();
    }
}
