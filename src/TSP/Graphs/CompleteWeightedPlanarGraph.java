package TSP.Graphs;

import TSP.City;
import java.io.*;
import java.util.Objects;

public class CompleteWeightedPlanarGraph extends Graph {
    /**
     * This class is used to encode a complete weighted planar graph . Note that in this project only complete graphs
     * were considered since any incomplete graph can be converted to a complete graph by setting missing edges with
     * infinite weights . Note by doing so , the theory that is represented in the write up will not be effected , nor
     * the algorithms. With regards to distance between points , TSPLIB provides instances were the co ordinates are in
     * euclidean space . Thus euclidean distance is defined . This will again enable the use of the 2*MST heuristic
     * since this can only be used whenever the triangle inequality holds. This class inherits the class graph hence
     * it contains the protected/public methods/fields.
     */

    /**
     *This is the default constructor and it just calls the default constructor of the parent
     */
    public CompleteWeightedPlanarGraph(){
        super();
    }

    /**
     * This is the non-default constructor and it just calls the non-default constructor of the parent
     * @param filePath
     *  Stores the TSPLIB instance file path to initialize the graph.
     */
    public CompleteWeightedPlanarGraph(String filePath){
        super(filePath);
    }

    /**
     * This function is used to initialize a complete weighted planar graph based on the TSPLIB instance who's
     * path is passed as parameter . Note that for this graph the distance matrix is filled as follows:
     * distance[i,j] = {0 if i==j , euclideanDistance[i,j] if i=/=j}.
     * @param filepath
     *  Stores the path of the TSPLIB instance to be used to initialize the graph
     */
    @Override
    public void initializeGraph(String filepath) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath)); //input file to reader
            String line = reader.readLine(); //read first line
            while(!Objects.equals(line, "NODE_COORD_SECTION")){ // Skip all lines until getting the line NODE_COORD_SECTION
                line = reader.readLine();                          // NOTE : ALL TSPLIB instances have this line
            }
            line = reader.readLine(); // Read next line to get first line of co ordinates
            while(!Objects.equals(line, "EOF")){ //until meeting the end of file that signals no more co ordinates
                String[] cityData = line.split("\\s+"); //split line into id,x co ordinate ,y co ordinate
                vertices.add(new City(Integer.parseInt(cityData[0])-1,Double.parseDouble(cityData[1]),
                        Double.parseDouble(cityData[2]))); // create new city and add it to the list of vertices
                line = reader.readLine(); //read next line
            }
            reader.close();//close the reader since reading is ready
            distanceMatrix = new double[vertices.size()][vertices.size()]; //give memory to the distance matrix
            for(int i =0;i<vertices.size();i++){ //initialize all entries according to euclidean distance between cities
                for(int j=0;j<vertices.size();j++){
                    distanceMatrix[i][j] = getEuclideanDistance(vertices.get(i),vertices.get(j));
                }
            }
        }catch (IOException e) { //if file is not found handle the error exception by displaying that file is not found
            System.out.println("File not found");
            System.exit(-1);
        }
    }

    /**
     * This method is used to compute the euclidean distance between 2 cities so that the distance matrix can be filled
     * @param city1
     *  Stores the first city
     * @param city2
     *  Stores the second city
     * @return
     *  The euclidean distance between the 2 cities passed as parameters
     */
    public double getEuclideanDistance(City city1,City city2){
        return Math.sqrt(Math.pow(city1.getX()-city2.getX(),2)+Math.pow(city1.getY()-city2.getY(),2));
    }

}
