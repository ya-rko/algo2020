package algo.hash;


public class HashMap<K, V> implements Map<K,V> {
    private static final int DEFAULT_CAPACITY = 1 << 5;

    private static class MapEntry<K, V> {
        private final K key;
        private V value;
        private MapEntry<K, V> next;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    @SuppressWarnings("unchecked")
    private MapEntry<K,V>[] table = (MapEntry<K,V>[]) new MapEntry[DEFAULT_CAPACITY];


    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key not must be null");
        }

        int index = calcTableIndex(key);

        MapEntry<K, V> entry;
        if ((entry = table[index]) == null) {
            table[index] = new MapEntry<>(key, value);
        } else {
            MapEntry<K, V> nextEntry = entry;
            do {
                if (key.equals(nextEntry.key)) {
                    nextEntry.value = value;
                    return;
                }
                entry = nextEntry;
            } while ((nextEntry = nextEntry.next) != null);

            entry.next = new MapEntry<>(key, value);
        }
    }

    
    @Override
    public V get(K key) {
        MapEntry<K, V> entry;
        return (entry = getEntry(key)) != null
               ? entry.value
               : null; 
    }

    
    @Override
    public void remove(K key) {
        if (key == null) {
            return;
        }

        MapEntry<K, V> entry;
        int index = calcTableIndex(key);

        if ((entry = table[index]) != null) {
            if (key.equals(entry.key)) {
                table[index] = entry.next;
                return;
            }

            MapEntry<K, V> prevEntry = entry;
            while ((entry = entry.next) != null) {
                if (key.equals(entry.key)) {
                    prevEntry.next = entry.next;
                    break;
                }
                prevEntry = entry;
            }
        }
    }

    
    private MapEntry<K, V> getEntry(K key) {
        MapEntry<K, V> entry;
        int index = calcTableIndex(key);

        if ((entry = table[index]) != null) {
            do {
                if (key.equals(entry.key)) {
                    return entry;
                }
            } while ((entry = entry.next) != null);
        }

        return null;
    }


    private int calcTableIndex(K key) {
        return (table.length - 1) & hash(key);
    }
    
    
    private int hash(K key) {
        return key.hashCode();
    }
}