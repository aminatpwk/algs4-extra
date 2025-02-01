package zgjidhje_ushtrimeve_shtese;

import klasat_ndihmese.Bag;
import klasat_ndihmese.Graph;

/**
 * Zgjidhje e ushtrimit 1 te DFS,BFS
 */
public class ParallelEdgeCounter {
    public static int countParallelEdges(Graph G) {
        int parallelEdges = 0;
        int V = G.V();
        boolean[] marked = new boolean[G.V()];

        for(int v = 0; v < G.V(); v++) {
            Bag<Integer> modified = new Bag<>();
            for(int w: G.adj(v)) {
                if(!marked[w]) {
                    marked[w] = true;
                    modified.add(w);
                }else{
                    parallelEdges++;
                }
            }
            for(int w: modified) {
                marked[w] = false;
            }
        }
        return parallelEdges/2;
    }
}
