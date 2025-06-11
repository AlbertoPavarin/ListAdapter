package myAdapter;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ListIteratorAdapter extends IteratorAdapter implements HListIterator {

    public ListIteratorAdapter(ListAdapter l) {
        super(l);
    }

    public ListIteratorAdapter(ListAdapter l, int i) {
        super(l);
        if (index<0 || index>list.size()) throw new IndexOutOfBoundsException();

        current = i;
    }

    public boolean hasPrevious() {
        return current > 0;
    }

    public Object previous() {
        try {
            int i = current - 1;
            Object previous = list.get(i);
            index = current = i;
            return previous;
        } catch(IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    public int nextIndex() {
        return current;
    }

    public int previousIndex() {
        return current-1;
    }

    // modifica ultimo elemento restituito
    public void set(Object o)  {
        if (index == -1)
		throw new IllegalStateException();

        list.set(index, o);
    }

    // TO TEST
    // inserisce prima dell'elemento successivo
    public void add(Object o) {   
        if(o == null) throw new NullPointerException();
        
        try {
		    list.add(current++, o);
		    index = -1;
	    } catch(IndexOutOfBoundsException e) {
		    throw new ConcurrentModificationException();
	    }
    }
}
