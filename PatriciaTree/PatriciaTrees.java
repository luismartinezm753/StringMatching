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
        ArrayList<Edge> edges = node.getChildrenEdges();
        if (node.isLeaf()) {
            if (s.equals("")) {
                node.addPosition(position);
                System.out.println(p);
            }
            else{
                reinsert(p, path, position);
            }
            return;
        }
        for (Edge edge: edges){
            String label = edge.getLabel();
            String prefix = greatestCommonPrefix(label, s);
            /*
            if (label.equals("") && path.equals(p)){
                int index=edges.indexOf(edge);
                node.getChildrenPosition(index).addPosition(position);
                return;
            }
            */
            /*
            if (prefix.equals(label)){
                int index = edges.indexOf(edge);
                path = path + label;
                insert(node.getChildrenPosition(index), p,s.substring(prefix.length(), s.length()), path, position);
                return;
            }
            else if (!prefix.equals(label) && !prefix.equals("")){
                int index = edges.indexOf(edge);
                Node child = node.getChildrenPosition(index);
                String pathToLeaf = getPathToLeaf(child);
                String pPrime = path + edge.getLabel() + pathToLeaf;
                reinsert(p, pPrime, position);
                return;
            }
            */

            if (prefix.equals(""))
                continue;
            if (!prefix.equals(label)){
                int index = edges.indexOf(edge);
                Node child = node.getChildrenPosition(index);
                String pathToLeaf = getPathToLeaf(child);
                String pPrime = path + edge.getLabel() + pathToLeaf;
                reinsert(p, pPrime, position);
                return;
            }
            else{
                int index = edges.indexOf(edge);
                path = path + label;
                insert(node.getChildrenPosition(index), p,s.substring(prefix.length(), s.length()), path, position);
                return;
            }

        }
        String pathToLeaf = getPathToLeaf(node);
        String pPrime = path + pathToLeaf;
        reinsert(p, pPrime, position);
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
    public ArrayList<Integer> search(Node node, String p, String s, String path){
        if (node.isLeaf()){
            if (p.equals(path))
                return node.getPositions();
            else
                return new ArrayList<Integer>();
        }
        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges){
            String label = edge.getLabel();
            String prefix = greatestCommonPrefix(label, s);
            if (prefix.equals(label)) {
                int index = edges.indexOf(edge);
                Node child = node.getChildrenPosition(index);
                s = s.substring(prefix.length(), s.length());
                path = path + label;
                return search(child, p, s, path);
            }
            if (prefix.equals(""))
                continue;

            /*else{
                return false;
            }*/
        }
        return new ArrayList<Integer>();

    }
    public void reinsert(String p, String pPrime, int position){
        String prefix=greatestCommonPrefix(p, pPrime);
        String sufix=p.substring(prefix.length(), p.length());
        reinsert_aux(prefix,sufix,position,root,"");

    }


    public void reinsert_aux(String prefix, String sufix, int position, Node node, String acumulado){
        boolean insert=false;
        if (node.isLeaf()){
            Edge newEdge=new Edge(sufix);
            Node newNode= new Node();
            newNode.addPosition(position);
            node.addEdge(newEdge);
            node.addNode(newNode);
            insert=true;
            if (!sufix.equals("") && !acumulado.equals("")){
                Edge finalEdge=new Edge("");
                Node finalNode=new Node();
                node.addEdge(finalEdge);
                node.addNode(finalNode);
                finalNode.setPositions((ArrayList<Integer>) node.getPositions().clone());
            }
            node.setPositions(new ArrayList<Integer>());
            return;
        }
        String labelPrefix="";
        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges) {
            String label = edge.getLabel();
            labelPrefix = greatestCommonPrefix(label, prefix);
            if (labelPrefix.equals("")){
                continue;
            }
            if (!labelPrefix.equals(label)){
                /*Hacer Split*/
                String prefixP=greatestCommonPrefix(label, prefix);
                String endLabel=label.substring(prefixP.length(),label.length());
                Node newNode=new Node();
                Node newNode2=new Node();
                Node child=node.getChildrenPosition(edges.indexOf(edge));
                Edge newEdge=new Edge(sufix);
                Edge newEdge2=new Edge(endLabel);
                edge.setLabel(prefixP);
                node.replaceNode(edges.indexOf(edge),newNode);
                newNode.addNode(child);
                newNode.addEdge(newEdge2);
                newNode.addNode(newNode2);
                newNode.addEdge(newEdge);
                newNode2.addPosition(position);
                insert=true;
                break;
            }else{
                /*Bajar*/
                insert=true;
                reinsert_aux(prefix.substring(label.length(), prefix.length()), sufix, position, node.getChildrenPosition(edges.indexOf(edge)), acumulado.concat(label));
            }
        }
        if (!insert){
            Edge newEdge=new Edge(sufix);
            Node newNode= new Node();
            newNode.addPosition(position);
            node.addEdge(newEdge);
            node.addNode(newNode);
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
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
