package myAdapter;

public interface HCollection {
    // Operazioni di base
    int size();
    boolean isEmpty();
    boolean contains(Object o);
    HIterator iterator();
    Object[] toArray();
    Object[] toArray(Object[] a);
    boolean add(Object o);
    boolean remove(Object o);

    // Operazioni bulk
    boolean containsAll(HCollection c);
    boolean addAll(HCollection c);
    boolean removeAll(HCollection c);
    boolean retainAll(HCollection c);
    void clear();

    // Confronto e hashing
    boolean equals(Object o);
    int hashCode();
}
