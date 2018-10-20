package TSP.Graphs;

import TSP.City;
import java.util.ArrayList;

public abstract class Graph {
    /**
     * This class is used to encode a graph . In this project a graph is taken to be abstract where all the common
     * fields and methods are presented in this class . The abstraction is then to be implemented by the respective
     * instances by implemented how the graph initializes it's edges . For example Kn has different edges than B(n,k).
     * Thus although in this project only complete graphs are considered due to the fact that we must be certain that
     * the graph contains a hamiltonian path , the possibility to add more graphs is given. Note that a distance matrix
     * is used to represent the graph since weighted graphs can be used . This enables different graphs to be represented
     * since the adjacency matrix restrictsthe entries to be either 1 or 0 , thus is not able to store distances
     */
    protected ArrayList<City> vertices; //Stores the vertices in the graph (which are the cities)
    protected double[][] distanceMatrix; //Stores the distance matrix

    /**
     * This is the default constructor and is used to assign memory to the list of vertices. Note that the distance
     * matrix was not given memory since the number of vertices are still not known
     */
    public Graph(){
        vertices = new ArrayList<City>();
    }

    /**
     * This constructor is used to create a graph given the filepath of the TSPLIB instance file.
     * @param filepath
     *  Stores the value to be stored in this.filepath
     */
    public Graph(String filepath){
        vertices = new ArrayList<City>(); //give memory
        initializeGraph(filepath); //initializes the graph based on the file instance
    }

    /**
     * This method is used to return the list of vertices which has private access.
     * @return
     *  The list of vertices
     */
    public ArrayList<City> getVertices(){
        return vertices;
    }

    /**
     * This method is used to return the distance matrix which was set to have private access
     * @return
     *  The distance matrix
     */
    public double[][] getDistanceMatrix(){
        return distanceMatrix;
    }

    /**
     * This method is used to set the distance matrix to some particular 2D double list . Note that this was done for
     * testing purposes so that different matrices could be used.
     * @param distanceMatrix
     *  Stores the matrix to be stored in this.distanceMatrix
     */
    public void setDistanceMatrix(double distanceMatrix[][]){
        this.distanceMatrix = distanceMatrix;
    }

    /**
     * This method is used to set the list of vertices to some particular list. Note that this was done for testing
     * purposes so that different lists could be used
     * @param vertices
     *  Stores the list of vertices to be stored in this.vertices
     */
    public void setVertices(ArrayList<City> vertices){
        this.vertices = vertices;
    }

    /**
     * This is the abstract method that is used to initialize a graph based on a TSPLIB instance . Note that if in some
     * graphs it is required not to use a filepath, it could be set to null . This was done abstract since different
     * graphs need different forms of initializations.
     * @param filepath
     *  Stores the TSPLIB instance file to be inputted in order to create the graph.
     */
    public abstract void initializeGraph(String filepath);
}
