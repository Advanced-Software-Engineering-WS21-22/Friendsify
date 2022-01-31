package registry;

import java.util.ArrayList;

public interface MockUpRegistry<E> {

    public void addMockUp(E value);
    public ArrayList<E> getAll();
    public E getByKey(int key);
    public void removeByKey(int key);
    public void changeMockup(int key, E value);
}
