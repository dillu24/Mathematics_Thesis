import TSP.ApproximationAlgorithms.ACO.ACOEngine;

/**
 * Created by Dylan Galea on 07/10/2018.
 */

public class AlgorithmLauncher {
    public static void main (String args[]){
        ACOEngine aco = new ACOEngine();
        aco.approximateTsp();
    }
}
