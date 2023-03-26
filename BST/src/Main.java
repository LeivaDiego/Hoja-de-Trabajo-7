import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Hoja-de-Trabajo-7/BST/src/texto.txt";
        String dictPath = "C:/Users/diego/Documents/UVG/5to Semestre/Algoritmos y Estructuras de Datos/Hoja-de-Trabajo-7/BST/src/diccionario.txt";

        boolean exit = false;
        Scanner input = new Scanner(System.in);
        ArrayList<String> dictArrayList = new ArrayList<String>();
        Dictionary dictionary = new Dictionary();
        FileManager fileManager = new FileManager();
        View view = new View();

        fileManager.FileScanner(dictPath, dictionary,dictArrayList);
        view.ShowMessage("Bienvenido a su diccionario de inles - español - frances");
        view.Separator();
        while (!exit){
            int opt = 0;
            view.Menu();
            opt = input.nextInt();
            switch (opt){
                case 1: //Mostrar diccionario ing - esp
                    view.Separator();
                    view.ShowMessage(dictionary.getEngToSpa());
                    view.Separator();
                    break;
                case 2: //Mostrar diccionario fra - esp
                    view.Separator();
                    view.ShowMessage(dictionary.getFreToSpa());
                    view.Separator();
                    break;
                case 3: //Agregar palabra al diccionario
                    view.Separator();
                    view.ShowMessage("Ingrese la palabra a agregar");
                    view.ShowMessage("El formato debe ser: ingles,español,frances");
                    String wordString = input.nextLine();
                    String[] validStrings = wordString.split(",");
                    if(validStrings.length==3) {
                        dictArrayList.add(wordString);
                        dictionary.AddWord(validStrings[0], validStrings[1], validStrings[2]);
                        fileManager.SaveFile(dictPath,dictArrayList);
                        view.ShowMessage("La palabra Ha sido agregada");
                    }else {
                        view.Error("La palabra no esta en el formato correcto");
                    }
                    view.Separator();
                    break;
                case 4: //Eliminar palabra del diccionario
                    view.Separator();
                    view.ShowMessage("Ingrese que palabra desea eliminar del diccionario");
                    String delete_word = input.nextLine().toLowerCase();
                    String delete_word_eng = dictionary.DeleteWord(delete_word);
                    if (delete_word_eng == null){
                        view.Error("La palabra ingresada no Existe en el diccionario");
                    }else {
                        for (int i=0; i<dictArrayList.size();i++){
                            String[] temp = dictArrayList.get(i).split(",");
                            if (temp[0].equals(delete_word_eng)){
                                dictArrayList.remove(i);
                            }
                        }
                        fileManager.SaveFile(dictPath, dictArrayList);
                        view.ShowMessage("La palabra ha sido eliminada");
                    }
                    view.Separator();
                    break;
                case 5: //Traducir archivo
                    fileManager.TranslateFile(filePath,dictionary);
                    break;
                case 6: //Salir
                    view.ShowMessage("Ha salido del programa");
                    exit = true;
                    break;
                default: // Cualquier otra opcion
                    view.Error("Opcion no valida, intente nuevamente");
                    break;
            }
        }

    }
}