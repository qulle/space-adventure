package commandable;

import game.GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 * The Missile class is the object that is fired when the Commandable objects shoot
 *
 */
public class Missile extends GameObject {
    private Commandable owner;
    private int direction;
    private Color backgroundColor;
    private Color borderColor;

    public Missile(Point position, int speed, int direction, Commandable owner, Color background, Color border) {
        super(position, "shot", speed, 1, 7, 7);
        this.owner = owner;
        this.direction = direction;
        backgroundColor = background;
        borderColor = border;
    }

    @Override
    public void drawYourself(Graphics g) {		
        g.setColor(backgroundColor);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(borderColor);
        g.drawRect(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void move(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_A:
                decX();
                break;
            case KeyEvent.VK_D:
                addX();
                break;
        }
    }

    @Override
    public void update() {
        move(direction);
        collisionChecker();
    }

    @Override
    public void collisionChecker() {
        if(objectOutOfBounds()) {
            owner.removeMissile(this);
        }

        if(owner.missileCollision(this)) {
            owner.removeMissile(this);
        }
    }
}