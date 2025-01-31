package zgjidhje_ushtrimeve_shtese;

import klasat_ndihmese.Bag;
import java.util.*;

/**
 * Zgjidhje e ushtrimit 5 :
 * Given a weighted line-graph (undirected connected graph, all vertices of degree 2, except two endpoints which have degree 1),
 * devise an algorithm that preprocesses the graph in linear time and can return the distance of the shortest path between any
 * two vertices in constant time.
 *
 * Perderisa na thuhet qe grafi eshte undirected dhe kerkohet ne kohe lineare si kompleksitet, per te gjetur shortest paths
 * mund te perdorim bfs ose dfs. (Ketu eshte perdorur bfs). Fillojme nga nyja burim qe eshte me degree 1, e fusim ne queue dhe
 * llogarisim distancat me fqinjet duke futur cdo fqinj ne queue. Pasi jane llogaritur distancat mes dy nyjeve fqinje, llogaritet
 * shortes path si vlere absolute. (vlera absolute e kthen ne kohe konstante distancen)
 */
public class AllPairsSPLine {

    //krijimi i klases entitet edge qe perfaqeson nje lidhje te peshuar
    static class WeightedEdge{
        int to, weight;
        WeightedEdge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }

    private int V;
    private Bag<WeightedEdge>[] adj;
    private int[] distTo;

    public AllPairsSPLine(int V){
        this.V = V;
        adj = (Bag<WeightedEdge>[])new Bag[V];
        for(int v= 0; v<V; v++){
            adj[v] = new Bag<>();
        }
        distTo = new int[V];
    }

    public void addEdge(int v, int w, int weight){
        adj[v].add(new WeightedEdge(w, weight));
        adj[w].add(new WeightedEdge(v, weight));
    }

    public int findEndPoint(){
        for(int v= 0; v<V; v++){
            if(adj[v].size()==1){
                return v;
            }
        }
        return -1;
    }

    public void preprocess(){
        int start = findEndPoint();
        Arrays.fill(distTo, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        distTo[start] = 0;
        while(!queue.isEmpty()){
            int v = queue.poll();
            for(WeightedEdge e : adj[v]){
                int w = e.to, weight = e.weight;
                if(distTo[w] == -1){
                    distTo[w] = distTo[v]+weight;
                    queue.add(w);
                }
            }
        }
    }

    public int shortestPath(int v, int w){
        return Math.abs(distTo[v] - distTo[w]);
    }
}
