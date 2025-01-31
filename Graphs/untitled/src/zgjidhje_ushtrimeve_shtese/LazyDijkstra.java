package zgjidhje_ushtrimeve_shtese;

import klasat_ndihmese.MinPQ;
import shortest_paths.DirectedEdge;
import shortest_paths.EdgeWeightedDigraph;

import java.util.Comparator;

/**
 * Zgjidhje e ushtrimit 7:
 * Develop an implementation LazyDijkstraSP.java of the lazy version of Dijkstra’s algorithm that is described in the text.
 * Implementimi lazy i fut te gjitha lidhjet e nje nyje ne priority queue => kerkon me shume kohe per te gjetur min. Lazy qendron
 * tek fakti qe mund te kemi relaksuar nje nyje me perpara dhe po e relaksojme perseri.
 */
public class LazyDijkstra {
    private boolean[]  marked;
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private MinPQ<DirectedEdge> pq;

    public LazyDijkstra(EdgeWeightedDigraph G, int s) {
        pq = new MinPQ<DirectedEdge>();
        marked = new boolean[G.V()];
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for(int v = 0; v < G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        relax(G,s);
        while(!pq.isEmpty()){
            DirectedEdge e = pq.delMin();
            int v = e.from(), w = e.to();
            if(!marked[w])
                relax(G,w);
        }
    }

    private void relax(EdgeWeightedDigraph G, int s){
        marked[s] = true;
        for(DirectedEdge e : G.adj(s)){
            int w = e.to();
            if(distTo[w] > distTo[s] + e.weight()){
                distTo[w] = distTo[s] + e.weight();
                edgeTo[w] = e;
                pq.insert(e);
            }
        }
    }
}
