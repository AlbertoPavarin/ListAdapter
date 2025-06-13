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

    public ListAdapter(HList l){
        v = new Vector();
        addAll(l);
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

    public boolean retainAll(HCollection c) {
        if (c == null) throw new NullPointerException();

        if (c.size() == 0)
            return false;

        boolean result = false;
        HIterator it = iterator();

        while (it.hasNext()) {
            Object elem =  it.next();
            
            if (!c.contains(elem)) {
                it.remove();
                result = true;
            }
        }
        return result;
    }

    public void clear() {
        v.clear();
    }

    public boolean equals(Object o) {
        if(o == null) throw new NullPointerException();
        
        if (!(o instanceof HList)) return false;

        HList listTmp = (HList)o;

        if (this.size() != listTmp.size()) return false;

        for (int i = 0; i < size(); i++) {
            if (get(i) != listTmp.get(i)) return false;
        }

        return true;
    }

    public int hashCode() {
        return v.hashCode();
    }

    // Metodi specifici di HList
    public Object get(int index) {
        return v.get(index);
    }

    public Object set(int index, Object element) {
        if(element == null) throw new NullPointerException();

        return v.set(index, element);
    }

    public void add(int index, Object element) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();

        if(element == null) throw new NullPointerException();

        v.add(index, element);
    }

    public int indexOf(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        if(o == null) throw new NullPointerException();
        
        return v.lastIndexOf(o);
    }

    // TO TEST
    public HListIterator listIterator() {
        return new ListIteratorAdapter(this);
    }

    // TO TEST
    public HListIterator listIterator(int index) {
        return new ListIteratorAdapter(this, index);
    }

    public HList subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= this.size() || fromIndex >= this.size() || toIndex < 0) throw new IndexOutOfBoundsException();
        if (fromIndex > toIndex) throw new IllegalArgumentException();
        
        ListAdapter tmpList = new ListAdapter();
        for (int i = fromIndex; i < toIndex ; i++) {
            tmpList.add(get(i)); 
        }

        return tmpList;
    }
}
