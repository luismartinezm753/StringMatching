import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by luism on 15-05-15.
 */
public class StringParser {
    static String[] SPECIAL_CHARS={",",".",":",";","{","}","[","]","!","¡","?","¿","\n","&","#","$"};
    static String[] FILE_NAMES={"","","",""};
    public static void main(String[] args) {
        try {
            //Read File Line By Line
            for (int i = 0; i < FILE_NAMES.length; i++) {
                BufferedReader br = new BufferedReader(new FileReader(FILE_NAMES[i]));
                BufferedWriter writer = new BufferedWriter(new FileWriter("Datos.txt"));
                String strLine;
                while ((strLine = br.readLine()) != null) {
                    strLine=strLine.toLowerCase();
                    for (int j = 0; j < SPECIAL_CHARS.length; i++) {
                        strLine=strLine.replaceAll(SPECIAL_CHARS[j],"");
                    }
                    writer.write(strLine);
                }
                br.close();
            }
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }
}
