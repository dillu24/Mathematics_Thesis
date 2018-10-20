package TSP.ApproximationAlgorithms.TwiceAroundMST;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;
import java.util.ArrayList;
import java.util.Random;

public class TwiceAroundMSTHeuristic {
    /**
     * This class is used to encode the twice around minimum spanning tree heuristic in order to provide an approximation
     * for the best tour in the TSP. Note that for this heuristic to be correct, the triangle inequality must hold in
     * the space the graph is being considered. In the case were TSPLIB instances are being used , euclidean distance
     * is defined in them thus , the triangle inequality must hold . That being said to avoid repetitions in the tour
     * it is needed that the graph must be complete , thus if the graph is not complete another heuristic must be used
     * or we add infinite weights to missing edges , however this can make the approximate tour very infeasible.
     */
    private Graph g; //Stores the graph to be considered
    private int [][] MSTAdjacencyMatrix; // Stores the MST's adjacency matrix calculated by Prim's algorithm
    private boolean[] visitedVertices; // A boolean array that is used to indicate that a city has already been visited
    private ArrayList<Integer> approximateTour; // Stores the result tour

    /**
     * This is the default constructor and is used to allocate memory and set default values to the fields
     */
    public TwiceAroundMSTHeuristic(){
        g = new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/burma14"); //default graph
        PrimMST mst = new PrimMST(g);//create prim's algorithm instance
        MSTAdjacencyMatrix = mst.calculateMinimumWeightSpanningTree(); //calculate mst's adjacency matrix
        visitedVertices = new boolean[g.getVertices().size()]; //allocate memory
        approximateTour = new ArrayList<>(); // allcoate memory
    }

    /**
     * This constructor is used to create an instance of the TwiceAroundMSTHeuristic given the graph as parameter
     * @param g
     *  Stores the graph to be stored in this.g
     */
    public TwiceAroundMSTHeuristic(Graph g){
        this.g = g;
        PrimMST mst = new PrimMST(g);//create prim's algorithm instance
        MSTAdjacencyMatrix = mst.calculateMinimumWeightSpanningTree();// calculate mst's adjacency matrix
        visitedVertices = new boolean[g.getVertices().size()]; // allocate memory
        approximateTour = new ArrayList<>(); // allocate memory
    }

    /**
     * This method is used to give an approximation of the TSP based on the 2*MST heuristic.
     * @return
     *  An approximation for the TSP
     */
    public double approximateTSP(){
        Random generator = new Random(System.currentTimeMillis()); //The random number generator instance
        double result = 0.0; //stores the result
        DFS(generator.nextInt(g.getVertices().size()));//Do a DFS of the MST by starting from a random vertex
        for(int i=0;i<approximateTour.size()-1;i++){//Calculate the result tour length
            result += g.getDistanceMatrix()[approximateTour.get(i)][approximateTour.get(i+1)];
        }
        result += g.getDistanceMatrix()[approximateTour.get(approximateTour.size()-1)][approximateTour.get(0)]; //do not forget to calculate the distance from the last city to first city (since hamiltonian)
        return result; //return result
    }

    /**
     * This function is used to perform DFS on the MST starting from some vertex. This function is implemented recursively
     * and does not repeat vertex visits since a boolean array checker called this.visitedVertices is used.
     * @param currentVertex
     *  Stores the vertex id the algorithm is currently in.
     */
    private void DFS(int currentVertex){
        approximateTour.add(currentVertex); //add current vertex to tour
        visitedVertices[currentVertex] = true; //mark current vertex as visited
        for(int i=0;i<g.getVertices().size();i++){ // for each vertex in the graph
            if(MSTAdjacencyMatrix[currentVertex][i] == 1 && !visitedVertices[i]){ //if vertex is adjacent to current vertex and not visited , visit it using recursion
                DFS(i);
            }
        }
    }
}
