package myAdapter;

import java.util.Enumeration;

public class IteratorAdapter implements HIterator{
    private Enumeration e;
    private ListAdapter list;

    public IteratorAdapter(ListAdapter l) {
        list = l;
        e = list.v.elements();
    }

    // TO DO
    public boolean hasNext() {
        return e.hasMoreElements();
    }

    // TO DO
    public Object next() {
        return e.nextElement();
    }

    // TO DO
    public void remove() {
        System.err.println("ppp");
    }
}
