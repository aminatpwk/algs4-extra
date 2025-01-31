package spanning_trees;
import klasat_ndihmese.Edge;
import klasat_ndihmese.Queue;
import klasat_ndihmese.UF;

import java.util.Arrays;
public class KruskalMST {
    private double weight;
    private Queue<Edge> mst = new Queue<Edge>();

    public KruskalMST(EdgeWeightedGraph G) {
        Edge[] edges = new Edge[G.E()];
        int t=0;
        for(Edge e: G.edges()) {
            edges[t++] = e;
        }
        Arrays.sort(edges);

        UF uf = new UF(G.V());
        for(int i=0; i< G.E() && mst.size() < G.V()-1; i++) {
            Edge e = edges[i];
            int v = e.either();
            int w = e.other(v);
            if(uf.find(v) != uf.find(w)) { //metoda find kthen setin ku ben pjese elementi i dhene si argument
                uf.union(v, w); //metoda union ben merge elementet ne nje set te vetem. TE DYJA METODAT KANE KOMPLEKSITET LOGN
                mst.enqueue(e);
                weight += e.weight();
            }
        }
    }

    public Iterable<Edge> edges(){
        return mst;
    }

    public double weight(){
        return weight;
    }
}
