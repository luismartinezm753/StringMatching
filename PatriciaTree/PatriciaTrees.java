package PatriciaTree;

import java.lang.String;
import java.util.ArrayList;

/**
 * Created by luism on 16-05-15.
 */
public class PatriciaTrees {
    private Node root;

    public PatriciaTrees(){
        root=new Node();
    }

    /**
     *
     * @param node nodo que se esta visitando
     * @param s substring de p que queda por buscar
     * @param p string completo a insertar
     * @param position posicion de p en el arreglo de strings
     */

    public void insert(Node node, String s, String p, String path, int position){
        if (node.isLeaf()) {
            if (s.equals("")) {
                node.addPosition(position);
            }
            else{
                reinsert(s, path, position, root);
            }
            return;
        }
        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges){
            String label = edge.getLabel();
            String prefix = greatestCommonPrefix(label, s);
            if (prefix.equals(""))
                continue;
            if (!prefix.equals(label)){
                int index = edges.indexOf(edge);
                Node child = node.getChildrenPosition(index);
                String pathToLeaf = getPathToLeaf(child);
                String pPrime = path + edge.getLabel() + pathToLeaf;
                reinsert(s, pPrime, position, root);
                return;
            }
            else{
                int index = edges.indexOf(edge);
                path = path + label;
                insert(node.getChildrenPosition(index), s.substring(prefix.length(), s.length()), p, path, position);
                return;
            }
        }
        String pathToLeaf = getPathToLeaf(node);
        String pPrime = path + pathToLeaf;
        reinsert(s, pPrime, position, root);


    }



    private String getPathToLeaf(Node child) {
        Node n = child;
        String s="";
        while (!n.isLeaf()){
            s = s + n.getEdgePosition(0).getLabel();
            n = n.getChildrenPosition(0);
        }
        return s;

    }

    public boolean search(Node nodo, String s){
        return true;
    }
    public void reinsert(String s, String ,Node nodo, int index, int position){
        Edge edge=nodo.getEdge(index);
        Node child=nodo.getChild(index);
        String label=edge.getLabel();
    }
}
