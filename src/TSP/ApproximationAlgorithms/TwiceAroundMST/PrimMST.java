package TSP.ApproximationAlgorithms.TwiceAroundMST;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;
import java.util.PriorityQueue;
import java.util.Random;
//Was made to work even on non complete graphs.

public class PrimMST {
    /**
     * This class encodes Prim's algorithm for finding a minimum spanning tree . This algorithm was made to work even
     * for incomplete graphs where a value different from 0 or infinity means that there is an edge between 2 vertices.
     * This was carried out in this way in order to cater for different options for different use of this algorithm.
     * Note that in this algorithm the adjacency matrix of the calculated MST is outputted as result since this is going
     * to be needed in the 2*MST heuristic approximation algorithm.
     */
   private Graph g; //This stores the graph to be considered
   private PriorityQueue<PrimsPriorityQueueEntry> priorityQueue; //this stores the priority queue
   private boolean[] verticesInMst; // this boolean array has value 1 aat index i if i has already been included in the MST , and 0 otherwise
   private PrimsPriorityQueueEntry[] allEntries; // this is used to store the current values in the entries because there is no way how to update directly the entries in the priority queue

    /**
     * This default constructor is used to initialize the fields of the mst to default values and allocate memory to
     */
   public PrimMST(){
       g = new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/berlin52"); // initialize to some graph instance
       priorityQueue = new PriorityQueue<>(g.getVertices().size()); // allocate memory
       verticesInMst = new boolean[g.getVertices().size()]; // allocate memory
       allEntries = new PrimsPriorityQueueEntry[g.getVertices().size()]; // allocate memory
   }

    /**
     * This constructor is used to create an instance of Prim's algorithm whose calculation of the MST is based on the
     * parameter value g
     * @param g
     *  Stores the graph to be stored in this.graph .. i.e the graph to be considered
     */
   public PrimMST(Graph g){
       this.g = g;
       priorityQueue = new PriorityQueue<>(g.getVertices().size()); // allocate memory
       verticesInMst = new boolean[g.getVertices().size()]; // allocate memory
       allEntries = new PrimsPriorityQueueEntry[g.getVertices().size()]; // allocate memory
   }

    /**
     * This method is used to calculate the MST based on Prim's algorithm . It returns the adjacency matrix of the
     * resultant tree
     * @return
     *  The adjacency matrix of the resultant tree
     */
   public int[][] calculateMinimumWeightSpanningTree(int startingVertex){
       int [][] MSTAdjacencyMatrix = new int[g.getVertices().size()][g.getVertices().size()]; //stores the resultant adjacency matrix of the tree
       double [][] distanceMatrix = g.getDistanceMatrix(); //stores the distance matrix of the graph
       PrimsPriorityQueueEntry firstEntry = new PrimsPriorityQueueEntry(startingVertex); // choose a random vertex for the graph and create an entry for it
       firstEntry.updateEntry(0.0,-1);//need to update first entry because it's edge value must be 0
       allEntries[firstEntry.getCityId()] = firstEntry; //modify first entry's entry in the list of entries
       priorityQueue.add(firstEntry); // add the first entry to the priority queue
       for(int i=0;i<g.getVertices().size();i++){ //create entries for all remaining vertices and add them to the priority queue
           if(firstEntry.getCityId() !=i){
               allEntries[i] = new PrimsPriorityQueueEntry(i);
               priorityQueue.add(allEntries[i]);
           }
       }
       while(!priorityQueue.isEmpty()){ //until priority queue is empty
           PrimsPriorityQueueEntry minEntry = priorityQueue.poll(); //remove the vertex having smallest key value
           if(minEntry.getNearestCityId() != -1){ // if this is not the first entry modify the adjacency matrix such that an edge is created between the minimum edge city and it's parent
               MSTAdjacencyMatrix[minEntry.getCityId()][minEntry.getNearestCityId()] = 1;
               MSTAdjacencyMatrix[minEntry.getNearestCityId()][minEntry.getCityId()] = 1;
           }
           verticesInMst[minEntry.getCityId()] = true; // indicate that the vertex has been added to the mst
           for(int i=0;i<g.getVertices().size();i++){ //for all vertices
               if(distanceMatrix[minEntry.getCityId()][i] != 0.0 && distanceMatrix[minEntry.getCityId()][i] != Double.MAX_VALUE
                       && !verticesInMst[i]){ // if vertex is part of an edge which is not a loop and not in the mst
                   if(allEntries[i].getMinimumWeightConnectedEdge() > distanceMatrix[minEntry.getCityId()][i]){ //if distance of considered edge is smaller than key
                       priorityQueue.remove(allEntries[i]); //remove the entry from priority queue
                       allEntries[i].updateEntry(distanceMatrix[minEntry.getCityId()][i],minEntry.getCityId()); //update it to new improved entry
                       priorityQueue.add(allEntries[i]); //add new entry back to the queue
                   }
               }
           }
       }
       return MSTAdjacencyMatrix; //return adjacency matrix ofmst
   }
}
