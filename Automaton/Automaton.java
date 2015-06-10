package Automaton;

import java.util.ArrayList;

/**
 * Created by luism on 16-05-15.
 */
public class Automaton {
    private int delta[][];
    private ArrayList<String> sigma;
    private int finalState;

    public Automaton(String pattern){
        // this.sigma = createSigma(pattern);
        this.sigma = new ArrayList<String>();
        char[] alphabet = "abcdefghijklmnñopqrstuvwxyz 1234567890".toCharArray();
        for (char c: alphabet){
            this.sigma.add((Character.toString(c)));
        }
        this.delta = new int[this.sigma.size()][pattern.length()+1];
        this.finalState = pattern.length();
        int length = pattern.length();
        int k;
        String pqa;
        String pk;
        for (int i=0; i < length + 1; i++){
            for (String c: sigma){
                k = Math.min(length+1, i+2);
                pqa = pattern.substring(0, i) + c;
                //System.out.println("pqa = " + pqa);
                do {
                    k--;
                    pk = pattern.substring(0, k);
                    //System.out.println("pk = " + pk);
                }
                while (!isSuffix(pk, pqa));
                //System.out.println(k);
                this.delta[sigma.indexOf(c)][i] = k;
            }
        }
    }

    public boolean isSuffix(String pk, String pqa) {
        String suffix = pqa.substring(pqa.length() - pk.length(), pqa.length());
        return suffix.equals(pk);
    }

    public ArrayList<String> createSigma(String pattern){
        char[] temp = pattern.toCharArray();
        ArrayList<String> sigma = new ArrayList<String>();
        for (char c: temp){
            String s = Character.toString(c);
            if (!sigma.contains(s)){
                sigma.add(s);
            }
        }
        return sigma;
    }

    public int search(String s){
        int length = s.length();
        int actualState = 0;
        int occurrences = 0;
        //System.out.println("Final=" + finalState);
        for (int i=1; i<length+1; i++){
            if (sigma.indexOf(s.substring(i-1, i)) != -1) {
                //System.out.println("char=" + s.substring(i-1, i));
                actualState = delta[sigma.indexOf(s.substring(i - 1, i))][actualState];
                //System.out.println("Estado = " + actualState);
                if (actualState == finalState) {
                    occurrences++;
                    actualState = 0;
                }
            }
            else{
                actualState = 0;
            }
        }
        return occurrences;


    }

    public int[][] getDelta() {
        return delta;
    }

    public void setDelta(int[][] delta) {
        this.delta = delta;
    }
}


