import TSP.ApproximationAlgorithms.TwiceAroundMST.PrimMST;
import TSP.Graphs.TestGraph;

/**
 * Created by Dylan Galea on 07/10/2018.
 */

public class AlgorithmLauncher {
    public static void main (String args[]){
        //ACOEngine aco = new ACOEngine();
        //aco.approximateTsp();
        TestGraph graph = new TestGraph(null);
        PrimMST prim = new PrimMST(graph);
        int [][]adj = prim.calculateMinimumWeightSpanningTree();
        for(int i=0;i<adj.length;i++){
            for(int j=0;j<adj.length;j++){
                System.out.print(adj[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
