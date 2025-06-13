package myTest;

import java.util.NoSuchElementException;
import myAdapter.HIterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;
import myAdapter.HIllegalStateException;

/**
 * Test suite per la classe {@link myAdapter.ListAdapter.IteratorAdapter}, che implementa {@link myAdapter.HIterator}
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi di un iteratore di una {@link myAdapter.HCollection} implementata da {@link myAdapter.ListAdapter}.
 * <p>
 * Design:  Si utilizzano JUnit 4.13, hamcrest-core-1.3 <br />
 *          La test suite include test cases per verificare il corretto e completo funzionamento dei metodi {@code hasNext()}, {@code next()}, e {@code remove()} <br />
 *          del CollectionIterator su ListAdapter vuoti e non vuoti. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.ListAdapter}, di cui viene fornita un'istanza. <br />
 *          Prima di eseguire qualunque test la ListAdapter viene popolata con due valori di prova e poi viene creato l'iteratore, quindi prima di ogni test la ListAdapter sarà sempre popolata con quei due valori
 *          {@link myAdapter.ListAdapter#add(Object) add} usato per aggiungere gli elementi nella funzione setUp.
 */
public class TestIterator {

    /**
     * La ListAdapter utilizzata per i test. Contiene gli elementi e da cui viene creato l'iteratore
     */
    private ListAdapter list = new ListAdapter();

    /**
     * L'iteratore alla ListAdapter
     */
    private HIterator it;

    /**
     * Configura la ListAdapter prima di iniziare con tutti i test
     */
    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
        it = list.iterator();
    }
    
    // INIZIO TEST next
    /**
     * Test del metodo {@link myAdapter.ListAdapter.IteratorAdapter#next() next} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code next()} ritorna correttamente gli elementi della ListAdapter in un ordine non particolare
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code next()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che restituisce tutti gli <br />
     *              elementi del ListAdapter come sono stati salvati. <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()}, {@link myAdapter.ListAdapter#size() size}, {@link myAdapter.ListAdapter#contains(Object) contains} <br />
     *                  1.  Si verifica che ogni elemento letto dall'iteratore appartenga al ListAdapter.  <br />
     *                  2.  Si verifica che il numero di elementi letti sia lo stesso del numero di elementi del ListAdapter. <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code next()} deve restuire gli elementi della collezione in un ordine non definito.
     */
    @Test
    public void testNext() {
        int i = 0;
        while (it.hasNext()) {
            Object elem =  it.next();
            assertTrue(list.contains(elem));
            i++;
        }

        assertEquals(i, list.size());
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter.IteratorAdapter#next() next} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code next()} lanci {@link NoSuchElementException} se iterato oltre la fine dell'iteratore di ListAdapter.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code next()} su un ListAdapter una volta che si itera oltre la fine dell'iteratore. <br />
     *             Verifico quindi che lanci {@code NoSuchElementException} se iterato oltre la fine dell'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()} <br />
     *                  1.  Si chiama next() per il totale di volte pari alla grandezza della lista, cioè 2  <br />
     *                  2.  Si richiama next() un'altra volta per verificare che sia lanciata NoSuchElementException. <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code next()} deve lanciare {@code NoSuchElementException} quando si cerca di iterarlo oltre la fine della collezione.
     */
    @Test(expected = NoSuchElementException.class)
    public void testNextNoElement() {
        it.next(); // facendolo 3 volte supero i 2 elementi della ListAdapter
        it.next();
        it.next();
    }
    // FINE TEST next

    // INIZIO TEST hasNext
    /**
     * Test del metodo {@link myAdapter.ListAdapter.IteratorAdapter#hasNext() hasNext} sulla vista di una ListAdapter.
     * 
     * @s.ummary Verifica che il metodo {@code hasNext()} ritorna correttamente true quando ci sono elementi successivi nella ListAdapter, e false altrimenti.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code hasNext()} su una ListAdapter formata da piu' elementi gia' prima della sua chiamata (restituzione affermativa {@code true}). <br />
     *              La chiamata di {@code hasNext()} su una ListAdapter vuoto. o il cui iteratore ha percorso tutti gli elementi, ci si aspetta ritorni {@code false}. <br />
     *              Ci si aspetta che la chiamata al metodo non comporti modifiche ne' alla ListAdapter ne' all'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@link myAdapter.ListAdapter#size() size}, {@link myAdapter.ListAdapter#contains(Object) contains} <br />
     *                  1.  Si verifica che hasNext() ritorni true.  <br />
     *                  2.  Si fa scorrere l'iteratore finche' ha oggetti da leggere. <br />
     *                  3.  Si verifica che ora hasNext() ritorni false.  <br />
     *                  4.  Si aggiunge un elemento alla ListAdapter. <br />
     *                  5.  Si verifica che hasNext() ritorni true.  <br />
     *                  6.  Si elimina l'ultimo elemento. <br />
     *                  7.  Si verifica che hasNext() ritorni false.  <br />
     *                  5.  Si controlla che hashNext() su una ListAdapter vuoto ritorni false. <br />
     *                  6.  Si controlla che la ListAdapter letta dall'iteratore non sia stato modificato. <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi (nel metodo cambia perchè vengono aggiunti e rimossi degli elementi, ma non a causa dell'iterator).
     * 
     * @r.esult    {@code hasNext()} deve restuire {@code true} quando l'iteratore possiede ancora elementi da leggere, {@code false} quando non ci sono piu' elementi da leggere.
     * 
     */
    @Test
    public void testHasNext() {
        assertTrue(it.hasNext());
        it.next();
        assertTrue(it.hasNext());
        it.next();
        assertFalse(it.hasNext()); // arrivando al secondo e ultimo elemento, hasNext ritorna false perchè non ci sono altri elementi

        list.add(4);
        assertTrue(it.hasNext()); // aggiungendo un altro elemento alla ListAdapter l'iteratore può puntare ora ad un altro elemento della ListAdapter

        it.next();
        it.remove();
        assertFalse(it.hasNext()); // eliminando l'ultimo elemento ritorno alla situazione pre aggiunta

        ListAdapter tmpList = new ListAdapter();
        it = tmpList.iterator();
        assertFalse(it.hasNext());

        assertEquals(list.size(), 2);
        assertTrue(list.contains(1) && list.contains(2));
    }

    // FINE TEST hasNext

    // INIZIO TEST remove
    /**
     * Test del metodo {@link myAdapter.ListAdapter.IteratorAdapter#remove() remove} sulla vista di una ListAdapter.
     * 
     * @s.ummary Verifica che il metodo {@code remove()} elimini correttamente gli elementi all'interno della ListAdapter.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code remove()} su una ListAdapter formata da piu' elementi gia' prima della sua chiamata. <br />
     *             Ci si aspetta che remove, dopo che è stato chiamato il metodo next, elimini l'elemento corrente nell'iterazione (quello restituito da next), modificando quindi la lista.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@code hasNext()}, {@link myAdapter.ListAdapter#size() size}, {@link myAdapter.ListAdapter#contains(Object) contains} <br />
     *                 1.          Si verifica che hasNext() ritorni true.  <br />
     *                 2.          Si fa scorrere l'iteratore finche' ha oggetti da leggere. <br />
     *                 3.          Ad ogni iterazione si elimina l'elemento ritornato da next  <br />
     *                 4.          Si controlla che la lista sia vuota e che quindi hasNext() ritorni false
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore è ora vuota dopo le chiamate di remove().
     * 
     * @r.esult    {@code remove()} deve rimuovere correttamente l'elemento ritornato da {@code next()} e aggiornare la dimensione della collezione che legge.
     * 
     */
    @Test
    public void testRemove() {
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        // controllo che ora la ListAdapter sia vuota
        assertTrue(list.isEmpty());
        assertFalse(it.hasNext());
    }
    
    /**
     * Test del metodo {@link myAdapter.ListAdapter.IteratorAdapter#remove() remove} sulla vista di una ListAdapter.
     * 
     * @s.ummary Verifica che il metodo {@code remove()} lanci l'eccezione {@code HIllegalStateException} se chiamato prima del prossimo {@code next()}.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code remove()} su una ListAdapter formata da piu' elementi gia' prima della sua chiamata. <br />
     *             Ci si aspetta che {@code remove}, non avendo chiamato il prossimo {@code next()} lanci l'eccezione {@code HIllegalStateException}.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@code hasNext()}, {@link myAdapter.ListAdapter#size() size}, {@link myAdapter.ListAdapter#contains(Object) contains} <br />
     *                 1.          Si verifica che {@code hasNext()} ritorni true.  <br />
     *                 2.          Si chiama {@code remove()} e ci si aspetta che lanci l'eccezione
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore è ora vuota dopo le chiamate di {@code remove()}.
     * 
     * @r.esult    {@code remove()} deve lanciare l'ecezione {@code HIllegalStateException}.
     * 
     */
    @Test(expected = HIllegalStateException.class)
    public void testRemoveIllegalAccess() {
        assertTrue(it.hasNext()); // mi assicuro che la ListAdapter non sia vuota
        it.remove();
    }
    // FINE TEST remove
}
