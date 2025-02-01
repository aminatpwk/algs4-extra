package zgjidhje_ushtrimeve_shtese;

import klasat_ndihmese.Graph;

/**
 * Zgjidhje e ushtrimit 2: DFS, BFS
 */
public class Bridge {
    private int bridges;
    private int cnt;
    private int[] pre;
    private int[] low;

    public Bridge(Graph G){
        low = new int[G.V()];
        pre = new int[G.V()];
        for(int v=0; v<G.V(); v++){
            low[v] = -1;
        }
        for(int v=0; v<G.V(); v++){
            pre[v] = -1;
        }
        for(int v=0; v<G.V(); v++){
            if(pre[v] == -1){
                dfs(G, v, v);
            }
        }
    }

    public int components(){
        return bridges + 1;
    }

    private void dfs(Graph G, int v, int u){
        pre[v] = cnt++;
        low[v] = pre[v];
        for(int w : G.adj(v)){
            if(pre[w] == -1) {
                dfs(G, v, w);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    System.out.println(v + "-" + w + " is a bridge");
                    bridges++;
                }
            }else if(w != u){
                low[v] = Math.min(low[v], pre[w]);
            }
        }
    }
}
