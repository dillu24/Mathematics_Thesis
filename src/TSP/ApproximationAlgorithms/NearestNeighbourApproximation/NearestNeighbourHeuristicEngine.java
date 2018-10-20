package TSP.ApproximationAlgorithms.NearestNeighbourApproximation;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;
import java.util.HashSet;
import java.util.Random;

public class NearestNeighbourHeuristicEngine {
    /**
     * This class is used to compute an approximation to the TSP problem based on the nearest neighbour heuristic. Note
     * that this algorithm is made to work on complete graphs since it must be certain that hamiltonian paths exists.
     * If graph to be inputted is not complete it can be converted to complete by creating the missing edges in the graph
     * with infinite weight.If such a construction is made the infinite edges will be avoided implicitly in the algorithm
     * since the shortest edges will be taken . If a hamiltonian path does not exist in the original graph , then it must
     * be that the original graph does not contain a hamiltonian path.
     */
    private Graph g; // stores the graph to be considered

    /**
     * The default constructor which is used to give a default empty graph to this.g
     */
    public NearestNeighbourHeuristicEngine(){
        g = new CompleteWeightedPlanarGraph();
    }

    /**
     * This constructor is used to create a new instance of the algorithm given the graph as parameter
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
     * This method is used to start the algorithm and perform an approximation to TSP
     * @return
     *  The approximation to TSP
     */
    public double ApproximateTsp(){
        Random randomNumberGenerator = new Random(System.currentTimeMillis()); //random number generator instance
        int numberOfCities = g.getVertices().size(); //stores the number of cities in the graph
        int startingCityId = randomNumberGenerator.nextInt(numberOfCities); // start from a random city in the graph
        double tourLength = 0.0; //stores the result
        HashSet<Integer> visitedCities = new HashSet<>(); //store the list of visited cities in a set to not visit again
        double[][] distanceMatrix = g.getDistanceMatrix(); // stores the distance matrix of the graph
        visitedCities.add(startingCityId); // add starting city to the list of visited cities
        int currentCityId = startingCityId; // set currentCityId to the starting city
        while(numberOfCities != visitedCities.size()){ //until computing a tour
            int chosenCityId = closestCity(currentCityId,distanceMatrix,numberOfCities,visitedCities); //determine closest city
            visitedCities.add(chosenCityId); //add next city to be visited to the list of visited cities
            tourLength += distanceMatrix[currentCityId][chosenCityId]; //update tour
            currentCityId = chosenCityId; //move to next city
        }
        tourLength += distanceMatrix[currentCityId][startingCityId]; //complete tour length by visiting from last city to starting city
        return tourLength; //return answer
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
            if(distanceMatrix[currentCity][i] <= smallestDistance){ // if vertex is closest to the current city so far store details
                smallestDistance = distanceMatrix[currentCity][i];
                chosenCityId = i;
            }
        }
        return chosenCityId; //return answer
    }
}
