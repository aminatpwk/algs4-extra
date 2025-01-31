package zgjidhje_ushtrimeve_shtese;

import klasat_ndihmese.Bag;
import klasat_ndihmese.Edge;
import klasat_ndihmese.UF;
import spanning_trees.EdgeWeightedGraph;

/**
 * Zgjidhje e ushtrimit 3 ne ushtrimet shtese te Prim dhe Kruskal
 */
public class BoruvkaMST {
    private Bag<Edge> mst = new Bag<Edge>();
    private double weight;

    public BoruvkaMST(EdgeWeightedGraph G) {
        UF uf = new UF(G.V());
        for(int t=1; t<=G.V() && mst.size() < G.V()-1; t = t+t) {
            Edge[] closest = new Edge[G.V()];
            for(Edge e : G.edges()){
                int v = e.either(), w = e.other(v);
                int i = uf.find(v), j = uf.find(w);
                if (i ==j) continue;
                if(closest[i] == null || less(e, closest[i]))
                    closest[i] = e;
                if(closest[j] == null || less(e, closest[j]))
                    closest[j] = e;
            }

            for(int i=0; i<G.V(); i++){
                Edge e = closest[i];
                if(e != null){
                    int v = e.either(), w = e.other(v);
                    if(uf.find(v) != uf.find(w)){
                        mst.add(e);
                        uf.union(v, w);
                        weight += e.weight();
                    }
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    private boolean less(Edge e1, Edge e2) {
        return e1.compareTo(e2) < 0;
    }
}
