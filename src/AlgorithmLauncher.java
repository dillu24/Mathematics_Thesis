/*
  This class contains the main method which is used to start all the algorithms that were developed to approximate the
  TSP instances found in TSPLIB.
 */

import TSP.ApproximationAlgorithms.ACO.ACOEngine;
import TSP.ApproximationAlgorithms.NearestNeighbourApproximation.NearestNeighbourHeuristicEngine;
import TSP.ApproximationAlgorithms.TwiceAroundMST.TwiceAroundMSTHeuristic;
import TSP.Graphs.CompleteWeightedPlanarGraph;

public class AlgorithmLauncher {
    public static void main (String args[]){
        ACOEngine aco = new ACOEngine(); //Creates the ACO algorithm object
        System.out.println("This is the Ant System's result: "+aco.approximateTsp()); //Starts the algorithm and
                                                                                      // displays result

        /* This was done for testing purposes only, to check that the MST output is correct
        TestGraph graph = new TestGraph(null);
        //PrimMST prim = new PrimMST(graph);
        //int [][]adj = prim.calculateMinimumWeightSpanningTree();
        //for(int i=0;i<adj.length;i++){
            //for(int j=0;j<adj.length;j++){
                //System.out.print(adj[i][j]);
                //System.out.print(" ");
            //}
            //System.out.println();
        }*/

        //This creates the TwiceAroundMstHeuristic object that is used to start the algorithm and displays result
        TwiceAroundMSTHeuristic twiceAroundMSTHeuristic = new TwiceAroundMSTHeuristic(
                new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/berlin52"));
        System.out.println("Twice around MST heuristic result: "+twiceAroundMSTHeuristic.approximateTSP());

        // This creates the NearestNeighbourHeuristic object which is also used to approximate tsp and then the result
        // is displayed
        NearestNeighbourHeuristicEngine nnh = new NearestNeighbourHeuristicEngine(
                new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/berlin52"));
        System.out.println("Nearest Neighbour heuristic result: "+nnh.ApproximateTsp());

        //Interface app = new Interface(); Interface for visuals will be developed later
    }
}
