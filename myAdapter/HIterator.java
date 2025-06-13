package myAdapter;

import java.util.NoSuchElementException;

/**
 * Iteratore per una collezione
 */
public interface HIterator {
    /**
     * Metodo che determina se l'iteratore ha altri elementi
     * @return true, se l'iteratore ha altri elementi
     */
    boolean hasNext();

    /**
     * Metodo che restituisce il prossimo elemento nell'iterazione
     * @return il prossimo elemento nell'iterazione
     * @throws NoSuchElementException se l'iteratore non ha ulteriori elementi
     */
    Object next();

    /**
     * Metodo che rimuove dalla collezione l'ultimo elemento ritornato dall'iteratore
     * @throws IllegalStateException se il metodo next non è ancora stato chiamato, oppure se remove è già stato chiamato dopo l'ultima chiamata a next
     */
    void remove(); 
}
