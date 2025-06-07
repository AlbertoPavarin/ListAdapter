package myTest;

import java.util.Vector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;

public class TestListAdapter {

    private ListAdapter list = new ListAdapter();

    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
    }

    // TEST METODO clear
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
    @Test
    public void testIsEmpty() {
        list.clear();
        assertTrue(list.size() == 0); // controllo se la lista è stata svuotata
        assertEquals("Se la lista è vuota ritorna True", true, list.isEmpty());

        list.add(1);
        assertTrue(list.size() == 1); // controllo se è stato aggiunto l'elemento alla lista
        assertEquals("Se la lista non è vuota ritorna False", false, list.isEmpty());
    }
    // FINE TEST METODO isEmpty

    // TEST METODO contains
    @Test(expected = NullPointerException.class)
    public void testContainNullObject() {
        list.contains(null);
    }

    @Test
    public void testContains() {
        assertTrue(list.contains(1)); // Controllo se l'elemento 1 è contenuto nella lista

        list.clear();
        assertTrue(list.size() == 0); // Controllo che la lista sia stata effettivamente pulita

        assertFalse(list.contains(1)); // Controllo che 1 non sia nella lista
    }
    // FINE TEST METODO contains

    // TEST METODO toArray
    @Test
    public void testToArray() {
        assertTrue(list.toArray() instanceof Object[]); // Controllo se quello restituito è un'array

        assertTrue(list.toArray().length == list.size()); // Controllo se le grandezze coincidono

        int oldLenght = list.toArray().length;
        list.clear();
        assertTrue(list.size() == 0); // Controllo se la lista è stata effettivamente svuotata
        
        assertFalse(oldLenght == list.toArray().length);
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayNullObject() {
        list.toArray(null);
    }
    // FINE TEST METODO toArray

    // TEST METODO add
    @Test(expected = NullPointerException.class)
    public void testAddNullObject() {
        list.add(null);
    }

    @Test
    public void testAdd() {
        // Controllo che aggiungendo un elemento alla lista ritorni true
        int oldLenght = list.size();
        assertTrue(list.add(1));
        assertTrue(list.add(2));

        assertNotEquals(list.size(), oldLenght); // Controllo che add abbia cambiato la lunghezza dato che ha aggiunto in coda un elemento

        assertTrue(list.size() == 4); // Controllo se sono effettivamente stati aggiunti

        list.add(0, 16);

        assertTrue(list.size() == 5); // Controllo se sono effettivamente stati aggiunti
        assertEquals(list.get(0), 16); // Controllo che nell'indice 0 cambiato c'è il nuovo elemento
        assertEquals(list.get(1), 1); // Controllo che il valore che era in 0 ora è in 1, se è stata traslata la lista
    }

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

    @Test(expected = NullPointerException.class)
    public void testAddIndexNullObject() {
        list.add(0, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexIndexOutOfBounds() {
        list.add(-1, 1);
    }
    // FINE TEST METODO add

    //TEST METODO remove
    @Test(expected = NullPointerException.class)
    public void testRemoveNullObject() {
        list.remove(null);
    }
    
    @Test
    public void testRemoveObject() {
        assertFalse(list.remove("Pippo")); // Controllo se l'elemento che sto provando ad eliminare non è presente nella lista

        list.add("Pippo");
        assertTrue(list.size() == 3); // Controllo se la grandezza è cambiata

        assertTrue(list.remove("Pippo")); // Controllo se l'elemento che sto provando ad eliminare ora è presente nella lista

        assertTrue(list.size() == 2); // Controllo se la grandezza è cambiata dopo l'eliminazione dell'elemento

    }

    @Test
    public void testRemoveIndex() {
        list.add("Pippo");
        assertTrue(list.size() == 3); // Controllo se la grandezza è cambiata

        assertTrue(list.get(2) == list.remove(2));
        assertTrue(list.size() == 2); // Controllo se la grandezza è cambiata
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBoundsIndex() {
        list.remove(-3);
    }    
    // FINE TEST METODO remove

    // TEST METODO equals
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

    @Test(expected = NullPointerException.class)
    public void testEqualsNullObject() {
        list.equals(null);
    }
    // FINE TEST METODO equals

    // INIZIO TEST METODO get
    @Test
    public void testGet() {
        assertEquals(1, list.get(0)); // Mi assicuro che il primo elemento sia quello inserito nella funzione di setup
        assertNotEquals(1, list.get(1)); // Mi assicuro che il secondo elemento sia diverso da 1, diverso quindi da quello inserito nella funzione di setup

        list.add(3);
        assertEquals(3, list.get(2)); // Mi assicuro che l'elemento inserito corrisponda
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBoundsIndex() {
        list.get(54);
    } 
    // FINE TEST METODO get

    // INIZIO TEST METODO set
    @Test
    public void testSet() {
        assertFalse(list.isEmpty()); // Mi assicuro che la lista non sia vuota
        
        assertEquals(list.get(0), 1); // Mi assicuro che il primo elemento sia quello inserito nella funzione di setup

        int oldLenght = list.size();
        list.set(0, 3);
        assertEquals(list.size(), oldLenght); // Controllo che set non cambi la lunghezza della lista
        assertNotEquals(list.get(0), 1); // Mi assicuro che set abbia cambiato il valore
        assertEquals(list.get(0), 3); // Mi assicuro che il valore sia uguale a quello impostato da set
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBoundsIndex() {
        list.set(54, 1);
    } 

    @Test(expected = NullPointerException.class)
    public void testSetsNullObject() {
        list.set(1, null);
    }
    // FINE TEST METDO set
}   