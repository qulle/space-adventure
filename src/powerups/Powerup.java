package powerups;

import commandable.*;
import game.GameObject;
import game.Level;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Powerup class is the superclass for all the powerup objects
 */
public abstract class Powerup extends GameObject {
    private int direction;
    private Level myLevel;
    private int delayToStart;
    private Timer startTimer = new Timer();

    public abstract void activate(Player player);

    public Powerup(Point position, String spriteName, int speed, int life, int width, int height, int direction, int startDelay, Level level) {
        super(position, spriteName, speed, life, width, height);
        this.direction = direction;
        myLevel = level;
        delayToStart = startDelay;

        startTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(myLevel.isRunning()) {
                    delayToStart--;
                    if(delayToStart <= 0) {
                        startTimer.cancel();
                    }
                }
            }
        }, 100, 1);
    }

    @Override
    public void update() {
        if(delayToStart <= 0) {
            move(direction);
        }
    }

    @Override
    public void move(int keyCode) {
        decX();

        if(objectOutOfBounds()) {
            myLevel.removePowerup(this);
        }
    }

    @Override
    public void collisionChecker() {}
}