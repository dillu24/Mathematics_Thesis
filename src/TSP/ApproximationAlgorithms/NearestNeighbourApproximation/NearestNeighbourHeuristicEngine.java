package TSP.ApproximationAlgorithms.NearestNeighbourApproximation;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;

import java.util.HashSet;
import java.util.Random;

/**
 * Created by Dylan Galea on 08/10/2018.
 */
public class NearestNeighbourHeuristicEngine {
    private Graph g;

    public NearestNeighbourHeuristicEngine(){
        g = new CompleteWeightedPlanarGraph();
    }

    public NearestNeighbourHeuristicEngine(Graph g){
        this.g = g;
    }

    public void setGraph(Graph g){
        this.g = g;
    }

    public Graph getGraph(){
        return g;
    }

    public double ApproximateTsp(){
        Random randomNumberGenerator = new Random(System.currentTimeMillis());
        int numberOfCities = g.getVertices().size();
        int startingCityId = randomNumberGenerator.nextInt(numberOfCities);
        double tourLength = 0.0;
        HashSet<Integer> visitedCities = new HashSet<>();
        double[][] distanceMatrix = g.getDistanceMatrix();
        visitedCities.add(startingCityId);
        int currentCityId = startingCityId;
        while(numberOfCities != visitedCities.size()){
            int chosenCityId = closestCity(currentCityId,distanceMatrix,numberOfCities,visitedCities);
            visitedCities.add(chosenCityId);
            tourLength += distanceMatrix[currentCityId][chosenCityId];
            currentCityId = chosenCityId;
        }
        tourLength += distanceMatrix[currentCityId][startingCityId];
        return tourLength;
    }

    private int closestCity(int currentCity , double[][] distanceMatrix,int numberOfCities,HashSet<Integer> visitedCities){
        double smallestDistance = Double.MAX_VALUE;
        int chosenCityId = -1;
        for(int i=0;i<numberOfCities;i++){
            if(visitedCities.contains(i)){
                continue;
            }
            if(distanceMatrix[currentCity][i] <= smallestDistance){
                smallestDistance = distanceMatrix[currentCity][i];
                chosenCityId = i;
            }
        }
        return chosenCityId;
    }
}
