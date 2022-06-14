package powerups;

import game.*;
import commandable.Player;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The Life class is the powerup that lets the player gain life
 */
public class Life extends Powerup {	
    public Life(Point position, int speed, int direction, int startDelay, Level level) {		
        super(position, "sprite-powerups", speed, 1, 30, 24, direction, startDelay, level);
    }

    @Override
    public void drawYourself(Graphics g) {
        g.drawImage(getSpriteImage(), getX(), getY(), getX() + 30, getY() + 24, 30, 0, 60, 24, null);
    }

    @Override
    public void activate(Player player) {
        player.gainLife();
    }
}