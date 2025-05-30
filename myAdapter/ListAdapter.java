package myAdapter;

import java.util.Vector;

public class ListAdapter implements HList {

    protected Vector v;

    public ListAdapter() {
        v = new Vector();
    }

    // Deve inserire tutti gli elementi di vec in v
    public ListAdapter(Vector vec){
        v = new Vector();
    }

    // Metodi da HCollection
    // TO TEST
    public int size() {
        return v.size();
    }

    // TO TEST
    public boolean isEmpty() {
        return v.isEmpty();
    }

    // TO TEST
    public boolean contains(Object o) {
        return v.contains(o);
    }

    // TO DO
    public HIterator iterator() {
        return null;
    }

    // TO TEST
    public Object[] toArray() {
        return v.toArray();
    }

    // TO TEST
    public Object[] toArray(Object[] a) {
        return v.toArray(a);
    }

    // TO DO
    public boolean add(Object o) {
        return false;
    }

    // TO DO
    public boolean remove(Object o) {
        return false;
    }

    // TO DO
    public boolean containsAll(HCollection c) {
        return false;
    }

    // TO DO
    public boolean addAll(HCollection c) {
        return false;
    }

    // TO DO
    public boolean addAll(int index, HCollection c) {
        return false;
    }

    // TO DO
    public boolean removeAll(HCollection c) {
        return false;
    }

    // TO DO
    public boolean retainAll(HCollection c) {
        return false;
    }

    // TO TEST
    public void clear() {
        v.clear();
    }

    // TO TEST
    public boolean equals(Object o) {
        return v.equals(o);
    }

    // TO DO
    public int hashCode() {
        return 0;
    }

    // Metodi specifici di HList
    // TO DO
    public Object get(int index) {
        return null;
    }

    // TO DO
    public Object set(int index, Object element) {
        return null;
    }

    // TO DO
    public void add(int index, Object element) {}

    // TO DO
    public Object remove(int index) {
        return null;
    }

    // TO DO
    public int indexOf(Object o) {
        return -1;
    }

    // TO DO
    public int lastIndexOf(Object o) {
        return -1;
    }

    // TO DO
    public HListIterator listIterator() {
        return null;
    }

    // TO DO
    public HListIterator listIterator(int index) {
        return null;
    }

    // TO DO
    public HList subList(int fromIndex, int toIndex) {
        return null;
    }
}
