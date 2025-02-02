package algorithms;
import java.util.Comparator;
public class Selection {
    private Selection() {}

    public void sort(Comparable[] a){
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for(int j = i + 1; j < N; j++){
                if(less(a[j], a[min])){
                    min = j;
                }
            }
            exch(a, i, min);
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

    private boolean isSorted(Comparable[] a){
        return isSorted(a, 0, a.length-1);
    }

    private boolean isSorted(Comparable[] a, int lo, int hi){
        for(int i = lo; i <= hi; i++){
            if(less(a[i], a[i-1])){
                return false;
            }
        }
        return true;
    }
}
