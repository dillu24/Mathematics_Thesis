package TSP;

public class City {
    /**
     * This class is used to encode a City in TSP.
     */
    private int iD; //Stores the unique identifier of the city in the graph , so that the distance matrix can be directly
                    // accessed
    private double x;//Stores the x-co ordinate location of the city in the 2D plane
    private double y;//Stores the y-co ordinate location of the city in the 2D plane

    /**
     * This is the default constructor and is used to initialize all values to their default values.
     */
    public City(){
        iD = 0;
        x = 0.0;
        y=0.0;
    }

    /**
     * This constructor is used to create a new City given the unique identifier,x co ordinate and y co ordinate
     * @param iD
     *  Stores the unique identifier to be stored in this.iD
     * @param x
     *  Stores the x co ordinate to be stored in this.x
     * @param y
     *  Stores the y co ordinate to be stored in this.y
     */
    public City(int iD,double x,double y){
        this.iD = iD;
        this.x = x;
        this.y = y;
    }

    /**
     * This method is used to modify the value of this.x . Note that encapsulation is used so that the variable 'x'
     * cannot be directly accessed from outside the class.
     * @param x
     *  Stores the value to be stored in this.x
     */
    public void setX(double x){
        this.x = x;
    }

    /**
     * This method is used to return the value stored in the private variable 'x' ,since this is set as private in order
     * to encourage encapsulation
     * @return
     *  The value stored in x
     */
    public double getX(){
        return x;
    }

    /**
     * This method is used to modify the value of this.y. Note that encapsulation is used so that the variable 'y'
     * cannot be directly accessed from outside the class.
     * @param y
     *  Stores the value to be stored in this.y
     */
    public void setY(double y){
        this.y = y;
    }

    /**
     * This method is used to return the value stored in the private variable 'y' , since this is set as private in
     * order to encourage encapsulation.
     * @return
     *  The value stored in y
     */
    public double getY(){
        return y;
    }

    /**
     * This method is used to modify the value of this.iD. Note that encapsulation is used so that the variable 'iD'
     * cannot be directly accessed from outside the class.
     * @param iD
     *  Stores the value to be stored in this.iD
     */
    public void setID(int iD){
        this.iD = iD;
    }

    /**
     * This method is used to return the value stored in the private variable 'iD', since this is set as private in
     * order to encourage encapsulation.
     * @return
     */
    public int getID(){
        return iD;
    }
}
