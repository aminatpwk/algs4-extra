package graphs;

import klasat_ndihmese.Stack;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G,s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Zgjidhje e ushtrimit 5 "undirected graphs, dfs, bfs"
     * @param G
     * @param v
     */
    private void dfsNonrecursive(Graph G, int v) {
        Stack<Integer> stack = new Stack<Integer>();
        Boolean[] marked = new Boolean[G.V()];
        stack.push(v);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if(!marked[current]) {
                marked[current] = true;
                for (int w : G.adj(current)) {
                    if (!marked[w]) {
                        stack.push(w);
                    }
                }
            }

        }
    } {}

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }
}
