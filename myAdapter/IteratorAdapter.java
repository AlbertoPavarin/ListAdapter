package myAdapter;

import java.util.Enumeration;

public class IteratorAdapter implements HIterator{
    private Enumeration e;
    private ListAdapter list;
    private int index;

    public IteratorAdapter(ListAdapter l) {
        list = l;
        e = list.v.elements();
        index = 0;
    }

    // TO DO
    public boolean hasNext() {
        return e.hasMoreElements();
    }

    // TO DO
    public Object next() {
        index++;
        return e.nextElement();
    }

    // TO DO
    public void remove() {
        list.remove(index);
        if (index != 0) index--;
    }
}
