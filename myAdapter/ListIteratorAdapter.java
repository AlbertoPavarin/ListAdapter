package myAdapter;

import java.util.ListIterator;

public class ListIteratorAdapter implements HListIterator {
    private ListIterator li;

    public ListIteratorAdapter(ListIterator l) {
        li = l;
    }

    // TO DO
    public boolean hasNext() {
        return li.hasNext();
    }

    // TO DO
    public Object next() {
        return li.next();
    }

    // TO DO
    public void remove() {
        li.remove();
    }

    // TO DO
    public boolean hasPrevious() {
        return li.hasPrevious();
    }

    // TO DO
    public Object previous() {
        return li.previous();

    }

    // TO DO
    public int nextIndex() {
        return li.nextIndex();

    }

    // TO DO
    public int previousIndex() {
        return li.previousIndex();
    }

    // modifica ultimo elemento restituito
    // TO DO
    public void set(Object o)  {
        if(o == null) throw new NullPointerException();

        li.set(o);
    }

    // inserisce prima dell'elemento successivo
    // TO DO
    public void add(Object o) {   
        if(o == null) throw new NullPointerException();

        li.add(o);
    }
}
