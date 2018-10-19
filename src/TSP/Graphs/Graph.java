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

    public void setDistanceMatrix(double distanceMatrix[][]){
        this.distanceMatrix = distanceMatrix;
    }

    public void setVertices(ArrayList<City> vertices){
        this.vertices = vertices;
    }

    public abstract void initializeGraph(String filepath);
}
