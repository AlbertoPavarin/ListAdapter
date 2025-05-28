package myAdapter;

public interface HIterator {
    boolean hasNext();
    Object next();
    void remove(); // opzionale, può lanciare UnsupportedOperationException
}
