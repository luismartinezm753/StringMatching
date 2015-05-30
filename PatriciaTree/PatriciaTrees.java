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
                reinsert(p, path, position);
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
        reinsert_aux(s, pPrime, position, root, "");
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
            Node finalNode=new Node();
            Edge finalEdge=new Edge("");
            newNode.addPosition(position);
            node.addNode(finalNode);
            node.addEdge(finalEdge);
            finalNode.setPositions((ArrayList<Integer>) node.getPositions().clone());
            node.setPositions(new ArrayList<Integer>());
            node.addEdge(newEdge);
            node.addNode(newNode);
            insert=true;
            //addFinalNode(node); falta arreglar esto
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
                insert=true;
            }else{
                /*Bajar*/
                insert=true;
                reinsert_aux(prefix.substring(label.length(), prefix.length()), sufix, position, node.getChildrenPosition(edges.indexOf(edge)), acumulado.concat(label));
            }
        }
        if (!insert){
            Edge newEdge=new Edge(prefix);
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
}
