package TSP;

/**
 * Created by Dylan Galea on 07/10/2018.
 */
public class City {
    private int iD;
    private double x;
    private double y;

    public City(){
        iD = 0;
        x = 0.0;
        y=0.0;
    }

    public City(int iD,double x,double y){
        this.iD = iD;
        this.x = x;
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }

    public double getX(){
        return x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getY(){
        return y;
    }

    public void setID(int iD){
        this.iD = iD;
    }

    public int getID(){
        return iD;
    }
}
