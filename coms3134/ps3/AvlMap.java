public class AvlMap<K extends Comparable<? super K>, V> implements Map<K, V> {

    private AvlTree<Pair<K,V>> tree;
    
    public AvlMap() {
        tree = new AvlTree<Pair<K,V>>();
    }

    public void put(K key, V value) {
        Pair<K, V> newPair = new Pair<>(key, value);
        tree.insert(newPair);
    }

    public V get(K key) {
        Pair<K, V> query = new Pair<>(key, null);
        Pair<K,V> found = tree.find(query, tree.root);  
        if (found == null)
            return null;
        return found.value;
    }
}