/**
 * Maria Gharabaghi
 * December 8, 2021
 */
package gharabaghibots;

import TurtleGraphics.StandardPen;

public interface Robot {
    public void move(int x, int y);
    public int getXPos();
    public int getYPos();
    public double getWeight();
    public boolean setWeight(double weight);
    public double getCost();
    public boolean setCost(double cost);
    public String getPowerSource();
    public int getEnergy();
    public boolean setEnergy(int energy);
    public String getMovementType();
    public String getSensorType();
    public void setSensorType(String sensorType);
    public void draw(StandardPen p);
    public String toString();
}
