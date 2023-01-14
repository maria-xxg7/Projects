/**
 * Maria Gharabaghi
 * December 8, 2021
 */
package gharabaghibots;

import TurtleGraphics.StandardPen;
import java.awt.Color;
import static java.awt.Color.black;

public abstract class AbstractRobot implements Robot {
    //Attributes (protected - only subclasses of AbstractRobot can access these variables)
    protected int x, y, energy;
    protected double weight, cost;
    protected String powerSource, movementType, sensorType;

    public AbstractRobot() {
        x = 0;
        y = 0;
        energy = 50;
        weight = 20;
        cost = 1000;
        powerSource = "Super Juice";
        movementType = "Normal";
        sensorType = "Movement";
    }

    public AbstractRobot(int x, int y, double weight, double cost) {
        this();
        this.x = x;
        this.y = y;
        this.weight = weight;
        this.cost = cost;
        energy = 50;
        powerSource = "Super Juice";
        movementType = "Normal";
        sensorType = "Movement";
    }
    
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXPos() {
        return x;
    }

    public int getYPos() {
        return y;
    }

    public double getWeight() {
        return weight;
    }

    public boolean setWeight(double weight) {
        if (!(MIN_WEIGHT <= weight && weight <= MAX_WEIGHT)) {
            return false;
        } else {
            this.weight = weight;
            return true;
        }
    }

    public double getCost() {
        return cost;
    }

    public boolean setCost(double cost) {
        if (!(MIN_COST <= cost && cost <= MAX_COST)) {
            return false;
        } else {
            this.cost = cost;
            return true;
        }
    }

    public String getPowerSource() {
        return powerSource;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean setEnergy(int energy) {
        if (!(energy <= MAX_ENERGY)) {
            return false;
        } else {
            this.energy = energy;
            return true;
        }
    }

    public String getMovementType() {
        return movementType;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String type) {
        this.sensorType = type;
    }

    public abstract void draw(StandardPen p);

    public String toString() {
        return "\nLocation: (" + x + ", " + y + ")"
                + "\nEnergy: " + energy
                + "\nWeight: " + weight
                + "\nCost: " + cost
                + "\nPower Source: " + powerSource
                + "\nMovement Type: " + movementType
                + "\nSensor Type: " + sensorType;
    }

    //Private Class Variables
    private static double MIN_WEIGHT = 10;
    private static double MAX_WEIGHT = 100;
    private static double MIN_COST = 1000;
    private static double MAX_COST = 10000;
    private static int MAX_ENERGY = 100;
    private static int NUM_ROBOTS = 4;

    public static String getWeightRules() {
        return " weight between " + MIN_WEIGHT + " & " + MAX_WEIGHT + " ";
    }

    public static String getCostRules() {
        return " cost between " + MIN_COST + " & " + MAX_COST + " ";
    }

    public static String getEnergyRules() {
        return "energy cannot be higher than " + MAX_ENERGY + " ";
    }

    public static int getNumRobots() {
        return NUM_ROBOTS;
    }

    public static void setNumRobots(int numRobots) {
        NUM_ROBOTS = numRobots;
    }

}
