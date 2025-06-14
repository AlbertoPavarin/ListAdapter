package myTest;

import com.sun.source.tree.AssertTree;
import java.util.Vector;
import myAdapter.HIterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThrows;


import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;
import myAdapter.HList;
import myAdapter.HIllegalStateException;


/**
 * Test suite per la classe {@link myAdapter.ListAdapter}, che implementa {@link myAdapter.HList} <br />
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi di una {@code ListAdapter}.
 * <p>
 * Design:  Si utilizzano JUnit 4.13, hamcrest-core-1.3 <br />
 *          La test suite include test cases per verificare il corretto e completo funzionamento dei metodi dell'interfaccia {@link myAdapter.HList} <br />
 *          su ListAdapter vuoti e non vuoti e tutte le sue sotto classi. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.ListAdapter.IteratorAdapter} e di {@link myAdapter.ListAdapter.ListIteratorAdapter}, utilizzati in qualche metodo. <br />
 *          Prima di eseguire qualunque test la ListAdapter viene popolata con due valori di prova, quindi prima di ogni test la ListAdapter sarà sempre popolata con quei due valori
 *          {@code#add(Object) add} usato per aggiungere gli elementi nella funzione setUp.
 */
public class TestListAdapter {

    /**
     * Costruttore di default.
     * Inizializza la classe di test.
     */
    public TestListAdapter() {
    }

    /**
     * La ListAdapter utilizzata per i test. Contiene gli elementi
     */
    private ListAdapter list = new ListAdapter();

    /**
     * Configura la ListAdapter prima di iniziare con tutti i test
     */
    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
    }

    // TEST METODO clear
    /**
     * Test del metodo {@link myAdapter.ListAdapter#clear() clear} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code clear()} svuoti correttamente la lista. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code clear()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che svuoti la lista <br />
     *              e di conseguenza azzeri la lunghezza della lista. <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code clear()}, {@code size()} <br />
     *                  1.      Si verifica che la lunghezza iniziale sia 2, quella settata dalla funzione di setUp.  <br />
     *                  2.      Si svuota la lista e si verifica che la lunghezza della lista sia 0. <br />
     *                  3.      Si richiama il metodo per vedere che la dimensione non ricambi richiamando il metodo
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve essere vuota dopo il test.
     * 
     * @r.esult    {@code clear()} deve svuotare la lista e modificarne la lunghezza.
     */
    @Test
    public void testClear() {
        assertTrue(list.size() == 2); // controllo se la dimensione della lista è quella iniziale corretta

        list.clear();
        assertEquals("Se la lista è vuota ritorna True", 0, list.size());

        list.clear();
         // Controllo che la dimensione non sia cambiata con un altro clear
        assertEquals("Se la lista è vuota ritorna True", 0, list.size());
        assertTrue(list.isEmpty() && list.size() == 0);
    }
    // FINE TEST METODO clear

    // TEST METODO size
    /**
     * Test del metodo {@link myAdapter.ListAdapter#size() size} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code size()} ritorni la lunghezza corretta della lista. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code size()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che ritorni la lunghezza corretta <br />
     *              della lista senza alterarla <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code add()}, {@code remove()} <br />
     *                  1.      Si verifica che la lunghezza iniziale sia 2, quella settata dalla funzione di setUp.  <br />
     *                  2.      Si chiama {@code remove()} e si controlla se la dimensione della lista è diminuita di 1, diventando quindi 1. <br />
     *                  3.      Si richiama il metodo per vedere che la dimensione non ricambi richiamando il metodo <br />
     *                  4.      Si aggiungono due elemento alla lista e si controlla se la dimensione della lista è aumentata di 2, diventando quindi 3. <br />
     *                  5.      Si svuota la lista e si controlla se la dimensione della lista sia 0. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve essere vuota dopo il test.
     * 
     * @r.esult    {@code size()} deve restituire la lunghezza corretta della lista.
     */
    @Test
    public void testSize() {
        assertEquals("Se la dimensione della lista è 2 ritorna True", 2, list.size());

        list.remove(1);
        assertEquals("Se la dimensione della lista è 1 ritorna True", 1, list.size());

        list.add(3);
        list.add(1);
        assertEquals("Se la dimensione della lista è 3 ritorna True", 3, list.size());

        list.clear();
        assertEquals("Se la lista è vuota ritorna True", 0, list.size());
    }
    // FINE TEST METODO size

    // TEST METODO isEmpty
    /**
     * Test del metodo {@link myAdapter.ListAdapter#isEmpty() isEmpty()} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code isEmpty()} ritorni {@code true} se la lista è vuota, false altrimenti. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code isEmpty()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che ritorni {@code true} quando la <br />
     *             lista è vuota e {@code false} quando non lo è <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code add()}, {@code remove()} <br />
     *                  1.      Si verifica che inizialmente il metodo ritorni {@code false} essendo la lista popolata.  <br />
     *                  2.      Si chiama clear per svuotare la lista e si controlla se la dimensione è 0. <br />
     *                  3.      Si verifica ora che essendo la lista svuotata e di dimensione 0, il metodo {@code isEmpty} ritorni {@code true} <br />
     *                  4.      Si aggiunge 1 elemento alla lista e si controlla se la dimensione della lista è aumentata di 1, diventando quindi 1. <br />
     *                  5.      Si verifica ora che avendo degli elementi al suo interno la lista non sia vuota, controllando che isEmpty ritorni {@code false}. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere lunghezza 1.
     * 
     * @r.esult    {@code isEmpty()} deve restituire {@code true} se la lista è vuota, false altrimenti.
     */
    @Test
    public void testIsEmpty() {
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.size() == 0); // controllo se la lista è stata svuotata
        assertEquals("Se la lista è vuota ritorna True", true, list.isEmpty());

        list.add(1);
        assertTrue(list.size() == 1); // controllo se è stato aggiunto l'elemento alla lista
        assertEquals("Se la lista non è vuota ritorna False", false, list.isEmpty());
    }
    // FINE TEST METODO isEmpty

    // TEST METODO contains
     /**
     * Test del metodo {@link myAdapter.ListAdapter#contains(Object) contains(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code @constains(Object)} ritorni {@code true} se la lista è vuota, false altrimenti. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code @constains(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che ritorni {@code true} quando la <br />
     *             lista contiene l'elemento e {@code false} quando non lo contiene <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code clear()}, {@code size()}, {@code get(int)} <br />
     *                  1.      Si verifica che inizialmente il metodo ritorni {@code true} cercando l'elemento ritornato dal metodo {@code get}, che è quindi all'interno della lista.  <br />
     *                  2.      Si chiama {@code clear} per svuotare la lista e si controlla se la dimensione è 0. <br />
     *                  3.      Si verifica ora che essendo la lista svuotata e di dimensione 0, il metodo ritorni {@code false} ricercando l'elemento di prima <br />
     *                  4.      Si aggiunge 1 elemento alla lista. <br />
     *                  5.      Si verifica ora che l'elemento appena aggiunto sia contenuto nella lista, e quindi il metodo deve ritornare {@code true}. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere lunghezza 1.
     * 
     * @r.esult    {@code contains(Object)} deve restituire {@code true} se la lista contiene l'elemento passato, false altrimenti.
     */
    @Test
    public void testContains() {
        Object elem = list.get(0);
        assertTrue(list.contains(elem)); // Controllo se l'elemento 1 è contenuto nella lista

        list.clear();
        assertTrue(list.size() == 0); // Controllo che la lista sia stata effettivamente pulita

        assertFalse(list.contains(elem)); // Controllo che 1 non sia nella lista

        list.add(5);
        assertTrue(list.contains(5));
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#contains(Object) contains(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code contains(Object)} lanci l'eccezione {@code NullPointerException} <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code contains(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando contains con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code contains(Object)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testContainNullObject() {
        list.contains(null);
    }
    // FINE TEST METODO contains

    // TEST METODO toArray
    /**
     * Test del metodo {@link myAdapter.ListAdapter#toArray() toArray()} e {@link myAdapter.ListAdapter#toArray(Object[]) toArray(Object[])} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code toArray()} e {@code toArray(Object[])} ritorni un'array contenente gli elementi della lista. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code toArray()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che ritorni un array della stessa <br />
     *             dimensione e che contenga gli stessi elementi della lista <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code clear()}, {@code size()}, {@code get(int)} <br />
     *                  1.      Si verifica che inizialmente il metodo ritorni {@code true} controllando che l'array ritornato sia un istanza di {@code Object[]}.  <br />
     *                  2.      Si salva l'array in una variabile e si controlla che le due lunghezze siano le stesse <br />
     *                  3.      Per ogni elemento all'interno della lista si controlla che ci sia al corrispondente indice lo stesso elemento all'interno dell'array <br />
     *                  4.      Si crea un altro array con la stessa dimensione della lista e lo si passa a toArray(Object) <br />
     *                  5.      Per ogni elemento all'interno della lista si controlla che ci sia al corrispondente indice lo stesso elemento all'interno del nuovo array creato <br />
     *                  6.      Si svuota la lista e si controlla che la dimensione sia 0. <br />
     *                  7.      Si verifica ora che il nuovo array, creato chiamando {@code toArray} non abbia la stessa lunghezza del primo array creato, essendo il secondo creato da una lista vuota. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve essere vuota
     * 
     * @r.esult   {@code toArray()} e {@code toArray(Object[])} deve restituire un array della stessa dimensione della lista e conn gli stessi elementi.
     */
    @Test
    public void testToArray() {
        assertTrue(list.toArray() instanceof Object[]); // Controllo se quello restituito è un'array

        Object[] arrObjects = list.toArray();
        assertTrue(arrObjects.length == list.size()); // Controllo se le grandezze coincidono

        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), arrObjects[i]);
        }

        Object[] tmpArray = new Object[list.size()];
        list.toArray(tmpArray);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), tmpArray[i]);
        }

        list.clear();
        assertTrue(list.size() == 0); // Controllo se la lista è stata effettivamente svuotata
        
        assertFalse(arrObjects.length == list.toArray().length);
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#toArray(Object[]) toArray(Object[])} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code toArray(Object[])} lanci l'eccezione {@code NullPointerException} <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code toArray(Object[])} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code toArray(Object[])} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code toArray(Object[])} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayNullObject() {
        list.toArray(null);
    }
    // FINE TEST METODO toArray

    // TEST METODO add
    /**
     * Test del metodo {@link myAdapter.ListAdapter#add(Object) add(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code add(Object)} ritorni {@code true} se la lista viene mutata, false altrimenti. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code add(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che ritorni {@code true} quando la <br />
     *             lista viene modificata dall'aggiunta dell'elemento e {@code false} altrimenti <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code get(int)} <br />
     *                  1.      Si salva la lunghezza attuale della lista e si verifica che aggiungendo due elementi con {@code add(Object)} il metodo ritorni true.  <br />
     *                  2.      Si chiama {@code size} e si verifica che la dimensione della lista sia effettivamente cambiata confrontandola con quella iniziale. <br />
     *                  3.      Si verifica che i due elementi inseriti siano dopo i due elementi originariamente all'interno della lista. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere lunghezza 4.
     * 
     * @r.esult    {@code add(Object)} deve restituire {@code true} se la lista viene mutata dal metodo, false altrimenti.
     */
    @Test
    public void testAdd() {
        // Controllo che aggiungendo un elemento alla lista ritorni true
        int oldLenght = list.size();
        assertTrue(list.add(3));
        assertTrue(list.add(4));

        assertNotEquals(list.size(), oldLenght); // Controllo che add abbia cambiato la lunghezza dato che ha aggiunto in coda un elemento

        assertTrue(list.size() == 4); // Controllo se sono effettivamente stati aggiunti

        assertEquals(list.get(2), 3); 
        assertEquals(list.get(3), 4); 
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#add(int, Object) add(int, Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code add(int, Object)} ritorni {@code true} se la lista viene mutata, false altrimenti. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code add(int, Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che ritorni {@code true} quando la <br />
     *             lista viene modificata dall'aggiunta dell'elemento e {@code false} altrimenti <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code get(int)} <br />
     *                  1.      Si salva la lunghezza attuale della lista e si verifica che aggiungendo un elemento con {@code add(int, Object)} in posizione 0 il metodo ritorni true.  <br />
     *                  2.      Si chiama {@code size} e si verifica che la dimensione della lista sia effettivamente cambiata confrontandola con quella iniziale. <br />
     *                  3.      Si verifica che l'elemento inserito sia all'indice 0, e che quello che era precedentemente in 0 sia ora in 1, e così via per gli altri indici. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere lunghezza 4.
     * 
     * @r.esult    {@code add(Object)} deve restituire {@code true} se la lista viene mutata dal metodo, false altrimenti.
     */
    @Test
    public void testAddIndex() {
        // Controllo che aggiungendo un elemento alla lista ritorni true
        int oldLenght = list.size();

        list.add(0, 16);
        assertNotEquals(list.size(), oldLenght); // Controllo che add abbia cambiato la lunghezza dato che ha aggiunto in coda un elemento

        assertTrue(list.size() == 3); // Controllo se sono effettivamente stati aggiunti
        assertEquals(list.get(0), 16); // Controllo che nell'indice 0 cambiato c'è il nuovo elemento
        assertEquals(list.get(1), 1); // Controllo che il valore che era in 0 ora è in 1, se è stata traslata la lista
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#add(Object) add(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code add(Object)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code add(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code add(Object)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code add(Object)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddNullObject() {
        list.add(null);
    }

    /**
    * Test del metodo {@link myAdapter.ListAdapter#add(int, Object) add(int, Object)} della ListAdapter.
    * 
    * @s.ummary   Verifica che il metodo {@code add(int, Object)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null <br />
    * 
    * @d.esign    Si vuole analizzare il comportamento di {@code add(int, Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
    *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
    * 
    * @d.escription    1.      Si verifica che chiamando {@code add(int, Object)} con un parametro Object null generi l'eccezione {@code NullPointerException}.  <br />
    * 
    * @p.recond   La ListAdapter e' stata popolata
    * 
    * @p.ostcond  La ListAdapter rimane invariata.
    * 
    * @r.esult    {@code add(int, Object)} deve generare l'eccezione {@code NullPointerException} quando si passa un oggetto null.
    */
    @Test(expected = NullPointerException.class)
    public void testAddIndexNullObject() {
        list.add(0, null);
    }

    /**
    * Test del metodo {@link myAdapter.ListAdapter#add(int, Object) add(int, Object)} della ListAdapter.
    * 
    * @s.ummary   Verifica che il metodo {@code add(int, Object)} lanci l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido <br />
    * 
    * @d.esign    Si vuole analizzare il comportamento di {@code add(int, Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
    *             {@code NullPointerException} quando viene passato come parametro un indice non valido <br />
    * 
    * @d.escription    1.      Si verifica che chiamando {@code add(int, Object)} con un parametro indice non valido generi l'eccezione {@code IndexOutOfBoundsException}.  <br />
    * 
    * @p.recond   La ListAdapter e' stata popolata
    * 
    * @p.ostcond  La ListAdapter rimane invariata.
    * 
    * @r.esult    {@code add(int, Object)} deve generare l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido.
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexIndexOutOfBounds() {
        list.add(-1, 1);
    }
    // FINE TEST METODO add

    //TEST METODO remove
    /**
     * Test del metodo {@link myAdapter.ListAdapter#remove(Object) remove(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code remove(Object)} ritorni {@code true} se l'elemento viene rimosso. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code remove(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che ritorni {@code true} quando la <br />
     *             lista viene modificata e l'elemento cercato viene eliminato e {@code false} altrimenti <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code add(Object)} <br />
     *                  1.      Si verifica che cercando di eliminare un elemento non in lista il metodo ritorni {@code false}.  <br />
     *                  2.      Si aggiunge un elemento nella lista e si controlla che la dimensione sia aumentata. <br />
     *                  3.      Si verifica che ora l'elemento aggiunto sia stato eliminato e che quindi il metodo ritorni {@code true} e che la dimensione sia cambiata. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione invariata.
     * 
     * @r.esult    {@code remove(Object)} deve restituire {@code true} se la lista viene mutata dal metodo e l'oggetto rimosso, false altrimenti.
     */
    @Test
    public void testRemoveObject() {
        assertFalse(list.remove("Pippo")); // Controllo se l'elemento che sto provando ad eliminare non è presente nella lista

        list.add("Pippo");
        assertTrue(list.size() == 3); // Controllo se la grandezza è cambiata

        assertTrue(list.remove("Pippo")); // Controllo se l'elemento che sto provando ad eliminare ora è presente nella lista

        assertTrue(list.size() == 2); // Controllo se la grandezza è cambiata dopo l'eliminazione dell'elemento

    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#remove(int) remove(int)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code remove(int)} ritorni l'elemento rimosso. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code remove(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che ritorni l'elemento rimosso all'indice passato <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code add(Object)}, {@code get(int)} <br />
     *                  1.      Si aggiunge un elemento alla lista e si controlla che la dimensione sia aumentata.  <br />
     *                  2.      Si controlla che l'elemento in posizione 2, quello appena inserito sia quello rimosso dalla posizione 2 ritornato dal metodo. <br />
     *                  3.      Si verifica che la dimensione sia nuovamente cambiata. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione invariata.
     * 
     * @r.esult    {@code remove(int)} deve restituire l'elemento eliminato in posizione dell'indice passato.
     */
    @Test
    public void testRemoveIndex() {
        list.add("Pippo");
        assertTrue(list.size() == 3); // Controllo se la grandezza è cambiata

        assertTrue(list.get(2) == list.remove(2));
        assertTrue(list.size() == 2); // Controllo se la grandezza è cambiata
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#remove(Object) remove(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code remove(Object)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code remove(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code remove(Object)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code remove(Object)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveNullObject() {
        list.remove(null);
    }

    /**
    * Test del metodo {@link myAdapter.ListAdapter#remove(int) remove(int)} della ListAdapter.
    * 
    * @s.ummary   Verifica che il metodo {@code remove(int)} lanci l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido <br />
    * 
    * @d.esign    Si vuole analizzare il comportamento di {@code remove(int)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
    *             {@code NullPointerException} quando viene passato come parametro un indice non valido <br />
    * 
    * @d.escription    1.      Si verifica che chiamando {@code remove(int)} con un parametro indice non valido generi l'eccezione {@code IndexOutOfBoundsException}.  <br />
    * 
    * @p.recond   La ListAdapter e' stata popolata
    * 
    * @p.ostcond  La ListAdapter rimane invariata.
    * 
    * @r.esult    {@code remove(int)} deve generare l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido.
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexOutOfBoundsIndex() {
        list.remove(-3);
    }    
    // FINE TEST METODO remove

    // TEST METODO equals
    /**
     * Test del metodo {@link myAdapter.ListAdapter#equals(Object) equals(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code equals(Object)} ritorni {@code true} se l'elemento passato come parametro è uguale alla ListAdapter. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code equals(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che l'oggetto passato per parametro <br />
     *             sia una lista, ha la stessa dimensione, stessi elementi nelle stesse posizioni della ListAdapter, ed è quindi uguale
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code clear()}, {@code add(Object)} <br />
     *                  1.      Si verifica che passando al metodo la stessa lista il metodo ritorni {@code true}.  <br />
     *                  2.      Viene creata una lista copiando la lista originale e si verifica che le due siano ancora uguali. <br />
     *                  3.      Si svuota la lista appena creata e si verifica che ora chiamando il metodo dalla lista originale ritorni {@code false}. <br />
     *                  4.      Creo un vector con gli stessi elementi della lista e controllo che chiamando il metodo sulla lista passando il vettore ritorni {@code false}
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione invariata.
     * 
     * @r.esult    {@code equals(Object)} deve restituire {@code true} se l'elemento passato è una lista e sono uguali.
     */
    @Test
    public void testEquals() {
        assertTrue(list.equals(list)); // Mi assicuro che la lista sia uguale a se stessa

        ListAdapter list2 = new ListAdapter(list); // copio la lista in una lista secondaria
        assertTrue(list.equals(list2)); // Controllo che la lista appena copiata sia uguale a quella iniziale

        list2.clear();
        assertFalse(list.equals(list2));

        Vector v = new Vector();
        v.add(1);
        v.add(2);
        assertFalse(list.equals(v)); // Controllo che con un altro tipo diverso da HList il metodo ritorni false
    } 

    /**
     * Test del metodo {@link myAdapter.ListAdapter#equals(Object) equals(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code equals(Object)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code equals(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code equals(Object)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code equals(Object)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testEqualsNullObject() {
        list.equals(null);
    }
    // FINE TEST METODO equals

    // INIZIO TEST METODO get
    /**
     * Test del metodo {@link myAdapter.ListAdapter#get(int) get(int)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code get(int)} ritorni l'elemento nella lista all'indice passato per parametro. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code get(int)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             l'elemento nella lista all'indice passato per parametro e che non possa trovare elementi in una lista vuota<br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code clear()}, {@code add(Object)} <br />
     *                  1.      Si verifica che il metodo ritorni il valore del primo valore inserito nella funzione di setUp quando si invoca {@code get(0)}.  <br />
     *                  2.      Si verifica che il metodo non ritorni il valore del primo valore inserito nella funzione di setUp quando si invoca {@code get(1)}, che ritorna invece il secondo. <br />
     *                  3.      Si inserisce un nuovo valore e si verifica che chiamando il metodo nella nuova posizione aggiunta ritorni il valore appena aggiunto. <br />
     *                  4.      Svuoto la lista e mi assicuro che lanci eccezione
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione 0.
     * 
     * @r.esult    {@code get(int)} deve restituire l'elemento che si trova all'interno della lista nell'indice passato come parametro.
     */
    @Test
    public void testGet() {
        assertEquals(1, list.get(0)); // Mi assicuro che il primo elemento sia quello inserito nella funzione di setup
        assertNotEquals(1, list.get(1)); // Mi assicuro che il secondo elemento sia diverso da 1, diverso quindi da quello inserito nella funzione di setup

        list.add(3);
        assertEquals(3, list.get(2)); // Mi assicuro che l'elemento inserito corrisponda

        list.clear();
        
        Exception ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(0);
        });
    }

    /**
    * Test del metodo {@link myAdapter.ListAdapter#get(int) get(int)} della ListAdapter.
    * 
    * @s.ummary   Verifica che il metodo {@code get(int)} lanci l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido <br />
    * 
    * @d.esign    Si vuole analizzare il comportamento di {@code get(int)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
    *             {@code NullPointerException} quando viene passato come parametro un indice non valido <br />
    * 
    * @d.escription    1.      Si verifica che chiamando {@code get(int)} con un parametro indice non valido generi l'eccezione {@code IndexOutOfBoundsException}.  <br />
    * 
    * @p.recond   La ListAdapter e' stata popolata
    * 
    * @p.ostcond  La ListAdapter rimane invariata.
    * 
    * @r.esult    {@code get(int)} deve generare l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido.
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBoundsIndex() {
        list.get(54);
    } 
    // FINE TEST METODO get

    // INIZIO TEST METODO set
    /**
     * Test del metodo {@link myAdapter.ListAdapter#set(int, Object) set(int, Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code set(int, Object)} modifichi l'elemento in indice passato per parametro con l'elemento passato per parametro e ritorni l'elemento appena modificato. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code set(int)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo modifichi <br />
     *             l'elemento in indice passato per parametro con l'elemento passato per parametro e ritorni l'elemento appena modificato e che quindi la lunghezza della lista rimanga invariata<br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code isEmpty()}, {@code get(int)}, {@code size()} <br />
     *                  1.      Mi assicuro che la lista sia vuota e che il primo elemento sia quello inserito nella funzione di setUp.  <br />
     *                  2.      Mi salvo la lunghezza corrente della lista. <br />
     *                  3.      Mi assicuro che chaimando {@code set(int, Object)} con dei valori di prova il metodo ritorni <br />
     *                          il valore che era contenuto nella posizione dell'indice appena passato prima della chiamata al metodo. <br />
     *                  4.      Controllo che la dimensione sia rimasta invariata e che il valore nell'indice passato a {@code set(int, Object)} sia cambiato e <br />
     *                          uguale a quello passato per parametro alla chiamata del metodo. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione invariata.
     * 
     * @r.esult    {@code set(int, Object)} deve modificare l'elemento in indice passato per parametro con l'elemento passato per parametro e ritorni l'elemento appena modificato.
     */
    @Test
    public void testSet() {
        assertFalse(list.isEmpty()); // Mi assicuro che la lista non sia vuota
        
        assertEquals(list.get(0), 1); // Mi assicuro che il primo elemento sia quello inserito nella funzione di setup

        int oldLenght = list.size();
        assertEquals(list.get(0), list.set(0, 3));
        assertEquals(list.size(), oldLenght); // Controllo che set non cambi la lunghezza della lista
        assertNotEquals(list.get(0), 1); // Mi assicuro che set abbia cambiato il valore
        assertEquals(list.get(0), 3); // Mi assicuro che il valore sia uguale a quello impostato da set
    }

    /**
    * Test del metodo {@link myAdapter.ListAdapter#set(int, Object) set(int, Object)} della ListAdapter.
    * 
    * @s.ummary   Verifica che il metodo {@code set(int, Object)} lanci l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido <br />
    * 
    * @d.esign    Si vuole analizzare il comportamento di {@code set(int, Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
    *             {@code NullPointerException} quando viene passato come parametro un indice non valido <br />
    * 
    * @d.escription    1.      Si verifica che chiamando {@code set(int, Object)} con un parametro indice non valido generi l'eccezione {@code IndexOutOfBoundsException}.  <br />
    * 
    * @p.recond   La ListAdapter e' stata popolata
    * 
    * @p.ostcond  La ListAdapter rimane invariata.
    * 
    * @r.esult    {@code set(int, Object)} deve generare l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido.
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBoundsIndex() {
        list.set(54, 1);
    } 

    /**
     * Test del metodo {@link myAdapter.ListAdapter#set(int, Object) set(int, Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code set(int, Object)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code set(int, Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code set(int, Object)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code set(int, Object)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetsNullObject() {
        list.set(1, null);
    }
    // FINE TEST METODO set

    // INIZIO TEST METODO indexOf
    /**
     * Test del metodo {@link myAdapter.ListAdapter#indexOf(Object) indexOf(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code indexOf(Object)} ritorni l'indice dell'elemento nella lista se presente oppure -1. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code indexOf(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             l'indice dell'elemento se trovato all'interno della lista, altrimenti se non viene trovato ritorni -1<br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code set()}, {@code add(Object)} <br />
     *                  1.      Mi assicuro che chiamando il metodo sui due elementi inseriti nella funzione di setUp vengano ritoranti gli indici corretti.  <br />
     *                  2.      Mi assicuro che cercando un altro numero diversi da quelli inseriti nella funzione di setup non ritorni gli indici corretti. <br />
     *                  3.      Modifico il valore con {@code set(int, Object)} dell'elemento in posizione 0 e controllo se chiamando {@code indexOf(Object)} con l'elemento appena passato per paramtro <br />
     *                          ritorni l'indice corretto, lo 0 <br />
     *                  4.      Controllo che chiamando il metodo su un valore che non è presente nella lista il metodo ritorni -1 <br />
     *                  5.      Aggiungo un elemento alla lista e chiamo il metodo sul nuovo elemento e controllo che l'indice ritornato sia la nuova posizione della lista
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione maggiore di 1 rispetto a quella iniziale con un valore cambiato.
     * 
     * @r.esult    {@code indexOf(Object)} ritorna l'indice dell'elemento passato come parametro se all'interno oppure -1.
     */
    @Test
    public void testIndexOf() {
        // Controllo che il valore restituito dal metodo sia uguale a quello inserito nella funzione di setup
        assertEquals(list.indexOf(1), 0);
        assertEquals(list.indexOf(2), 1);

        // Controllo con un valore diverso
        assertNotEquals(list.indexOf(45), 0);
        assertNotEquals(list.indexOf(45), 1);


        list.set(0, 0);
        assertEquals(list.indexOf(0), 0); // controllo se l'elemento appena cambiato sia stato effettivamente cambiato, e che non sia quindi più quello di setup

        assertEquals(-1, list.indexOf(3)); // controllo se l'elemento non è trovato nella lista

        // provo aggiungendo un elemento alla lista
        list.add(3);
        assertEquals(list.indexOf(3), 2);
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#indexOf(Object) indexOf(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code indexOf(Object)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code indexOf(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code indexOf(Object)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code indexOf(Object)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testIndexOfNullObject() {
        list.indexOf(null);
    }
    // FINE TEST METODO indexOf

    // INIZIO TEST METODO LastIndexOf
    /**
     * Test del metodo {@link myAdapter.ListAdapter#lastIndexOf(Object) lastIndexOf(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code lastIndexOf(Object)} ritorni l'ultimo indice dell'elemento nella lista se presente oppure -1. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code lastIndexOf(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             l'ultimo indice dell'elemento se trovato all'interno della lista, altrimenti se non viene trovato ritorni -1<br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code set()}, {@code add(Object)} <br />
     *                  1.      Mi assicuro che chiamando il metodo sui due elementi inseriti nella funzione di setUp vengano ritoranti gli indici corretti.  <br />
     *                  2.      Mi assicuro che cercando un altro numero diversi da quelli inseriti nella funzione di setup non ritorni gli indici corretti. <br />
     *                  3.      Modifico il valore con {@code set(int, Object)} dell'elemento in posizione 0 e controllo se chiamando {@code lastIndexOf(Object)} con l'elemento appena passato per paramtro <br />
     *                          ritorni l'indice corretto, lo 0 <br />
     *                  4.      Controllo che chiamando il metodo su un valore che non è presente nella lista il metodo ritorni -1 <br />
     *                  5.      Aggiungo un elemento alla lista e chiamo il metodo sul nuovo elemento e controllo che l'indice ritornato sia la nuova posizione della lista <br />
     *                  6.      Aggiungo un elemento già presente nella lista e chiamo il metodo sul nuovo elemento e controllo che l'indice ritornato sia la nuova posizione della lista e non il primo indice trovato <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione maggiore di 2 rispetto a quella iniziale con un valore cambiato.
     * 
     * @r.esult    {@code lastIndexOf(Object)} ritorna l'ultimo indice dell'elemento passato come parametro se all'interno oppure -1.
     */
    @Test
    public void testLastIndexOf() {
        // Controllo che il valore restituito dal metodo sia uguale a quello inserito nella funzione di setup
        assertEquals(list.lastIndexOf(1), 0);
        assertEquals(list.lastIndexOf(2), 1);

        // Controllo con un valore diverso
        assertNotEquals(list.lastIndexOf(45), 0);
        assertNotEquals(list.lastIndexOf(45), 1);

        list.set(0, 0);
        assertEquals(list.lastIndexOf(0), 0); // controllo se l'elemento appena cambiato sia stato effettivamente cambiato, e che non sia quindi più quello di setup

        assertEquals(-1, list.lastIndexOf(3));

        // provo aggiungendo un elemento alla lista
        list.add(3);
        assertEquals(list.lastIndexOf(3), 2);

        // provo aggiungendo un elemento uguale a quello già presente
        list.add(2);
        assertNotEquals(list.lastIndexOf(2), 1);
        assertEquals(list.lastIndexOf(2), 3);
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#lastIndexOf(Object) lastIndexOf(Object)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code lastIndexOf(Object)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code lastIndexOf(Object)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code lastIndexOf(Object)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code indexOf(Object)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testLastIndexOfNullObject() {
        list.lastIndexOf(null);
    }
    // FINE TEST METODO indexOf

    // INIZIO TEST METODO subList
    /**
     * Test del metodo {@link myAdapter.ListAdapter#subList(int, int) subList(int, int)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code subList(int, int)} ritorni una sotto lista della lista originale. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code subList(int, int)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             una sotto lista che contiene tutti gli elementi da fromIndex (compreso) a toIndex (escluso) <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code get(int)}, {@code add(Object)} <br />
     *                  1.      Creo una sotto lista chiamando il metodo e controllo che gli elementi di tale sotto lista siano gli stessi contenuti nella lista originale tra gli indici passati e controllo che la dimensione sia quella aspettata.  <br />
     *                  2.      Aggiungo dei valori e ricreo un'altra sotto lista e riverifico la lunghezza e i valori, confrontandoli con quelli della lista originale. <br />
     *                  3.      Creo una lista con gli indici che combaciano e controllo che la dimensione della lista sia 0, come aspettato
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione maggiore di 4 rispetto a quella iniziale.
     * 
     * @r.esult    {@code lastIndexOf(Object)} ritorna l'ultimo indice dell'elemento passato come parametro se all'interno oppure -1.
     */
    @Test
    public void testSubList() {
        // Controllo con un solo elemento 
        HList tmpL = list.subList(0 , 1);
        assertEquals(tmpL.size(), 1);
        assertEquals(tmpL.get(0), 1); // Controllo che il primo elemento sia effettivamente il primo elemento della lista originale inserito nella funzione dei setup

        // aggiungo un po' di valori
        for (int i = 0; i < 4; i++) {
            list.add(2+i+1);
        }
        // list = [1, 2, 3, 4, 5, 6]
        tmpL = list.subList(1, 5);
        assertEquals(tmpL.size(), 4);
        // Constrollo che gli elementi della sublist siano effettivamente quelli che vanno dall'indice 1 al 4(compreso, quindi 5 non compreso)
        for (int i = 0; i < tmpL.size(); i++) {
            assertEquals(tmpL.get(i), list.get(i+1));
        }

        // provo assegnando gli indici uguali
        tmpL = list.subList(0, 0);
        assertEquals(tmpL.size(), 0);
    }

    /**
    * Test del metodo {@link myAdapter.ListAdapter#subList(int, int) subLIst(int, int)} della ListAdapter.
    * 
    * @s.ummary   Verifica che il metodo {@code subList(int, int)} lanci l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice, o gli indici non validi <br />
    * 
    * @d.esign    Si vuole analizzare il comportamento di {@code subList(int, int)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
    *             {@code NullPointerException} quando viene passato come parametro un indice o gli indici non validi<br />
    * 
    * @d.escription    1.      Si verifica che chiamando {@code subList(int, int)} con un parametro indice(fromIndex) non valido generi l'eccezione {@code IndexOutOfBoundsException}.  <br />
    *                  2.      Si verifica che chiamando {@code subList(int, int)} con i parametri indice non validi (fromIndex maggiore di toIndex) generi l'eccezione {@code IndexOutOfBoundsException}.  <br />
    * 
    * @p.recond   La ListAdapter e' stata popolata
    * 
    * @p.ostcond  La ListAdapter rimane invariata.
    * 
    * @r.esult   {@code subList(int, int)} deve generare l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice o indici non valido.
    */
    @Test
    public void testSubListFromIndexOutOfBound() {
        Exception ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.subList(-1, 0);
        });

        ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.subList(13, 1);
        });

        ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.subList(1, 0);
        });
    }

    /**
    * Test del metodo {@link myAdapter.ListAdapter#subList(int, int) subList(int, int)} della ListAdapter.
    * 
    * @s.ummary   Verifica che il metodo {@code subList(int, int)} lanci l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice, o gli indici non validi <br />
    * 
    * @d.esign    Si vuole analizzare il comportamento di {@code subList(int, int)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
    *             {@code NullPointerException} quando viene passato come parametro un indice o gli indici non validi<br />
    * 
    * @d.escription    1.      Si verifica che chiamando {@code subList(int, int)} con un parametro indice(toIndex) non valido generi l'eccezione {@code IndexOutOfBoundsException}.  <br />
    * 
    * @p.recond   La ListAdapter e' stata popolata
    * 
    * @p.ostcond  La ListAdapter rimane invariata.
    * 
    * @r.esult   {@code subList(int, int)} deve generare l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice o indici non valido.
    */
    @Test
    public void testSubListToIndexOutOfBound() {
        Exception ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.subList(0, 5);
        });

        ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.subList(0, -2);
        });
    }
    // FINE TEST METODO subList

    // INIZIO TEST METODO containsAll
    /**
     * Test del metodo {@link myAdapter.ListAdapter#containsAll(HCollection) containsAll(HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code containsAll(HCollection)} ritorni {@code true} se la ListAdapter contiene tutta la HCollection passata per parametro. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code containsAll(HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             {@code true} nel momento in cui tutti gli elementi della HCollection passata per parametro siano contenuti nella ListAdapter <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code remove(int)}, {@code add(Object)} <br />
     *                  1.      Creo una lista uguale a quella originale e controllo che gli elementi di tale sotto lista siano tutti contenuti all'interno della lista originale, ritorni quindi {@code true}.  <br />
     *                  2.      Aggiungo dei valori e ricontrollo che la nuova lista continui a contenere tutti gli elementi della lista originale, ritorna quindi {@code true}. <br />
     *                  3.      Rimuovo uno dei valori nella nuova lista che era contenuto nella lista originale e controllo che il metodo ritorni {@code false}. <br />
     *                  4.      Riaggiungo l'elemento appena rimosso e controllo che ora il metodo ritorni ancora {@code true}. <br />
     *                  5.      Controllo che chiamando il metodo dalla nuova lista e passando se stessa come parametro ritorni {@code true}. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter deve avere dimensione invariata.
     * 
     * @r.esult    {@code containsAll(HCollection)} ritorna {@code true} se la ListAdapter contiene tutta la HCollection passata per parametro, {@code false} altrimenti.
     */
    @Test
    public void testContainsAll() {
        HList tmpL = new ListAdapter(list);
        assertTrue(tmpL.containsAll(list)); // Verifico che una lista con gli stessi elementi della lista di test ritorni true

        tmpL.add(3);
        tmpL.add(4);
        assertTrue(tmpL.containsAll(list)); // Controllo che anche aggiungendo elementi alla lista rimanga true

        tmpL.remove(0);
        assertFalse(tmpL.containsAll(list)); // Controllo che rimuovendo un elemento che è contenuto nella lista di test ritorni false
        tmpL.add(1);
        assertTrue(tmpL.containsAll(list)); // Controllo che riaggiungendolo ritorni true

        assertTrue(tmpL.containsAll(tmpL)); // Controllo che contenga tutta se stessa
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#containsAll(HCollection) containsAll(HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code containsAll(HCollection)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code containsAll(HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code containsAll(HCollection)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code containsAll(HCollection)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllNullObject() {
        list.containsAll(null);
    }
    // FINE TEST METODO containsAll

    // INIZIO TEST METODO addAll
    /**
     * Test del metodo {@link myAdapter.ListAdapter#addAll(HCollection) addAll(HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code addAll(HCollection)} ritorni {@code true} se la ListAdapter viene cambiata come risultato della chiamata. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code addAll(HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             {@code true} nel momento in cui tutti gli elementi della HCollection passata per parametro vengono inseriti nella lista, ed è quindi cambiata, {@code false} alrimenti <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code isEmpty()}, {@code equals(Object)}<br />
     *                  1.      Creo una lista e controllo che la lista sia vuota.  <br />
     *                  2.      Chiamo il metodo sulla nuova lista e controllo che ritorni {@code true} come risultato della chiamata. <br />
     *                  3.      Si verifica che aggiungendo tutti gli elementi della lista originale ad una lista vuota le due siano uguali. <br />
     *                  4.      Si verifica che passando una lista vuota al metoto ritorni {@code false}. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter originale deve avere dimensione invariata e tmpL e list devono essere uguali.
     * 
     * @r.esult    {@code addAll(HCollection)} ritorna {@code true} se la ListAdapter viene cambiata, {@code false} altrimenti.
     */
    @Test
    public void testAddAll() {
        HList tmpL = new ListAdapter();
        assertTrue(tmpL.isEmpty());

        assertTrue(tmpL.addAll(list)); 
        assertTrue(tmpL.equals(list)); // Controllo che aggiungendo tutti gli elementi in un'altra lista le due coincidano

        assertFalse(tmpL.addAll(new ListAdapter())); // Controllo che passando una lista vuota al metodo ritorni false
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#addAll(int, HCollection) addAll(int, HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code addAll(int, HCollection)} ritorni {@code true} se la ListAdapter viene cambiata come risultato della chiamata. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code addAll(int, HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             {@code true} nel momento in cui tutti gli elementi della HCollection passata per parametro vengono inseriti nella lista a partire dall'indice passato per parametro, <br />
     *             ed è quindi cambiata, {@code false} alrimenti <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code get(int)}, {@code add(Object)}, {@code containsAll(HCollection)}<br />
     *                  1.      Creo una lista nuova e si aggiungono due valori di prova diversi da quelli inseriti nella funzione di setUp.  <br />
     *                  2.      Si verifica che gli elementi nella nuova lista non siano contenuti nella lista originale. <br />
     *                  3.      Salvo il valore contenuto nell'indice 1 della lista originale e aggiungo la nuova lista nella lista originale, a partire dall'indice 1, assicurandomi che il metodo ritorni {@code true}. <br />
     *                  4.      Si verifica che passando una lista vuota al metoto ritorni {@code false}. <br />
     *                  5.      Si verifica che in posizione 1 nella lista sia contenuto il primo elemento della lista inserita. <br />
     *                  6.      Si verifica che in posizione 1 nella lista non sia più contenuto l'elemento che prima del test era prima in posizione 1. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter originale deve avere dimensione pari a list.size + tmpL.size.
     * 
     * @r.esult    {@code addAll(int, HCollection)} ritorna {@code true} se la ListAdapter viene cambiata, {@code false} altrimenti.
     */
    @Test
    public void testAddAllIndex() {
        HList tmpL = new ListAdapter();
        tmpL.add(45);    
        tmpL.add(15);
        
        assertFalse(tmpL.containsAll(list)); // Controllo che inizialmente non tutti gli elementi contenuti in tmpL non siano anche in list
        
        Object e = list.get(1);

        assertTrue(list.addAll(1, tmpL));
        assertFalse(list.addAll(0, new ListAdapter()));

        assertEquals(list.get(1), 45);
        assertNotEquals(list.get(1), e);
    }

    /**
    * Test del metodo {@link myAdapter.ListAdapter#addAll(int, HCollection) addAll(int, HCollection)} della ListAdapter.
    * 
    * @s.ummary   Verifica che il metodo {@code addAll(int, HCollection)} lanci l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido <br />
    * 
    * @d.esign    Si vuole analizzare il comportamento di {@code addAll(int, HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
    *             {@code NullPointerException} quando viene passato come parametro un indice o gli indici non validi<br />
    * 
    * @d.escription    1.      Si verifica che chiamando {@code addAll(int, HCollection)} con un parametro indice non valido generi l'eccezione {@code IndexOutOfBoundsException}.  <br />
    * 
    * @p.recond   La ListAdapter e' stata popolata
    * 
    * @p.ostcond  La ListAdapter rimane invariata.
    * 
    * @r.esult   {@code addAll(int, HCollection)} deve generare l'eccezione {@code IndexOutOfBoundsException} quando si passa un indice non valido.
    */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddAllIndexOutOfBound() {
        HList tmpL = new ListAdapter();
        tmpL.add(45);    
        tmpL.add(15);

        list.addAll(-3, tmpL);
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#addAll(HCollection) addAll(HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code addAll(HCollection)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code addAll(HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code addAll(HCollection)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code addAll(HCollection)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddAllNullObject() {
        list.addAll(null);
    }
    // FINE TEST METODO addAll

    // INIZIO TEST METODO removeAll
    /**
     * Test del metodo {@link myAdapter.ListAdapter#removeAll(HCollection) removeAll(HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code removeAll(HCollection)} ritorni {@code true} se la ListAdapter viene cambiata come risultato della chiamata. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code removeAll(HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             {@code true} nel momento in cui tutti gli elementi della HCollection passata per parametro vengono rimossi nella lista, ed è quindi cambiata, {@code false} alrimenti <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code containsAll(HCollection)}, {@code add(Object)}<br />
     *                  1.      Creo una lista uguale a quella originale e inoltre aggiungo altri valori all'interno e salvo la sua lunghezza.  <br />
     *                  2.      Si verifica che la nuova lista contenga tutta la lista iniziale. <br />
     *                  3.      Si verifica che chiamando il metodo {@code removeAll(HCollection)} il metodo ritorni {@code true}. <br />
     *                  4.      Si verifica dopo la rimozione che la nuova lista non contenga più gli elementi della lista originale. <br />
     *                  5.      Si verifica dopo la rimozione le lunghezza non coincidano e che la dimensione della nuova lista sia pari a quella prima della rimozione meno quella della lista originale. <br />
     *                  6.      Si verifica che chiamando il metodo con una lista vuota il metodo ritorni {@code false}. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter originale deve avere dimensione invariata.
     * 
     * @r.esult    {@code removeAll(HCollection)} ritorna {@code true} se la ListAdapter viene cambiata, {@code false} altrimenti.
     */
    @Test
    public void testRemoveAll() {
        HList tmpL = new ListAdapter(list);
        tmpL.add(4);
        tmpL.add(6);
        tmpL.add(5);
        int oldLenght = tmpL.size();

        assertTrue(tmpL.containsAll(list)); // Controllo che inizialmente la lista contenga la lista di test
        assertTrue(tmpL.removeAll(list));
        assertFalse(tmpL.containsAll(list)); // Controllo che ora la lista non contenga più la lista di test
        assertEquals(tmpL.size(), oldLenght - list.size()); // Verifico che la lunghezza della lista sia quella aspettata

        assertFalse(tmpL.removeAll(new ListAdapter())); // Controllo che passando una lista vuota al metodo ritorni false
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#removeAll(HCollection) removeAll(HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code removeAll(HCollection)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code removeAll(HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code removeAll(HCollection)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code removeAll(HCollection)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllNullObject() {
        list.removeAll(null);
    }
    // FINE TEST METODO removeAll

    // INIZIO TEST METODO retainAll
    /**
     * Test del metodo {@link myAdapter.ListAdapter#retainAll(HCollection) retainAll(HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code retainAll(HCollection)} ritorni {@code true} se la ListAdapter viene cambiata come risultato della chiamata. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code retainAll(HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che il metodo ritorni <br />
     *             {@code true} nel momento in cui tutti gli elementi non della HCollection passata per parametro vengono rimossi nella lista, ed è quindi cambiata, {@code false} alrimenti <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code size()}, {@code containsAll(HCollection)}, {@code add(Object)}<br />
     *                  1.      Creo una lista uguale a quella originale e inoltre aggiungo altri valori all'interno e salvo la sua lunghezza.  <br />
     *                  2.      Si verifica che la nuova lista contenga tutta la lista iniziale. <br />
     *                  3.      Si verifica che chiamando il metodo {@code retainAll(HCollection)} il metodo ritorni {@code true}. <br />
     *                  4.      Si verifica dopo la chiamata al metodo la nuova lista contenga ancora gli elementi della lista originale. <br />
     *                  5.      Si verifica dopo la chiamata al metodo le lunghezza coincidano e che la dimensione della nuova lista sia pari a quella della lista originale. <br />
     *                  6.      Si verifica che gli elementi inseriti nella nuova lista non siano più in tale lista. <br />
     *                  7.      Si verifica che chiamando il metodo con una lista vuota come parametro ritorni {@code true} e che sia quindi vuota. <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter originale deve avere dimensione invariata.
     * 
     * @r.esult    {@code retainAll(HCollection)} ritorna {@code true} se la ListAdapter viene cambiata, {@code false} altrimenti.
     */
    @Test
    public void testRetainAll() {
        HList tmpL = new ListAdapter(list);
        tmpL.add(4);
        tmpL.add(6);
        tmpL.add(5);
        int oldLenght = tmpL.size();

        assertTrue(tmpL.containsAll(list)); // Controllo che inizialmente la lista contenga la lista di test
        assertTrue(tmpL.retainAll(list));
        assertTrue(tmpL.containsAll(list)); // Controllo che anche dopo la chiamata del metodo gli elementi di list siano dentro tmpList
        assertEquals(tmpL.size(), list.size()); // Verifico che la lunghezza della lista si aquella aspettata, cioè quella di list

        assertFalse(tmpL.contains(4));
        assertFalse(tmpL.contains(5));
        assertFalse(tmpL.contains(6));

        assertTrue(tmpL.retainAll(new ListAdapter())); // Controllo che passando una lista vuota al metodo ritorni true
        assertTrue(tmpL.isEmpty());
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter#retainAll(HCollection) retainAll(HCollection)} della ListAdapter.
     * 
     * @s.ummary   Verifica che il metodo {@code retainAll(HCollection)} lanci l'eccezione {@code NullPointerException} quando si passa un oggetto null<br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code retainAll(HCollection)} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che lanci l'eccezione <br />
     *             {@code NullPointerException} quando viene passato come parametro un oggetto null <br />
     * 
     * @d.escription    1.      Si verifica che chiamando {@code retainAll(HCollection)} con un parametro null generi l'eccezione {@code NullPointerException}.  <br />
     * 
     * @p.recond   La ListAdapter e' stata popolata
     * 
     * @p.ostcond  La ListAdapter rimane invariata.
     * 
     * @r.esult    {@code retainAll(HCollection)} deve generare l'eccezione {@code NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testRetainAllNullObject() {
        list.retainAll(null);
    }
    // FINE TEST METODO retainAll

    // INIZIO TEST METODO hashCode
    /**
     * Test del metodo {@link myAdapter.ListAdapter#hashCode() hashCode}.
     * 
     * @s.ummary   Verifica che il metodo {@code hashCode()} generi correttamente l'hashCode della lista che chiama il metodo.
     * 
     * @d.esign    Si vuole verificare che il metodo {@code hashCode()} preveda il corretto aggiornamento dell'hashCode della lista al variare <br />
     *               degli elementi contenuti nella lista, senza generare errori. <br /> 
     *              Se due liste hanno lo stesso hashCode allora si dimostra che se {@code l1.equals(l2)}, allora {@code l1.hashCode()} == {@code l2.hashCode()}.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code add(Object)}, {@code clear(Object)} 
     *                  1.  Si crea una lista ausiliaria inizialmente uguale alla lista chiamante. <br />
     *                  2.  Si verifica che le due mappe sono uguali e che di conseguenza anche i loro hashCode sono uguali. <br />
     *                  3.  Si verifica che aggiungendo un nuovo valore i due hashCode non coincidano più. <br />
     *                  4.  Si svuota la lista di partenza e si verifica che l'hashCode di una lista vuota e' 1. <br />
     *                  5.  Si confronta l'hashCode della lista vuota con la lista di partenza e si verifica che non siano uguali, modifiche alla lista comporta modifiche all'hashcode.
     *      
     * @p.recond   La lista e' stata popolata.
     * 
     * @p.ostcond  La lista e' stata svuotata.
     * 
     * @r.esult    Il metodo {@code hashCode()} restituisce i risultati attesi nei vari punti.
     * 
     */
    @Test
    public void testHashCode() {
        HList l = new ListAdapter(list);
        assertTrue(list.equals(l));
        assertEquals(l.hashCode(), list.hashCode());

        l.add(3);
        assertNotEquals(l.hashCode(), list.hashCode());

        l.clear();
        assertEquals(l.hashCode(), 1);
        assertNotEquals(l.hashCode(), list.hashCode());
    }
    // FINE TEST METODO hashCode

    // INIZIO TEST METODO iterator
    /** 
     * Per i test riguardanti gli iteratori, consultare {@link myTest.TestIterator}
     * */
    @Test
    public void testIterator() {}
    // FINE TEST METODO iterator

    // INIZIO TEST METODO listIterator
    /** 
     * Per i test riguardanti gli iteratori, consultare {@link myTest.TestListIterator}
     * */
    @Test
    public void testListIterator() {}
    // FINE TEST METODO listIterator
}   