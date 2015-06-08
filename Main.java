import PatriciaTree.PatriciaTrees;

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
    static String[] SPECIAL_CHARS={"\\,","\\.","\\:","\\;","\\{","\\}","\\[","\\]","\\!","\\¡","\\?","\\¿","\n","\\&","\\#","\\$"};
    public static void main(String[] args) throws IOException {
        PatriciaTrees patricia=new PatriciaTrees();
        BufferedReader br = new BufferedReader(new FileReader("003ssb.txt"));
        String strLine;
        int i=0;
        ArrayList<String> allWords=new ArrayList<>();
        while ((strLine = br.readLine()) != null) {
            strLine=strLine.toLowerCase();
            for (int j = 0; j < SPECIAL_CHARS.length; j++) {
                strLine=strLine.replaceAll(SPECIAL_CHARS[j],"");
            }
            String[] words = strLine.split(" ");
            for(String word : words){
                allWords.add(word);
            }
        }
        long time_start = System.currentTimeMillis();
        for (String s :allWords){
            patricia.insert(patricia.getRoot(),s,s,"",i);
            i++;
        }
        long time_end = System.currentTimeMillis();
        long total_search=System.currentTimeMillis();
        for (int j = 0; j < allWords.size() / 10; j++) {
            long search_time=System.currentTimeMillis();
            String s = allWords.get(randInt(0, allWords.size() - 1));
            patricia.search(patricia.getRoot(), s, s,"");
            long search_timeFinal=System.currentTimeMillis();
            total_search=+(search_timeFinal-search_time);
            System.out.println("Buscar "+s+" demoro "+(search_timeFinal-search_time)+" milisegundos");
        }
        System.out.println("Buscar en total demoro: "+total_search);
        System.out.println("insertar demoro: "+ ( time_end - time_start ) +" milisegundos");
        br.close();
    }
}
