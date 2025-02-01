package graphs;

import klasat_ndihmese.Bag;

public class Digraph {
    private int V;
    private int E;
    private Bag<Integer>[] adj;
    private int[] indegree;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for(int v = 0; v < V; v++){
            adj[v] = new Bag<Integer>();
        }
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v){
        return adj[v];
    }

    public int outdegree(int v){
        return adj[v].size();
    }

    public int indegree(int v){
        return indegree[v];
    }

    public Digraph reverse(){
        Digraph reverse = new Digraph(V);
        for(int v = 0; v < V; v++){
            for(int w : adj[v]){
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }
}
