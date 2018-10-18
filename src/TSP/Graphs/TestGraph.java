//Test Class for MST
package TSP.Graphs;
import TSP.City;

/**
 * Created by Dylan Galea on 18/10/2018.
 */
public class TestGraph extends Graph {
    public TestGraph(String filepath){
        super(filepath);
    }
    @Override
    public void initializeGraph(String filepath) {
        for(int i=0;i<9;i++){
            vertices.add(new City(i,0,0));
        }
        double distanceMatrix[][] = {{0.0,4.0,8.0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
                {4.0,0.0,11.0,8.0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
                {8.0,11.0,0.0,Double.MAX_VALUE,7.0,1.0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
                {Double.MAX_VALUE,8.0,Double.MAX_VALUE,0.0,2.0,Double.MAX_VALUE,7.0,4.0,Double.MAX_VALUE},
                {Double.MAX_VALUE,Double.MAX_VALUE,7.0,2.0,0.0,6.0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
                {Double.MAX_VALUE,Double.MAX_VALUE,1.0,Double.MAX_VALUE,6.0,0.0,Double.MAX_VALUE,2.0,Double.MAX_VALUE},
                {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,7.0,Double.MAX_VALUE,Double.MAX_VALUE,0.0,14.0,9.0},
                {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,4.0,Double.MAX_VALUE,2.0,14.0,0.0,10.0},
                {Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,9.0,10.0,0.0}};
        setDistanceMatrix(distanceMatrix);
    }
}
