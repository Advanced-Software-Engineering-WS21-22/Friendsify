import java.util.ArrayList;
import java.util.HashMap;

public class MockUpRegistry<K, V> implements Registry<K, V>{

    HashMap<K, V> items = null;

    public MockUpRegistry() {
        items = new HashMap<>();
    }

    public void addMockUp(K key, V value) {
        items.put(key, value);
    }


    public ArrayList<V> getAll() {
        ArrayList<V> allEntries = new ArrayList<>();

        for (V value : items.values()) {
            allEntries.add(value);
        };

        return  allEntries;
    }


    public V getByKey(K key) {
        return items.get(key);
    }

    public void removeByKey(K key) {
        items.remove(key);
    }
}
