package SuffixTrie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by luism on 10-06-15.
 */
public class Main {
    static Random rand = new Random();
    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    public static final int TICKS=10000;
    public static void main(String[] args) throws IOException {
        ArrayList<String> books = new ArrayList<String>();
        books.add("Ingles/003ssb.txt");
        books.add("Ingles/005ssb.txt");
        books.add("Espanol/003ssb.txt");
        books.add("Espanol/005ssb.txt");
        books.add("Aleman/003ssb.txt");
        books.add("Aleman/005ssb.txt");

        for (String book: books) {
            SuffixTrie suffixTrie = new SuffixTrie();
            BufferedReader br = new BufferedReader(new FileReader(book));
            String strLine;
            int i = 0;
            ArrayList<CompactCharSequence> allWords = new ArrayList<CompactCharSequence>();
            String all = "";
            System.err.println("Inicio preprocesamiento texto");
            while ((strLine = br.readLine()) != null) {
                strLine = strLine.toLowerCase();
                strLine = strLine.replaceAll("[^\\w\\s]", "");
                String[] words = strLine.split(" ");
                for (String word : words) {
                    allWords.add(new CompactCharSequence(word));
                }
                all = all + strLine;
            }
            System.err.println("Fin preprocesamiento texto");

            System.err.println("Inicio generacion sufijos");
            ArrayList<CompactCharSequence> suffixs = suffixTrie.generateSuffix(all);
            System.err.println("Fin generacion sufijos");

            System.err.println("Inicio Insert");
            int suffixsSize = suffixs.size();
            long time_start = System.currentTimeMillis();
            for (CompactCharSequence sufix : suffixs) {
                if (i % TICKS == 0)
                    System.err.println("Insertados " + i + " sufijos de " + suffixsSize);
                suffixTrie.insert(suffixTrie.getRoot(), sufix, sufix, new CompactCharSequence(""), i);
                i++;
            }
            long time_end = System.currentTimeMillis();
            long totalConstructionTime = time_end - time_start;

            System.err.println("Fin Insert");

            System.err.println("Inicio busqueda");
            int vacios = 0;
            int wordsToSearch = allWords.size() / 10;
            long total_search = 0;
            for (int j = 0; j < wordsToSearch; j++) {
                if (j % TICKS == 0)
                    System.err.println("Buscadas " + j + " palabras de " + wordsToSearch);
                CompactCharSequence s = allWords.get(randInt(0, allWords.size() - 1));
                long search_time = System.currentTimeMillis();
                boolean result = suffixTrie.searchTrie(suffixTrie.getRoot(), s, s, new CompactCharSequence(""));
                long search_timeFinal = System.currentTimeMillis();
                total_search += (search_timeFinal - search_time);
                if (!result) {
                    vacios++;
                }
            }
            System.err.println("Fin SuffixTrie");
            System.out.println("Estadisticas para " + book);
            long meanSearchTime = total_search / wordsToSearch;
            long meanConstructionTime = totalConstructionTime / suffixsSize;
            System.out.println("Construir el suffix demoro: " + totalConstructionTime);
            System.out.println("Insertar cada sufijo demoro: " + meanConstructionTime);

            System.out.println("Buscar en total demoro: " + total_search);
            System.out.println("Buscar en promedio demoro: " + meanSearchTime);
            br.close();
        }
    }
}
