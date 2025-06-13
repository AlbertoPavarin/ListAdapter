package myAdapter;

import java.util.NoSuchElementException;

public class IteratorAdapter implements HIterator{
    protected ListAdapter list;
    protected int current;
    protected int index;

    public IteratorAdapter(ListAdapter l) {
        list = l;
        current = 0;
        index = -1;
    }

    public boolean hasNext() {
        return current != list.size();
    }

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

    public void remove() {
        if (index == -1)
		    throw new IllegalStateException();

	    list.remove(index);
		if (index < current)
		    current--;
		index = -1;
    }
}
