package zgjidhje_ushtrimeve_shtese;

import klasat_ndihmese.Graph;
import klasat_ndihmese.Stack;

/**
 * Zgjidhja e ushtrimit 8: DFS, BFS
 */
public class AllPaths {
    public boolean[] onPath;
    private Stack<Integer> path;
    private int numberOfPaths;

    public AllPaths(Graph G, int s, int t){
        onPath = new boolean[G.V()];
        path = new Stack<Integer>();
        dfs(G,s,t);
    }

    private void dfs(Graph G, int s, int t){
        path.push(s);
        onPath[s] = true;
        if(s==t){
            numberOfPaths++;
        }else{
            for(int w: G.adj(s)){
                if(!onPath[w]){
                    dfs(G,w,t);
                }
            }
        }
        path.pop();
        onPath[s] = false;
    }

    private void processCurrentPath(){
        Stack<Integer> reverse = new Stack<Integer>();
        for(int v : path){
            reverse.push(v);
        }
        if(reverse.size() >= 1){
            System.out.println(reverse.pop());
        }
        while(!reverse.isEmpty()){
            System.out.print("-"+reverse.pop());
        }
        System.out.println();
    }

    public int numberOfPaths(){
        return numberOfPaths;
    }
}
