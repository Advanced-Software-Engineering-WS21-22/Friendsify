package registry;

import java.util.ArrayList;

public class MockUpMockUpRegistryImpl<E> implements MockUpRegistry<E> {

    ArrayList<E> items;

    public MockUpMockUpRegistryImpl() {
        items = new ArrayList();
    }

    public void addMockUp(E value) {
        items.add(value);
    }

    public ArrayList<E> getAll() {
        return  items;
    }

    public E getByKey(int key) {
        return items.get(key);
    }

    public void removeByKey(int key) {
        items.remove(key);
    }

    public void changeMockup(int key, E value) {
        items.set(key, value);
    }
}
