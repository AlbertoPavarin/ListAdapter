package myAdapter;

public interface HListIterator extends HIterator {
    boolean hasPrevious();
    Object previous();
    int nextIndex();
    int previousIndex();
    void set(Object o); // modifica ultimo elemento restituito
    void add(Object o); // inserisce prima dell'elemento successivo
}
