
import java.util.ArrayList;

/**
 * Interfaz para el Arbol binario
 * @author diego leiva
 *
 * Referencia de MalonsoUVG
 */
public interface IBinarySearchTree<K, V> {

	void insert(K id, V value);
	
	V delete(K id);
	
	V find(K id);
	
	int count();
	
	boolean isEmpty();
	
	ArrayList<V> getElements();
	
	void inOrder(ITreeTraversal<V> traversal);
	
	void preOrder(ITreeTraversal<V> traversal);
	
	void postOrder(ITreeTraversal<V> traversal);
	
}
