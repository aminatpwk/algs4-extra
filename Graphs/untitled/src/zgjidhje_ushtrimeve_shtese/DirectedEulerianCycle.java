package zgjidhje_ushtrimeve_shtese;

import graphs.Digraph;
import klasat_ndihmese.Stack;

import java.util.Iterator;

public class DirectedEulerianCycle {
    private Stack<Integer> cycle = null;

    public DirectedEulerianCycle(Digraph G) {
        if(G.E() == 0)
            return;

        for(int v=0; v<G.V(); v++){
            if(G.outdegree(v) != G.indegree(v)){
                return;
            }
        }

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for(int v=0; v<G.V(); v++){
            adj[v] = G.adj(v).iterator();
        }

        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        cycle = new Stack<Integer>();
        while(!stack.isEmpty()){
            int v = stack.pop();
            while(adj[v].hasNext()){
                stack.push(v);
                v = adj[v].next();
            }
            cycle.push(v);
        }
        if(cycle.size() != G.E() +1){
            cycle = null;
        }
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

    public boolean hasEulerianCycle(){
        return cycle != null;
    }

    private int nonIsolatedVertex(Digraph G){
        for(int v=0; v<G.V(); v++){
            if(G.outdegree(v) > 0){
                return v;
            }
        }
        return -1;
    }
}
