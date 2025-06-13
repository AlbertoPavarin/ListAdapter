package myAdapter;

/**
 * L'interfaccia rappresenta un gruppo di oggetti, conosciuto anche come una collezione.
 * Fornisce operazioni per manipolare e gestire insiemi di oggetti.
 * Alcune collezioni possono contenere duplicati, mente altre no
 */
public interface HCollection {
    
    // Operazioni di base
    /**
     * Restituisce il numero di elementi nella collezione.
     * @return il numero di elementi 
     */
    int size();

    /**
     * Metodo che indica se la collezione e' vuota o meno
     * 
     * @return true se nella collezione non sono presenti elementi
     */
    boolean isEmpty();

    /**
     * Verifica se la collezione contiene un determinato oggetto.
     * @param o l'oggetto da ricercare all'interno della collezione
     * @return true se l'oggetto è presente
     * @throws NullPointerException se o e' null
     */
    boolean contains(Object o) throws NullPointerException;

    /**
     * Restituisce un iteratore sugli elementi della collezione.
     * @return un {@code HIterator}
     */
    HIterator iterator();

    /**
     * Restituisce un array contenente tutti gli elementi della collezione. Inoltre nessuna reference è mantenuta alla collezione dall'array, è quindi indipendente
     * @return un array contenente tutti gli elementi
     */
    Object[] toArray();

    /**
     * Restituisce un array contenente tutti gli elementi della collezione; 
     * il runtime type dell'array restituito è quello dell'array specificato se l'array passato per parametro non riesce a contenere la collezione;
     * se invece riesce a contenerlo viene ritornato l'array passato per parametro
     * @param a l'array in cui inserire gli elementi se è abbastanza grande
     * @return un array contenente tutti gli elementi
     * @throws NullPointerException se a è null
     * @throws ArrayStoreException se il tipo specificato dell'array non è un supertipo di ogni elemento nella collezione
     */
    Object[] toArray(Object[] a) throws NullPointerException, ArrayStoreException;

    /**
     * Aggiunge un elemento alla collezione. Controlla inoltre che l'elemento sia all'interno della lista nel caso la collezione non supporti duplicati
     * @param o l'elemento da aggiungere
     * @return true se la collezione è cambiata
     * @throws NullPointerException se a è null
     */
    boolean add(Object o) throws NullPointerException;

    /**
     * Rimuove una singola istanza dell'elemento specificato, se presente.
     * @param o l'elemento da rimuovere
     * @return {@code true} se l'elemento è stato rimosso
     */
    boolean remove(Object o) throws NullPointerException;

    // Operazioni bulk
    /**
     * Verifica se la collezione contiene tutti gli elementi della collezione specificata.
     * @param c la collezione da controllare
     * @return true se tutti gli elementi sono presenti
     * @throws NullPointerException se la collezione passata è null
     */
    boolean containsAll(HCollection c) throws NullPointerException;

    /**
     * Aggiunge tutti gli elementi della collezione specificata alla seguente collezione.
     * @param c la collezione da aggiungere
     * @return true se la collezione è cambiata
     * @throws NullPointerException se la collezione passata è null
     */
    boolean addAll(HCollection c) throws NullPointerException;

    /**
     * Rimuove tutti gli elementi presenti anche nella collezione specificata. Quindi dopo la chiamata al metodo la seguente collezione non conterrà più gli elementi della collezione specificata
     * @param c la collezionen da confrontare
     * @return true se la collezionen è cambiata
     * @throws NullPointerException se la collezione passata è null
     */
    boolean removeAll(HCollection c) throws NullPointerException;

    /**
     * Mantiene solo gli elementi presenti anche nella collezionen specificata. Quindi dopo la chiamata a questo metodo la seguente collezione conterrà solamente gli elementi della collezione specificata 
     * @param c la collezionen da confrontare
     * @return true se la collezionen è cambiata
     * @throws NullPointerException se la collezione passata è null
     */
    boolean retainAll(HCollection c) throws NullPointerException;

    /**
     * Rimuove tutti gli elementi dalla collezione, svuota quindi la collezione
     */
    void clear();

    // Confronto e hashing
    /**
     * Confronta l'oggetto specificato con questa collezione per vedere se le due collezioni sono uguali. Confronta la lunghezza, il tipo e se gli elementi all'interno sono gli stessi 
     * @param o l'oggetto da confrontare
     * @return true se sono uguali
     * @throws NullPointerException se la collezione passata è null
     */
    boolean equals(Object o) throws NullPointerException;

    /**
     * Restituisce il valore hash della collection.
     * @return il valore hash
     */
    int hashCode();
}
