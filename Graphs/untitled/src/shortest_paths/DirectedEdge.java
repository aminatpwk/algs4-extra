package shortest_paths;

/**
 * Paraqet nje lidhje te drejtuar te peshuar ne nje graf
 */
public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public double weight(){
        return weight;
    }

    public String toString(){
        return v + "->" +w+ " "+String.format("%5.2f", weight);
    }
}
