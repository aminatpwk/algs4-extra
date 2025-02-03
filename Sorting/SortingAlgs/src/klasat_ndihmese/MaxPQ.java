package klasat_ndihmese;

import java.security.Key;
import java.util.Comparator;

public class MaxPQ {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;
    private Key minItem;

    public MaxPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
        minItem = null;
    }

    public MaxPQ() {
        this(1);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * Zgjidhje e ushtrimit 4 tek ushtrimet shtese
     * @param x
     */
    public void insert(Key x) {
        pq[++n] = x;
        swim(n);
        if(minItem == null || less(x, minItem)){
            minItem = x;
        }
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public Key min(){
        return minItem;
    }

}
