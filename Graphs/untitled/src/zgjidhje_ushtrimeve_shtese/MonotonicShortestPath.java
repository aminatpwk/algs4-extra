package zgjidhje_ushtrimeve_shtese;

import klasat_ndihmese.IndexMinPQ;
import shortest_paths.DirectedEdge;
import shortest_paths.EdgeWeightedDigraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Zgjidhje e ushtrimit 6:
 * Given an edge-weighted digraph, find a monotonic shortest path from s to every other vertex. A path is monotonic if the
 * weight of every edge on the path is either strictly increasing or strictly decreasing.
 *
 * Meqe do te gjejm shortest path nga nje nyje tek te tjerat, dhe nuk na thuhet qe ka pesha negative te lidhjeve, mund te
 * implementojme algoritmin Dijkstra me pak permiresime.
 * Fillimisht behen sort lidhjet e grafit ne rend rrites. Me pas merret nga minPQ elementi me i vogel dhe kontrollohen lidhjet
 * fqinje te tij nese jane monotonike. Nese po atehere relaksohen lidhjet sipas monotonise.
 */
public class MonotonicShortestPath {
    private double[] distTo;
    private DirectedEdge[] edgeTo;

    public MonotonicShortestPath(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[s] = 0.0;

        double[] distIncreasing = dijkstraMonotonic(G, s, true);
        DirectedEdge[] edgeIncreasing = Arrays.copyOf(edgeTo, edgeTo.length);

        double[] distDecreasing = dijkstraMonotonic(G, s, false);
        DirectedEdge[] edgeDecreasing = Arrays.copyOf(edgeTo, edgeTo.length);

        for(int v=0; v<G.V(); v++){
            if(distIncreasing[v] < distDecreasing[v]){
                distTo[v] = distIncreasing[v];
                edgeTo[v] = edgeIncreasing[v];
            }else{
                distTo[v] = distDecreasing[v];
                edgeTo[v] = edgeDecreasing[v];
            }
        }
    }

    private double[] dijkstraMonotonic(EdgeWeightedDigraph G, int s,boolean increasingOrder){
        IndexMinPQ<Double> pq = new IndexMinPQ<>(G.V());
        double[] distTo = new double[G.V()];
        DirectedEdge[] edgeTemp = new DirectedEdge[G.V()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[s] = 0.0;
        pq.insert(s, 0.0);

        DirectedEdge[] edges = new DirectedEdge[G.V()];
        int index = 0;
        for(DirectedEdge e : G.edges()){
            edges[index++] = e;
        }

        Arrays.sort(edges, Comparator.comparingDouble(DirectedEdge::weight));
        if(!increasingOrder){
            Arrays.sort(edges, Comparator.comparingDouble(DirectedEdge::weight).reversed());
        }

        while(!pq.isEmpty()){
            int v = pq.delMin();
            for(DirectedEdge e : G.adj(v)){
                if(isMonotonic(edgeTemp[v], e, increasingOrder)){
                    relax(e, distTo, edgeTemp, pq);
                }
            }
        }

        edgeTo = edgeTemp;
        return distTo;
    }

    private boolean isMonotonic(DirectedEdge prev, DirectedEdge curr, boolean increasingOrder){
        return prev == null || (increasingOrder ? prev.weight() < curr.weight() : prev.weight() > curr.weight());
    }

    private void relax(DirectedEdge e, double[] distTo, DirectedEdge[] edgeTemp, IndexMinPQ<Double> pq){
        int v = e.from(), w = e.to();
        if(distTo[w] > distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTemp[w] = e;
            if(pq.contains(w)){
                pq.decreaseKey(w, distTo[w]);
            }else{
                pq.insert(w, distTo[w]);
            }
        }
    }
}
