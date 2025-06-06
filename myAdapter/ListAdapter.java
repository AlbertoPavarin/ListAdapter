package myAdapter;

import java.util.Vector;

public class ListAdapter implements HList {

    protected Vector v;

    public ListAdapter() {
        v = new Vector(1);
    }

    // Deve inserire tutti gli elementi di vec in v
    public ListAdapter(Vector vec){
        v = new Vector();
    }

    // Metodi da HCollection
    public int size() {
        return v.size();
    }

    public boolean isEmpty() {
        return v.isEmpty();
    }

    public boolean contains(Object o) {
        if(o == null) throw new NullPointerException();

        return v.contains(o);
    }

    // TO TEST
    public HIterator iterator() {
        return new IteratorAdapter(this);
    }

    public Object[] toArray() {
        return v.toArray();
    }

    public Object[] toArray(Object[] a) {
        if(a == null) throw new NullPointerException();

        return v.toArray(a);
    }

    public boolean add(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.add(o);
    }

    public boolean remove(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.remove(o);
    }

    public Object remove(int index) {
        return v.remove(index);
    }

    // TO TEST
    public boolean containsAll(HCollection c) {
        if (c == null) throw new NullPointerException();
        HIterator it = c.iterator();

        while (it.hasNext()) {
            Object elem =  it.next();
            if (!contains(elem))
                return false;
        }
        return true;
    }

    // TO TEST
    public boolean addAll(HCollection c) {
        if (c == null) throw new NullPointerException();

        HIterator it = c.iterator();

        if (c.size() == 0)
            return false;

        while (it.hasNext()) {
            Object elem =  it.next();
            
            add(elem);
        }

        return true;
    }

    // TO TEST
    public boolean addAll(int index, HCollection c) {
        if (c == null) throw new NullPointerException();

        if (c.size() == 0)
            return false;

        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();

        HIterator it = c.iterator();

        while (it.hasNext()) {
            Object elem =  it.next();
            
            add(index++, elem);
        }
        return true;
    }

    // TO TEST
    public boolean removeAll(HCollection c) {
        if (c == null) throw new NullPointerException();

        if (c.size() == 0)
            return false;
        
        boolean result = false;
        HIterator it = c.iterator();
        
        while (it.hasNext()) {
            Object elem =  it.next();
            
            if (contains(elem)) {
                remove(elem);
                result = true;
            } 
        }

        return result;
    }

    // TO TEST
    public boolean retainAll(HCollection c) {
        if (c == null) throw new NullPointerException();

        if (c.size() == 0)
            return false;

        boolean result = false;
        HIterator it = c.iterator();

        while (it.hasNext()) {
            Object elem =  it.next();
            
            if (!contains(elem)) {
                remove(elem);
                result = true;
            }
        }
        return result;
    }

    public void clear() {
        v.clear();
    }

    // TO TEST
    public boolean equals(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.equals(o);
    }

    // TO DO
    public int hashCode() {
        return 0;
    }

    // Metodi specifici di HList
    // TO TEST
    public Object get(int index) {
        return v.get(index);
    }

    // TO TEST
    public Object set(int index, Object element) {
        return v.set(index, element);
    }

    // TO TEST
    public void add(int index, Object element) {
        v.add(index, element);
    }

    // TO TEST
    public int indexOf(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.indexOf(o);
    }

    // TO TEST
    public int lastIndexOf(Object o) {
        if(o == null) throw new NullPointerException();
        
        return -1;
    }

    // TO TEST
    public HListIterator listIterator() {
        return null;
    }

    // TO TEST
    public HListIterator listIterator(int index) {
        return null;
    }

    // TO DO
    public HList subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= this.size() || fromIndex >= this.size() || toIndex < 0) throw new IndexOutOfBoundsException();
        if (fromIndex > toIndex) throw new IllegalArgumentException();
        
        ListAdapter tmpList = new ListAdapter();
        for (int i = fromIndex; i < toIndex; i++) {
            
        }
        return tmpList;
    }
}
