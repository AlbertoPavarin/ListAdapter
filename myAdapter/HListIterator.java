package myAdapter;

import java.util.NoSuchElementException;

/**
 * Questa interfaccia definisce un iteratore bidirezionale per le liste. Estende HIterator
 * aggiungendo la capacità di attraversare la lista in entrambe le direzioni, ottenere
 * l'indice della posizione corrente e modificare gli elementi durante l'iterazione.
 *
 * @see HIterator
 * @see HList
 */
public interface HListIterator extends HIterator {
    /**
     * Verifica se esiste un elemento precedente nella lista.
     *
     * @return true se esiste un elemento precedente, false altrimenti
     */
    boolean hasPrevious();

    /**
     * Restituisce l'elemento precedente nella lista e sposta il cursore all'indietro.
     *
     * @return l'elemento precedente nella lista
     * @throws NoSuchElementException se non esiste un elemento precedente
     */
    Object previous() throws NoSuchElementException;

    /**
     * Restituisce l'indice dell'elemento che verrebbe restituito da una chiamata a next().
     *
     * @return l'indice dell'elemento successivo, o la dimensione della lista se siamo alla fine
     */
    int nextIndex();

    /**
     * Restituisce l'indice dell'elemento che verrebbe restituito da una chiamata a previous().
     *
     * @return l'indice dell'elemento precedente
     * @throws NoSuchElementException se non esiste un elemento precedente
     */
    int previousIndex() throws NoSuchElementException;

    /**
     * Sostituisce l'ultimo elemento restituito da next() o previous() con l'elemento specificato.
     *
     * @param o l'elemento con cui sostituire l'ultimo elemento restituito
     * @throws HIllegalStateException se next() o previous() non sono stati chiamati, o se
     *         add() o remove() sono stati chiamati dopo l'ultima chiamata a next() o previous()
     */
    void set(Object o) throws HIllegalStateException;

    /**
     * Inserisce l'elemento specificato nella lista prima dell'elemento che verrebbe
     * restituito da next().
     *
     * @param o l'elemento da inserire
     * @throws NullPointerException se l'elemento specificato è null
     */
    void add(Object o) throws NullPointerException;
}
