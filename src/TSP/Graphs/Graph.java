package TSP.Graphs;

import TSP.City;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Dylan Galea on 07/10/2018.
 */
public abstract class Graph {
    protected ArrayList<City> vertices;
    protected double[][] adjacencyMatrix;

    public Graph(){
        vertices = new ArrayList<City>();
        adjacencyMatrix = new double[0][0];
    }

    public Graph(String filepath){
        vertices = new ArrayList<City>();
        initializeGraph(filepath);
    }

    public ArrayList<City> getVertices(){
        return vertices;
    }

    public double[][] getAdjacencyMatrix(){
        return adjacencyMatrix;
    }

    public abstract void initializeGraph(String filepath);
}
