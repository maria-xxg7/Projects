/**
 * Maria Gharabaghi
 * Program that draws a Koch snowflake recursively based on user input (depth of recursion)
 */

package gharabaghifractal;

//Imports
import TurtleGraphics.StandardPen; //Pen
import java.awt.Color; //Colour
import static java.lang.Math.sqrt; //Square root
import javax.swing.JOptionPane; //Dialog boxes/messages

public class GharabaghiFractal {

    public static void main(String[] args) throws InterruptedException {
        //Declare & initialize variables & pen
        StandardPen p = new StandardPen(500, 500); //Create a new pen
        int level = 0; //Depth of recursion
        String input = ""; //String that holds the input of the user (level)
        boolean slowDown = false; //Boolean for if the drawing should be slowed

        while (level != 999) { //While the level is not equal to the sentinel value, keep prompting the user
            input = JOptionPane.showInputDialog("Enter the depth of the recursion or enter 999 to stop"); //Prompt the user to input the level or stop the program
            erase(p, level, slowDown); //Erase previous drawing
            try { //Try to parse the input & draw the fractal
                level = Integer.parseInt(input); //Input needs to be parsed into an integer 
                slowDown = true; //Slow down the drawing
                draw(p, level, slowDown); //Draw the fractal
                slowDown = false; //Stop slowing down (erase at the normal speed)
            } catch (NumberFormatException e) { //Show an error message if there are any illegal inputs
                JOptionPane.showMessageDialog(null, "Error"); //Error message
            }

            if (level == 999) { //If the input (level) is equal to the sentinal value, then...
                System.exit(0); //Exit
            }
        }
    }

    /**
     * Erases the previous fractal
     *
     * @param p - pen
     * @param level - depth of the recursion
     * @param slowDown - if the drawing should be slowed down
     * @throws InterruptedException
     */
    public static void erase(StandardPen p, int level, boolean slowDown) throws InterruptedException {
        p.setColor(Color.white); //Set colour to white (draw over the previous fractal in white)
        draw(p, level, slowDown); //Draw the fractal (white)
        p.setColor(Color.black); //Reset the colour back to black
    }

    /**
     * Draws the fractals
     *
     * @param p - pen
     * @param level - depth of the recursion
     * @param slowDown - if the drawing should be slowed down
     * @throws InterruptedException
     */
    public static void draw(StandardPen p, int level, boolean slowDown) throws InterruptedException {
        //Each fractal is a line (create 3 to complete the triangle/snowflake)
        fractal(p, level, -100, 0, 100, 0, slowDown); //Top
        fractal(p, level, 100, 0, 0, -150, slowDown); //Right side
        fractal(p, level, 0, -150, -100, 0, slowDown); //Left side
    }

    /**
     * Pauses the execution by the inputted time (in milliseconds)
     *
     * @param num - milliseconds
     * @throws InterruptedException
     */
    public static void slowDown(int num) throws InterruptedException {
        Thread.sleep(num); //When the program is slowed, it pauses drawing in num millisecond intervals
    }

    /**
     * Method that draws a line from one coord to another
     *
     * @param p - pen
     * @param x - first x pos
     * @param y - second y pos
     * @param x5 - second x pos
     * @param y5 - second y pos
     */
    public static void line(StandardPen p, int x, int y, int x5, int y5) {
        p.up(); //Lift pen up to position
        p.move(x, y); //Move to the correct starting x & y pos
        p.down(); //Place pen down to draw
        p.move(x5, y5); //Move to the end of the line (x5 & y5)
    }

    /**
     * Method that draws the fractal recursively
     *
     * @param p - pen
     * @param level - depth of recursion
     * @param x - front of the line (x pos)
     * @param y - front of the line (y pos)
     * @param x5 - end of the line (x pos)
     * @param y5 - end of the line (y pos)
     */
    public static void fractal(StandardPen p, int level, int x, int y, int x5, int y5, boolean slowDown) throws InterruptedException {
        int dist, dist2, length, length2, x2, y2, x3, y3, x4, y4; //Declare variables (coordinates)
        //If slowDown is true, then slow down the drawing of the fractal
        if (slowDown == true) {
            slowDown(20); //Call slowDown method
        }

        //Base case (if the depth is 0, then draw a line)
        if (level == 0) {
            line(p, x, y, x5, y5); //Draw a line at the correct coordinates

        //Otherwise...
        } else {
            dist = x5 - x; //Distance between the points (length of the line)
            dist2 = y5 - y; //Height (distance between the lowest & highest y pos)
            length = dist / 3; //Length of the segment (divide line into 3 equal parts)
            length2 = dist2 / 3; //Length of the segment (divide height into 3 equal parts)

            //CALCULATE ALL THE COORDINATES BETWEEN THE FIRST & LAST
            //Second coordinate (move forward)
            x2 = x + length; //initial x pos plus the segment length (dist divided by 3)
            y2 = y + length2; //initial y pos plus the segment length (dist2 divided by 3)

            //Third coordinate (move to form the tip of the triangle)
            /*
            Find middle of the line (x+x5)/2, then find how much to shift the x 
            Use special triangles - 30, 60, 90 triangle to find the base (base is sqrt(3)*height which is y-y5)
            Then divide by 6 (not dividing shifts the x pos over by half of the length of the line (too far out))
             */
            x3 = (int) ((x + x5) / 2 + (sqrt(3) * (y - y5)) / 6);
            /*
            Find the midpoint of the height (y+y5)/2, then find how much to shift the y
            Use special triangles (to get the base multiply sqrt(3)*height which is the distance between x and x5)
            Then divide by 6 (not dividing shifts the y pos over by half of the height (too far out))
             */
            y3 = (int) ((y + y5) / 2 + (sqrt(3) * dist) / 6);

            //Fourth coordinate (back down)
            x4 = x + length * 2; //initial x pos plus 2 segment lengths
            y4 = y + length2 * 2; //initial y pos plus 2 segment lengths

            /*
            Recursively call the fractal method & keep calling until the level is 0 
            (Subtract 1 from the level each time (work through all of the levels until it reaches 0 -->
            the if statement catches when it becomes 0 and will draw the line at the new inputted coords)
            Have four fractals for each segment 
             */
            fractal(p, level - 1, x, y, x2, y2, slowDown); //Fractal (flat)
            fractal(p, level - 1, x2, y2, x3, y3, slowDown); //Fractal slanting up
            fractal(p, level - 1, x3, y3, x4, y4, slowDown); //Fractal slanting down
            fractal(p, level - 1, x4, y4, x5, y5, slowDown); //Fractal (flat)
        }
    }
}