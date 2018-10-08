package TSP.ApproximationAlgorithms.ACO;

import java.util.HashSet;

/**
 * Created by Dylan Galea on 08/10/2018.
 */
public class Ant {
    private HashSet<Integer> visitedCities;
    private int currentCityId;
    private int startingCityId;
    private double routeLength;

    public Ant(){
        visitedCities = new HashSet<>();
        currentCityId = -1;
        startingCityId = -1;
        routeLength = 0.00;
    }

    public Ant(int startingCityId){
        visitedCities = new HashSet<>();
        this.startingCityId = startingCityId;
        currentCityId = startingCityId;
        visitedCities.add(currentCityId);
    }

    public HashSet<Integer> getVisitedCities(){
        return visitedCities;
    }

    public void setStartingCityId(int startingCityId){
        this.startingCityId = startingCityId;
    }

    public int getStartingCityId(){
        return startingCityId;
    }

    public void setCurrentCityId(int currentCityId){
        this.currentCityId = currentCityId;
    }

    public int getCurrentCityId(){
        return currentCityId;
    }

    public double getRouteLength(){
        return routeLength;
    }

    public void moveToCity(int nextCityId,double[][] adjacencyMatrix){
        routeLength -= adjacencyMatrix[currentCityId][startingCityId]; // to remove returning back since uncomplete
        visitedCities.add(nextCityId);
        routeLength += adjacencyMatrix[currentCityId][nextCityId];
        currentCityId = nextCityId;
        routeLength += adjacencyMatrix[currentCityId][startingCityId]; // return back for now to complete tour
    }

    public boolean antCompletedTour(int numberOfCities){
        return numberOfCities == visitedCities.size();
    }
}
