package commandable;

import game.*;
import staticManagers.ColorManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The LargeBattleShip class is a Commandable enemy that gives 50p when hit by players missiles
 *
 */
public class LargeBattleship extends Enemy {	
    public LargeBattleship(Point position, int speed, int life, int direction, int fieringSpeed, int startDelay, Level myLevel) {
        super(position, "sprite-large-battleship", speed, life, 80, 45, direction, fieringSpeed, startDelay, myLevel);
    }
       
    @Override
    public void drawYourself(Graphics g) {
        if(isAlive()) {
            g.drawImage(getSpriteImage(), getX(), getY(), getX() + 80, getY() + 45, 0, 0, 80, 45, null);
        }else {
            g.drawImage(getSpriteImage(), getX(), getY(), getX() + 80, getY() + 45, 80, 0, 160, 45, null);
        }

        g.setColor(ColorManager.RED_DARK.getColor());
        g.fillRect((getX() + (getWidth() / 2)) - 20, getY() - 10, (int)(((double)getLife() / (double)getOriginalLife()) * 40), 5);
        g.setColor(Color.decode("#000000"));
        g.drawRect((getX() + (getWidth() / 2)) - 20, getY() - 10, 40, 5);
    }

    @Override
    public int getScoreToGive() {
        return 50;
    }
}