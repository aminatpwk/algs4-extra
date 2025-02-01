package graphs;

public class Topological {
    private Iterable<Integer> order;
    private int[] rank;

    public Topological(Digraph G){
        DirectedCycle finder = new DirectedCycle(G);
        if(!finder.hasCycle()){
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePost();
            rank = new int[G.V()];
            int i = 0;
            for(int v : order){
                rank[v] = i++;
            }
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean hasOrder(){
        return order != null;
    }

    public boolean isDAG(){
        return hasOrder();
    }

    public int rank(int v){
        if(hasOrder())
            return rank[v];
        else return -1;
    }

    /**
     * Zgjidhje e ushtrimit 7 tek topological order, kosaraju-sharir.
     *
     * @param G
     * @param s
     */
    public boolean hasHamiltonianPath(Digraph G, int s){
        if(!isDAG()){
            return false;
        }
        Topological tp = new Topological(G);
        if(tp.hasOrder()){
            return true;
        }
        return false;
    }
}
