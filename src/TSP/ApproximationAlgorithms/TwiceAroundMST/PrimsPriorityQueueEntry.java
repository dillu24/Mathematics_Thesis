package TSP.ApproximationAlgorithms.TwiceAroundMST;

public class PrimsPriorityQueueEntry implements Comparable<PrimsPriorityQueueEntry>{
    /**
     * This class represents an entry in the priority queue that is used in Prim's algorithm . A normal priority queue
     * could not be used since more than 1 field is needed to be hold in the priority queue.
     */
    private int cityID; //stores the city id
    private Double minimumWeightConnectedEdge; //stores the key value in the priority queue
    private int nearestCityId; // stores the id of the city that gave the minimum key so far

    /**
     * This constructor is used to create a new entry for a particular city given it's id.
     * @param cityID
     */
    public PrimsPriorityQueueEntry(int cityID){
        this.cityID = cityID;
        minimumWeightConnectedEdge = Double.MAX_VALUE; //set all entry keys to infinity in the beginning
        nearestCityId = -1; //default value
    }

    /**
     * This method is used to return the value in this.cityID
     * @return
     *  The value stored in this.cityID
     */
    public int getCityId(){
        return cityID;
    }

    /**
     * This method is used to return the value stored in this.minimumWeightConnectedEdge
     * @return
     *  The value stored in this.minimumWeightConnectedEdge
     */
    public double getMinimumWeightConnectedEdge(){
        return minimumWeightConnectedEdge;
    }

    /**
     * This method is used to return the value stored in this.nearestCityId
     * @return
     *  The value stored in this.nearestCityId
     */
    public int getNearestCityId(){
        return nearestCityId;
    }

    /**
     * This method is used to update an entry in the priority queue whenever in the algorithm the values need to be
     * modified
     * @param minimumWeightConnectedEdge
     *  Stores the value to be stored in this.minimumWeightConnectedEdge
     * @param nearestCityId
     *  Stores the value to be stored in this.nearestCityId
     */
    public void updateEntry(double minimumWeightConnectedEdge , int nearestCityId){
        this.minimumWeightConnectedEdge = minimumWeightConnectedEdge;
        this.nearestCityId = nearestCityId;
    }

    /**
     * This method is used to define how to compare the different entries in the priority queue since we have a min-
     * queue. Thus as it can be seen in the implementation the Double's compartor is used by comparing the values stored
     * in this.minimumWeightConnectedEdge . In this way the shortest edge considered so far in Prim's algorithm would
     * be at the top of the queue.
     * @param entry
     *  Stores the entry to be compared
     * @return
     *  1 if value in current class is greater than entry.getMinimumWeightConnectedEdge
     *  0 otherwise
     */
    @Override
    public int compareTo(PrimsPriorityQueueEntry entry) {
        return Double.compare(minimumWeightConnectedEdge,entry.getMinimumWeightConnectedEdge());
    }
}
