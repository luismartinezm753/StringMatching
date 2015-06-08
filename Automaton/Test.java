package Automaton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by milenkotomic on 02-06-15.
 */
public class Test {
    static Random rand = new Random();
    public static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
    public static final int TICKS = 10000;
    static String[] SPECIAL_CHARS={"\\'", "\\-", "\\,","\\.","\\:","\\;","\\{","\\}","\\[","\\]","\\!","\\¡","\\?","\\¿","\n","\\&","\\#","\\$"};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("003ssb.txt"));
        String strLine;
        String text = "";
        int i=0;
        ArrayList<String> allWords=new ArrayList<String>();
        while ((strLine = br.readLine()) != null) {
            if (i % TICKS == 0){
                System.err.println("Linea: " + i);
            }
            strLine=strLine.toLowerCase();
            /*for (int j = 0; j < SPECIAL_CHARS.length; j++) {
                strLine=strLine.replaceAll("[^\\w\\s]", "");
            }*/
            strLine=strLine.replaceAll("[^\\w\\s]","");
            text+=strLine;

            String[] words= strLine.split(" ");
            for(String word : words){
                allWords.add(word);
            }
            i++;
        }
        Automaton automaton = new Automaton(" ");
        long totalConstructionTime = 0;
        long totalSearchTime = 0;
        int wordsToSearch = allWords.size() / 1000;
        System.err.println("Inicio automata");
        System.err.println("Palabras a buscar: " + allWords.size() / 1000);
        for (int j = 0; j < wordsToSearch; j++) {
            if (j % 1000 == 0){
                System.err.println("Linea: " + j);
            }
            String s = allWords.get(randInt(0, allWords.size() - 1));
            long start_time_construction = System.currentTimeMillis();
            automaton = new Automaton(s);
            long end_time_construction = System.currentTimeMillis();
            totalConstructionTime += (end_time_construction - start_time_construction);
            System.err.println("Construir automata " + s+ " : " + (end_time_construction - start_time_construction));

            long startSearchTime = System.currentTimeMillis();
            automaton.search(text);
            long endSearchTime = System.currentTimeMillis();
            totalSearchTime += (endSearchTime - startSearchTime);

        }
        long meanSearchTime = totalSearchTime / wordsToSearch;
        long meanConstructionTime = totalConstructionTime / wordsToSearch;
        System.out.println("Construir todos los automatas demoro: " + totalConstructionTime);
        System.out.println("Construir cada automata demoro: " + meanConstructionTime);

        System.out.println("Buscar en total demoro: " + totalSearchTime);
        System.out.println("Buscar en promedio demoro: " + meanSearchTime);
        br.close();
    }
}
