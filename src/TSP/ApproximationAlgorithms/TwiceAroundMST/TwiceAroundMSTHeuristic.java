package TSP.ApproximationAlgorithms.TwiceAroundMST;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;
import java.util.ArrayList;
/**
 * This class is used to encode the Twice Around the Minimum Spanning Tree algorithm in order to provide an approximation
 * for the TSP. Note that for this heuristic to have a known approximation factor, the triangle inequality must hold in
 * the space the graph is being considered. In the case were TSPLIB instances are being used, euclidean distance
 * is defined in them thus, the triangle inequality must hold. That being said, to avoid repetitions in the tour,
 * the graphs must be complete. It must also be noted that in this implementation, the algorithm gives the oportunity
 * to each vertex to act as a starting vertex. As a result, the Hamiltonian cycle of least weight encountered by
 * the algorithm is then returned.
 */
public class TwiceAroundMSTHeuristic {
    private Graph g; //Stores the graph to be considered
    private int [][] MSTAdjacencyMatrix; // Stores the MST's adjacency matrix calculated by Prim's algorithm
    private boolean[] visitedVertices; // A boolean array that is used to indicate that a city has already been visited
    private ArrayList<Integer> approximateTour; // Stores the result tour

    /**
     * This is the default constructor and is used to allocate memory and set default values to the fields
     */
    public TwiceAroundMSTHeuristic(){
        g = new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/berlin52"); //default graph
        visitedVertices = new boolean[g.getVertices().size()]; //allocate memory
        approximateTour = new ArrayList<>(); // allocate memory
    }

    /**
     * This constructor is used to create an instance of the TwiceAroundMSTHeuristic, given the graph as parameter
     * @param g
     *  Stores the graph to be stored in this.g
     */
    public TwiceAroundMSTHeuristic(Graph g){
        this.g = g;
        visitedVertices = new boolean[g.getVertices().size()]; // allocate memory
        approximateTour = new ArrayList<>(); // allocate memory
    }

    /**
     * This method is used to give an approximation of the TSP based on the 2*MST heuristic.
     * @return
     *  An approximation for the TSP
     */
    public double approximateTSP(){
        double result = Double.MAX_VALUE; //stores the result
        for(int j=0;j<g.getVertices().size();j++) { //give the opportunity to each vertex to act as starting vertex for better approximation result
            PrimMST mst = new PrimMST(g);//create prim's algorithm instance
            MSTAdjacencyMatrix = mst.calculateMinimumWeightSpanningTree(j); //calculate mst's adjacency matrix starting from current vertex
            double partialResult = 0.0;
            DFS(j);//Do a DFS of the MST by starting from current vertex of mst
            for (int i = 0; i < approximateTour.size() - 1; i++) {//Calculate the result tour length
                partialResult += g.getDistanceMatrix()[approximateTour.get(i)][approximateTour.get(i + 1)];
            }
            partialResult += g.getDistanceMatrix()[approximateTour.get(approximateTour.size() - 1)][approximateTour.get(0)]; //do not forget to calculate the distance from the last city to first city (since hamiltonian)
            if (partialResult < result) {
                result = partialResult;
            }
            visitedVertices = new boolean[g.getVertices().size()]; //empty array to restart algorithm
            approximateTour = new ArrayList<>(); //empty list to restart algorithm
        }
        return result; //return result
    }

    /**
     * This function is used to perform DFS on the MST starting from some given vertex. This function is implemented
     * recursively and does not visit visited vertices since a boolean array checker called this.visitedVertices is used.
     * @param currentVertex
     *  Stores the vertex id the algorithm is currently in.
     */
    private void DFS(int currentVertex){
        approximateTour.add(currentVertex); //add current vertex to tour
        visitedVertices[currentVertex] = true; //mark current vertex as visited
        for(int i=0;i<g.getVertices().size();i++){ // for each vertex in the graph
            if(MSTAdjacencyMatrix[currentVertex][i] == 1 && !visitedVertices[i]){ //if vertex is adjacent to current vertex and not visited, visit it using recursion
                DFS(i);
            }
        }
    }
}
