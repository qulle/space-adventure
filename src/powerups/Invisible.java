package powerups;

import game.*;
import commandable.*;
import java.awt.Graphics;
import java.awt.Point;

/**
 * The Invisible class is the powerup that makes the player invisible and protects the player from missiles
 *
 */
public class Invisible extends Powerup {
    public Invisible(Point position, int speed, int direction, int startDelay, Level level) {		
        super(position, "sprite-powerups", speed, 1, 30, 24, direction, startDelay, level);
    }

    @Override
    public void drawYourself(Graphics g) {
        g.drawImage(getSpriteImage(), getX(), getY(), getX() + 30, getY() + 24, 60, 0, 90, 24, null);
    }

    @Override
    public void activate(Player player) {
        player.makeInvisible();
    }
}