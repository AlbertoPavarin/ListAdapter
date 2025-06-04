package myAdapter;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class ListIteratorAdapter implements HListIterator {
    private Enumeration e;
    private ListAdapter list;
    private int index;
    private int itState;

    public ListIteratorAdapter(ListAdapter l) {
        list = l;
        e = list.v.elements();
        index = 0;
        itState = 0;
    }

    public boolean hasNext() {
        return e.hasMoreElements();
    }

    public Object next() {
        if (index != list.size() - 1) index++;
        itState = 1;
        return e.nextElement();
    }

    public void remove() {
        if (itState == 0) throw new IllegalAccessError();

        itState = 0;
        list.remove(index);
        if (index != 0) index--;
    }

    // TO TEST
    public boolean hasPrevious() {
        return index > 0;
    }

    // TO TEST
    public Object previous() {
        if (index == 0) throw new NoSuchElementException();
        index--;
        itState = 2;
        return list.get(index);
    }

    // TO TEST
    public int nextIndex() {
        return index+1;
    }

    // TO TEST
    public int previousIndex() {
        return index-1;
    }

    // modifica ultimo elemento restituito
    // TO TEST
    public void set(Object o)  {
        if (itState == 0) throw new IllegalAccessError();

        if(o == null) throw new NullPointerException();

        list.set(index, o);
    }

    // inserisce prima dell'elemento successivo
    // TO DO
    public void add(Object o) {   
        if(o == null) throw new NullPointerException();
        if (index == 0) {
            list.add(o);
            e.nextElement();
        }
        else if (index == list.size() - 1) list.add(o);
        else list.add(index+1, o);

        itState = 0;
        index++;
    }
}
