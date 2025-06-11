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

    // INIZIO TEST METODO indexOf
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

        assertEquals(-1, list.indexOf(3));

        // provo aggiungendo un elemento alla lista
        list.add(3);
        assertEquals(list.indexOf(3), 2);
    }

    @Test(expected = NullPointerException.class)
    public void testIndexOfNullObject() {
        list.indexOf(null);
    }
    // FINE TEST METODO indexOf

    // INIZIO TEST METODO LastIndexOf
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

    @Test(expected = NullPointerException.class)
    public void testLastIndexOfNullObject() {
        list.lastIndexOf(null);
    }
    // FINE TEST METODO indexOf

    // INIZIO TEST METODO subList
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

    @Test(expected = IllegalArgumentException.class)
    public void testSubListIllegalArgument() {
        list.subList(1, 0);
    }

    @Test
    public void testSubListFromIndexOutOfBound() {
        Exception ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.subList(-1, 0);
        });

        ex = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.subList(13, 1);
        });
        
    }

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

    @Test(expected = NullPointerException.class)
    public void testContainsAllNullObject() {
        list.containsAll(null);
    }
    // FINE TEST METODO containsAll

    // INIZIO TEST METODO addAll
    @Test
    public void testAddAll() {
        HList tmpL = new ListAdapter();
        assertTrue(tmpL.isEmpty());

        assertTrue(tmpL.addAll(list)); 
        assertTrue(tmpL.equals(list)); // Controllo che aggiungendo tutti gli elementi in un'altra lista le due coincidano

        assertFalse(tmpL.addAll(new ListAdapter())); // Controllo che passando una lista vuota al metodo ritorni false
    }

    // @Test
    // public void testAddAllIndex() {
    //     HList tmpL = new ListAdapter();
    //     tmpL.add(45);    
    //     tmpL.add(15);
        
    //     assertFalse(tmpL.contains(list)); // Controllo che inizialmente non tutti gli elementi contenuti in tmpL non siano anche in list
        
    //     tmpL.addAll(1, list);
    // }

    @Test(expected = NullPointerException.class)
    public void testAddAllNullObject() {
        list.addAll(null);
    }
    // FINE TEST METODO containsAll

    // INIZIO TEST METODO removeAll
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
        assertEquals(tmpL.size(), oldLenght - list.size()); // Verifico che la lunghezza della lista si aquella aspettata

        assertFalse(tmpL.removeAll(new ListAdapter())); // Controllo che passando una lista vuota al metodo ritorni false
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllNullObject() {
        list.removeAll(null);
    }
    // FINE TEST METODO removeAll

    // INIZIO TEST METODO retainAll
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

        assertFalse(tmpL.retainAll(new ListAdapter())); // Controllo che passando una lista vuota al metodo ritorni false
    }

    @Test(expected = NullPointerException.class)
    public void testRetainAllNullObject() {
        list.retainAll(null);
    }
    // FINE TEST METODO retainAll

    // INIZIO TEST METODO hashCode
    @Test
    public void testHashCode() {
        HList l = new ListAdapter(list);
        assertEquals(l.hashCode(), list.hashCode());

        l.add(3);
        assertNotEquals(l.hashCode(), list.hashCode());

        l.clear();
        assertEquals(l.hashCode(), 1);
        assertNotEquals(l.hashCode(), list.hashCode());
    }
    // FINE TEST METODO hashCode

    // INIZIO TEST METODO iterator
    @Test
    public void testIterator() {
        HIterator it = list.iterator();
        int index = 0;

        // Controllo che gli elementi dell'iteratore siano gli stessi della lista e che siano nelle stesse posizioni
        while (it.hasNext()) {
            Object elem =  it.next();
            
            assertEquals(elem, list.get(index++));
        }

        // Eliminino tutti gli elementi della lista tramite iteratore
        it = list.iterator(); 
        while (it.hasNext()) {
            it.next();
            it.remove();
        }

        assertEquals(list.size(), 0); // Controllo che gli elementi siano stati eliminati
    }
    // FINE TEST METODO iterator
}   