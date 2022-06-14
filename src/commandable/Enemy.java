package commandable;

import game.Level;
import game.NPC;
import staticManagers.ColorManager;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Enemy class is the superclass for all the Commandable enemies in the game
 *
 */
public abstract class Enemy extends NPC implements Commandable {
    private int direction;
    private final int originalLife;
    private Level myLevel;
    private List<Missile> firedMissiles = new ArrayList<Missile>();
    private Timer startTimer;
    private int delayToStart;

    public Enemy(Point position, String spriteName, int speed, int life, int width, int height, int direction, int firingSpeed, int startDelay, Level myLevel) {
        super(position, spriteName, speed, life, width, height);
        this.direction = direction;
        this.myLevel = myLevel;
        delayToStart = startDelay;
        originalLife = life;

        (startTimer = new Timer()).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(Enemy.this.myLevel.isRunning()) {
                    delayToStart--;
                    if(delayToStart <= 0) {
                        startTimer.cancel();
                    }
                }
            }
        }, 100, 1);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(isAlive() && delayToStart <= 0 && Enemy.this.myLevel.isRunning()) {
                    shoot();
                }
            }
        }, 1000, firingSpeed);
    }

    public int getOriginalLife() {
        return originalLife;
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
        if(getLife() == 0) {
            setSpeed(5);
            addY();
        }

        if(objectOutOfBounds()) {
            myLevel.removeEnemy(this);
        }
    }

    @Override 
    public boolean missileCollision(Missile missile) {
        if(myLevel.missileCollisionPlayer(missile)) {
            return true;
        }

        return false;
    }

    @Override
    public void collisionChecker() {}

    @Override
    public void addScore(int score) {}

    @Override
    public void shoot() {
        firedMissiles.add(new Missile(new Point(getX(), getY() + ((getHeight() / 2)  + 3)), (getSpeed() + 15), KeyEvent.VK_A, this, ColorManager.RED_LIGHT.getColor(), ColorManager.RED_DARK.getColor()));
    }

    @Override
    public void updateMissiles() {
        List<Missile> copyFieredMissiles = new ArrayList<Missile>(firedMissiles);
        for(Missile m : copyFieredMissiles) {
            m.update();
        }
    }

    @Override
    public void removeMissile(Missile missile) {
        firedMissiles.remove(missile);
    }

    @Override
    public void drawMissiles(Graphics g) {
        for(Missile m : firedMissiles) {
            m.drawYourself(g);
        }
    }
}