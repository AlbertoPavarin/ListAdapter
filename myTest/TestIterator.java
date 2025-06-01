package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import myAdapter.IteratorAdapter;
import myAdapter.ListAdapter;

public class TestIterator {

    private ListAdapter list = new ListAdapter();
    private IteratorAdapter it = new IteratorAdapter(list);


    @Before
    public void setUp() {
        list.add(1);
        list.add(2);
    }
 
    @Test
    public void testNext() {
        it.next();
        list.add(3);
        it.remove();
        System.err.println(it.hasNext());
        it.remove();
        System.err.println(it.hasNext());
    }
}
