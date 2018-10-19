package ACOGUI;
import TSP.ApproximationAlgorithms.ACO.ACOEngine;
import TSP.City;
import TSP.Graphs.CompleteWeightedPlanarGraph;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dylan Galea on 19/10/2018.
 */
public class Interface extends Frame {
    private ACOEngine aco;
    private CompleteWeightedPlanarGraph graph;
    private ArrayList<City> vertices;
    private double[][] distanceMatrix;

    public Interface(){
        graph = new CompleteWeightedPlanarGraph();
        vertices = new ArrayList<>();
        createGraph();

        setSize(1000,500);
        setLayout(null);
        setBackground(Color.lightGray);
        addListeners();
        setVisible(true);
    }

    private void createGraph(){
        Random randomNumberGenerator = new Random(System.currentTimeMillis());
        for(int i=0;i<4;i++){
            vertices.add(new City(i,randomNumberGenerator.nextInt(1000),randomNumberGenerator.nextInt(500)));
        }
        graph.setVertices(vertices);
        distanceMatrix = new double[vertices.size()][vertices.size()];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(i==j){
                    distanceMatrix[i][j] = 0.0;
                }else{
                    distanceMatrix[i][j] = graph.getEuclideanDistance(vertices.get(i),vertices.get(j));
                }
            }
        }
        graph.setDistanceMatrix(distanceMatrix);
    }

    public void paint(Graphics g){
        ArrayList<City> vertices = graph.getVertices();
        for (City currentVertex : vertices) {
            g.fillOval((int) Math.round(currentVertex.getX()), (int) Math.round(currentVertex.getY()), 10, 10);
        }
        for(int i=0;i<vertices.size();i++){
            City firstVertex = vertices.get(i);
            for (City secondVertex : vertices) {
                g.drawLine((int) Math.round(firstVertex.getX()), (int) Math.round(firstVertex.getY())
                        , (int) Math.round(secondVertex.getX()), (int) Math.round(secondVertex.getY()));
            }
        }
        setVisible(true);
    }

    private void addListeners(){
        closeWindowListener();
    }

    private void closeWindowListener(){
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                dispose();
            }
        });
    }
}
