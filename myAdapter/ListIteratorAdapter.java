package myAdapter;

import java.util.ListIterator;

public class ListIteratorAdapter implements HListIterator {
    private ListIterator li;

    public ListIteratorAdapter(ListIterator l) {
        li = l;
    }

    // TO TEST
    public boolean hasNext() {
        return li.hasNext();
    }

    // TO TEST
    public Object next() {
        return li.next();
    }

    // TO TEST
    public void remove() {
        li.remove();
    }

    // TO TEST
    public boolean hasPrevious() {
        return li.hasPrevious();
    }

    // TO TEST
    public Object previous() {
        return li.previous();

    }

    // TO TEST
    public int nextIndex() {
        return li.nextIndex();

    }

    // TO TEST
    public int previousIndex() {
        return li.previousIndex();
    }

    // modifica ultimo elemento restituito
    // TO TEST
    public void set(Object o)  {
        if(o == null) throw new NullPointerException();

        li.set(o);
    }

    // inserisce prima dell'elemento successivo
    // TO TEST
    public void add(Object o) {   
        if(o == null) throw new NullPointerException();

        li.add(o);
    }
}
