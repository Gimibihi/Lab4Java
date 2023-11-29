package Lab4Bijeikis;


public interface MapADT<K,V>{

    V put(K key,V value);

    V get(K key);

    V remove(K key);

    boolean contains(K key);
}
