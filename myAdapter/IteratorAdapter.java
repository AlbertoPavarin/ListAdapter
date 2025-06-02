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

    public boolean hasNext() {
        return e.hasMoreElements();
    }

    public Object next() {
        if (index != list.size() - 1) index++;
        return e.nextElement();
    }

    // TO TEST circa
    public void remove() {
        list.remove(index);
        if (index != 0) index--;
    }
}
