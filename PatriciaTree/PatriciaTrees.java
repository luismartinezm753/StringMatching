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
