/**
 * Clase que sirve para mostrar en pantalla diferentes mensajes
 * @author diego leiva
 */
public class View {

    public void Menu(){
        System.out.println("1. Mostrar Diccionario Ingles-Español");
        System.out.println("2. Mostrar Diccionario Frances-Español");
        System.out.println("3. Agregar palabra al diccionario");
        System.out.println("4. Borrar palabra del diccionario");
        System.out.println("5. Leer y traducir arhivo");
        System.out.println("6. Salir\n");
    }

    public void Separator(){
        System.out.println("\n------------------------------------------\n");
    }

    public void Error(String error){
        System.out.println("ERROR: "+ error);
    }

    public void ShowMessage(String message){
        System.out.println(message);
    }

}
