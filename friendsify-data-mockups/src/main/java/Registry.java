import java.util.ArrayList;

public interface Registry<K, V> {

    public void addMockUp(K key, V value);
    public ArrayList<V> getAll();
    public V getByKey(K key);
    public void removeByKey(K key);
}
