package zgjidhjeve_ushtrimeve_shtese;

import klasat_ndihmese.MaxPQ;
import klasat_ndihmese.MinPQ;
import java.security.Key;

/**
 * Zgjidhja e ushtrimit 6, Priority Queues, Heapsort
 */
public class DynamicMedian {
    private MaxPQ maxHeap;
    private MinPQ minHeap;

    public DynamicMedian() {
        maxHeap = new MaxPQ();
        minHeap = new MinPQ();
    }

    public void insert(Key n){
        if(maxHeap.isEmpty() || n < maxHeap.min()){
            maxHeap.insert(n);
        }else{
            minHeap.insert(n);
        }

        if(maxHeap.size() > minHeap.size() +1 ){
            minHeap.insert(maxHeap.delMax());
        }else if(minHeap.size() > maxHeap.size()){
            maxHeap.insert(minHeap.delMin());
        }
    }

    public Key findMedian(){
        if(maxHeap.size() == minHeap.size()){
            return (maxHeap.min() + minHeap.delMin())/2.0;
        }
        return maxHeap.min();
    }

    public void removeMedian(){
        if(maxHeap.size() >= minHeap.size()){
            maxHeap.delMax();
        }else{
            minHeap.delMin();
        }

        if(maxHeap.size() > minHeap.size()+1){
            minHeap.insert(maxHeap.delMax());
        }else if(minHeap.size() > maxHeap.size()){
            maxHeap.insert(minHeap.delMin());
        }
    }
}
