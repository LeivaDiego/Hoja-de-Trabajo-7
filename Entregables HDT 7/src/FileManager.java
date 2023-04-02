import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private View view = new View();

    /**
     * Lee un archivo de texto y obtiene las palabras del diccionario
     * @param path la ruta del archivo
     * @param dict el diccionario
     * @param dictArray el array del diccionario
     */
    public void FileScanner(String path, Dictionary dict, ArrayList<String> dictArray){
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line=bufferedReader.readLine())!= null){
                dictArray.add(line);
                String[] values = line.split(",");
                dict.AddWord(values[0],values[1],values[2]);
            }
            bufferedReader.close();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    /**
     * Guarda el diccionario modificado en el archivo de texto
     * @param path la ruta del archivo
     * @param words las palabras a guardar
     */
    public void SaveFile(String path, ArrayList<String> words){

        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i =0; i<words.size(); i++){
                bufferedWriter.write(words.get(i)+"\n");
            }
            bufferedWriter.close();

        }catch (Exception e){
            view.Error((String.valueOf(e)));
            throw new RuntimeException(e);
        }
    }

    /**
     * Traduce un archivo de texto al español
     * @param path la ruta del archivo
     * @param dict el dictionario a traducir
     */
    public void TranslateFile(String path, Dictionary dict){
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line=bufferedReader.readLine())!= null){
                String newString = "";
                line=line.replace(".","");
                line=line.replace(",", "");
                line=line.replace(";", "");
                line=line.replace(":", "");
                String[] wordStrings = line.split(" ");
                for(int i =0;i<wordStrings.length;i++) {
                    if(dict.SearchWord(wordStrings[i])!=null) {
                        wordStrings[i]=dict.SearchWord(wordStrings[i]);
                    }else {
                        wordStrings[i]="*"+wordStrings[i]+"*";
                    }
                    newString+=wordStrings[i]+" ";
                }
                view.ShowMessage(newString);
            }
            bufferedReader.close();

        }catch (IOException e){
            view.Error((String.valueOf(e)));
            throw new RuntimeException(e);
        }
    }

}
