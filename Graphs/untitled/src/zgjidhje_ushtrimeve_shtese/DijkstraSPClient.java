package zgjidhje_ushtrimeve_shtese;

import shortest_paths.DijkstraSP;
import shortest_paths.EdgeWeightedDigraph;

/**
 * Zgjidhje e ushtrimit 13:
 * 13.	The diameter of a digraph is the length of the maximum-length shortest path connecting two vertices. Write a
 * DijkstraSP client that finds the diameter of a given EdgeWeightedDigraph that has nonnegative weights.
 *
 * Implementojme algoritmin si zakonisht dhe update-ojme vleren e diametrin me vleren maksimale te distancave nga nyja burim.
 */
public class DijkstraSPClient {
    public static double findDiameter(EdgeWeightedDigraph G){
        double diameter = 0.0;
        for(int s = 0; s < G.V(); s++){
            DijkstraSP d = new DijkstraSP(G,s);

            for(int v = 0; v < G.V(); v++){
                if(d.hasPathTo(v)){
                    diameter = Math.max(diameter, d.distTo(v));
                }
            }
        }
        return diameter;
    }
}
