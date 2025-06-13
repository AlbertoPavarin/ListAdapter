package myAdapter;

/**
 * L'interfaccia HList estende HCollection e definisce una collezione ordinata di elementi.
 * Gli elementi possono essere inseriti e acceduti tramite il loro indice di posizione.
 * L'interfaccia supporta operazioni di accesso posizionale, ricerca e iterazione bidirezionale.
 *
 * @see HCollection
 * @see HListIterator
 */
public interface HList extends HCollection {
    /**
     * Restituisce l'elemento alla posizione specificata nella lista.
     *
     * @param index indice dell'elemento da restituire
     * @return l'elemento alla posizione specificata nella lista
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    Object get(int index) throws IndexOutOfBoundsException;

    /**
     * Sostituisce l'elemento alla posizione specificata nella lista con l'elemento specificato.
     *
     * @param index indice dell'elemento da sostituire
     * @param element elemento da memorizzare alla posizione specificata
     * @return l'elemento precedentemente presente alla posizione specificata
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    Object set(int index, Object element) throws IndexOutOfBoundsException;

    /**
     * Inserisce l'elemento specificato alla posizione specificata nella lista.
     * Sposta a destra di una posizione tutti gli elementi correntemente presenti a partire dalla posizione specificata.
     *
     * @param index indice a cui l'elemento specificato deve essere inserito
     * @param element elemento da inserire
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    void add(int index, Object element) throws IndexOutOfBoundsException;

    /**
     * Rimuove l'elemento alla posizione specificata nella lista.
     * Sposta a sinistra di una posizione tutti gli elementi correntemente presenti a partire dalla posizione specificata.
     *
     * @param index indice dell'elemento da rimuovere
     * @return l'elemento che è stato rimosso dalla lista
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    Object remove(int index) throws IndexOutOfBoundsException;

    /**
     * Restituisce l'indice della prima occorrenza dell'elemento specificato in questa lista,
     * o -1 se la lista non contiene l'elemento.
     *
     * @param o elemento da cercare nella lista
     * @return l'indice della prima occorrenza dell'elemento specificato nella lista,
     *         o -1 se la lista non contiene l'elemento
     * @throws NullPointerException se l'elemento specificato è null
     */
    int indexOf(Object o) throws NullPointerException;

    /**
     * Restituisce l'indice dell'ultima occorrenza dell'elemento specificato in questa lista,
     * o -1 se la lista non contiene l'elemento.
     *
     * @param o elemento da cercare nella lista
     * @return l'indice dell'ultima occorrenza dell'elemento specificato nella lista,
     *         o -1 se la lista non contiene l'elemento
     * @throws NullPointerException se l'elemento specificato è null
     */
    int lastIndexOf(Object o) throws NullPointerException;

    /**
     * Restituisce un iteratore sulla lista che permette di scorrere gli elementi in entrambe le direzioni.
     *
     * @return un iteratore sulla lista
     */
    HListIterator listIterator();

    /**
     * Restituisce un iteratore sulla lista che permette di scorrere gli elementi in entrambe le direzioni,
     * iniziando dalla posizione specificata nella lista.
     *
     * @param index indice del primo elemento da restituire tramite una chiamata a next()
     * @return un iteratore sulla lista che inizia dalla posizione specificata
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    HListIterator listIterator(int index) throws IndexOutOfBoundsException;

    /**
     * Restituisce una vista della porzione di questa lista compresa tra fromIndex (incluso) e toIndex (escluso).
     * Se fromIndex e toIndex sono uguali, la lista restituita è vuota.
     *
     * @param fromIndex indice iniziale, inclusivo
     * @param toIndex indice finale, esclusivo
     * @return una vista della porzione specificata di questa lista
     * @throws IndexOutOfBoundsException se fromIndex o toIndex sono fuori dai limiti
     * @throws IllegalArgumentException se fromIndex è maggiore di toIndex
     */
    HList subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException;
}
