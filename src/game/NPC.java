package game;

import java.awt.Point;

/**
 * The NPC class is the superclass for all the non-playable-characters in the game
 *
 */
public abstract class NPC extends GameObject {
    public abstract int getScoreToGive();

    public NPC(Point position, String spriteName, int speed, int life, int width, int height) {
        super(position, spriteName, speed, life, width, height);
    }
}