public class Pair<K, V> {

    private K k;
    private V v;

    public Pair(K x, V y) {
        k = x;
        v = y;
    }

    public void print() {
        System.out.println("(" + k + " | " + v + ")");
    }

}
