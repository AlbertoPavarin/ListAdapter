package myTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import myAdapter.ListAdapter;
import myAdapter.HList;

public class TestListAdapter {

    private HList list = new ListAdapter();

    @Before
    public void setUp() {
        
    }

    @Test
    public void testSize() {
        assertEquals("Se la mappa è vuota ritorna True", 0, list.size());
    }
}