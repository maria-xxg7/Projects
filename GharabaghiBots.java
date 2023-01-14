/**
 * Maria Gharabaghi
 */

package gharabaghibots;

import TurtleGraphics.StandardPen;

public class GharabaghiBots {

    public static void main(String[] args) {
        StandardPen p = new StandardPen(500, 500);
        AbstractRobot.setNumRobots(6);
        Robot[] robots = new Robot[AbstractRobot.getNumRobots()];

        // Create bots
        robots[0] = new SentryBot(-100, 100, 50, 5000, "Sword");
        robots[1] = new WorkerBot(0, 0, 10, 1500, "Regular");
        robots[2] = ((SentryBot) robots[0]).clone();
        robots[3] = ((WorkerBot) robots[1]).clone();
        robots[4] = new CleanerBot(0, 0, 10, 10000, "Broom");
        robots[5] = new CleanerBot();

        // Set lift capacity
        ((WorkerBot) robots[3]).setLiftCapacity(15);

        // Move bots
        robots[1].move(100, 100);
        if (((SentryBot) robots[2]).equals((SentryBot) robots[0])) {
            robots[2].move(100, -100);
        }

        if (((WorkerBot) robots[3]).equals((WorkerBot) robots[1]) == false) {
            robots[3].move(-100, -100);
        }

        //Draw robots
        for (Robot robot : robots) {
            robot.draw(p);
        }

        //ACTIVITY REPORT
        System.out.println("ACTIVITY REPORT:");
        System.out.println("Total number of robots working: " + robots.length);

        //Test what happens when the cleaner cleans one of the attacked worker bots
        ((SentryBot) robots[0]).attack(robots[1]);
        ((CleanerBot) robots[4]).clean(robots[1]);
        //Test what happens when a sentry bot attack the cleaner
        ((SentryBot) robots[0]).attack(robots[4]);
        //Test cleaner clone
        robots[5] = ((CleanerBot) robots[4]).clone();

        //Output robot information
        for (Robot robot : robots) {
            System.out.println(robot);
        }
    }
}
