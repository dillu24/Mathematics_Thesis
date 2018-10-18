package TSP.ApproximationAlgorithms.TwiceAroundMST;

import TSP.Graphs.CompleteWeightedPlanarGraph;
import TSP.Graphs.Graph;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by Dylan Galea on 15/10/2018.
 */
public class PrimMST {
   private Graph g;
   private PriorityQueue<PrimsPriorityQueueEntry> priorityQueue;
   private boolean[] verticesInMst;
   private PrimsPriorityQueueEntry[] allEntries;

   public PrimMST(){
       g = new CompleteWeightedPlanarGraph("./src/TSP/GraphInstances/berlin52");
       priorityQueue = new PriorityQueue<>(g.getVertices().size());
       verticesInMst = new boolean[g.getVertices().size()];
       allEntries = new PrimsPriorityQueueEntry[g.getVertices().size()];
   }

   public PrimMST(Graph g){
       this.g = g;
       priorityQueue = new PriorityQueue<>(g.getVertices().size());
       verticesInMst = new boolean[g.getVertices().size()];
       allEntries = new PrimsPriorityQueueEntry[g.getVertices().size()];
   }

   public void calculateMinimumWeightSpanningTree(){
       Random randomNumberGenerator = new Random(System.currentTimeMillis());
       double [][] distanceMatrix = g.getDistanceMatrix();
       PrimsPriorityQueueEntry firstEntry = new PrimsPriorityQueueEntry(randomNumberGenerator.nextInt(g.getVertices().size()));
       firstEntry.updateEntry(0.0,-1);
       allEntries[firstEntry.getCityId()] = firstEntry;
       priorityQueue.add(firstEntry);
       for(int i=0;i<g.getVertices().size();i++){
           if(firstEntry.getCityId() !=i){
               allEntries[i] = new PrimsPriorityQueueEntry(i);
               priorityQueue.add(allEntries[i]);
           }
       }
   }
}
