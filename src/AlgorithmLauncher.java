import TSP.ApproximationAlgorithms.ACO.ACOEngine;
import TSP.ApproximationAlgorithms.TwiceAroundMST.PrimMST;

/**
 * Created by Dylan Galea on 07/10/2018.
 */

public class AlgorithmLauncher {
    public static void main (String args[]){
        //ACOEngine aco = new ACOEngine();
        //aco.approximateTsp();
        PrimMST prim = new PrimMST();
        prim.calculateMinimumWeightSpanningTree();
    }
}
