/**
 * Maria Gharabaghi
 */

package gharabaghibots;

import TurtleGraphics.StandardPen;
import java.awt.Color;

public class CleanerBot extends AbstractRobot {
    //Attributes
    String cleaningTool;
    
    public CleanerBot(){
        super();
        cleaningTool = "Broom";
    }
    
    public CleanerBot(int x, int y, double weight, double cost, String cleaningTool) {
        super(x, y, weight, cost);
        this.cleaningTool = cleaningTool;
    }

    public void draw(StandardPen p) {
        int width = 30;
        int length = 40;
        int armLength = width/4;
        int strapLength = length/4;
        p.setColor(Color.black);
        //Head
        rect(p, this.x, this.y, width, length);
        
        //Body
        rect(p, this.x - length/4, this.y-width, width+ width/2, length + length/2);
        
        //Legs
        rect(p, this.x, this.y-width-(width+ width/2), armLength, length/4);
        rect(p, this.x+length-length/4, this.y-width-(width+ width/2), armLength, length/4);
        
        //Arms
        rect(p, this.x - length/4-armLength, this.y-width-armLength, width, armLength);
        rect(p, this.x +length+length/4, this.y-width-armLength, width, armLength);

        //Face
        rect(p, this.x+length/4, this.y-width/3, length/10, length/10);
        rect(p, this.x+length-length/4-length/10, this.y-width/3, length/10, length/10);
        line(p, this.x+length/4+length/10, this.y-width*2/3, this.x+length-length/4-length/10, this.y-width*2/3);
        line(p, this.x+length/4+length/10, this.y-width*2/3, this.x+length/4+length/10-2, this.y-width*2/3+2);
        line(p, this.x+length-length/4-length/10, this.y-width*2/3, this.x+length-length/4-length/10+2, this.y-width*2/3+2);
        
        p.setColor(Color.pink);
        
        //Outfit
        rect(p, this.x, this.y-width-width/3, width, length);
        line(p, this.x+ strapLength, this.y-width-width/3, this.x+strapLength, this.y-width-width/3+strapLength);
        line(p, this.x+length- strapLength, this.y-width-width/3, this.x+length-strapLength, this.y-width-width/3+strapLength);
        line(p, this.x - length/4, this.y-width-length/2, this.x+length+length/4, this.y-width-length/2);
        rect(p, this.x+strapLength, this.y+strapLength/2, strapLength/2, length/2);
        circle(p, this.x+strapLength +length/4, this.y+strapLength/2+3, 3);
    }
    
    public void rect(StandardPen p, int x, int y, int width, int length) {
        p.up(); //Lift the pen up
        p.move(x, y); //Move the pen to the correct location
        p.down(); //Put the pen down to draw
        p.setDirection(0); //Set direction to 0

        for (int i = 0; i < 2; i++) {
            p.move(length); //Draw the width of the rectangle
            p.turn(-90); //Turn the pen right
            p.move(width); //Draw the height of the rectangle
            p.turn(-90); //Turn the pen right
        }
    }
    
    public void line(StandardPen p, int x, int y, int x2, int y2) {
        p.up();
        p.move(x,y);
        p.down();
        p.move(x2, y2);
    }
    
    public void circle(StandardPen p, int x, int y, int rad) {
        double side = 2.0 * Math.PI * rad / 120.0; //2*pi*r is the circumference, divide the radius by 120 to create a smaller circle (otherwise: will not fit on the output screen)
        p.up(); //Lift the pen up to move it without drawing
        p.move(x+rad, y-side / 2.0); //move it to where the the circle border should be
        p.setDirection(90); //Set direction
        p.down(); //Set the pen down (to draw)
        
        //Move the pen 120 times while moving to the side (circumference), creating a circle
        for(int i = 0; i <120; i++){
            p.move(side);
            p.turn(3); //Turn (make a circle)
        }
    }
    
    public void clean(Robot other) {
        if(other instanceof WorkerBot) {
            other.setEnergy(50);
        }else if(other instanceof SentryBot) {
            other.setEnergy(50);
        }
    }
    
    public String getCleaningTool() {
        return cleaningTool;
    }
    
    public String toString() {
        return "\nCLEANER BOT:"
                + super.toString()
                + "\nCleaning using: " + cleaningTool;
    }

    public boolean equals(CleanerBot other) {
        return this.x == other.getXPos()
                && this.y == other.getYPos()
                && this.cleaningTool.equals(other.getCleaningTool());
    }

    public CleanerBot clone() {
        CleanerBot other = new CleanerBot(x, y, weight, cost, cleaningTool);
        return other;
    }
}
