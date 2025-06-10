package myAdapter;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class IteratorAdapter implements HIterator{
    private ListAdapter list;
    private int current;
    private int index;

    public IteratorAdapter(ListAdapter l) {
        list = l;
        current = 0;
        index = -1;
    }

    public boolean hasNext() {
        return current != list.size();
    }

    // TO TEST
    public Object next() {
        try {
            Object next = list.get(current);
		    index = current++;
		    return next;
        }
        catch(IndexOutOfBoundsException e) {
		    throw new NoSuchElementException();
	    }
    }

    // TO TEST 
    public void remove() {
       if (index == -1)
		throw new IllegalStateException();

	    try {
		    list.remove(index);
		    if (index < current)
		        current--;
		    index = -1;
	    } catch(IndexOutOfBoundsException e) {
		    throw new ConcurrentModificationException();
	    }
    }
}
