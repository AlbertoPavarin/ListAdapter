package myAdapter;

import java.util.Enumeration;

public class ListIteratorAdapter implements HListIterator {
    private Enumeration e;
    private ListAdapter list;
    private int index;
    private int itState;

    public ListIteratorAdapter(ListAdapter l) {
        list = l;
        e = list.v.elements();
        index = 0;
        itState = 1;
    }

    // TO TEST
    public boolean hasNext() {
        return e.hasMoreElements();
    }

    // TO TEST
    public Object next() {
        return e.nextElement();
    }

    // TO DO
    public void remove() {
    }

    // TO DO
    public boolean hasPrevious() {
        return false;
    }

    // TO DO
    public Object previous() {
        return null;
    }

    // TO DO
    public int nextIndex() {
        return -1;
    }

    // TO DO
    public int previousIndex() {
        return -1;
    }

    // modifica ultimo elemento restituito
    // TO DO
    public void set(Object o)  {
        if(o == null) throw new NullPointerException();

        list.set(index, o);
    }

    // inserisce prima dell'elemento successivo
    // TO DO
    public void add(Object o) {   
        if(o == null) throw new NullPointerException();

    }
}
