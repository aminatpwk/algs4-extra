package graphs;

import klasat_ndihmese.Queue;
import klasat_ndihmese.Stack;

public class DepthFirstOrder {
    private boolean[] marked;
    private int[] pre;
    private int[] post;
    private Queue<Integer> preOrder;
    private Queue<Integer> postOrder;
    private int preCounter;
    private int postCounter;

    public DepthFirstOrder(Digraph G) {
        pre = new int[G.V()];
        post = new int[G.V()];
        marked = new boolean[G.V()];
        postOrder = new Queue<Integer>();
        preOrder = new Queue<Integer>();
        for(int v = 0; v < G.V(); v++){
            if(!marked[v]){
                dfs(G,v);
            }
        }
    }

    private void dfs(Digraph G, int v){
        marked[v] = true;
        pre[v] = preCounter++;
        preOrder.enqueue(v);
        for(int w : G.adj(v)){
            if(!marked[w]){
                dfs(G,w);
            }
        }
        postOrder.enqueue(v);
        post[v] = postCounter++;
    }

    public int pre(int v){
        return pre[v];
    }

    public int post(int v){
        return post[v];
    }

    public Iterable<Integer> post(){
        return postOrder;
    }

    public Iterable<Integer> pre(){
        return preOrder;
    }

    public Iterable<Integer> reversePost(){
        Stack<Integer> reverse = new Stack<Integer>();
        for(int v : postOrder){
            reverse.push(v);
        }
        return reverse;
    }
}
