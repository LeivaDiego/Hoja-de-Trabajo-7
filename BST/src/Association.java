/**
 * Clase que modela las asociaciones del diccionario
 * (es un TreeNode)
 * @author diego leiva
 *
 * Referencia de MalonsoUVG
 */
public class Association<K, V> {

	private V value;
	private K id;
	private Association<K, V> left;
	private Association<K, V> right;
	private Association<K, V> parent;
	
	public Association(K id, V value) {
		setId(id);
		setValue(value);
		setLeft(null);
		setRight(null);
		setParent(null);
	}
	
	/**
	 * Obtiene el valor del nodo de arbol
	 * @return el valor del nodo
	 */
	public V getValue() {
		return value;
	}

	/**
	 * Establece el valor del nodo de arbol
	 * @param value el valor a establecer
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * Obtiene el id del nodo de arbol
	 * @return el id del nodo
	 */
	public K getId() {
		return id;
	}

	/**
	 * Establece el id del nodo de arbol
	 * @param id el id a establecer
	 */
	public void setId(K id) {
		this.id = id;
	}

	/**
	 * Obtiene el valor del nodo de la izquierda
	 * @return el valor del nodo
	 */
	public Association<K, V> getLeft() {
		return left;
	}

	/**
	 * Establece el valor del nodo de la izquierda
	 * @param left el valor a establecer
	 */
	public void setLeft(Association<K, V> left) {
		this.left = left;
	}

	/**
	 * Obtiene el valor del nodo de la derecha
	 * @return el valor del nodo
	 */
	public Association<K, V> getRight() {
		return right;
	}

	/**
	 * Establece el valor del nodo de la derecha
	 * @param right el valor a establecer
	 */
	public void setRight(Association<K, V> right) {
		this.right = right;
	}

	/**
	 * Obtiene el valor del nodo padre
	 * @return el valor del nodo padre
	 */
	public Association<K, V> getParent() {
		return parent;
	}

	/**
	 * Establece el valor del nodo padre
	 * @param parent el valor a establecer
	 */
	public void setParent(Association<K, V> parent) {
		this.parent = parent;
	}
	
	
}
