package myAdapter;

import java.util.Iterator;

public class IteratorAdapter implements HIterator{
    private Iterator iterator;

    public IteratorAdapter(Iterator i) {
        iterator = i;
    }

    // TO TEST
    public boolean hasNext() {
        return iterator.hasNext();
    }

    // TO TEST
    public Object next() {
        return iterator.next();
    }

    // TO TEST
    public void remove() {
        iterator.remove();
    }
}
