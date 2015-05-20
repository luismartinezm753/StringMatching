
package PatriciaTree;

import java.util.ArrayList;

/**
 * Created by milenkotomic on 20-05-15.
 */


public class Node {
    private ArrayList<Integer> positions;
    private ArrayList<Node> children;
    private ArrayList<Edge> childrenEdges;


    public Node() {
        this.children = new ArrayList<Node>();
        this.childrenEdges = new ArrayList<Edge>();
        this.positions = new ArrayList<Integer>();
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public ArrayList<Edge> getChildrenEdges() {
        return childrenEdges;
    }

    public void setChildrenEdges(ArrayList<Edge> childrenEdges) {
        this.childrenEdges = childrenEdges;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }

    public Node getChildrenPosition(int index) {
        return children.get(index);
    }

    public Edge getEdgePosition(int index) {
        return childrenEdges.get(index);
    }

    public void addNode(Node n){
        children.add(n);
    }

    public void addEdge(Edge e){
        childrenEdges.add(e);
    }

    public void removeNode(int index){
        children.remove(index);
    }

    public void removeEdge(int index){
        childrenEdges.remove(index);
    }
}
