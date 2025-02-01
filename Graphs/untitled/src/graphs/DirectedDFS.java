package graphs;

public class DirectedDFS {
    public boolean[] marked;
    private int count;

    public DirectedDFS(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G,s);
    }

    private void dfs(Graph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }
}
