import java.util.ArrayList;
import java.util.Comparator;

/**
 * Clase que modela el BST (Binary Search Tree)
 * @author diego leiva
 *
 * Referencia de MalonsoUVG
 */
public class BinarySearchTree<K, V> implements IBinarySearchTree<K, V> {
	
	private int count;
	private Association<K, V> root;
	private Comparator<K> keyComparator;
	
	public BinarySearchTree(Comparator<K> _keyComparator) {
		this.keyComparator = _keyComparator;
		root = null;
		count = 0;
	}


	/**
	 * Inserta un valor en el nodo dado
	 * @param id el id del nodo
	 * @param value el valor a insertar
	 */
	@Override
	public void insert(K id, V value) {
		
		if (isEmpty()) {
			root = new Association<K, V>(id, value);
			count++;
		} else {
			internalInsert(root, id, value);
		}
	}

	/**
	 * Elimina un nodo dado
	 * @param id el id del nodo
	 * @return
	 */
	@Override
	public V delete(K id) {
		if (!isEmpty()) {
			int result = keyComparator.compare(root.getId(), id);
			
			if (result > 0) {
				return internalDelete(root.getLeft(), id, true);
			} else if (result < 0) {
				return internalDelete(root.getRight(), id, false);
			} else { //la raiz es el nodo a borrar
				
				if (count() == 1) {
					
					V temp = root.getValue();
					root = null;
					count--;
					return temp;
					
				} else {
					
					V tempValue = root.getValue();
					
					if (root.getRight() != null) { //Buscar hijo derecho mas izquierdo
							
						Association<K, V> leftOfTheRights = root.getRight();
						
						while(leftOfTheRights.getLeft() != null) {
							leftOfTheRights = leftOfTheRights.getLeft(); 
						}
						
						//asignar el lado izquierdo
						leftOfTheRights.setLeft(root.getLeft());
						if (leftOfTheRights.getLeft() != null)
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						
						//asignar el lado derecho
						if (keyComparator.compare(root.getRight().getId(), leftOfTheRights.getId()) != 0) { //Solo si el de la izqueirda de  leftOfTheRights es diferente a root.right
							leftOfTheRights.getParent().setLeft(null);
							
							Association<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) {
								newRootRight = newRootRight.getRight();
							}
							
							newRootRight.setRight(root.getRight());
							if (newRootRight.getRight() != null) {
								newRootRight.getRight().setParent(newRootRight);;
							}
						}
						
						//asignar nuevos padres
						if (leftOfTheRights.getRight() != null)
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						
						leftOfTheRights.setParent(null);
						root = leftOfTheRights;
						
						count--;
						return tempValue;
						
					} else { //Buscar hijo izquierdo mas derecho
						
						Association<K, V> rightOfTheLefts = root.getLeft();
						
						while(rightOfTheLefts.getRight() != null) {
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						}
						
						//asignar el lado izquierdo
						rightOfTheLefts.setRight(root.getRight());
						if (rightOfTheLefts.getRight() != null)
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						
						//asignar el lado derecho
						if (keyComparator.compare(root.getLeft().getId(), rightOfTheLefts.getId()) != 0) { //Solo si rightOfTheLefts es diferente a root.left
							rightOfTheLefts.getParent().setRight(null);
							
							Association<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) {
								newRootLeft = newRootLeft.getLeft();
							}
							
							newRootLeft.setLeft(root.getLeft());
							if (newRootLeft.getLeft() != null) {
								newRootLeft.getLeft().setParent(newRootLeft);;
							}
						}
						
						//asignar nuevos padres
						if (rightOfTheLefts.getLeft() != null)
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						
						rightOfTheLefts.setParent(null);
						root = rightOfTheLefts;
						
						count--;
						return tempValue;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 	Busca el nodo dado
	 * @param id el id del nodo
	 * @return el valor del nodo
	 */
	@Override
	public V find(K id) {
		return internalFind(root, id);
	}

	/**
	 * Cuenta la cantidad de nodos del arbol binario
	 * @return la cantidad de nodos
	 */
	@Override
	public int count() {
		return count;
	}

	/**
	 * Verifica si el arbol esta vacio
	 * @return verdadero si esta vacio, falso si no lo esta
	 */
	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * Obtiene un listado de los elementos que componen al arbol binario
	 * @return el listado de elementos
	 */
	@Override
	public ArrayList<V> getElements() {
		ArrayList<V> list = new ArrayList<V>();
		
		internalGetElements(list, root);
		
		return list;
	}

	/**
	 * Metodo de ordenamiento InOrder del arbol binario
	 * @param traversal el recorrido del ordenamiento
	 */
	@Override
	public void inOrder(ITreeTraversal<V> traversal) {
		internalInOrder(root, traversal);
	}

	/**
	 * Metodo de ordenamiento preOrder del arbol binario
	 * @param traversal el recorrido del ordenamiento
	 */
	@Override
	public void preOrder(ITreeTraversal<V> traversal) {
		internalPreOrder(root, traversal);
		
	}

	/**
	 * Metodo de ordenamiento postOrder del arbol binario
	 * @param traversal el recorrido del ordenamiento
	 */
	@Override
	public void postOrder(ITreeTraversal<V> traversal) {
		internalPostOrder(root, traversal);
	}


	/**
	 * Insercion de nodo dentro del arbol binario
	 * @param actual el nodo actual
	 * @param id el id del nodo
	 * @param value el valor del nodo
	 */
	private void internalInsert(Association<K, V> actual, K id, V value) {
		
		int result = keyComparator.compare(actual.getId(), id);
		
		if (result > 0) { //si el id actual es mayor que el nuevo id, entonces buscar en el lado izquierdo
			
			if (actual.getLeft() == null) { //No posee hijos del lado izquierdo
				Association<K, V> newNode = new Association<K, V>(id, value);
				actual.setLeft(newNode);
				newNode.setParent(actual);
				count++;
			} else {
				internalInsert(actual.getLeft(), id, value);
			}
			
		} else if (result < 0) { //si el id actual es menor que el nuevo id, entonces buscar en el lado derecho
			if (actual.getRight() == null) { //no posee hijos del lado derecho
				Association<K, V> newNode = new Association<K, V>(id, value);
				actual.setRight(newNode);
				newNode.setParent(actual);
				count++;
			} else {
				internalInsert(actual.getRight(), id, value);
			}
		}
		
	}

	/**
	 * Metodo de ordenamiento interno InOrder del arbol binario
	 * @param actual el nodo actual
	 * @param traversal el recorrido del ordenamiento
	 */
	private void internalInOrder(Association<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
			internalInOrder(actual.getLeft(), traversal);
			
			traversal.Walk(actual.getValue());
			
			internalInOrder(actual.getRight(), traversal);
		}
	}

	/**
	 * Metodo de ordenamiento interno PreOrder del arbol binario
	 * @param actual el nodo actual
	 * @param traversal el recorrido del ordenamiento
	 */
	private void internalPreOrder(Association<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
			traversal.Walk(actual.getValue());
			
			internalPreOrder(actual.getLeft(), traversal);
			
			internalPreOrder(actual.getRight(), traversal);
		}
	}

	/**
	 * Metodo de ordenamiento interno PostOrder del arbol binario
	 * @param actual el nodo actual
	 * @param traversal el recorrido del ordenamiento
	 */
	private void internalPostOrder(Association<K, V> actual, ITreeTraversal<V> traversal) {
		if (actual != null) {
		
			internalPostOrder(actual.getLeft(), traversal);
			
			internalPostOrder(actual.getRight(), traversal);
			
			traversal.Walk(actual.getValue());
		}
	}

	/**
	 * Busqueda de nodo interna
	 * @param actual el nodo actual
	 * @param id el id del nodo
	 * @return el valor del nodo a buscar
	 */
	private V internalFind(Association<K, V> actual, K id) {
		if (actual != null) {
			int result = keyComparator.compare(actual.getId(), id);
			
			if (result > 0) {
				return internalFind(actual.getLeft(), id);
			} else if (result < 0) {
				return internalFind(actual.getRight(), id);
			} else {
				return actual.getValue();
			}
			
		} else {
			return null;
		}
	}

	/**
	 * Obtencion de elementos internos del arbol
	 * @param list listado de nodos del arbol
	 * @param actual el nodo actual
	 */
	private void internalGetElements(ArrayList<V> list, Association<K, V> actual) {
		if (actual != null) {
			internalGetElements(list, actual.getLeft());
			
			list.add(actual.getValue());
			
			internalGetElements(list, actual.getRight());
		}
	}

	/**
	 * Eliminacion de nodo interno
	 * @param actual el nodo actual
	 * @param id el id del nodo
	 * @param isLeft si esta a la izquierda o no
	 * @return el valor del nodo
	 */
	private V internalDelete(Association<K, V> actual, K id, boolean isLeft) {
		if (actual != null) {
			int result = keyComparator.compare(actual.getId(), id);
			
			if (result > 0) { //busca si esta en el lado izquierdo
				return internalDelete(actual.getLeft(), id, true);
			} else if (result < 0) {//busca si esta en el lado derecho
				return internalDelete(actual.getRight(), id, false);
			} else { //el nodo actual es el que se elimina
				
				//es un nodo izquierdo
				if ( (actual.getLeft() == null) && (actual.getRight() == null) ) { // si es izquierdo
					V tempValue = actual.getValue();
					if (isLeft) {
						actual.getParent().setLeft(null);
						actual.setParent(null);
					} else {
						actual.getParent().setRight(null);
						actual.setParent(null);
					}
					count--;
					return tempValue;
				} else { //es un nodo intermedio
				
					V tempValue = actual.getValue();
					
					if (actual.getRight() != null) { //Buscar hijo derecho mas izquierdo
						
						Association<K, V> leftOfTheRights = actual.getRight();
						
						while(leftOfTheRights.getLeft() != null) {
							leftOfTheRights = leftOfTheRights.getLeft(); 
						}
						
						//asignar lado izquierdo
						leftOfTheRights.setLeft(actual.getLeft());
						if (leftOfTheRights.getLeft() != null)
							leftOfTheRights.getLeft().setParent(leftOfTheRights);
						
						//asignar lado derecho
						if (keyComparator.compare(actual.getRight().getId(), leftOfTheRights.getId()) != 0) { //Aolo si leftOfTheRights es diferente a root.right
							leftOfTheRights.getParent().setLeft(null);
							
							Association<K, V> newRootRight = leftOfTheRights;
							
							while (newRootRight.getRight() != null) {
								newRootRight = newRootRight.getRight();
							}
							
							newRootRight.setRight(actual.getRight());
							if (newRootRight.getRight() != null) {
								newRootRight.getRight().setParent(newRootRight);;
							}
							
						}
						
						//asignar padres
						if (leftOfTheRights.getRight() != null)
							leftOfTheRights.getRight().setParent(leftOfTheRights);
						
						//asignar nuevo hijo a los padres
						leftOfTheRights.setParent(actual.getParent());
						if (isLeft) {
							leftOfTheRights.getParent().setLeft(leftOfTheRights);
						} else {
							leftOfTheRights.getParent().setRight(leftOfTheRights);
						}
						
						count--;
						return tempValue;
						
					} else { //Buscar hijo izquierdo mas derecho
						
						Association<K, V> rightOfTheLefts = actual.getLeft();
						
						while(rightOfTheLefts.getRight() != null) {
							rightOfTheLefts = rightOfTheLefts.getRight(); 
						}
						
						//asignar lado derecho
						rightOfTheLefts.setRight(actual.getRight());
						if (rightOfTheLefts.getRight() != null)
							rightOfTheLefts.getRight().setParent(rightOfTheLefts);
						
						//asignar lado izquierdo
						if (keyComparator.compare(actual.getLeft().getId(), rightOfTheLefts.getId()) != 0) { //Solo si rightOfTheLefts es diferente a root.left
							rightOfTheLefts.getParent().setRight(null);
							
							Association<K, V> newRootLeft = rightOfTheLefts;
							
							while (newRootLeft.getLeft() != null) {
								newRootLeft = newRootLeft.getLeft();
							}
							
							newRootLeft.setLeft(actual.getLeft());
							if (newRootLeft.getLeft() != null) {
								newRootLeft.getLeft().setParent(newRootLeft);;
							}
							
						}
						
						//asignar padres
						if (rightOfTheLefts.getLeft() != null)
							rightOfTheLefts.getLeft().setParent(rightOfTheLefts);
						
						rightOfTheLefts.setParent(actual.getParent());
						if (isLeft) {
							rightOfTheLefts.getParent().setLeft(rightOfTheLefts);
						} else {
							rightOfTheLefts.getParent().setRight(rightOfTheLefts);
						}
						
						count--;
						return tempValue;
					}
					
				}
			}
		} else {
			return null;
		}
	}
}
