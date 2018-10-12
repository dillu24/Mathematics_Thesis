package TSP.Graphs;

import TSP.City;

import java.util.ArrayList;

/**
 * Created by Dylan Galea on 07/10/2018.
 */
public abstract class Graph {
    protected ArrayList<City> vertices;
    protected double[][] distanceMatrix;

    public Graph(){
        vertices = new ArrayList<City>();
        distanceMatrix = new double[0][0];
    }

    public Graph(String filepath){
        vertices = new ArrayList<City>();
        initializeGraph(filepath);
    }

    public ArrayList<City> getVertices(){
        return vertices;
    }

    public double[][] getDistanceMatrix(){
        return distanceMatrix;
    }

    public abstract void initializeGraph(String filepath);
}
