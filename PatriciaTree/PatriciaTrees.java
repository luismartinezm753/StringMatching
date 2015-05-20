import java.lang.String;
import java.util.ArrayList;
package PatriciaTree;
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
    public void reinsert(String insertar, String acumulado, int position, Node nodo){
        String prefix=greatestCommonPrefix(insertar,acumulado);
        String sufix=insertar.substring(prefix.length() - 1, insertar.length());
        Edge edge=nodo.containsString(prefix);
        if (edge == null) {
            Node newNodo=new Node();
            Node leaf=new Node();
            Edge emptyEdge=new Edge("");
            nodo.addNode(leaf);
            nodo.addEdge(emptyEdge);
            emptyEdge.setPositions(nodo.getPositions);
            nodo.setPositions(null);
            Edge newEdge=new Edge(insertar);
            nodo.addNode(newNodo);
            nodo.addEdge(newEdge);
            newNodo.addPosition(position);
        }else{

        }

    }
    public String greatestCommonPrefix(String a, String b) {
        int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return a.substring(0, i);
            }
        }
        return a.substring(0, minLength);
    }
}
