package myTest;

import java.util.NoSuchElementException;

import myAdapter.HIllegalStateException;
import myAdapter.HListIterator;
import myAdapter.ListAdapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test suite per la classe {@link myAdapter.ListAdapter.ListIteratorAdapter}, che implementa {@link myAdapter.HListIterator}
 * <p>
 * Summary: Verifica il corretto funzionamento dei metodi di un iteratore di una {@link myAdapter.ListAdapter}.
 * <p>
 * Design:  Si utilizzano JUnit 4.13, hamcrest-core-1.3 <br />
 *          La test suite include test cases per verificare il corretto e completo funzionamento dei metodi {@code set()}, {@code hasPrevious()}, {@code previous()}, {@code nextIndex()}, {@code previousIndex()}, e {@code add()} <br />
 *          del ListIterator su ListAdapter vuoti e non vuoti. <br />
 *          Si suppongono funzionanti i metodi di {@link myAdapter.ListAdapter}, di cui viene fornita un'istanza. <br />
 *          Prima di eseguire qualunque test la ListAdapter viene popolata con due valori di prova e poi viene creato l'iteratore, quindi prima di ogni test la ListAdapter sarà sempre popolata con quei due valori
 *          {@link myAdapter.ListAdapter#add(Object) add} usato per aggiungere gli elementi nella funzione setUp.
 */

public class TestListIterator {
    /**
     * La ListAdapter utilizzata per i test. Contiene gli elementi e da cui viene creato l'iteratore
     */
    private ListAdapter list = new ListAdapter();
    /**
     * L'iteratore alla ListAdapter
     */
    private HListIterator it;

    /**
     * Configura la ListAdapter prima di iniziare con tutti i test
     */
    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
        it = list.listIterator();
    }

    // INIZIO TEST set
    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#set(Object) set} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code set()} modifichi correttamente l'elemento corrente della iterazione, ritornata da next() o previous(). <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code set()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che modifichi gli  <br />
     *              elementi del ListAdapter con il valore passato come parametro e che non modifichi la dimensione della lista. <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@link myAdapter.ListAdapter#get(int) get} <br />
     *                  1.      Si salva il primo elemento della lista <br />
     *                  2.      Si chiama {@code next()} <br />
     *                  3.      Si chiama {@code set()} con un valore di prova <br />
     *                  4.      Si verifica che il valore corrente della lista sia stato modificato <br />
     *                  5.      Si verifica che il valore corrente della lista sia quello passato come parametro di {@code set()} <br />
     *                  6.      Si verifica che la lista non abbia cambiato dimensione <br />
     *
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code set()} ha modificato il valore corrente della lista . 
     */
    @Test
    public void testSet() {
        Object previousObject = list.get(0); // salvo il vecchio valore della lista in posizione 0
        it.next(); // mi sposto alla posizione 0 della lista
        it.set(0); // modifica il valore in posizione 0

        assertFalse(list.get(0) == previousObject); // controllo che i 2 valori non coincidano più
        assertTrue((Integer)list.get(0) == 0); // controllo che il valore nuovo della posizione 1 della lista sia quello impostato con set

        assertEquals(list.size(), 2); // controllo che la lista non abbia cambiato dimensione
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#set(Object) set} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code set()} lanci {@link HIllegalStateException} se il metodo è chiamato prima del prossimo {@code next} o {@code previous}.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code set()} su un ListAdapter una volta che si chiama il metodo prima del prossimo {@code next} o {@code previous}. <br />
     *             Verifico quindi che lanci {@code HIllegalStateException} se si chiama il metodo prima del prossimo {@code next} o {@code previous}.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()} <br />
     *                  1.  Si controlla che la lista abbia altri elementi  <br />
     *                  2.  Si chiama set() per verificare che sia lanciata HIllegalStateException. <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code set()} deve lanciare {@code HIllegalStateException} quando si chiama il metodo prima del prossimo {@code next} o {@code previous}.
     */
    @Test(expected = HIllegalStateException.class)
    public void testSetIllegalAccess() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.set(0);
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#set(Object) set} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code set()} lanci {@link NullPointerException} se il metodo è chiamato con un parametro null.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code set()} su un ListAdapter una volta che si chiama il metodo con un parametro null. <br />
     *             Verifico quindi che lanci {@code NullPointerException} se si chiama il metodo passando il metodo con un parametro null.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()} <br />
     *                  1.      Si controlla che la lista abbia altri elementi  <br />
     *                  2.      Si chiama next() per spostarsi alla posizione corrente <br />
     *                  3.      Si chiama set() con parametro null per verificare che sia lanciata NullPointerException. <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code set()} deve lanciare {@code NullPointerException} quando si chiama il metodo con un parametro null.
     */
    @Test(expected = NullPointerException.class)
    public void testSetNull() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.next();
        it.set(null);
    }
    // FINI TEST set

    // INIZIO TEST add
    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#add(Object) add} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code add()} aggiunga correttamente un elemento nella prossima posizione. <br />
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code add()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che aggiunga un  <br />
     *              elemento nel ListAdapter con il valore passato come parametro e che quindi modifichi la dimensione della lista. <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@link myAdapter.ListAdapter#get(int) get} {@link myAdapter.ListAdapter#size() size} <br />
     *                  1.      Si salva l'elemento in posizione 1 della lista <br />
     *                  2.      Si chiama list.size per salvare la vecchia lunghezza <br />
     *                  3.      Si chiama {@code next()} per passare al prossimo elemento <br />
     *                  4.      Si chiama {@code add()} con un valore di prova <br />
     *                  5.      Si verifica che la lista abbia cambiato dimensione <br /> <br />
     *                  6.      Si verifica che {@code add} abbia aggiunto il valore nella posizione corretta<br />
     *                  7.      Si verifica che il vecchio elemento in posizione 1 sia stato shiftato di 1 verso destra, trovandosi quindi in posizione 2 <br />
     *
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene un elemento in più, cioè quello aggiunto.
     * 
     * @r.esult    {@code add()} ha aggiunto un elemento nella lista. 
     */
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
    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#add(Object) add} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code set()} lanci {@link NullPointerException} se il metodo è chiamato con un parametro null.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code add()} su un ListAdapter una volta che si chiama il metodo con un parametro null. <br />
     *             Verifico quindi che lanci {@code NullPointerException} se si chiama il metodo passando il metodo con un parametro null.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()} <br />
     *                  1.      Si controlla che la lista abbia altri elementi  <br />
     *                  2.      Si chiama add() con parametro null per verificare che sia lanciata NullPointerException. <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code set()} deve lanciare {@code NullPointerException} quando si chiama il metodo con un parametro null.
     */
    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        assertTrue(it.hasNext()); // mi assicuro che la lista non sia vuota
        it.add(null);
    }
    // FINE TEST add

    // INIZIO TEST hasPrevious
    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#hasPrevious() hasPrevious} sulla vista di una ListAdapter.
     * 
     * @s.ummary Verifica che il metodo {@code hasPrevious()} ritorna correttamente true quando ci sono elementi precedenti nella ListAdapter, e false altrimenti.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code hasPrevious()} su una ListAdapter formata da piu' elementi gia' prima della sua chiamata (restituzione affermativa {@code true}). <br />
     *              La chiamata di {@code hasPrevious()} su una ListAdapter vuoto. o il cui iteratore si trova all'inizio, ci si aspetta ritorni {@code false}. <br />
     *              Ci si aspetta che la chiamata al metodo non comporti modifiche ne' alla ListAdapter ne' all'iteratore.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code next()}, {@link myAdapter.ListAdapter#size() size}, {@link myAdapter.ListAdapter#contains contains} <br />
     *                  1.      Si verifica che all'inzio dell'iterazione non ci siano elementi precedenti  <br />
     *                  2.      Si chiama next() per spostarsi alla posizione corrente <br />
     *                  3.      Si verifica che ci siano elementi precedenti  <br />
     *                  4.      Si chiama previous() per spostarsi alla posizione precedente <br />
     *                  5.      Si verifica che non ci siano elementi precedenti  <br />
     *
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code hasPrevious()} deve restuire {@code true} quando l'iteratore possiede ancora elementi precedenti da leggere, {@code false} quando non ci sono piu' elementi precedenti da leggere.
     * 
     */
    @Test
    public void testHasPrevious() {
        assertFalse(it.hasPrevious()); // Mi assicuro di essere all'inizio
        it.next();

        assertTrue(it.hasPrevious()); // Controllo che scorrendo la lista abbia qualcosa prima della posizione corrente

        it.previous();

        assertFalse(it.hasPrevious()); // Controllo che ritornando all'inizio della lista non abbia niente come elemento precedente
    }

    // FINE TEST hasPrevious

    // INIZIO TEST previous
    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#previous() next} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code previous()} ritorna correttamente gli elementi della ListAdapter in un ordine non particolare
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code previous()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che restituisce tutti gli <br />
     *              elementi del ListAdapter come sono stati salvati. <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()}, {@code hasPrevious()}, {@code next()}, {@link myAdapter.ListAdapter#size() size}, {@link myAdapter.ListAdapter#contains(Object) contains} <br />
     *                  1.      Si va all'ultimo elemento della lista.  <br />
     *                  2.      Si verifica che il numero di elementi letti sia lo stesso del numero di elementi del ListAdapter. <br />
     *                  3.      Si verifica che chiamando {@code previous()} si ritorni all'inizio della lista e che gli elementi ritornati corrispondano a quelli salvati nella ListAdapter. <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code previous()} deve restuire gli elementi della collezione in un ordine non definito.
     */
    @Test
    public void testPrevious() {
        int i = 0;
        while (it.hasNext())
        {
            it.next();
            i++;
        }

        assertEquals(list.size(), i);

        while (it.hasPrevious())
            assertEquals("Ritorna true se l'elemento ritornato da previous è uguale a quello dell'indice della lista", it.previous(), list.get(--i)); // --i prechè devo controllare quello prima, non il corrente
    }

    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#previous() previous} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code previous()} lanci {@link NoSuchElementException} se non si hanno più elementi precedenti.
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code previous()} su un ListAdapter una volta che si chiama il metodo non avendo più elementi precedenti. <br />
     *             Verifico quindi che lanci {@code NoSuchElementException} se si chiama il metodo non avendo più elementi precedenti.
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasPrevious()} <br />
     *                  1.  Si chiama previous() all'inizio della lista e si verifica che lanci l'eccezione  <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code previous()} deve lanciare {@code NoSuchElementException} quando si cerca di iterarlo oltre la fine della collezione.
     */
    @Test(expected = NoSuchElementException.class)
    public void testPreviousNoElement() {
        it.previous(); // Controllo che essendo all'inizio non ci siano elementi precedenti
    }
    // FINE TEST previous

    // INIZIO TEST nextIndex
    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#nextIndex() nextIndex} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code nextIndex()} ritorni l'indice del prossimo elemento che sarebbe restituito da next().
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code nextIndex()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che restituisca il corretto indice <br />
     *              cioè l'indice del prossimo elemento che sarebbe restituito da next. <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()}, {@code hasrPevious()}, {@code next()}, {@link myAdapter.ListAdapter#size() size}, {@link myAdapter.ListAdapter#contains(Object) contains} <br />
     *                  1.      Si controlla di essere all'inizio della lista.  <br />
     *                  2.      Si verifica che chiamando nextIndex all'inizio della lista l'indice ritornato sia 0 (inizio lista) <br />
     *                  3.      Si verifica che continuando ad andare al prossimo elemento con next, l'indice ritornato sia corretto. <br />
     *                  4.      Si verifica che una volta finiti gli elementi da leggere, il prossimo indice sia pari alla grandezza della lista <br />
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code nextIndex()} deve restuire l'indice corretto.
     */
    @Test
    public void testNextIndex() {
        int i = 0;
        assertFalse(it.hasPrevious()); // Mi assicuro di essere all'inizio della lista
        assertEquals("Ritorna true se nextIndex ritorna il primo indice", i, it.nextIndex());

        while(it.hasNext()) {
            assertEquals("Ritorna true se nextIndex ritorna il primo indice", i++, it.nextIndex());
            it.next();
        }

        assertEquals("Ritorna true se nextIndex ritorna l'ultimo indice", list.size(), it.nextIndex());
        assertEquals(list.size(), i);
    }
    // FINE TEST nextIndex

    // INIZIO TEST previousIndex
    /**
     * Test del metodo {@link myAdapter.ListAdapter.ListIteratorAdapter#previousIndex() previousIndex} sulla vista dei valori di una ListAdapter con elementi.
     * 
     * @s.ummary   Verifica che il metodo {@code previousIndex()} ritorni l'indice del elemento precedente che sarebbe restituito da previous().
     * 
     * @d.esign    Si vuole analizzare il comportamento di {@code previousIndex()} su un ListAdapter fatto di piu' elementi gia' prima della sua chiamata. Verifico che restituisca il corretto indice <br />
     *              cioè l'indice del elemento precedente che sarebbe restituito da previous. <br />
     * 
     * @d.escription   Si suppongono testati e funzionanti il metodo {@code hasNext()}, {@code hasPrevious()}, {@code next()}, {@link myAdapter.ListAdapter#size() size}, {@link myAdapter.ListAdapter#contains(Object) contains} <br />
     *                  1.      Si controlla di essere all'inizio della lista.  <br />
     *                  2.      Si verifica che essendo all'inizio della lista il metodo ritorni -1 <br />
     *                  3.      Si va all'ultimo elemento con next tenendo conto dell'indice in cui si è <br />
     *                  4.      Si controlla che la lunghezza della lista non sia cambiata <br />
     *                  5.      Si ritorna all'inizio della lista e ad ogni iterazione si controlla se l'indice ritornato è quello corretto <br />
     *                  4.      Si controlla che la lunghezza della lista non sia cambiata <br />
     * 
     * 
     * @p.recond   La ListAdapter che viene letta dall'iteratore e' stata popolata e l'iteratore e' stato posizionato all'inizio della collezione
     * 
     * @p.ostcond  La ListAdapter letta dall'iteratore contiene ancora lo stesso numero di elementi.
     * 
     * @r.esult    {@code previousIndex()} deve restuire l'indice corretto.
     */
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
        int oldLength = i;
        assertEquals(list.size(), i);

        while (it.hasPrevious()) {
            assertEquals("Ritorna true se nextIndex ritorna l'ultimo indice", --i, it.previousIndex()); // --i prechè devo controllare quello prima, non il corrente
            it.previous();
        }

        assertEquals(list.size(), oldLength);
    }
    // FINE TEST previousIndex
}