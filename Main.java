import PatriciaTree.PatriciaTrees;
import SuffixTrie.CompactCharSequence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by luism on 05-06-15.
 */
public class Main {
    static Random rand = new Random();
    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public static final int TICKS=5000;
    public static void main(String[] args) throws IOException {
        PatriciaTrees patricia=new PatriciaTrees();
        BufferedReader br = new BufferedReader(new FileReader("003ssb.txt"));
        String strLine;
        int i=0;
        ArrayList<CompactCharSequence> allWords=new ArrayList<CompactCharSequence>();
        while ((strLine = br.readLine()) != null) {
            strLine=strLine.toLowerCase();
            strLine=strLine.replaceAll("[^\\w\\s]","");
            String[] words = strLine.split(" ");
            for(String word : words){
                CompactCharSequence sequence=new CompactCharSequence(word);
                allWords.add((CompactCharSequence) sequence.append(new CompactCharSequence("$")));
            }
        }
        long time_start = System.currentTimeMillis();
        System.out.println("Inicio Insert");
        for (CompactCharSequence s :allWords){
            if (i%TICKS==0)
                System.out.println("LLevamos "+i+" Palabras");
            patricia.insert(patricia.getRoot(),s,s,new CompactCharSequence(""),i);
            i++;
        }
        long time_end = System.currentTimeMillis();
        long total_search=System.currentTimeMillis();
        int vacios=0;
        for (int j = 0; j < allWords.size() / 10; j++) {
            CompactCharSequence s = allWords.get(randInt(0, allWords.size() - 1));
            long search_time=System.currentTimeMillis();
            ArrayList<Integer> result=patricia.search(patricia.getRoot(), s, s, new CompactCharSequence(""));
            long search_timeFinal=System.currentTimeMillis();
            total_search=+(search_timeFinal-search_time);
            System.out.println("Buscar " + s + " demoro " + (search_timeFinal - search_time) + " milisegundos, resultados: " + result);
            if (result.isEmpty()){
                vacios++;
            }

        }
        System.out.println("Buscar en total demoro: "+total_search);
        System.out.println("insertar demoro: "+ ( time_end - time_start ) +" milisegundos");
        System.out.println("Vacios: "+vacios+" Total: "+allWords.size()/10);
        br.close();
    }
}
