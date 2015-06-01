package SuffixTrie;

import PatriciaTree.Edge;
import PatriciaTree.Node;
import PatriciaTree.PatriciaTrees;

import java.util.ArrayList;

/**
 * Created by luism on 16-05-15.
 */
public class SuffixTrie extends PatriciaTrees{
    //Node root;
    Node lastMark;
    ArrayList<SuffixLinks> links;
    public static final char SPECIAL_CHAR='$';
    public SuffixTrie(){
        super();
        lastMark=getRoot();
        links=new ArrayList<SuffixLinks>();
    }

    public ArrayList<SuffixLinks> getLinks() {
        return links;
    }

    public void setLinks(ArrayList<SuffixLinks> links) {
        this.links = links;
    }
    public void addLink(SuffixLinks link){
        links.add(link);
    }

    public Node getLastMark() {
        return lastMark;
    }

    public void setLastMark(Node lastMark) {
        this.lastMark = lastMark;
    }
    @Override
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
            SuffixLinks newLink=new SuffixLinks(node,lastMark);
            addLink(newLink);
            setLastMark(node);
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
                Node child= node.getChildrenPosition(edges.indexOf(edge));
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
        /**
         * Genera todos los sufijos de un string
         * @param s string
         * @return un lista con todos los sufijos de s
         */
    public ArrayList<String> generateSuffix(String s){
        ArrayList<String> suffixes=new ArrayList<String>();
        for (int i = s.length()-1; i >=0; i--) {
            String sufix=s.substring(i,s.length());
            suffixes.add(sufix);
        }
        return suffixes;
    }
}
