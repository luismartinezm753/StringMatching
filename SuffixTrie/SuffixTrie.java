
package SuffixTrie;

import PatriciaTree.Edge;
import PatriciaTree.Node;
import PatriciaTree.PatriciaTrees;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by luism on 16-05-15.
 */

public class SuffixTrie extends PatriciaTrees{
    public static final int TICKS=5000;
    public static final CompactCharSequence specialChar = new CompactCharSequence("$");
    Node lastMark;
    ArrayList<SuffixLinks> links;
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
    public boolean searchTrie(Node node, CompactCharSequence p, CompactCharSequence s, CompactCharSequence path) throws IOException {
        if (node.isLeaf()){
            if (p.compareTo(path)==0)
                return true;
            else
                return false;
        }
        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges){
            CharSequence label = edge.getLabel();
            CompactCharSequence prefix = greatestCommonPrefix((CompactCharSequence)label, s);
            if (prefix.compareTo("")==0)
                continue;
            if (prefix.compareTo(label)==0) {
                int index = edges.indexOf(edge);
                Node child = node.getChildrenPosition(index);
                s =(CompactCharSequence) s.subSequence(prefix.length(), s.length());
                path = (CompactCharSequence) path.append(label);
                if (path.equals(p)){
                    return true;
                }
                return searchTrie(child, p, s, path);
            }else if(((CompactCharSequence) label).startsWith(s)){
                return true;
            }
        }
        return false;
    }
    @Override
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
            SuffixLinks newLink=new SuffixLinks(node,lastMark);
            addLink(newLink);
            setLastMark(node);
            return;
        }
        CompactCharSequence labelPrefix;
        ArrayList<Edge> edges = node.getChildrenEdges();
        for (Edge edge: edges) {
            CharSequence label = edge.getLabel();
            labelPrefix = greatestCommonPrefix((CompactCharSequence)label, (CompactCharSequence)prefix);
            if (labelPrefix.equals(new CompactCharSequence(""))){
                continue;
            }
            if (!labelPrefix.equals(label)){
                /*Hacer Split*/
                CompactCharSequence prefixP=greatestCommonPrefix((CompactCharSequence)label, (CompactCharSequence)prefix);
                CharSequence endLabel=label.subSequence(prefixP.length(), label.length());
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
                reinsert_aux(prefix.subSequence(label.length(), prefix.length()), sufix, position, node.getChildrenPosition(edges.indexOf(edge)), acumulado.append(label));
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
    public ArrayList<CompactCharSequence> generateSuffix(String s){
        ArrayList<CompactCharSequence> suffixes=new ArrayList<CompactCharSequence>();
        CompactCharSequence sequence=new CompactCharSequence(s);
        int length=sequence.length();
        CompactCharSequence subSequence;
        int j=0;
        for (int i = length-1; i >=0; i--) {
            if (j%TICKS==0)
                System.err.println("LLevamos "+j+" Palabras");
            subSequence= (CompactCharSequence) sequence.subSequence(i,length);
            //CompactCharSequence temp=(CompactCharSequence)subSequence.append(specialChar);
            suffixes.add(subSequence);
            j++;
        }
        return suffixes;
    }
}

