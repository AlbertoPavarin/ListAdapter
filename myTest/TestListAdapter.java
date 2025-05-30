package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;
import myAdapter.HList;

public class TestListAdapter {

    private HList list = new ListAdapter();

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
        assertTrue(list.add(1));
        assertTrue(list.add(2));

        assertTrue(list.size() == 4); // Controllo se sono effettivamente stati aggiunti
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

}