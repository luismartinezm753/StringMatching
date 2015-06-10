package PatriciaTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by milenkotomic on 08-06-15.
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
            PatriciaTrees tree = new PatriciaTrees();
            int i = 0;
            String strLine;
            System.err.println("Preprocesando texto");
            ArrayList<String> allWords = new ArrayList<String>();
            while ((strLine = br.readLine()) != null) {
                strLine = strLine.toLowerCase();
                strLine = strLine.replaceAll("[^\\w\\s]", "");
                String[] words = strLine.split(" ");
                for (String word : words) {
                    word += "$";
                    allWords.add(word);
                }
            }
            System.err.println("Preprocesando texto terminado");

            System.err.println("Incio insercion en arbol");
            int wordsSize = allWords.size();
            System.err.println("Inicio Insert");
            long time_start = System.currentTimeMillis();
            for (String s : allWords) {
                if (i % TICKS == 0)
                    System.err.println("Insertadas " + i + " de " + wordsSize);
                tree.insert(tree.getRoot(), s, s, "", i);
                i++;
            }
            long time_end = System.currentTimeMillis();
            System.err.println("Fin insercion en arbol");
            System.err.println("Incio busqueda en arbol");
            int wordsToSearch = wordsSize / 10;
            long start_search = System.currentTimeMillis();
            for (int j = 0; j < wordsToSearch; j++) {
                if (j % TICKS == 0)
                    System.err.println("Buscadas " + j + " de " + wordsToSearch);
                String s = allWords.get(randInt(0, allWords.size() - 1));
                ArrayList<Integer> result = tree.search(tree.getRoot(), s, s, "");
                if (result.size() == 0){
                    System.out.println("Palabra no encontrada: " + s);
                }
            }

            long end_search = System.currentTimeMillis();
            System.err.println("Fin busqueda en arbol");

            System.out.println("Estadisticas para " + book);

            System.out.println("Palabras insertadas: " + wordsSize);
            long insertionTime = (time_end - time_start);
            long insertionTimePerWord = insertionTime / wordsSize;
            System.out.println("Tiempo de insercion total: " + insertionTime + "milisegundos");
            System.out.println("Tiempo de insercion por palabra: " + insertionTimePerWord + "milisegundos");

            System.out.println("Palabras buscadas: " + wordsToSearch);
            long searchTime = (end_search - start_search);
            long searchTimePerWord = searchTime / wordsToSearch;
            System.out.println("Tiempo de busqueda total: " + searchTime + "milisegundos");
            System.out.println("Tiempo de busqueda por palabra: " + searchTimePerWord + "milisegundos");
            br.close();

        }

    }
}
