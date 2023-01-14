/**
 * Maria Gharabaghi
 */

package gharabaghibots;

import TurtleGraphics.StandardPen;
import java.awt.Color;

public class SentryBot extends AbstractRobot {

    private String weaponType;

    public SentryBot() {
        super();
        weaponType = "sword";
    }

    public SentryBot(int x, int y, double weight, double cost, String weaponType) {
        super(x, y, weight, cost);
        this.weaponType = weaponType;
    }

    public void setWeaponType(String weapon) {
        this.weaponType = weapon;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void attack(Robot other) {

        if (other instanceof WorkerBot) {
            System.out.println("SENTRY BOT targeted WORKER BOT");
            other.setEnergy(0);
        } else if (other instanceof SentryBot) {
            if (this.weaponType.equals(((SentryBot) other).getWeaponType())) {
                System.out.println("SENTRY BOT was attacked by the same weapon");
                other.setEnergy(other.getEnergy()/2);
                this.setEnergy(getEnergy()/2);
            }else {
                System.out.println("SENTRY BOT was attacked by a different weapon");
                other.setEnergy(other.getEnergy()/2);
                this.setEnergy(getEnergy()*2);
            }
        }else {
            this.setEnergy(0);
            System.out.println("SENTRY BOT encountered CLEANER BOT");
        }
    }

    public void draw(StandardPen p) {
        int width = 40;
        int length = 60;
        int armLength = width/2;
        p.setColor(Color.orange);
        
        //Head
        rect(p, this.x, this.y, width, length);
        
        //Body
        rect(p, this.x - length/4, this.y-width, width+ width/2, length + length/2);
        
        //Legs
        rect(p, this.x, this.y-width-(width+ width/2), width/4, armLength);
        rect(p, this.x+length-armLength, this.y-width-(width+ width/2), width/4, armLength);

        //Arms
        rect(p, this.x - length/4-armLength, this.y-width-width/4, width, armLength);
        rect(p, this.x +length+length/4, this.y-width-width/4, width, armLength);
        
        //Face
        triangle(p, this.x+length/6, this.y-width/3, true);
        triangle(p, this.x+length-length/6, this.y-width/3, false);
        line(p, this.x+length/4+length/10, this.y-width*3/4, this.x+length-length/4-length/10, this.y-width*3/4);
        line(p, this.x+length/2, this.y, this.x+width/2, this.y+width/4);
        line(p, this.x+width/2, this.y+width/4, this.x+length/2, this.y+width/2);
        circle(p,this.x+length/2, this.y+width/2+ 3, 3);
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
    
    public void triangle(StandardPen p, int x, int y, boolean right) {
        int side = 10;
        p.up();
        p.move(x,y);
        p.setDirection(0);
        p.down();
        
        if(right == true) {
            p.move(x, y-side);
            p.move(x+side, y-side);
            p.move(x,y);
        }else {
            p.move(x, y-side);
            p.move(x-side, y-side);
            p.move(x,y);
        }
    }

    public String toString() {
        return "\nSENTRY BOT:"
                + super.toString()
                + "\nWeapon Type: " + weaponType;
    }

    public boolean equals(SentryBot other) {
        return this.x == other.getXPos()
                && this.y == other.getYPos()
                && this.weaponType.equals(other.getWeaponType());
    }

    public SentryBot clone() {
        SentryBot other = new SentryBot(x, y, weight, cost, weaponType);
        return other;
    }
}
