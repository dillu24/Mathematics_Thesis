package TSP.ApproximationAlgorithms.TwiceAroundMST;

/**
 * Created by Dylan Galea on 15/10/2018.
 */
public class PrimsPriorityQueueEntry implements Comparable<PrimsPriorityQueueEntry>{
    private int cityID;
    private Double minimumWeightConnectedEdge;
    private int nearestCityId;

    public PrimsPriorityQueueEntry(int cityID){
        this.cityID = cityID;
        minimumWeightConnectedEdge = Double.MAX_VALUE;
        nearestCityId = -1;
    }

    public int getCityId(){
        return cityID;
    }

    public double getMinimumWeightConnectedEdge(){
        return minimumWeightConnectedEdge;
    }

    public int getNearestCityId(){
        return nearestCityId;
    }

    public void updateEntry(double minimumWeightConnectedEdge , int nearestCityId){
        this.minimumWeightConnectedEdge = minimumWeightConnectedEdge;
        this.nearestCityId = nearestCityId;
    }

    @Override
    public int compareTo(PrimsPriorityQueueEntry entry) {
        return minimumWeightConnectedEdge.compareTo(entry.getMinimumWeightConnectedEdge());
    }
}
