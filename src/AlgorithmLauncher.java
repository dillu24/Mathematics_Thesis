/*
  This class contains the main method which is used to start all the algorithms that were developed to approximate the
  TSP instances found in TSPLIB.
 */

import TSP.ApproximationAlgorithms.NearestNeighbourApproximation.NearestNeighbourHeuristicEngine;
import TSP.ApproximationAlgorithms.TwiceAroundMST.PrimMST;
import TSP.ApproximationAlgorithms.TwiceAroundMST.TwiceAroundMSTHeuristic;
import TSP.City;
import TSP.Graphs.CompleteWeightedPlanarGraph;

import java.util.ArrayList;

public class AlgorithmLauncher {
    public static void main (String args[]){
        //ACOEngine aco = new ACOEngine(); //Creates the ACO algorithm object
        //System.out.println("This is the Ant Colony System's result: "+aco.approximateTsp()); //Starts the algorithm and
                                                                                      // displays result
        /*This was done for testing purposes only, to check that the MST output is correct
        CompleteWeightedPlanarGraph testGraph = new CompleteWeightedPlanarGraph();
        ArrayList<City> vertices = new ArrayList<>();
        for(int i=0;i<9;i++){
            vertices.add(new City(i,0,0));
        }
        testGraph.setVertices(vertices);
        double distanceMatrix[][] = {{0.0,4.0,8.0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
                {4.0,0.0,11.0,8.0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
                {8.0,11.0,0.0,Double.MAX_VALUE,7.0,1.0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
                {Double.MAX_VALUE,8.0,Double.MAX_VALUE,0.0,2.0,Double.MAX_VALUE,7.0,4.0,Double.MAX_VALUE},
                {Double.MAX_VALUE,Double.MAX_VALUE,7.0,2.0,0.0,6.0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
                {Double.MAX_VALUE,Double.MAX_VALUE,1.0,Double.MAX_VALUE,6.0,0.0,Double.MAX_VALUE,2.0,Double.MAX_VALUE},
                {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,7.0,Double.MAX_VALUE,Double.MAX_VALUE,0.0,14.0,9.0},
                {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,4.0,Double.MAX_VALUE,2.0,14.0,0.0,10.0},
                {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,9.0,10.0,0.0}};
        testGraph.setDistanceMatrix(distanceMatrix);
        PrimMST mst = new PrimMST(testGraph);
        int [][]adj = mst.calculateMinimumWeightSpanningTree(0);
        for (int[] anAdj : adj) {
            for (int j = 0; j < adj.length; j++) {
                System.out.print(anAdj[j]);
                System.out.print(" ");
            }
            System.out.println();
        }*/

        //This creates the TwiceAroundMstHeuristic object that is used to start the algorithm and displays result
        TwiceAroundMSTHeuristic twiceAroundMSTHeuristic = new TwiceAroundMSTHeuristic(
                new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/u574"));
        System.out.println("Twice around the MST heuristic result: "+twiceAroundMSTHeuristic.approximateTSP());

        // This creates the NearestNeighbourHeuristic object which is also used to approximate tsp and then the result
        // is displayed
        //NearestNeighbourHeuristicEngine nnh = new NearestNeighbourHeuristicEngine(
                //new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/u2319"));
        //System.out.println("Nearest Neighbour heuristic result: "+nnh.approximateTsp());


    }
}
