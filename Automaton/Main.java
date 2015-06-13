package Automaton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by milenkotomic on 02-06-15.
 */
public class Main {
    static Random rand = new Random();
    public static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }
    public static final int TICKS = 10000;
    public static void main(String[] args) throws IOException {
        ArrayList<String> books = new ArrayList<String>();
        books.add("Ingles/003ssb.txt");
        books.add("Ingles/005ssb.txt");
        books.add("Espanol/003ssb.txt");
        books.add("Espanol/005ssb.txt");
        books.add("Aleman/003ssb.txt");
        books.add("Aleman/005ssb.txt");

        for (String book: books) {
            BufferedReader br = new BufferedReader(new FileReader(book));
            String strLine;
            String text = "";
            int i = 0;
            ArrayList<String> allWords = new ArrayList<String>();
            System.err.println("Inicio preprocesamiento texto");
            while ((strLine = br.readLine()) != null) {
                strLine = strLine.toLowerCase();
                strLine = strLine.replaceAll("[^\\w\\s]", "");
                text += strLine;

                String[] words = strLine.split(" ");
                for (String word : words) {
                    allWords.add(word);
                }
                i++;
            }
            System.err.println("Inicio preprocesamiento texto");

            Automaton automaton = new Automaton(" ");

            long totalConstructionTime = 0;
            long totalSearchTime = 0;
            int wordsToSearch = allWords.size() / 10;
            long start_time_construction, end_time_construction;
            System.err.println("Inicio automata");
            for (int j = 0; j < wordsToSearch; j++) {
                if (j % TICKS == 0) {
                    System.err.println("Buscando  " + j + " de " + wordsToSearch);
                }
                String s = allWords.get(randInt(0, allWords.size() - 1));
                start_time_construction = System.currentTimeMillis();
                automaton = new Automaton(s);
                end_time_construction = System.currentTimeMillis();
                totalConstructionTime += (end_time_construction - start_time_construction);


                long startSearchTime = System.currentTimeMillis();
                automaton.search(text);
                long endSearchTime = System.currentTimeMillis();
                totalSearchTime += (endSearchTime - startSearchTime);

            }
            System.err.println("Fin automata");
            System.out.println("Estadisticas para " + book);
            long meanSearchTime = totalSearchTime / wordsToSearch;
            long meanConstructionTime = totalConstructionTime / wordsToSearch;
            System.out.println("Construir todos los automatas demoro: " + totalConstructionTime);
            System.out.println("Construir cada automata demoro: " + meanConstructionTime);

            System.out.println("Buscar en total demoro: " + totalSearchTime);
            System.out.println("Buscar en promedio demoro: " + meanSearchTime);
            br.close();
        }


    }
}
