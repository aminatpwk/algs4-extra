package spanning_trees;

import klasat_ndihmese.Edge;
import klasat_ndihmese.MinPQ;
import klasat_ndihmese.Queue;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        for(int v = 0; v < G.V(); v++){
            if(!marked[v]){
                prim(G,v);
            }
        }
    }

    private void prim(EdgeWeightedGraph G, int s){
        while(!pq.isEmpty()){
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if(marked[v] && marked[w]){
                continue;
            }
            mst.enqueue(e);
            if(!marked[v]){
                visit(G,v);
            }
            if(!marked[w]){
                visit(G, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v){
        marked[v] = true;
        for(Edge e : G.adj(v)){
            if(!marked[e.other(v)]){
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }
}
