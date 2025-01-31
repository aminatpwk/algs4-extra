package shortest_paths;

import klasat_ndihmese.Queue;
import klasat_ndihmese.Stack;

public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQueue;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        for(int v=0; v< G.V(); v++){
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue = new Queue<Integer>();
        onQueue[s] = true;
        while(!queue.isEmpty()&& !hasNegativeCycle()){
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }
    }

    /**
     * zgjidhje e ushtrimit 15:
     * Suppose that we add a constructor to the Bellman-Ford algorithm that differs from the constructor given only in that
     * it omits the second argument and that it initializes all distTo[] entries to 0. Show that, if a client uses that constructor,
     * a client call to hasNegativeCycle( ) returns true if and only if the graph has a negative cycle
     * (and negativeCycle( ) returns that cycle).
     * @param G
     */
    public BellmanFordSP(EdgeWeightedDigraph G) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQueue = new boolean[G.V()];
        for(int v=0; v< G.V(); v++){
            distTo[v] = 0.0;
        }
        queue = new Queue<Integer>();
        for(int v=0; v< G.V(); v++){
            if(distTo[v]==0.0){
                queue.enqueue(v);
                onQueue[v] = true;
            }
        }
        while(!queue.isEmpty() && !hasNegativeCycle()){
            int v = queue.dequeue();
            onQueue[v] = false;
            relax(G, v);
        }
    }
    private void relax(EdgeWeightedDigraph G, int v){
        for(DirectedEdge e : G.adj(v)){
            int w = e.to();
            if(distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if(!onQueue[w]){
                    queue.enqueue(w);
                    onQueue[w] = true;
                }
            }
            if(++cost % G.V() == 0){
                findNegativeCycle();
                if(hasNegativeCycle())
                    return;
            }
        }
    }

    public boolean hasNegativeCycle(){
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle(){
        return cycle;
    }

    private void findNegativeCycle(){
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for(int v=0; v< V; v++){
            if(edgeTo[v] != null){
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(spt);
        cycle = finder.cycle();
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
