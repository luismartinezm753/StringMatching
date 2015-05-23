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
     * @param p string completo a insertar
     * @param s substring de p que queda por buscar
     * @param path string que se ha formado en el recorrido del arbol
     * @param position posicion de p en el arreglo de strings
     */
    public void insert(Node node, String p, String s, String path, int position){
        if (node.isLeaf()) {
            if (s.equals("")) {
                node.addPosition(position);
            }
            else{
                reinsert(s, path, position, root,"");
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
                reinsert(p, pPrime, position, root,"");
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
        reinsert(s, pPrime, position, root,"");
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

    /**
     *
     * @param node nodo actual
     * @param p string a buscar
     * @param s substring que queda por buscar
     * @param path string que se ha formado al recorrer el arbol
     * @return true si al llegar a una hoja path es igual a p
     */
    public boolean search(Node node, String p, String s, String path){
        if (node.isLeaf()){
            return p.equals(path);
        }
        else{
            if (p.equals(path))
                return false;
        }

        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges){
            String label = edge.getLabel();
            String prefix = greatestCommonPrefix(label, s);
            if (prefix.equals(""))
                continue;
            if (prefix.equals(label)){
                int index = edges.indexOf(edge);
                Node child = node.getChildrenPosition(index);
                s = s.substring(s.length() - prefix.length(), s.length());
                path = path + label;
                return search(child, p, s, path);
            }
            else{
                return false;
            }
        }
        return false;

    }


    public void reinsert(String p, String pPrime, int position, Node node, String acumulado){
        String prefix=greatestCommonPrefix(p, pPrime);
        String sufix=p.substring(prefix.length(), p.length());
        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges) {
            String label = edge.getLabel();
            String labelPrefix = greatestCommonPrefix(label, prefix);
            if (labelPrefix.equals(""))
                continue;
            if (prefix.equals(label)) {
                //Inserto el sufijo que me falta
                Node newNode = new Node();
                Edge newEdge = new Edge(sufix);
                newNode.addPosition(position);
                node.addEdge(newEdge);
                node.addNode(newNode);
                addFinalNode(newNode);
                return;
            } else if(prefix.startsWith(acumulado.concat(label))) {
                reinsert(p,pPrime,position,node.getChildrenPosition(edges.indexOf(edge)),acumulado.concat(label));
            }else if(label.startsWith(prefix) && prefix.length()<label.length()){//hago split
                String split=label.substring(prefix.length(),label.length());
                Node newNode = new Node();
                Edge newEdge = new Edge(sufix);
                Edge splitEdge=new Edge(split);
                Node child=node.getChildrenPosition(edges.indexOf(edge));
                edge.setLabel(prefix);
                node.removeNode(edges.indexOf(edge));
                node.addNode(newNode);
                newNode.addEdge(newEdge);
                newNode.addEdge(splitEdge);
                newNode.addNode(child);
                newNode.addPosition(position);
                //addFinalNode(newNode);
                return;
            }
        }
        if (prefix.equals("")){
            Node newNode = new Node();
            Edge newEdge = new Edge(p);
            newNode.addPosition(position);
            node.addEdge(newEdge);
            node.addNode(newNode);
            //addFinalNode(newNode);
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
    public void addFinalNode(Node node){
        Node leaf = new Node();
        Edge emptyEdge = new Edge("");
        node.addEdge(emptyEdge);
        node.addNode(leaf);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    public String printTree(Node node, int index){
        if (node.isLeaf()){
            return "";
        }else{
            return node.getEdgePosition(index).getLabel()+printTree(node.getChildrenPosition(index),index);
        }
    }
}
