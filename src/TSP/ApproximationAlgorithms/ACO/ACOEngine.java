//To make it faster considered complete graph .. if graph if not complete do very large distances to avoid .. loops
//avoided because the ant will add the vertex to the list of vertices hence it will not consider it.
package TSP.ApproximationAlgorithms.ACO;

import TSP.ApproximationAlgorithms.NearestNeighbourApproximation.NearestNeighbourHeuristicEngine;
import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by Dylan Galea on 08/10/2018.
 */
public class ACOEngine {
    private ArrayList<Ant> listOfAnts;
    private int beta;
    private double alpha;
    private double q0;
    private double t0;
    private double[][] pheromoneMatrix;
    private Graph graph;
    private int numberOfAnts;

    public ACOEngine(){
        listOfAnts = new ArrayList<>();
        beta = 2;
        alpha = 0.1;
        q0 = 0.9;
        numberOfAnts = 10;
        graph = new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/berlin52");
        NearestNeighbourHeuristicEngine nnh = new NearestNeighbourHeuristicEngine(graph);
        t0 = 1/(graph.getVertices().size()* nnh.ApproximateTsp());
        pheromoneMatrix = new double[graph.getVertices().size()][graph.getVertices().size()];
        for(int i=0;i<graph.getVertices().size();i++){
            for(int j=0;j<graph.getVertices().size();j++){
                pheromoneMatrix[i][j] = 0.00005;
            }
        }
    }

    public ACOEngine(int beta, double alpha, double q0, double t0, String filePath,int numberOfAnts){
        listOfAnts = new ArrayList<>();
        this.beta = beta;
        this.alpha = alpha;
        this.q0 = q0;
        graph = new CompleteWeightedPlanarGraph(filePath);
        this.t0 = t0;
        this.numberOfAnts = numberOfAnts;
        pheromoneMatrix = new double[graph.getVertices().size()][graph.getVertices().size()];
        for(int i=0;i<graph.getVertices().size();i++){
            for(int j=0;j<graph.getVertices().size();j++){
                pheromoneMatrix[i][j] = 0.00005;
            }
        }
    }

    private void createAnts(){
        Random randomNumberGenerator = new Random(System.currentTimeMillis());
        for(int i =0;i<numberOfAnts;i++){
            listOfAnts.add(new Ant(randomNumberGenerator.nextInt(graph.getVertices().size())));
        }
    }

    private int determineNextCity(Ant ant){
        Random randomNumberGenerator = new Random(System.currentTimeMillis());
        double q = randomNumberGenerator.nextDouble();
        if(q<=q0){
            return probabilisticFormulaPart1(ant);
        }else{
            return probabilisticFormulaPart2(ant);
        }
    }

    private int probabilisticFormulaPart1(Ant ant){
        HashSet<Integer> visitedCities = ant.getVisitedCities();
        int currentCityId = ant.getCurrentCityId();
        double max_value = Double.MIN_VALUE;
        int chosenCityId = -1;
        for(int i=0;i< graph.getVertices().size();i++){
            if(visitedCities.contains(i)){
                continue;
            }
            double computedValue = pheromoneMatrix[currentCityId][i]*Math.pow(1/(graph.getDistanceMatrix()[currentCityId][i]),beta);
            if(computedValue >= max_value){
                max_value = computedValue;
                chosenCityId = i;
            }
        }
        return chosenCityId;
    }

    private int probabilisticFormulaPart2(Ant ant){
        Random randomNumberGenerator = new Random(System.currentTimeMillis());
        double randomNumber = randomNumberGenerator.nextDouble();
        double totalProbability = 0.0;
        for(int i=0;i<graph.getVertices().size();i++){
            totalProbability += probabilisticDistribution(ant,i);
            if(totalProbability > randomNumber){
                return i;
            }
        }
        return -1; //on error
    }

    private double probabilisticDistribution(Ant ant,int nextCityId){
        int currentCityId = ant.getCurrentCityId();
        HashSet<Integer> visitedCities = ant.getVisitedCities();
        double denominator = 0.0;
        if(visitedCities.contains(nextCityId)){
            return 0.0;
        }else{
            for(int i=0;i<graph.getVertices().size();i++) {
                if (visitedCities.contains(i)) {
                    continue;
                }
                denominator += pheromoneMatrix[currentCityId][i] * Math.pow(1 /
                        (graph.getDistanceMatrix()[currentCityId][i]), beta);
            }
            return (pheromoneMatrix[currentCityId][nextCityId] * Math.pow(1 /
                    (graph.getDistanceMatrix()[currentCityId][nextCityId]), beta)) / denominator;
        }
    }

    private void localTrailUpdating(int cityId1 , int cityId2){
        pheromoneMatrix[cityId1][cityId2] = ((1-alpha)*pheromoneMatrix[cityId1][cityId2]) + (alpha*t0);
        pheromoneMatrix[cityId2][cityId1] = ((1-alpha)*pheromoneMatrix[cityId2][cityId1]) + (alpha*t0);
    }

    private void globalTrailUpdating(int cityId1, int cityId2, double tourLength){
        pheromoneMatrix[cityId1][cityId2] = ((1-alpha)*pheromoneMatrix[cityId1][cityId2]) + (alpha*(1/tourLength));
        pheromoneMatrix[cityId2][cityId1] = ((1-alpha)*pheromoneMatrix[cityId2][cityId1]) + (alpha*(1/tourLength));
    }

    public double approximateTsp(){
        double routeLength = Double.MAX_VALUE;
        int numberOfCities = graph.getVertices().size();
        double [][] distanceMatrix = graph.getDistanceMatrix();
        for(int i=0;i<1000000;i++){
            createAnts();
            while(!listOfAnts.get(listOfAnts.size()-1).antCompletedTour(numberOfCities)){
                for(int j=0;j<numberOfAnts;j++){
                    Ant ant = listOfAnts.get(j);
                    int nextCityId = determineNextCity(ant);
                    localTrailUpdating(ant.getCurrentCityId(),nextCityId);
                    ant.moveToCity(nextCityId,distanceMatrix);
                }
            }
            double shortestTour = Double.MAX_VALUE;
            int bestAntIndex = -1;
            for(int j=0;j<numberOfAnts;j++){ //To update pheromone back also .. nahseb trid tamila nkella bla sens ..also ccekja lim lahjar tour
                Ant ant = listOfAnts.get(j);
                localTrailUpdating(ant.getCurrentCityId(),ant.getStartingCityId());
                if(ant.getRouteLength()<shortestTour){
                    shortestTour = ant.getRouteLength();
                    bestAntIndex = j;
                }
            }
            Ant ant = listOfAnts.get(bestAntIndex);
            ArrayList<Integer> bestRoute = ant.getVisitedCitiesOrdered();
            for(int j=0;j<bestRoute.size()-1;j++){
                globalTrailUpdating(bestRoute.get(j),bestRoute.get(j+1),ant.getRouteLength());
            }
            globalTrailUpdating(bestRoute.get(bestRoute.size()-1),bestRoute.get(0),ant.getRouteLength());
            if(ant.getRouteLength() < routeLength){
                routeLength = ant.getRouteLength();
            }
            System.out.println("Generation "+i+" Best tour length = "+routeLength);
            listOfAnts = new ArrayList<>();
        }
        return routeLength;
    }

}
