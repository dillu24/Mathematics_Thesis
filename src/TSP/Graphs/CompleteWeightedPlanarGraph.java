package TSP.Graphs;

import TSP.City;

import java.io.*;
import java.util.Objects;

/**
 * Created by Dylan Galea on 07/10/2018.
 */
public class CompleteWeightedPlanarGraph extends Graph {

    public CompleteWeightedPlanarGraph(){
        super();
    }

    public CompleteWeightedPlanarGraph(String filePath){
        super(filePath);
    }

    @Override
    public void initializeGraph(String filepath) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String line = reader.readLine();
            while(!Objects.equals(line, "NODE_COORD_SECTION")){
                line = reader.readLine();
            }
            line = reader.readLine();
            while(!Objects.equals(line, "EOF")){
                String[] cityData = line.split("\\s+");
                vertices.add(new City(Integer.parseInt(cityData[0])-1,Double.parseDouble(cityData[1]),
                        Double.parseDouble(cityData[2])));
                line = reader.readLine();
            }
            reader.close();
            adjacencyMatrix = new double[vertices.size()][vertices.size()];
            for(int i =0;i<vertices.size();i++){
                for(int j=0;j<vertices.size();j++){
                    adjacencyMatrix[i][j] = getEuclideanDistance(vertices.get(i),vertices.get(j));
                }
            }
        }catch (IOException e) {
            System.out.println("File not found");
            System.exit(-1);
        }
    }

    private double getEuclideanDistance(City city1,City city2){
        return Math.sqrt(Math.pow(city1.getX()-city2.getX(),2)+Math.pow(city1.getY()-city2.getY(),2));
    }

}
