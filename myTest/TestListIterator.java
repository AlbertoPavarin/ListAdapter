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

    // INIZIO TEST set
    @Test
    public void testSet() {
        Object previousObject = list.get(0); // salvo il vecchio valore della lista in posizione 0
        it.next(); // mi sposto alla posizione 0 della lista
        it.set(0); // modifica il valore in posizione 0

        assertFalse(list.get(0) == previousObject); // controllo che i 2 valori non coincidano più
        assertTrue((Integer)list.get(0) == 0); // controllo che il valore nuovo della posizione 1 della lista sia quello impostato con set
    }

    @Test(expected = IllegalStateException.class)
    public void testSetIllegalAccess() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.set(0);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNull() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.next();
        it.set(null);
    }
    // FINI TEST set

    // INIZIO TEST add
    @Test
    public void testAdd() {
        Object previousObject = list.get(1); // salvo il vecchio valore della lista in posizione 1
        int oldLength = list.size();
        it.next(); // mi sposto alla posizione 0 della lista
        it.add(0); // modifica il valore in posizione 1 
        // list = [1, 0, 2]
        assertNotEquals(list.size(), oldLength); // controllo che la lunghezza sia cambaita

        assertFalse(list.get(1) == previousObject); // controllo che il valore sia stato aggiunto nella corrente posizione, in questo caso la posizione 1
        assertEquals(it.next(), previousObject); // controllo che il vecchio valore sia stato shiftato di 1 verso destra, ora in posizione 2

    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.add(null);
    }
    // FINI TEST set

    // INIZIO TEST hasPrevious
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
        int i = 0;
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
        it.previous(); // Controllo che essendo all'inizio non ci siano elementi precedenti
    }
    // FINE TEST previous

    // INIZIO TEST nextIndex
    @Test
    public void testNextIndex() {
        assertFalse(it.hasPrevious()); // Mi assicuro di essere all'inizio della lista
        assertEquals("Ritorna true se nextIndex ritorna il primo indice", 0, it.nextIndex());

        while(it.hasNext())
            it.next();

        assertEquals("Ritorna true se nextIndex ritorna l'ultimo indice", list.size(), it.nextIndex());
    }
    // FINE TEST nextIndex

    // INIZIO TEST previousIndex
    @Test
    public void testPreviousIndex() {
        int i = 0;
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