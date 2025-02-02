package zgjidhjeve_ushtrimeve_shtese;

import klasat_ndihmese.Inversions;

public class KendallTau {
    public static long distance(int[] a, int[] b){
        if(a.length != b.length){
            throw new IllegalArgumentException("Array dimensions disagree");
        }
        int n = a.length;
        int[] ainv = new int[n];
        for(int i=0; i<n; i++){
            ainv[a[i]] = i;
        }
        int[] bnew = new int[n];
        for(int i=0; i<n; i++){
            bnew[i] = ainv[b[i]];
        }
        return Inversions.count(bnew);
    }
}
