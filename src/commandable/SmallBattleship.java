package commandable;

import game.*;
import staticManagers.ColorManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The SmallBattleShip class is a Commandable enemy that gives 150p when hit by players missiles
 *
 */
public class SmallBattleship extends Enemy {		
    public SmallBattleship(Point position, int speed, int life, int direction, int firingSpeed, int startDelay, Level myLevel) {
        super(position, "sprite-small-battleship", speed, life, 53, 30, direction, firingSpeed, startDelay, myLevel);
    }

    @Override
    public void drawYourself(Graphics g) {
        if(isAlive()) {
            g.drawImage(getSpriteImage(), getX(), getY(), getX() + 53, getY() + 30, 0, 0, 53, 30, null);
        }else {
            g.drawImage(getSpriteImage(), getX(), getY(), getX() + 53, getY() + 30, 53, 0, 106, 30, null);
        }

        g.setColor(ColorManager.RED_DARK.getColor());
        g.fillRect((getX() + (getWidth() / 2)) - 20, getY() - 10, (int)(((double)getLife() / (double)getOriginalLife()) * 40), 5);
        g.setColor(Color.decode("#000000"));
        g.drawRect((getX() + (getWidth() / 2)) - 20, getY() - 10, 40, 5);
    }

    @Override
    public int getScoreToGive() {
        return 150;
    }
}