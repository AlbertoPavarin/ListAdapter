package myAdapter;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
     * Oggetto che rappresenta una collezione di elementi.
     * Implementa una serie di metodo che permettonon la manipolazione dell'oggetto
     * Costruisce una nuova lista vuota con capacità iniziale di 1 elemento.
     */
public class ListAdapter implements HList {
    /**
     * Il Vector che contiene gli elementi della lista.
     */
    protected Vector v;

    /**
     * Costruisce una nuova lista vuota con capacità iniziale di 1 elemento.
     */
    public ListAdapter() {
        v = new Vector(1);
    }

    /**
     * Costruisce una nuova lista contenente gli elementi del Vector specificato.
     *
     * @param vec il Vector i cui elementi devono essere inseriti nella lista
     * @throws NullPointerException se vec è null
     */
    public ListAdapter(Vector vec){
        if (vec == null) throw new NullPointerException();
        v = vec;
    }

    /**
     * Costruisce una nuova lista contenente gli elementi della HList specificata.
     *
     * @param l la HList i cui elementi devono essere inseriti nella lista
     * @throws NullPointerException se l è null
     */
    public ListAdapter(HList l){
        if (l == null) throw new NullPointerException();
        v = new Vector();
        addAll(l);
    }

    /**
     * Restituisce il numero di elementi nella lista.
     * @return il numero di elementi 
     */
    public int size() {
        return v.size();
    }

    /**
     * Verifica se la lista è vuota.
     *
     * @return true se la lista non contiene elementi, false altrimenti
     */
    public boolean isEmpty() {
        return v.isEmpty();
    }

    /**
     * Verifica se la lista contiene un determinato oggetto.
     *
     * @param o l'elemento da cercare nella lista
     * @return true se l'elemento è presente nella lista, false altrimenti
     * @throws NullPointerException se l'elemento specificato è null
     */
    public boolean contains(Object o) {
        if(o == null) throw new NullPointerException();

        return v.contains(o);
    }

    /**
     * Restituisce un iteratore sugli elementi della lista.
     * 
     * @return un nuovo iteratore sugli elementi della lista
     */
    public HIterator iterator() {
        return new IteratorAdapter(this);
    }

    /**
     *  Restituisce un array contenente tutti gli elementi della lista. Inoltre nessuna reference è mantenuta alla collezione dall'array, è quindi indipendente
     *
     * @return un array contenente tutti gli elementi della lista
     */
    public Object[] toArray() {
        return v.toArray();
    }

    /**
     * Restituisce un array contenente tutti gli elementi della collezione; 
     * il runtime type dell'array restituito è quello dell'array specificato se l'array passato per parametro non riesce a contenere la collezione;
     * se invece riesce a contenerlo viene ritornato l'array passato per parametro
     *
     * @param a l'array in cui memorizzare gli elementi se abbastanza grande
     * @return un array contenente tutti gli elementi della lista
     * @throws NullPointerException se l'array specificato è null
     * @throws ArrayStoreException se il tipo specificato dell'array non è un supertipo di ogni elemento nella collezione
     */
    public Object[] toArray(Object[] a) {
        if(a == null) throw new NullPointerException();

        return v.toArray(a);
    }

    /**
     * Aggiunge l'elemento specificato alla fine della lista.
     *
     * @param o l'elemento da aggiungere
     * @return true se l'elemento è stato aggiunto con successo e la lista è stata modificata
     * @throws NullPointerException se l'elemento specificato è null
     */
    public boolean add(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.add(o);
    }

    /**
     * Rimuove una singola istanza dell'elemento specificato, se presente.
     *
     * @param o l'elemento da rimuovere
     * @return true se l'elemento è stato rimosso, false se non era presente
     * @throws NullPointerException se l'elemento specificato è null
     */
    public boolean remove(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.remove(o);
    }

    /**
     * Rimuove l'elemento alla posizione specificata dalla lista.
     *
     * @param index l'indice dell'elemento da rimuovere
     * @return l'elemento rimosso
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    public Object remove(int index) {
        return v.remove(index);
    }

    /**
     * Verifica se la lista contiene tutti gli elementi della collezione specificata.
     *
     * @param c la collezione da verificare
     * @return true se tutti gli elementi sono presenti, false altrimenti
     * @throws NullPointerException se la collezione specificata è null
     */
    public boolean containsAll(HCollection c) {
        if (c == null) throw new NullPointerException();
        HIterator it = c.iterator();

        while (it.hasNext()) {
            Object elem =  it.next();
            if (!contains(elem))
                return false;
        }
        return true;
    }

    /**
     * Aggiunge tutti gli elementi della collezione specificata alla fine della lista.
     *
     * @param c la collezione contenente gli elementi da aggiungere
     * @return true se la lista è stata modificata, false altrimenti
     * @throws NullPointerException se la collezione specificata è null
     */
    public boolean addAll(HCollection c) {
        if (c == null) throw new NullPointerException();

        HIterator it = c.iterator();

        if (c.size() == 0)
            return false;

        while (it.hasNext()) {
            Object elem =  it.next();
            
            add(elem);
        }

        return true;
    }

    /**
     * Inserisce tutti gli elementi della collezione specificata nella lista a partire dalla posizione specificata.
     *
     * @param index l'indice in cui inserire il primo elemento della collezione specificata
     * @param c la collezione contenente gli elementi da inserire nella lista
     * @return true se la lista è stata modificata, false altrimenti
     * @throws NullPointerException se la collezione specificata è null
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    public boolean addAll(int index, HCollection c) {
        if (c == null) throw new NullPointerException();
    
        if (c.size() == 0)
            return false;
    
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
    
        HIterator it = c.iterator();
    
        while (it.hasNext()) {
            Object elem =  it.next();
            
            add(index++, elem);
        }
        return true;
        }

    /**
     * Rimuove dalla lista tutti gli elementi contenuti nella collezione specificata.
     *
     * @param c la collezione contenente gli elementi da rimuovere
     * @return true se la lista è stata modificata, false altrimenti
     * @throws NullPointerException se la collezione specificata è null
     */
    public boolean removeAll(HCollection c) {
        if (c == null) throw new NullPointerException();

        if (c.size() == 0)
            return false;
        
        boolean result = false;
        HIterator it = c.iterator();
        
        while (it.hasNext()) {
            Object elem =  it.next();
            
            if (contains(elem)) {
                remove(elem);
                result = true;
            } 
        }

        return result;
    }

    /**
     * Mantiene solo gli elementi della lista che sono contenuti nella collezione specificata.
     *
     * @param c la collezione contenente gli elementi da mantenere
     * @return true se la lista è stata modificata, false altrimenti
     * @throws NullPointerException se la collezione specificata è null
     */
    public boolean retainAll(HCollection c) {
        if (c == null) throw new NullPointerException();

        if (c.size() == 0)
            return false;

        boolean result = false;
        HIterator it = iterator();

        while (it.hasNext()) {
            Object elem =  it.next();
            
            if (!c.contains(elem)) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    /**
     * Rimuove tutti gli elementi dalla lista, quindi svuotandola
     */
    public void clear() {
        v.clear();
    }

    /**
     * Confronta l'oggetto specificato con questa collezione per vedere se le due collezioni sono uguali. Confronta la lunghezza, il tipo e se gli elementi all'interno sono gli stessi.
     *
     * @param o l'oggetto da confrontare con questa lista
     * @return true se l'oggetto specificato è uguale a questa lista
     * @throws NullPointerException se l'oggetto specificato è null
     */
    public boolean equals(Object o) {
        if(o == null) throw new NullPointerException();
        
        if (!(o instanceof HList)) return false;

        HList listTmp = (HList)o;

        if (this.size() != listTmp.size()) return false;

        for (int i = 0; i < size(); i++) {
            if (get(i) != listTmp.get(i)) return false;
        }

        return true;
    }

    /**
     * Restituisce il codice hash per questa lista.
     *
     * @return il codice hash per questa lista
     */
    public int hashCode() {
        return v.hashCode();
    }

    /**
     * Restituisce l'elemento alla posizione specificata nella lista.
     *
     * @param index l'indice dell'elemento da restituire
     * @return l'elemento alla posizione specificata
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    public Object get(int index) {
        return v.get(index);
    }

    /**
     * Sostituisce l'elemento alla posizione specificata nella lista con l'elemento specificato.
     *
     * @param index l'indice dell'elemento da sostituire
     * @param element l'elemento da memorizzare alla posizione specificata
     * @return l'elemento precedentemente memorizzato alla posizione specificata
     * @throws NullPointerException se l'elemento specificato è null
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    public Object set(int index, Object element) {
        if(element == null) throw new NullPointerException();

        return v.set(index, element);
    }

    /**
     * Inserisce l'elemento specificato alla posizione specificata nella lista.
     *
     * @param index l'indice in cui inserire l'elemento specificato
     * @param element l'elemento da inserire
     * @throws NullPointerException se l'elemento specificato è null
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    public void add(int index, Object element) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();

        if(element == null) throw new NullPointerException();

        v.add(index, element);
    }

    /**
     * Restituisce l'indice della prima occorrenza dell'elemento specificato nella lista.
     *
     * @param o l'elemento da cercare
     * @return l'indice della prima occorrenza dell'elemento specificato nella lista,
     *         o -1 se la lista non contiene l'elemento
     * @throws NullPointerException se l'elemento specificato è null
     */
    public int indexOf(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.indexOf(o);
    }

    /**
     * Restituisce l'indice dell'ultima occorrenza dell'elemento specificato nella lista.
     *
     * @param o l'elemento da cercare
     * @return l'indice dell'ultima occorrenza dell'elemento specificato nella lista,
     *         o -1 se la lista non contiene l'elemento
     * @throws NullPointerException se l'elemento specificato è null
     */
    public int lastIndexOf(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.lastIndexOf(o);
    }

    /**
     * Restituisce un iteratore di lista sugli elementi di questa lista.
     *
     * @return un iteratore di lista sugli elementi di questa lista
     */
    public HListIterator listIterator() {
        return new ListIteratorAdapter(this);
    }

    /**
     * Restituisce un iteratore di lista sugli elementi di questa lista, iniziando dalla posizione specificata.
     *
     * @param index l'indice della prima occorrenza da restituire dall'iteratore di lista
     * @return un iteratore di lista sugli elementi di questa lista, iniziando dalla posizione specificata
     * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti
     */
    public HListIterator listIterator(int index) {
        return new ListIteratorAdapter(this, index);
    }

    /**
     * Restituisce una vista della porzione di questa lista tra fromIndex, inclusivo, e toIndex, esclusivo.
     * Se fromIndex e toIndex sono uguali, la lista restituita è vuota. La lista restituita è supportata
     * da questa lista, quindi le modifiche non-strutturali nella lista restituita sono riflesse in questa lista.
     *
     * @param fromIndex l'indice iniziale, inclusivo
     * @param toIndex l'indice finale, esclusivo
     * @return una vista della porzione specificata di questa lista
     * @throws IndexOutOfBoundsException se fromIndex o toIndex sono fuori dai limiti
     * @throws IllegalArgumentException se fromIndex è maggiore di toIndex
     */
    public HList subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= this.size() || fromIndex >= this.size() || toIndex < 0) throw new IndexOutOfBoundsException();
        if (fromIndex > toIndex) throw new IllegalArgumentException();
        
        ListAdapter tmpList = new ListAdapter();
        for (int i = fromIndex; i < toIndex ; i++) {
            tmpList.add(get(i)); 
        }

        return tmpList;
    }
    /**
     * Questa classe fornisce un'implementazione base dell'interfaccia HIterator per iterare
     * su una ListAdapter. Mantiene lo stato dell'iterazione e fornisce le operazioni
     * fondamentali per attraversare e modificare la lista.
     *
     * @see HIterator
     * @see ListAdapter
     */
    public class IteratorAdapter implements HIterator {
        /**
         * La lista su cui opera l'iteratore.
         */
        protected ListAdapter list;

        /**
         * L'indice della posizione corrente nell'iterazione.
         */
        protected int current;

        /**
         * L'indice dell'ultimo elemento restituito da next().
         * Vale -1 se next() non è stato ancora chiamato o se è stata
         * chiamata l'operazione remove() dopo l'ultima chiamata a next().
         */
        protected int index;

        /**
         * Costruisce un nuovo IteratorAdapter per la ListAdapter specificata.
         * L'iteratore viene posizionato all'inizio della lista.
         *
         * @param l la ListAdapter su cui iterare
         */
        public IteratorAdapter(ListAdapter l) {
            list = l;
            current = 0;
            index = -1;
        }

        /**
         * Verifica se esistono altri elementi nella lista da attraversare.
         *
         * @return true se ci sono ancora elementi da attraversare, false altrimenti
         */
        public boolean hasNext() {
            return current != list.size();
        }

        /**
         * Restituisce l'elemento successivo nella lista e avanza il cursore.
         *
         * @return l'elemento successivo nella lista
         * @throws NoSuchElementException se non ci sono più elementi da attraversare
         */
        public Object next() {
            try {
                Object next = list.get(current);
    		    index = current++;
    		    return next;
            }
            catch(IndexOutOfBoundsException e) {
    		    throw new NoSuchElementException();
    	    }
        }

        /**
        * Rimuove dalla lista l'ultimo elemento restituito da next().
        * Questa operazione può essere chiamata solo una volta per chiamata a next().
        *
        * @throws HIllegalStateException se next() non è stato chiamato, o se remove()
        *         è già stato chiamato dopo l'ultima chiamata a next()
        */
        public void remove() {
            if (index == -1)
    		    throw new HIllegalStateException();

    	    list.remove(index);
    		if (index < current)
    		    current--;
    		index = -1;
        }
    }

    /**
     * Questa classe implementa l'interfaccia HListIterator fornendo un iteratore bidirezionale
     * per la classe ListAdapter. Estende IteratorAdapter aggiungendo funzionalità per
     * attraversare la lista in entrambe le direzioni e modificare gli elementi durante
     * l'iterazione.
     *
     * @see HListIterator
     * @see IteratorAdapter
     * @see ListAdapter
     */
    private class ListIteratorAdapter extends IteratorAdapter implements HListIterator {
        /**
         * Costruisce un nuovo ListIteratorAdapter che inizia dall'inizio della lista.
         *
         * @param l la ListAdapter su cui iterare
         */
        public ListIteratorAdapter(ListAdapter l) {
            super(l);
        }
        /**
         * Costruisce un nuovo ListIteratorAdapter che inizia dalla posizione specificata.
         *
         * @param l la ListAdapter su cui iterare
         * @param i l'indice iniziale dell'iteratore
         * @throws IndexOutOfBoundsException se l'indice è fuori dai limiti 
         */
        public ListIteratorAdapter(ListAdapter l, int i) {
            super(l);
            if (index<0 || index>list.size()) throw new IndexOutOfBoundsException();
            current = i;
        }
        /**
         * Verifica se esiste un elemento precedente nella lista.
         *
         * @return true se esiste un elemento precedente, false altrimenti
         */
        public boolean hasPrevious() {
            return current > 0;
        }
        /**
         * Restituisce l'elemento precedente nella lista e sposta il cursore all'indietro.
         *
         * @return l'elemento precedente nella lista
         * @throws NoSuchElementException se non esiste un elemento precedente
         */
        public Object previous() {
            try {
                int i = current - 1;
                Object previous = list.get(i);
                index = current = i;
                return previous;
            } catch(IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }
        /**
         * Restituisce l'indice dell'elemento che verrebbe restituito da una chiamata a next().
         *
         * @return l'indice dell'elemento successivo
         */
        public int nextIndex() {
            return current;
        }
        /**
         * Restituisce l'indice dell'elemento che verrebbe restituito da una chiamata a previous().
         *
         * @return l'indice dell'elemento precedente, o -1 se siamo all'inizio della lista
         */
        public int previousIndex() {
            return current-1;
        }
        /**
         * Sostituisce l'ultimo elemento restituito da next() o previous() con l'elemento specificato.
         *
         * @param o l'elemento con cui sostituire l'ultimo elemento restituito
         * @throws HIllegalStateException se next() o previous() non sono stati chiamati, o se
         *         add() o remove() sono stati chiamati dopo l'ultima chiamata a next() o previous()
         */
        public void set(Object o)  {
            if (index == -1) throw new HIllegalStateException();
            list.set(index, o);
        }
        /**
         * Inserisce l'elemento specificato nella lista prima dell'elemento che verrebbe
         * restituito da next().
         *
         * @param o l'elemento da inserire
         * @throws NullPointerException se l'elemento specificato è null
         */
        public void add(Object o) {   
            if(o == null) throw new NullPointerException();
        	list.add(current++, o);
        	index = -1;
        
        }
    }
}