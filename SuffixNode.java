import java.util.ArrayList;

/**
 * Created by luism on 16-05-15.
 */

/**
 * Son los nodos del suffixTrie, guardan la referencia de los arcos donde se guarda un caracter y los hijos del nodo
 */
public class SuffixNode {

    private ArrayList<SuffixNode> childs;
    private ArrayList<SuffixEdge> childEdges;
    public SuffixNode(){
        childEdges=new ArrayList<SuffixEdge>();
        childs=new ArrayList<SuffixNode>();
    }
    public void addChild(SuffixNode nodo){
        childs.add(nodo);

    }
    public void addEdge(SuffixEdge edge){
        childEdges.add(edge);
    }

    public ArrayList<SuffixNode> getChilds() {
        return childs;
    }
    public ArrayList<SuffixEdge> getChildEdges() {
        return childEdges;
    }
    /**
     * Retorna el i-esimo arco de este nodo
     * @param i, la posicion del arco que se quiere
     * @return el arco almacenado en la poscion i.
     */
    public SuffixEdge getEdge(int i){
        return childEdges.get(i);
    }

    /**
     * Retorna el i-esimo hijo de este nodo
     * @param i, la posicion del hijo que se quiere
     * @return el arco almacenado en la posicion i
     */
    public SuffixNode getNode(int i){
        return childs.get(i);
    }

    public boolean isLeaf() {
        return childs.isEmpty();
    }

    /**
     * indica si un caracter esta en algun arco del nodo y entrega ese arco
     * @param s un caracter
     * @return Retorna el arco que contiene ese caracter
     */
    public SuffixEdge containsChar(char s){
        for (SuffixEdge edge : childEdges){
            if (edge.getS()==s){
                return edge;
            }
        }
        return null;
    }
}
