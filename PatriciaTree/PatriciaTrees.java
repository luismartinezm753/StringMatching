package PatriciaTree;

import SuffixTrie.CompactCharSequence;

import java.io.IOException;
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
     *  @param node nodo que se esta visitando
     * @param p string completo a insertar
     * @param s substring de p que queda por buscar
     * @param path string que se ha formado en el recorrido del arbol
     * @param position posicion de p en el arreglo de strings
     */
    public void insert(Node node, CompactCharSequence p, CompactCharSequence s, CompactCharSequence path, int position) throws IOException {
        ArrayList<Edge> edges = node.getChildrenEdges();
        if (node.isLeaf()) {
            if (s.compareTo("")==0) {
                node.addPosition(position);
            }
            else{
                reinsert(p, path, position);
            }
            return;
        }
        for (Edge edge: edges){
            CompactCharSequence label = (CompactCharSequence) edge.getLabel();
            CompactCharSequence prefix = greatestCommonPrefix(label, s);

            if (prefix.compareTo("")==0)
                continue;
            if (prefix.compareTo(label)!=0){
                int index = edges.indexOf(edge);
                Node child = node.getChildrenPosition(index);
                String pathToLeaf = getPathToLeaf(child);
                //CompactCharSequence pPrime = path + edge.getLabel() + new CompactCharSequence(pathToLeaf);
                CompactCharSequence pPrime= (CompactCharSequence) path.append(edge.getLabel()).append(new CompactCharSequence(pathToLeaf));
                reinsert(p, pPrime, position);
                return;
            }
            else{
                int index = edges.indexOf(edge);
                //path = path + label;
                path= (CompactCharSequence) path.append(label);
                insert(node.getChildrenPosition(index), p, (CompactCharSequence) s.subSequence(prefix.length(), s.length()), path, position);
                return;
            }

        }
        String pathToLeaf = getPathToLeaf(node);
        //CompactCharSequence pPrime = path + new CompactCharSequence(pathToLeaf);
        CompactCharSequence pPrime= (CompactCharSequence) path.append(new CompactCharSequence(pathToLeaf));
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
    public ArrayList<Integer> search(Node node, CompactCharSequence p, CompactCharSequence s, CompactCharSequence path) throws IOException {
        if (node.isLeaf()){
            if (p.compareTo(path)==0)
                return node.getPositions();
            else
                return new ArrayList<Integer>();
        }
        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges){
            CharSequence label = edge.getLabel();
            CompactCharSequence prefix = greatestCommonPrefix((CompactCharSequence)label, s);
            if (prefix.compareTo(label)==0) {
                int index = edges.indexOf(edge);
                Node child = node.getChildrenPosition(index);
                s =(CompactCharSequence) s.subSequence(prefix.length(), s.length());
                path = (CompactCharSequence) path.append(label);
                return search(child, p, s, path);
            }
            if (prefix.compareTo("")==0)
                continue;

            /*else{
                return false;
            }*/
        }
        return new ArrayList<Integer>();

    }
    public void reinsert(CompactCharSequence p, CompactCharSequence pPrime, int position) throws IOException {
        CompactCharSequence prefix=greatestCommonPrefix(p, pPrime);
        CompactCharSequence sufix= (CompactCharSequence) p.subSequence(prefix.length(), p.length());
        reinsert_aux(p,sufix,position,root,new CompactCharSequence(""));

    }


    public void reinsert_aux(CharSequence prefix, CompactCharSequence sufix, int position, Node node, Appendable acumulado) throws IOException {
        boolean insert=false;
        if (node.isLeaf()){
            Edge newEdge=new Edge(sufix);
            Node newNode= new Node();
            newNode.addPosition(position);
            node.addEdge(newEdge);
            node.addNode(newNode);
            insert=true;
            if (!sufix.equals(new CompactCharSequence("")) && !acumulado.equals(new CompactCharSequence(""))){
                Edge finalEdge=new Edge("");
                Node finalNode=new Node();
                node.addEdge(finalEdge);
                node.addNode(finalNode);
                finalNode.setPositions((ArrayList<Integer>) node.getPositions().clone());
            }
            node.setPositions(new ArrayList<Integer>());
            return;
        }
        CompactCharSequence labelPrefix;
        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges) {
            CharSequence label = edge.getLabel();
            labelPrefix = greatestCommonPrefix((CompactCharSequence)label, (CompactCharSequence) prefix);
            if (labelPrefix.equals(new CompactCharSequence(""))){
                continue;
            }
            if (!labelPrefix.equals(label)){
                /*Hacer Split*/
                CompactCharSequence prefixP=greatestCommonPrefix((CompactCharSequence)label, (CompactCharSequence) prefix);
                CompactCharSequence endLabel= (CompactCharSequence) label.subSequence(prefixP.length(), label.length());
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
                reinsert_aux(prefix.subSequence(label.length(), prefix.length()), sufix, position, node.getChildrenPosition(edges.indexOf(edge)), acumulado.append(label));
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
    public CompactCharSequence greatestCommonPrefix(CompactCharSequence a, CompactCharSequence b) {
        int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                //return a.substring(0, i);
                return (CompactCharSequence) a.subSequence(0,i);
            }
        }
        return (CompactCharSequence) a.subSequence(0,minLength);
        //return a.substring(0, minLength);
    }
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
