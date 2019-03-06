package TSP.ApproximationAlgorithms.ACO;

import TSP.ApproximationAlgorithms.NearestNeighbourApproximation.NearestNeighbourHeuristicEngine;
import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class ACOEngine {
    /**
     * This class is used to represent the ACO ant system algorithm presented in the Dorigo paper. This graph considers
     * only the case when the graph is complete so that we are sure that a Hamiltonian path exists. Note that any
     * weighted graph can be modified to a complete graph by setting the missing edges to infinite weight distance . As
     * a result with the mechanisms taken in this algorithm , such large edges would not be considered and thus the
     * algorithm won't be effected. This algorithm has a number of parameters as discussed by Dorigo paper and they were
     * set in the default constructor as suggested in the same paper. Loops in the graph are also avoided implicitly
     * since the ant will never visit a city that it has already visited since a visited city will be market as visited
     * when added to a set of visited cities.
     */
    private ArrayList<Ant> listOfAnts; //stores all the ants traversing the graph
    private int beta; //Weighs the importance of pheromone to closeness of next city
    private double alpha; // the level of pheromone evaporation at each iteration
    private double q0; // the probability bias
    private double t0; // the amount of pheromone deposited at each iteration
    private double[][] pheromoneMatrix; // stores the level of pheromone between each edge
    private Graph graph; // stores the graph to be considered
    private int numberOfAnts; // stores the number of ants traversing the graph

    /**
     * This is the default constructor and is used to created an instance of the algorithm based on values specified
     * in the Dorigo paper.
     */
    public ACOEngine(){
        listOfAnts = new ArrayList<>(); //allocate memory
        beta = 2;
        alpha = 0.1;
        q0 = 0.9;
        numberOfAnts = 10;
        graph = new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/berlin52");
        NearestNeighbourHeuristicEngine nnh = new NearestNeighbourHeuristicEngine(graph);
        t0 = 1/(graph.getVertices().size()* nnh.ApproximateTsp()); // as suggested in Dorigo paper
        pheromoneMatrix = new double[graph.getVertices().size()][graph.getVertices().size()]; //allocate memory
        for(int i=0;i<graph.getVertices().size();i++){ //initialize pheromone matrix
            for(int j=0;j<graph.getVertices().size();j++){
                pheromoneMatrix[i][j] = 0.00005;
            }
        }
    }

    /**
     * This constructor is used to create an instance of the algorithm based on default values as the previous constructor
     * , however using the specified graph as parameter
     * @param g
     *  Stores the graph to be stored in this.graph
     */
    public ACOEngine(Graph g){
        listOfAnts = new ArrayList<>(); //allocate memory
        beta = 2;
        alpha = 0.1;
        q0 = 0.9;
        numberOfAnts = 10;
        graph = g;
        NearestNeighbourHeuristicEngine nnh = new NearestNeighbourHeuristicEngine(graph);
        t0 = 1/(graph.getVertices().size()* nnh.ApproximateTsp()); //as suggested by Dorigo paper
        pheromoneMatrix = new double[graph.getVertices().size()][graph.getVertices().size()]; //allocation memory
        for(int i=0;i<graph.getVertices().size();i++){ //initialize pheromone matrix with starting default value
            for(int j=0;j<graph.getVertices().size();j++){
                pheromoneMatrix[i][j] = 0.00005;
            }
        }
    }

    /**
     * This constructor is used to create a new aco algorithm instance based on parameter value as wanted by the user
     * @param beta
     *  Stores the value to be stored in this.beta
     * @param alpha
     *  Stores the value to be stored in this.alpha
     * @param q0
     *  Stores the value to be stored in this.q0
     * @param t0
     *  Stores the value to be stored in this.t0
     * @param filePath
     *  Stores the value to be stored in this.filePath
     * @param numberOfAnts
     *  Stores the value to be stored in this.numberOfAnts
     */
    public ACOEngine(int beta, double alpha, double q0, double t0, String filePath,int numberOfAnts){
        listOfAnts = new ArrayList<>();//allocate memory
        this.beta = beta;
        this.alpha = alpha;
        this.q0 = q0;
        graph = new CompleteWeightedPlanarGraph(filePath); //create a new graph instance based on filepath parameter
        this.t0 = t0;
        this.numberOfAnts = numberOfAnts;
        pheromoneMatrix = new double[graph.getVertices().size()][graph.getVertices().size()]; //allocate memory
        for(int i=0;i<graph.getVertices().size();i++){ // initialize pheromone matrix
            for(int j=0;j<graph.getVertices().size();j++){
                pheromoneMatrix[i][j] = 0.00005;
            }
        }
    }

    /**
     * This method is used to create ants in the algorithm. Each ant created starts on any random city in the graph
     */
    private void createAnts(){
        Random randomNumberGenerator = new Random(System.currentTimeMillis()); //random number generator instance
        for(int i =0;i<numberOfAnts;i++){ // create this.numberOfAnts ants on a random city and add the ant to the list of ants
            listOfAnts.add(new Ant(randomNumberGenerator.nextInt(graph.getVertices().size())));
        }
    }

    /**
     * This method is used to determine the next city the ant must travel to based on the formula presented in the
     * Dorigo paper
     * @param ant
     *  Stores the ant that will consider the next move
     * @return
     *  The id of the city to be visited next
     */
    private int determineNextCity(Ant ant){
        Random randomNumberGenerator = new Random(System.currentTimeMillis()); // the random number generator instance
        double q = randomNumberGenerator.nextDouble(); // get random number
        if(q<=q0){ // if random number less than or equal to q0 consider first part of the formula
            return probabilisticFormulaPart1(ant);
        }else{ // if random number greater than q0 consider second part of the formula
            return probabilisticFormulaPart2(ant);
        }
    }

    /**
     * This method encodes the first part of the formula presented in the dorigo paper
     * @param ant
     *  Stores the ant to move to the next city
     * @return
     *  The id of the next city to move to.
     */
    private int probabilisticFormulaPart1(Ant ant){
        HashSet<Integer> visitedCities = ant.getVisitedCities(); //stores the ant's currently visited cities
        int currentCityId = ant.getCurrentCityId(); //stores the city id the ant is currently in
        double max_value = Double.MIN_VALUE; // stores the maximum computed value
        int chosenCityId = -1; //stores the id of the next city to travel to
        for(int i=0;i< graph.getVertices().size();i++){ //for each vertex
            if(visitedCities.contains(i)){ // if vertex is already visited skip it
                continue;
            }
            //otherwise compute a value as shown in the formula
            double computedValue = pheromoneMatrix[currentCityId][i]*Math.pow(1/(graph.getDistanceMatrix()[currentCityId][i]),beta);
            if(computedValue >= max_value){ // if computed value is bigger than maximum value store that city's details
                                            // since it has the best value in terms of closeness + pheromone strength
                max_value = computedValue;
                chosenCityId = i;
            }
        }
        return chosenCityId; //return chosen city
    }

    /**
     * This method is used to encode the second part of the formula whenever q>qo . This is based on a random variable,
     * thus a form of roulette wheel selection was implemented in this function. This part of the formula is used to
     * explore new paths in the graph so that local minimum are avoided
     * @param ant
     *  Stores the ant to be visited
     * @return
     *  The id of the chosen city.
     */
    private int probabilisticFormulaPart2(Ant ant){
        Random randomNumberGenerator = new Random(System.currentTimeMillis()); //the random number generator instance
        double randomNumber = randomNumberGenerator.nextDouble();// get a random number between 0 and 1
        double totalProbability = 0.0;
        for(int i=0;i<graph.getVertices().size();i++){
            totalProbability += probabilisticDistribution(ant,i); // get the probability of that vertex in the distribution
            if(totalProbability > randomNumber){ // if the threshold is exceeded the city that exceed the threshold is chosen.
                return i;
            }
        }
        return -1; //on error
    }

    /**
     * This function is used to encode the probability distribution of each vertex in the second part of the formula.
     * @param ant
     *  Stores the ant who's next move is to be determined
     * @param nextCityId
     *  Stores the ID of the vertex whose probability is to be computed
     * @return
     *  The probability in the distribution of the city stored in the parameter nextCityId
     */
    private double probabilisticDistribution(Ant ant,int nextCityId){
        int currentCityId = ant.getCurrentCityId(); //stores the city id of where is the ant currently
        HashSet<Integer> visitedCities = ant.getVisitedCities(); // stores the set of visited cities by the ant
        double denominator = 0.0; // stores the denominator in the probability
        if(visitedCities.contains(nextCityId)){ // if city has already been visited it has probability 0 to be visited again
            return 0.0;
        }else{
            for(int i=0;i<graph.getVertices().size();i++) {
                if (visitedCities.contains(i)) { // if city has already been visited it does not contribute to the denominator of the probability
                    continue;
                }
                denominator += pheromoneMatrix[currentCityId][i] * Math.pow(1 /
                        (graph.getDistanceMatrix()[currentCityId][i]), beta); //calculate denominator
            }
            return (pheromoneMatrix[currentCityId][nextCityId] * Math.pow(1 /
                    (graph.getDistanceMatrix()[currentCityId][nextCityId]), beta)) / denominator; //return value according to formula
        }
    }

    /**
     * This function is used to modify the pheromone level locally between edges as suggested in the Dorigo paper .
     * Note that since in this problem there is no multi-edges , 2 locations in the pheromone matrix must be updated.
     * Local pheromone update is important because it prevents ants travelling the same path in the same time step, thus
     * promoting more exploration in the graph.
     * @param cityId1
     *  Stores the id of one of the vertices incident to the edge
     * @param cityId2
     *  Stores the id of one of the vertices incident to the edge
     */
    private void localTrailUpdating(int cityId1 , int cityId2){
        pheromoneMatrix[cityId1][cityId2] = ((1-alpha)*pheromoneMatrix[cityId1][cityId2]) + (alpha*t0);
        pheromoneMatrix[cityId2][cityId1] = ((1-alpha)*pheromoneMatrix[cityId2][cityId1]) + (alpha*t0);
    }

    /**
     * This method is used to globally update pheromone between 2 cities . This is carried out as suggested in the
     * Dorigo paper and again the pheromone matrix must be updated in 2 locations. Note that global pheromone updating
     * is important to award edges belonging to the shortest tour in the same iteration with more pheromone.
     * @param cityId1
     *  Stores the id of one of the vertices incident to the edge
     * @param cityId2
     *  Stores the id of one of the vertices incident to the edge
     * @param tourLength
     *  Stores the length of the best tour in an iteration.
     */
    private void globalTrailUpdating(int cityId1, int cityId2, double tourLength){
        pheromoneMatrix[cityId1][cityId2] = ((1-alpha)*pheromoneMatrix[cityId1][cityId2]) + (alpha*(1/tourLength));
        pheromoneMatrix[cityId2][cityId1] = ((1-alpha)*pheromoneMatrix[cityId2][cityId1]) + (alpha*(1/tourLength));
    }

    /**
     * This method is used to start the algorithm
     * @return
     *  The approximation value of the problem.
     */
    public double approximateTsp(){
        double routeLength = Double.MAX_VALUE; //stores the result
        int numberOfCities = graph.getVertices().size(); // stores the number of cities in the graph
        double [][] distanceMatrix = graph.getDistanceMatrix(); //stores the distance matrix of the graph in question
        for(int i=0;i<100000;i++){ // for a number of iterations
            createAnts(); //create new ants at each iteration for more efficiency (thus not need to clear list taking o(n)time)
            while(!listOfAnts.get(listOfAnts.size()-1).antCompletedTour(numberOfCities)){ //until last ant completed tour(i.e all of them complete)
                for(int j=0;j<numberOfAnts;j++){ //for each ant in the list
                    Ant ant = listOfAnts.get(j); // get an ant
                    int nextCityId = determineNextCity(ant); // determine the next city to be moved to
                    localTrailUpdating(ant.getCurrentCityId(),nextCityId);// update pheromone locally
                    ant.moveToCity(nextCityId,distanceMatrix); // move the ant to the new city
                }
            }
            double shortestTour = Double.MAX_VALUE; //stores the length of the shortest tour in the current iteration
            int bestAntIndex = -1; //stores the index of the best ant that performed the shortest tour in the iteration
            for(int j=0;j<numberOfAnts;j++){ //for each ant
                Ant ant = listOfAnts.get(j); //get ant
                localTrailUpdating(ant.getCurrentCityId(),ant.getStartingCityId()); //locally update pheromone from last city to first city
                if(ant.getRouteLength()<shortestTour){ // if route length is shorter than current shortest store ant details
                    shortestTour = ant.getRouteLength();
                    bestAntIndex = j;
                }
            }
            //ToDo change it to global due to convergence
            Ant ant = listOfAnts.get(bestAntIndex); //get ant that produced the best tour in the current iteration
            ArrayList<Integer> bestRoute = ant.getVisitedCitiesOrdered(); //get route of the best ant
            for(int j=0;j<bestRoute.size()-1;j++){ // globally update pheromone on the edges belonging to best ant
                globalTrailUpdating(bestRoute.get(j),bestRoute.get(j+1),ant.getRouteLength());
            }
            globalTrailUpdating(bestRoute.get(bestRoute.size()-1),bestRoute.get(0),ant.getRouteLength()); //globally update pheromone from last ant to first
            if(ant.getRouteLength() < routeLength){ // if length of route produced by the best ant in the iteration is better than previous best store it
                routeLength = ant.getRouteLength();
            }
            System.out.println("Iteration "+i+" Best tour length = "+routeLength); //output to screen
            listOfAnts = new ArrayList<>(); // create new list to avoid clearing it thus taking o(n) time
        }
        return routeLength; //return result.
    }

}
