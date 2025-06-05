package myTest;

import com.sun.source.tree.AssertTree;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import myAdapter.ListIteratorAdapter;
import myAdapter.ListAdapter;

public class TestListIterator {
    private ListAdapter list = new ListAdapter();
    private ListIteratorAdapter it = new ListIteratorAdapter(list);

    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
    }

    // INIZIO TEST add
    @Test
    public void testAdd() {
        // svuoto la lista dai due elementi iniziali
        list.clear();

        // Mi assicuro che la lista sia vuota
        assertTrue(list.size() == 0 && list.isEmpty());

        it.add(1);
        assertTrue(it.hasNext()); // Mi assicuro che l'elemento sia stato inserito
        // assertTrue(it.hasPrevious()); // Mi assicuro che l'elemento appena inserito in una lista nuova sia prima del cursore
        // assertEquals("Ritorna true se l'elemento previous è proprio quello inserito nella lista", 1, it.previous());
    }
    // FINE TEST add

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
        it.next();
        it.remove();

        assertFalse(it.hasNext());
        assertFalse(it.hasPrevious()); 
    }
    
    @Test(expected = IllegalAccessError.class)
    public void testRemoveIllegalAccess() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.remove();
    }
    // FINE TEST remove

    // INIZIO TEST set
    @Test
    public void testSet() {
        Object previousObject = list.get(1); // salvo il vecchio valore della lista in posizione 1
        it.next(); // mi sposto alla posizione 1 della lista
        it.set(0); // modifica il valore in posizione 1

        assertFalse(list.get(1) == previousObject); // controllo che i 2 valori non coincidano più
        assertTrue((Integer)list.get(1) == 0); // controllo che il valore nuovo della posizione 1 della lista sia quello impostato con set
    }

    @Test(expected = IllegalAccessError.class)
    public void testSetIllegalAccess() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.set(0);
    }

    // FINE TEST hasPrevious
    @Test
    public void testHasPrevious() {
        assertFalse(it.hasPrevious()); // Mi assicuro di essere all'inizio
        it.next();

        assertTrue(it.hasPrevious()); // Controllo che scorrendo la lista abbia qualcosa prima della posizione corrente

        it.previous();

        assertFalse(it.hasPrevious()); // Controllo che ritornando all'inizio della lista non abbia niente come elemento precedente

        it.add(3);
        assertTrue(it.hasPrevious());
        it.previous();
        assertFalse(it.hasPrevious());
    }

    // FINE TEST hasPrevious

    // INIZIO TEST previous
    @Test
    public void testPrevious() {
        int i = -1;
        list.add(3);
        while (it.hasNext())
        {
            it.next();
            i++;
        }

        while (it.hasPrevious())
            assertEquals("Ritorna true se l'elemento ritornato da previous è uguale a quello dell'indice della lista", it.previous(), list.get(--i)); // --i prechè devo controllare quello prima, non il corrente
    }

    @Test(expected = NoSuchElementException.class)
    public void testPreviousNoElement() {
        it.previous();
    }
    // FINE TEST previous

    // INIZIO TEST nextIndex
    @Test
    public void testNextIndex() {
        assertFalse(it.hasPrevious()); // Mi assicuro di essere all'inizio della lista
        assertEquals("Ritorna true se nextIndex ritorna il primo indice", 0, it.nextIndex());

        while(it.hasNext())
            it.next();

        assertEquals("Ritorna true se nextIndex ritorna l'ultimo indice", list.size()-1, it.nextIndex());
    }
    // FINE TEST nextIndex

    // INIZIO TEST previousIndex
    @Test
    public void testPreviousIndex() {
        int i = -1;
        assertFalse(it.hasPrevious()); // Mi assicuro di essere all'inizio della lista
        assertEquals("Ritorna true se nextIndex ritorna l'indice negativo", -1, it.previousIndex());
        while(it.hasNext())
        {
            it.next();
            i++;
        }

        assertEquals("Ritorna true se nextIndex ritorna l'ultimo indice", --i, it.previousIndex()); // --i prechè devo controllare quello prima, non il corrente
    }
    // FINE TEST previousIndex
}