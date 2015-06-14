package PatriciaTree;

import SuffixTrie.CompactCharSequence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by milenkotomic on 14-06-15.
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

        for (String book : books) {
            BufferedReader br = new BufferedReader(new FileReader(book));
            String strLine;
            ArrayList<String> allWords = new ArrayList<String>();
            System.err.println("Inicio preprocesamiento texto");
            while ((strLine = br.readLine()) != null) {
                strLine = strLine.toLowerCase();
                strLine = strLine.replaceAll("[^\\w\\s]", "");
                String[] words = strLine.split(" ");
                for (String word : words) {
                    allWords.add(word + "$");
                }
            }
            System.err.println("Fin preprocesamiento texto");

            System.err.println("Inicio insersion en arbol");
            PatriciaTrees tree = new PatriciaTrees();
            int allWordsSize = allWords.size();
            int i=0;
            long startConstructionTime = System.currentTimeMillis();
            for (String word : allWords) {
                if (i % TICKS == 0)
                    System.err.println("Insertadas " + i + " palabras de " + allWordsSize);
                tree.insert(tree.getRoot(), new CompactCharSequence(word), new CompactCharSequence(word), new CompactCharSequence(""), i);
                i++;
            }
            long endConstructionTime = System.currentTimeMillis();
            long totalConstructionTime = endConstructionTime - startConstructionTime;
            System.err.println("Fin insersion en arbol");

            System.err.println("Inicio busqueda en arbol");
            int wordsToSearch = allWords.size() / 10;
            long startSearchTime = System.currentTimeMillis();
            for (int j = 0; j < wordsToSearch; j++) {
                if (j % TICKS == 0) {
                    System.err.println("Buscando  " + j + " de " + wordsToSearch);
                }
                String s = allWords.get(randInt(0, allWords.size() - 1));
                tree.search(tree.getRoot(), new CompactCharSequence(s), new CompactCharSequence(s), new CompactCharSequence(""));
            }
            long endSearchTime = System.currentTimeMillis();
            long totalSearchTime = endSearchTime - startSearchTime;
            System.err.println("Fin busqueda en arbol");


            System.err.println("Fin arbol");
            System.out.println("Estadisticas para " + book);
            double meanSearchTime = totalSearchTime / (double) wordsToSearch;
            double meanConstructionTime = totalConstructionTime / (double) wordsToSearch;
            System.out.println("Construir el patricia tree demoro: " + totalConstructionTime);
            System.out.println("Insertar cada palabra demoro: " + meanConstructionTime);

            System.out.println("Buscar en total demoro: " + totalSearchTime);
            System.out.println("Buscar en promedio demoro: " + meanSearchTime);
            br.close();


        }
    }

}
