package TSP.ApproximationAlgorithms.ACO;

import java.util.ArrayList;
import java.util.HashSet;

public class Ant {
    /**
     * This class encodes the artificial ant described in the Dorigo ant system paper that is used to traverse the graph
     * created using a TSPLIB instance. Each ant has a number of fields which were set to private so that no one can
     * access or modify directly the fields (thus having encapsulation). Each ant has a set of visited cities and a list
     * of visited cities . 2 were needed so that the algorithm is more efficient since in the algorithm we need to check
     * if an ant has already moved to a particular city . In this case the set is used because it only takes O(1) time
     * to do so . However whenever we want the order how the cities were visited , the array list is used for efficiency.
     * Each ant also contains the id of the city it is currently in , the idof the city it started in and the route
     * length of the traverse route so far .
     */
    private HashSet<Integer> visitedCities;
    private ArrayList<Integer> visitedCitiesOrdered;
    private int currentCityId;
    private int startingCityId;
    private double routeLength;

    /**
     * This default constructor is used to give default values to the newly created ant whenever no parameters are given
     */
    public Ant(){
        visitedCities = new HashSet<>();
        visitedCitiesOrdered = new ArrayList<>();
        currentCityId = -1;
        startingCityId = -1;
        routeLength = 0.00;
    }

    /**
     * This constructor is used to create a new ant on a particular city specifying the city id as parameter
     * @param startingCityId
     *  Stores the id of the city the ant is starting on.
     */
    public Ant(int startingCityId){
        visitedCities = new HashSet<>(); //allocate memory
        visitedCitiesOrdered = new ArrayList<>(); //allocate memory
        this.startingCityId = startingCityId; //update starting city
        currentCityId = startingCityId; // update current city
        visitedCities.add(currentCityId); // add city to the set to mark as visited
        visitedCitiesOrdered.add(currentCityId); // add city to the permutation
    }

    /**
     * This method is used to return the private set of visited cities by the ant
     * @return
     *  The set of cities visited by the ant
     */
    public HashSet<Integer> getVisitedCities(){
        return visitedCities;
    }

    /**
     * This method is used to return the list of visited cities by the ant in order.
     * @return
     *  The list of cities visited by the ant in order
     */
    public ArrayList<Integer> getVisitedCitiesOrdered(){ return visitedCitiesOrdered; }

    /**
     * This method is used to store a value in this.startingCityId
     * @param startingCityId
     *  Stores the value to be stored in this.StartingCityId
     */
    public void setStartingCityId(int startingCityId){
        this.startingCityId = startingCityId;
    }

    /**
     * This method is used to return the value stored in this.startingCityId
     * @return
     *  The value stored in this.startingCityId
     */
    public int getStartingCityId(){
        return startingCityId;
    }

    /**
     * This method is used to update the value stored in this.currentCityId
     * @param currentCityId
     *  Stores the value to be stored in this.currentCityId
     */
    public void setCurrentCityId(int currentCityId){
        this.currentCityId = currentCityId;
    }

    /**
     * This method is used to return the value stored in this.currentCityId
     * @return
     *  The value stored in this.currentCityId
     */
    public int getCurrentCityId(){
        return currentCityId;
    }

    /**
     * This method is used to return the value stored in this.routeLength
     * @return
     *  The value stored in this.routeLength
     */
    public double getRouteLength(){
        return routeLength;
    }

    /**
     * This method is used by the algorithm to move the ant to a new city. Note that the validation of whether the move
     * is valid was done in the class ACOEngine. In this method the values in this.currentCityId,this.startingCityId,
     * this.visitedCities,this.visitedCitiesOrdered,this.routeLength are updated accordingly .
     * @param nextCityId
     *  Stores the id of the city to be visited
     * @param distanceMatrix
     *  Stores the distance matrix of the graph so that the route length can be modifed.
     */
    public void moveToCity(int nextCityId,double[][] distanceMatrix){
        visitedCities.add(nextCityId); // add the new city to be visited to the set of cities visited
        visitedCitiesOrdered.add(nextCityId); // add the new city to be visited to the permutation of cities visited
        routeLength += distanceMatrix[currentCityId][nextCityId]; //modify the distance
        currentCityId = nextCityId; // update current city to the last city that was visited
    }

    /**
     * This method is used in the ACOEngine class to check if the ant has completed the tour
     * @param numberOfCities
     *  Stores the number of cities in the TSP problem
     * @return
     *  True if tour is complete
     *  False otherwise
     */
    public boolean antCompletedTour(int numberOfCities){
        return numberOfCities == visitedCities.size();
    }
}
