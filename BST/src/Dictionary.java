/**
 * Clase que modela y hace las funciones del diccionario de ingles, español y frances
 * @author diego leiva
 */
public class Dictionary {
    BinarySearchTree<String, String> eng_to_spa = new BinarySearchTree<String,String>(new StringComparator()); //ingles a español
    BinarySearchTree<String, String> fre_to_spa = new BinarySearchTree<String,String>(new StringComparator()); //frances a español
    BinarySearchTree<String, String> spa_to_eng = new BinarySearchTree<String,String>(new StringComparator()); //español a ingles
    BinarySearchTree<String, String> spa_to_fre = new BinarySearchTree<String,String>(new StringComparator()); //español a frances


    /**
     * Busca una palablra en el diccionario en cualquiera de los tres idiomas
     * @param word palabra a buscar
     * @return palabra en ingles
     */
    public String SearchWord(String word) {
        String eng_word="";
        String spa_word="";
        String fre_word="";
        if(eng_to_spa.find(word)!=null) {
            eng_word=word;
            spa_word= eng_to_spa.find(word);
            fre_word= spa_to_fre.find(spa_word);
        }else if (fre_to_spa.find(word)!=null){
            fre_word=word;
            spa_word= fre_to_spa.find(word);
            eng_word= spa_to_eng.find(spa_word);
        }else if(spa_to_eng.find(word)!=null) {
            spa_word=word;
            fre_word= spa_to_fre.find(word);
            eng_word= spa_to_eng.find(word);
        }else {
            spa_word=null;
        }
        return spa_word;
    }

    /**
     * Elimina una palabra dicionario
     * La palabra puede ser ingresada en cualquiera de los tres idiomas
     * @param word palabra a eliminar
     * @return palabra que fue eliminada, en ingles
     */
    public String DeleteWord(String word) {
        String eng_word="";
        String spa_word="";
        String fre_word="";
        if(eng_to_spa.find(word)!=null) {
            eng_word=word;
            spa_word= eng_to_spa.find(word);
            fre_word= spa_to_fre.find(spa_word);
            eng_to_spa.delete(eng_word);
            fre_to_spa.delete(fre_word);
            spa_to_fre.delete(spa_word);
            spa_to_eng.delete(spa_word);
        }else if (fre_to_spa.find(word)!=null){
            fre_word=word;
            spa_word= fre_to_spa.find(word);
            eng_word= spa_to_eng.find(spa_word);
            eng_to_spa.delete(eng_word);
            fre_to_spa.delete(fre_word);
            spa_to_fre.delete(spa_word);
            spa_to_eng.delete(spa_word);
        }else if(spa_to_eng.find(word)!=null) {
            spa_word=word;
            fre_word= spa_to_fre.find(word);
            eng_word= spa_to_eng.find(word);
            eng_to_spa.delete(eng_word);
            fre_to_spa.delete(fre_word);
            spa_to_fre.delete(spa_word);
            spa_to_eng.delete(spa_word);
        }else {
            eng_word=null;
        }
        return eng_word;
    }


    /**
     * Agrega nuevas palabras al diccionario
     * @param eng palabra en ingles
     * @param spa palabra en español
     * @param fre palabra en frances
     */
    public void AddWord(String eng, String spa, String fre) {
        eng_to_spa.insert(eng.toLowerCase(),spa.toLowerCase());
        fre_to_spa.insert(fre.toLowerCase(),spa.toLowerCase());
        spa_to_eng.insert(spa.toLowerCase(),eng.toLowerCase());
        spa_to_fre.insert(spa.toLowerCase(),fre.toLowerCase());
    }

    /**
     * Muestra el contenido del diccionario en ingles-español ordenado a partir de las palabras en ingles
     * @return lista de palabras en ingles y español
     */
    public String getEngToSpa() {
        TreeTraversal<String> traversal = new TreeTraversal<>();
        eng_to_spa.inOrder(traversal);
        String str ="";
        for(int i = 0; i< eng_to_spa.count(); i++) {
            str+="("+ spa_to_eng.find(traversal.list.get(i))+","+traversal.list.get(i)+")";
        }
        return str;
    }

    /**
     * Muestra el contenido del diccionario en frances-español ordenado a partir de las palabras en frances
     * @return lista de palabras en frances y español
     */
    public String getFreToSpa() {
        TreeTraversal<String> traversal = new TreeTraversal<>();
        fre_to_spa.inOrder(traversal);
        String str ="";
        for(int i = 0; i< fre_to_spa.count(); i++) {
            str+="("+ spa_to_fre.find(traversal.list.get(i))+","+traversal.list.get(i)+")";
        }
        return str;
    }
}
