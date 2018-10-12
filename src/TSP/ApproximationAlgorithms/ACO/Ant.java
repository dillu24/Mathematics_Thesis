package TSP.ApproximationAlgorithms.ACO;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Dylan Galea on 08/10/2018.
 */
public class Ant {
    private HashSet<Integer> visitedCities;
    private ArrayList<Integer> visitedCitiesOrdered; //due to global updating
    private int currentCityId;
    private int startingCityId;
    private double routeLength;

    public Ant(){
        visitedCities = new HashSet<>();
        visitedCitiesOrdered = new ArrayList<>();
        currentCityId = -1;
        startingCityId = -1;
        routeLength = 0.00;
    }

    public Ant(int startingCityId){
        visitedCities = new HashSet<>();
        visitedCitiesOrdered = new ArrayList<>();
        this.startingCityId = startingCityId;
        currentCityId = startingCityId;
        visitedCities.add(currentCityId);
        visitedCitiesOrdered.add(currentCityId);
    }

    public HashSet<Integer> getVisitedCities(){
        return visitedCities;
    }

    public ArrayList<Integer> getVisitedCitiesOrdered(){ return visitedCitiesOrdered; }

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

    public void moveToCity(int nextCityId,double[][] distanceMatrix){
        routeLength -= distanceMatrix[currentCityId][startingCityId]; // to remove returning back since uncomplete
        visitedCities.add(nextCityId);
        visitedCitiesOrdered.add(nextCityId);
        routeLength += distanceMatrix[currentCityId][nextCityId];
        currentCityId = nextCityId;
        routeLength += distanceMatrix[currentCityId][startingCityId]; // return back for now to complete tour
    }

    public boolean antCompletedTour(int numberOfCities){
        return numberOfCities == visitedCities.size();
    }
}
