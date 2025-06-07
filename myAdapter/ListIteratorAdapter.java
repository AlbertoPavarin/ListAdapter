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

    public ListIteratorAdapter(ListAdapter l, int i) {
        if (index<0 || index>list.size()) throw new IndexOutOfBoundsException();

        list = l;
        e = list.v.elements();
        index = i;
        itState = 0;

        for (int j = 0; j < i; j++) {
            e.nextElement();
        }
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

    public boolean hasPrevious() {
        return index > 0;
    }

    public Object previous() {
        if (index == 0) throw new NoSuchElementException();
        index--;
        itState = 1;
        return list.get(index);
    }

    public int nextIndex() {
        return index;
    }

    public int previousIndex() {
        return index-1;
    }

    // modifica ultimo elemento restituito
    public void set(Object o)  {
        if (itState == 0) throw new IllegalAccessError();

        if(o == null) throw new NullPointerException();

        list.set(index, o);
    }

    // inserisce prima dell'elemento successivo
    public void add(Object o) {   
        if(o == null) throw new NullPointerException();
        
        list.add(index++, o);

        itState = 0;
    }
}
