package algorithms;

import klasat_ndihmese.StdRandom;

public class Quick {
    private Quick() {}

    public void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
    }

    private void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    private void threeWaySort(Comparable[] a, int lo, int hi){
        if(hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while(i <= gt){
            int cmp = a[i].compareTo(v);
            if(cmp < 0){
                exh(a, lt++, i++);
            }else if(cmp > 0){
                exh(a, i, gt--);
            }else{
                i++;
            }
        }
        threeWaySort(a, lo, lt-1);
        threeWaySort(a, gt+1, hi);
    }

    private int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi+1;
        Comparable v = a[lo];
        while(true){
            while(less(a[++i], v)){
                if(i == hi) break;
            }
            while(less(v, a[--j])){
                if(j == lo) break;
            }
            if(i >= j) break;
            exh(a, i, j);
        }
        exh(a, lo, j);
        return j;
    }

    private boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private void exh(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
