import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void searchWordTest() {
        Dictionary dict = new Dictionary();
        dict.AddWord("apple", "manzana", "pomme");
        dict.AddWord("banana", "plátano", "banane");
        dict.AddWord("cat", "gato", "chat");

        // Búsqueda de palabras en inglés
        assertEquals("manzana", dict.SearchWord("apple"));
        assertEquals("plátano", dict.SearchWord("banana"));
        assertEquals("gato", dict.SearchWord("cat"));

        // Búsqueda de palabras en español
        assertEquals("manzana", dict.SearchWord("manzana"));
        assertEquals("plátano", dict.SearchWord("plátano"));
        assertEquals("gato", dict.SearchWord("gato"));

        // Búsqueda de palabras en francés
        assertEquals("apple", dict.SearchWord("pomme"));
        assertEquals("banana", dict.SearchWord("banane"));
        assertEquals(null, dict.SearchWord("chien"));
    }

    @Test
    void deleteWordTest() {
        Dictionary dict = new Dictionary();
        dict.AddWord("apple", "manzana", "pomme");
        dict.AddWord("banana", "plátano", "banane");
        dict.AddWord("cat", "gato", "chat");

        // Eliminación de palabras en inglés
        assertEquals("apple", dict.DeleteWord("apple"));
        assertNull(dict.SearchWord("apple"));

        // Eliminación de palabras en español
        assertEquals("banana", dict.DeleteWord("plátano"));
        assertNull(dict.SearchWord("plátano"));

        // Eliminación de palabras en francés
        assertEquals(null, dict.DeleteWord("pomme"));
        assertNull(dict.SearchWord("pomme"));
    }
}