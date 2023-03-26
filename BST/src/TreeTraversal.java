import java.util.ArrayList;

/**
 * Clase que modela el recorrido del arbol
 * @author diego leiva
 *
 * Referencia de MalonsoUVG
 */
public class TreeTraversal<V> implements ITreeTraversal<V>{

    public ArrayList<V> list = new ArrayList<V>();

    /**
     * Realiza el recorrido
     * @param value valor
     */
    @Override
    public void Walk(V value) {
        list.add(value);
    }
}
