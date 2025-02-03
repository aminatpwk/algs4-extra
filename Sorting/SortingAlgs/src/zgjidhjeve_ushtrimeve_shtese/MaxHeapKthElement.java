package zgjidhjeve_ushtrimeve_shtese;

/**
 * Zgjidhje e ushtrimit 5, Priority Queue, Heapsort
 */
public class MaxHeapKthElement {
    private int[] heap;
    private int n;
    private int count;

    public MaxHeapKthElement(int[] heap, int n) {
        this.heap = heap;
        this.n = n;
        this.count = 0;
    }

    public boolean kthLargestElement(int k, int x){
        count = 0;
        return kthLargestElement(1, k, x);
    }

    public boolean kthLargestElement(int index, int k, int x) {
        if(index > n){
            return false;
        }

        if(heap[index] >= x){
            count++;
            if(count == k){
                return true;
            }

            return kthLargestElement(2 * index, k, x) || kthLargestElement(2 * index + 1, k, x);
        }
        return false;
    }
}
