package commandable;

import java.awt.Graphics;

/**
 * The Commandable interface is applied to a class and can then fight in the game
 *
 */
public interface Commandable {
    public void shoot();
    public void updateMissiles();
    public void drawMissiles(Graphics g);
    public void removeMissile(Missile missile);
    public void addScore(int score);
    public boolean missileCollision(Missile missile);
}