package klasat_ndihmese;

import java.security.Key;
import java.util.Comparator;

public class MinPQ {
    private Key[] pq;
    private int n;
    private Comparator<Key> comparator;

    public MinPQ(int initCapacity) {
        pq = (Key[]) new Object[initCapacity];
        n = 0;
    }

    public MinPQ(){
        this(1);
    }

    public MinPQ(Key[] keys){
        n = keys.length;
        pq = (Key[]) new Object[n];
        for(int i = 0; i < n; i++){
            pq[i+1] = keys[i];
        }
        for(int k = n/2; k >=1; k--){
            sink(k);
        }
    }

    public boolean isEmpty(){
        return n == 0;
    }

    public int size(){
        return n;
    }

    private void sink(int k){
        while(2*k <= n){
            int j = 2*k;
            if(j < n && greater(j, j+1)){
                j++;
            }
            if(!greater(k,j)){
                break;
            }
            exch(k,j);
            k = j;
        }
    }

    private void swim(int k){
        while( k >1 && greater(k/2, k)){
            exch(k/2, k);
            k = k/2;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public Key delMin(){
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n+1] = null;
        return min;
    }

    public void insert(Key x){
        pq[++n] = x;
        swim(n);
    }

    /**
     * The two methods below are a solution to exercise 3 of Priority Queue, Heapsort
     * @return
     */
    private boolean isMinHeap(){
        for(int i = 1; i <=n; i++){
            if(pq[i] == null) return false;
        }
        for(int i = n+1; i<pq.length; i++){
            if(pq[i] != null) return false;
        }
        if(pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }
    private boolean isMinHeapOrdered(int k){
        if(k > n) return true;
        int left = 2*k;
        int right = 2*k+1;
        if(left < n && greater(k, left)){
            return false;
        }
        if(right < n && greater(k, right)){
            return false;
        }
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }
}
