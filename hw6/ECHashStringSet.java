import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** A set of String values.
 *  @author Brian Chiang
 */
class ECHashStringSet implements StringSet {
    private static double minLoadFactor = 0.2;
    private static double maxLoadFactor = 5;

    public ECHashStringSet() {
        _storage = new LinkedList[5];
        for (int i = 0; i < 5; i++) {
            _storage[i] = new LinkedList<>();
        }
        _size = 0;
    }

    @Override
    public void put(String s) {
        if (loadFactor() >= maxLoadFactor) {
            resize();
        }
        _storage[index(s)].add(s);
        _size += 1;
    }
    private void resize() {
        List stored = asList();
        _storage = new LinkedList[_storage.length * 2];

        for (int i = 0; i < _storage.length; i++) {
            _storage[i] = new LinkedList<>();
        }

        for (Object obj : stored) {
            String s = (String) obj;
            _storage[index(s)].add(s);
        }
    }

    @Override
    public boolean contains(String s) {
        return _storage[index(s)].contains(s);
    }

    @Override
    public List<String> asList() {
        LinkedList<String> result = new LinkedList<>();
        for (LinkedList<String> stored : _storage) {
            result.addAll(stored);
        }
        return result;
    }

    public Iterator<String> iterator(String low, String high) {
        return null;
    }
    private int index(String s) {
        return (s.hashCode() & 0x7fffffff) % _storage.length;
    }
    private double loadFactor() {
        return (double) _size / (double) _storage.length;
    }
    private int _size;
    private LinkedList<String>[] _storage;
}
