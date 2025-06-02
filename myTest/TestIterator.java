package myTest;

import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import myAdapter.IteratorAdapter;
import myAdapter.ListAdapter;

public class TestIterator {

    private ListAdapter list = new ListAdapter();
    private IteratorAdapter it = new IteratorAdapter(list);


    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
    }
    
    // INIZIO TEST next
    @Test
    public void testNext() {
        list.add(3);
        assertEquals("Ritorna True se il primo elemento è 1", 1, it.next());
        assertEquals("Ritorna True se il secondo elemento è 2", 2, it.next());
        assertEquals("Ritorna True se il secondo elemento è 3", 3, it.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void testNextNoElement() {
        it.next(); // facendolo 3 volte supero i 2 elementi della lista
        it.next();
        it.next();
    }
    // FINE TEST next

    // INIZIO TEST hasNext
    @Test
    public void testHasNext() {
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext()); // arrivando al secondo e ultimo elemento, hasNext ritorna false perchè non ci sono altri elementi

        list.add(4);
        assertTrue(it.hasNext()); // aggiungendo un altro elemento alla lista l'iteratore può puntare ora ad un altro elemento della lista

        it.remove();
        assertFalse(it.hasNext()); // eliminando l'ultimo elemento ritorno alla situazione pre aggiunta
    }

    // FINE TEST hasNext

    // INIZIO TEST remove
    @Test
    public void testRemove() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.remove();

        assertTrue(it.hasNext()); // mi assicuro che all'interno della lista ci sia ancora un elemento
        it.next();
        assertFalse(it.hasNext());

        it.remove();
    }

    // FINE TEST remove
}
