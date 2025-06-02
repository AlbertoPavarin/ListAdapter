package myAdapter;

import java.util.Enumeration;

public class IteratorAdapter implements HIterator{
    private Enumeration e;
    private ListAdapter list;
    private int index;
    private int itState;

    public IteratorAdapter(ListAdapter l) {
        list = l;
        e = list.v.elements();
        index = 0;
        itState = 1;
    }

    public boolean hasNext() {
        return e.hasMoreElements();
    }

    public Object next() {
        if (index != list.size() - 1) index++;
        itState = 1;
        return e.nextElement();
    }

    // TO TEST circa
    public void remove() {
        if (itState == 0) throw new IllegalAccessError();

        itState = 0;
        list.remove(index);
        if (index != 0) index--;
    }
}
