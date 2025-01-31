package spanning_trees;

import klasat_ndihmese.Bag;
import klasat_ndihmese.Edge;

public class EdgeWeightedGraph {
    private int V;
    private int E;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; ++v) {
            adj[v] = new Bag<Edge>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int degree(int v) {
        return adj[v].size();
    }

    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for(int v = 0; v < V; ++v) {
            int selfLoops = 0;
            for(Edge e: adj(v)){
                if(e.other(v) > v){
                    list.add(e);
                }
                else if(e.other(v) == v){
                    if(selfLoops % 2 == 0)
                        list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

}
