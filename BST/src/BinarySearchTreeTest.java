import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void InsertOneElementTest() {
        BinarySearchTree<String, String> tree = new BinarySearchTree<String, String>(new StringComparator());
        tree.insert("a", "b");
        assertEquals(1, tree.count());
        assertEquals("b", tree.getElements().get(0));
    }

    @Test
    void InsertMultipleElementsTest() {
        BinarySearchTree<String, String> tree = new BinarySearchTree<String, String>(new StringComparator());
        tree.insert("d", "4");
        tree.insert("b", "2");
        tree.insert("a", "1");
        tree.insert("c", "3");

        assertEquals(4, tree.count());
        assertEquals("4", tree.getElements().get(3));
    }

    @Test
    void InOrderWalkTest() {
        BinarySearchTree<String, String> tree = new BinarySearchTree<String, String>(new StringComparator());
        tree.insert("d", "test4");
        tree.insert("b", "test2");
        tree.insert("a", "test1");
        tree.insert("c", "test3");

        assertEquals(4, tree.count());

        TreeTraversal<String> recorrido = new TreeTraversal();
        tree.inOrder(recorrido);

        assertEquals("test1", recorrido.list.get(0));
        assertEquals("test2", recorrido.list.get(1));
        assertEquals("test3", recorrido.list.get(2));
        assertEquals("test4", recorrido.list.get(3));
    }

    @Test
    void DeleteOneElementTest() {
        BinarySearchTree<String, String> tree = new BinarySearchTree<String, String>(new StringComparator());
        tree.insert("this", "test");
        assertEquals(1, tree.count());
        assertEquals("test", tree.getElements().get(0));
        tree.delete("this");
        assertEquals(0, tree.count());
    }

}