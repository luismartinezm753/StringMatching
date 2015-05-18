import java.util.ArrayList;

/**
 * Created by luism on 16-05-15.
 */
public class SuffixTrie {
    SuffixNode root;
    public static final char SPECIAL_CHAR='$';
    public SuffixTrie(){

        root=new SuffixNode();
    }
    public boolean search(String s, SuffixNode nodo, boolean flag){
        if (flag==true && nodo.getEdge(0).getS()==SPECIAL_CHAR){
            return flag;
        }else{
            SuffixEdge edge=nodo.containsChar(s.charAt(0));
            if (s.charAt(0)==edge.getS()){
                int index=nodo.getChildEdges().indexOf(edge);
                return flag && search(s.substring(1,s.length()),nodo.getNode(index),flag);
            }else{
                flag=false;
                return false;
            }
        }
    }
    public void insert(String s, SuffixNode nodo){
        SuffixEdge edge=nodo.containsChar(s.charAt(0));
        int index=nodo.getChildEdges().indexOf(edge);
        if (edge == null) {
            SuffixNode nodeActual=nodo;
            SuffixNode nodeAnterior=nodo;
            for (int i = 0; i < s.length(); i++) {
                SuffixNode newNode=new SuffixNode();
                nodeActual.addChild(newNode);
                nodeActual.addEdge(new SuffixEdge(s.charAt(i)));
                nodeAnterior=nodeActual;
                nodeActual=newNode;
            }
            nodeActual.addChild(new SuffixNode());
            nodeActual.addEdge(new SuffixEdge(SPECIAL_CHAR));
        }else{
            insert(s.substring(1,s.length()),nodo.getNode(index));
        }
    }

    /**
     * Genera todos los sufijos de un string
     * @param s string
     * @return un lista con todos los sufijos de s
     */
    public ArrayList<String> generateSuffix(String s){
        ArrayList<String> suffixes=new ArrayList<String>();
        suffixes.add(s);
        for (int i = 0; i < s.length(); i++) {
            String sufix=s.substring(i,s.length());
            suffixes.add(sufix);
        }
        return suffixes;
    }
}
