import java.util.Comparator;

/**
 * Clase comparadora de cadenas String
 * @author diego leiva
 *
 * Refencia de malonsoUVG
 */
public class StringComparator implements Comparator<String> {

    /**
     * Compara las cadenas string dadas
     * @param o1 el primer objeto a ser comparado
     * @param o2 el segundo objeto a ser comparado
     * @return la comparacion
     */
    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
