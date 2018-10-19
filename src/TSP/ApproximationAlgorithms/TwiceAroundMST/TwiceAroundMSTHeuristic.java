package TSP.ApproximationAlgorithms.TwiceAroundMST;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;

import java.util.ArrayList;
import java.util.Random;
//Graph must be complete for this to work , thus why we are considering complete graphs .. if not complete we either use
// another heuristic or we add infinity .. making the path very unfeasible

/**
 * Created by Dylan Galea on 18/10/2018.
 */
public class TwiceAroundMSTHeuristic {
    private Graph g;
    private int [][] MSTAdjacencyMatrix;
    private boolean[] visitedVertices;
    private ArrayList<Integer> approximateTour;

    public TwiceAroundMSTHeuristic(){
        g = new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/burma14");
        PrimMST mst = new PrimMST(g);
        MSTAdjacencyMatrix = mst.calculateMinimumWeightSpanningTree();
        visitedVertices = new boolean[g.getVertices().size()];
        approximateTour = new ArrayList<>();
    }

    public TwiceAroundMSTHeuristic(Graph g){
        this.g = g;
        PrimMST mst = new PrimMST(g);
        MSTAdjacencyMatrix = mst.calculateMinimumWeightSpanningTree();
        visitedVertices = new boolean[g.getVertices().size()];
        approximateTour = new ArrayList<>();
    }

    public double approximateTSP(){
        Random generator = new Random(System.currentTimeMillis());
        double result = 0.0;
        DFS(generator.nextInt(g.getVertices().size()));
        for(int i=0;i<approximateTour.size()-1;i++){
            result += g.getDistanceMatrix()[approximateTour.get(i)][approximateTour.get(i+1)];
        }
        result += g.getDistanceMatrix()[approximateTour.get(approximateTour.size()-1)][approximateTour.get(0)];
        return result;
    }

    private void DFS(int currentVertex){
        approximateTour.add(currentVertex);
        visitedVertices[currentVertex] = true;
        for(int i=0;i<g.getVertices().size();i++){
            if(MSTAdjacencyMatrix[currentVertex][i] == 1 && !visitedVertices[i]){
                DFS(i);
            }
        }
    }
}
