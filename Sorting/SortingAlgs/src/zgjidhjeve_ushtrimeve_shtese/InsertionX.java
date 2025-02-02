package zgjidhjeve_ushtrimeve_shtese;

public class InsertionX {
    private InsertionX(){}

    public void sortingWithHalfExchanges(Comparable[] a){
        int N = a.length;
        for(int i = 2; i < N; i++){
            Comparable v = a[i];
            int j = i;
            while(less(v, a[j-1])){
                a[j] = a[j-1];
                j--;
            }
            a[j] = v;
        }
    }

    public void sortWithNoFullExchanges(int[] a){
        int N = a.length;
        for(int i = 1; i < N; i++){
            int key = a[i];
            int j = i - 1;
            while(j >= 0 && a[j] > key){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
    }

    private boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
}
