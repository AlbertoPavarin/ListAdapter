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
}