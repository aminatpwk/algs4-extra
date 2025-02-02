package algorithms;

public class Insertion {
    private Insertion(){

    }

    public void sort(Comparable[] a){
        int n = a.length;
        for(int i = 1; i < n; i++){
            for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
                exch(a, j, j-1);
            }
        }
    }

    private boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }

    private void exch(Object[] a, int i, int j){
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
