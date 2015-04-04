import java.util.LinkedList;
import java.util.ListIterator;

public class SeparateChainingMap<K extends Comparable<? super K>, V> implements Map<K, V> {

    private LinkedList<Pair<K, V>>[] table;
    public static final int INITIAL_SIZE = 10;
    public int entries = 0;

    @SuppressWarnings("unchecked")
    public SeparateChainingMap() {
        table = (LinkedList<Pair<K, V>>[]) new LinkedList[INITIAL_SIZE];
    }

	public void put(K key, V value) {
        entries++;
        int hash = key.hashCode();
        if(table[hash % table.length].equals(null)) {
            LinkedList<Pair<K, V>> entry = new LinkedList<Pair<K, V>>();
            Pair<K, V> newPair = new Pair<>(key, value);
            entry.add(newPair);
            table[hash % table.length] = entry;
        }
        else {
            LinkedList<Pair<K, V>> entry = table[hash % table.length];
            ListIterator<Pair<K, V>> listIterator = entry.listIterator();
            Pair <K, V> newPair = new Pair<>(key, value);
            while (listIterator.hasNext()) {
                Pair<K, V> cur = listIterator.next();
                if(cur.compareTo(newPair) == 0) {
                    entry.remove(cur);
                    entries--;
                    break;
                }
            }
            entry.addFirst(newPair);
            table[hash % table.length] = entry;
        }
        if(getLoadFactor() > 1) {
            rehash();
        }
    }

	public V get(K key) {
        int hash = key.hashCode();
        LinkedList<Pair<K, V>> entry = table[hash % table.length];
        ListIterator<Pair<K, V>> listIterator = entry.listIterator();
        while (listIterator.hasNext()) {
            Pair<K, V> cur = listIterator.next();
            Pair<K, V> tmp = new Pair<>(key, null);
            if(cur.compareTo(tmp) == 0) {
                return cur.value;
            }
        }
        return null;
    }
    
    private int getLoadFactor() {
        return (entries / table.length);
    }
    
    @SuppressWarnings("unchecked")
	private void rehash() {
        LinkedList<Pair<K, V>>[] newTable = (LinkedList<Pair<K, V>>[]) new LinkedList[table.length * 2];
        for(LinkedList<Pair<K, V>> entry : table) {
            ListIterator<Pair<K, V>> listIterator = entry.listIterator();
            while (listIterator.hasNext()) {
                Pair<K, V> cur = listIterator.next();
                int hash = cur.key.hashCode();
                if(newTable[hash % newTable.length].equals(null)) {
                    LinkedList<Pair<K, V>> newEntry = new LinkedList<Pair<K, V>>();
                    newEntry.add(cur);
                    newTable[hash % newTable.length] = newEntry;
                }
                else {
                    LinkedList<Pair<K, V>> newEntry = newTable[hash % newTable.length];
                    newEntry.addFirst(cur);
                    newTable[hash % newTable.length] = newEntry;
                }
            }
        }
        table = newTable;
    }
}