package TSP.ApproximationAlgorithms.NearestNeighbourApproximation;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;
import java.util.HashSet;
/**
 * This class is used to compute an approximation to the TSP problem based on the nearest neighbour heuristic. Note
 * that this algorithm is made to work on complete graphs only since it must be certain that hamiltonian paths exists.
 * If graph to be inputted is not complete, it can be converted to complete by creating the missing edges in the graph
 * with infinite weight. As a result, if a hamiltonian path does not exist in the original graph, then the weight
 * of the approximation will be infinite. Note that for better approximations, the algorithm gives an opportunity
 * to each vertex to act as a starting vertex, where then the best Hamiltonian cycle is chosen.
 */
public class NearestNeighbourHeuristicEngine {
    private Graph g; // stores the graph to be considered

    /**
     * The default constructor which is used to store a default empty graph to this.g
     */
    public NearestNeighbourHeuristicEngine(){
        g = new CompleteWeightedPlanarGraph();
    }

    /**
     * This constructor is used to create a new instance of the algorithm, given the input graph as parameter
     * @param g
     *  Stores the value to be stored in this.g
     */
    public NearestNeighbourHeuristicEngine(Graph g){
        this.g = g;
    }

    /**
     * This method is used to update the value stored in this.g
     * @param g
     *  Stores the value to be stored in this.g
     */
    public void setGraph(Graph g){
        this.g = g;
    }

    /**
     * This method is used to return the value stored in this.g
     * @return
     *  The value stored in this.g
     */
    public Graph getGraph(){
        return g;
    }

    /**
     * This method is used to start the algorithm and perform an approximation for the TSP
     * @return
     *  The weight of the approximation to the TSP
     */
    public double approximateTsp(){
        int numberOfCities = g.getVertices().size(); //stores the number of cities in the graph
        double bestTourLength = Double.MAX_VALUE; //stores the result
        double[][] distanceMatrix = g.getDistanceMatrix(); // stores the distance matrix of the graph
        for(int i=0;i<numberOfCities;i++) { //execute the algorithm by starting from a different vertex each time
            double tourLength = 0.0; //stores the result of the considered iteration
            HashSet<Integer> visitedCities = new HashSet<>(); //store the list of visited cities in a set to not visit again
            visitedCities.add(i); // add starting city to the list of visited cities
            int currentCityId = i; // set currentCityId to the starting city
            while (numberOfCities != visitedCities.size()) { //until computing a tour
                int chosenCityId = closestCity(currentCityId, distanceMatrix, numberOfCities, visitedCities); //determine closest city
                visitedCities.add(chosenCityId); //add next city to be visited to the list of visited cities
                tourLength += distanceMatrix[currentCityId][chosenCityId]; //update tour length
                currentCityId = chosenCityId; //move to next city
            }
            tourLength += distanceMatrix[currentCityId][i]; //complete tour length by visiting the starting city
            if(bestTourLength > tourLength){ // if this tour is the best so far tour encountered, store it's weight
                bestTourLength = tourLength;
            }
        }
        return bestTourLength; //return the weight of the best Hamiltonian cycle found by the algorithm
    }

    /**
     * This method is used to determine the closest city to a given city in the graph.
     * @param currentCity
     *  Stores the id of the city it is currently being considered
     * @param distanceMatrix
     *  Stores the distance matrix of the graph
     * @param numberOfCities
     *  Stores the number of vertices in the graph
     * @param visitedCities
     *  Stores the visited cities so far in the algorithm
     * @return
     *  The id of the closest city to the current city
     */
    private int closestCity(int currentCity , double[][] distanceMatrix,int numberOfCities,HashSet<Integer> visitedCities){
        double smallestDistance = Double.MAX_VALUE; //stores the smallest distance to a city from current city
        int chosenCityId = -1; //stores the closest city from the current city
        for(int i=0;i<numberOfCities;i++){ // for each vertex
            if(visitedCities.contains(i)){ // if vertex has already been visited do not consider it
                continue;
            }
            if(distanceMatrix[currentCity][i] <= smallestDistance){ // if vertex is the closest to the current city so far store details
                smallestDistance = distanceMatrix[currentCity][i];
                chosenCityId = i;
            }
        }
        return chosenCityId; //return answer
    }
}
