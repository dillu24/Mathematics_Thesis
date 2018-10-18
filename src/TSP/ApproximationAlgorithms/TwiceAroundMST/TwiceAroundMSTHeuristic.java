package TSP.ApproximationAlgorithms.TwiceAroundMST;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;


/**
 * Created by Dylan Galea on 18/10/2018.
 */
public class TwiceAroundMSTHeuristic {
    private Graph g;
    private int [][] MSTAdjacencyMatrix;

    public TwiceAroundMSTHeuristic(){
        g = new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/berlin52");
    }

    public TwiceAroundMSTHeuristic(Graph g){
        this.g = g;
    }

    public double approximateTSP(){
        double result = 0.0;
        //ToDo do DFS and set etc
        return result;
    }
}
