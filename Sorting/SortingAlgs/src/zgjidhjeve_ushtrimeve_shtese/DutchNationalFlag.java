package zgjidhjeve_ushtrimeve_shtese;

public class DutchNationalFlag {
    public DutchNationalFlag() {}

    public void dutchNationalFlag(int[] a){
        int n = a.length;
        int lo = 0;
        int hi = n-1;
        int mid = 0;
        while(mid <= hi){
            if(a[mid] == 0){
                exch(a, mid, lo);
                lo++;
                mid++;
            }else if(a[mid] == 1){
                mid++;
            }else{
                exch(a, mid, hi);
                hi--;
            }
        }
    }

    private void exch(int[] a, int i, int j){
        Object swap = a[i];
        a[i] = a[j];
        a[j] = (Integer) swap;
    }
}
