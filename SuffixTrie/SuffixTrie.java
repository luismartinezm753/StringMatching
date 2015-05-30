package SuffixTrie;

import java.util.ArrayList;

/**
 * Created by luism on 16-05-15.
 */
public class SuffixTrie {
    SuffixNode root;
    SuffixNode lastMark;
    ArrayList<SuffixLinks> links;
    public static final char SPECIAL_CHAR='$';
    public SuffixTrie(){
        root=new SuffixNode();
        lastMark=root;
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
    public SuffixNode getRoot() {
        return root;
    }

    public void setRoot(SuffixNode root) {
        this.root = root;
    }
    public SuffixNode getLastMark() {
        return lastMark;
    }

    public void setLastMark(SuffixNode lastMark) {
        this.lastMark = lastMark;
    }
    public boolean search(SuffixNode node, String p, String s, String path){
        return false;
    }
    public void insert(SuffixNode node, String p, String s, String path, int position){

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
