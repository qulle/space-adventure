package commandable;

import game.GameObject;
import game.Level;
import game.SpaceAdventure;
import staticManagers.SpriteManager;
import staticManagers.ColorManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The Player class is the class that makes the player
 *
 */
public class Player extends GameObject implements Commandable {
    private Level currentLevel;
    private int score = 0;
    private boolean invisible = false;
    private Timer powerupTimer;
    private final int ORIGINALPOWERUPTIME = 10000;
    private int powerupTime = ORIGINALPOWERUPTIME;
    private List<Missile> firedMissiles = new ArrayList<Missile>();
    private Map<Integer, Boolean> commands = new HashMap<Integer, Boolean>();
	
    public Player(Point position, int speed) {
        super(position, "sprite-player", speed, 5, 80, 40);
    }

    private void doCommand(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                move(keyCode);
                break;
            case KeyEvent.VK_SPACE:
                shoot();
                break;
        }
    }

    private void drawLife(Graphics g) {
        g.drawImage(SpriteManager.getImage("sprite-life"), 3, 620, 165, 647, 0, (27 * getLife()), 165, ((27 * getLife() + 27)), null);
    }

    private void drawPowerup(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(Color.BLACK);
        g.drawString("Invisible", 3, 595);
        g.setColor(ColorManager.BLUE_LIGHT.getColor());
        g.fillRect(3, 600, (int)(((double)powerupTime / (double)ORIGINALPOWERUPTIME) * 160), 10);
        g.setColor(ColorManager.BLUE_DARK.getColor());
        g.drawRect(3, 600, 160, 10);
    }

    public void setLevel(Level level) {
        currentLevel = level;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void drawYourself(Graphics g) {
        if(invisible) {
            g.drawImage(getSpriteImage(), getX(), getY(), getX() + 80, getY() + 40, 80, 0, 160, 40, null);
        }else {
            g.drawImage(getSpriteImage(), getX(), getY(), getX() + 80, getY() + 40, 0, 0, 80, 40, null);
        }

        drawLife(g);

        if(isInvisible()) {
            drawPowerup(g);
        }
    }

    @Override
    public void drawMissiles(Graphics g) {
        for(Missile m : firedMissiles) {
            m.drawYourself(g);
        }
    }

    @Override
    public void move(int keyCode) {
        switch(keyCode) {
            case KeyEvent.VK_S:
                if((getY() + getSpeed()) <= (SpaceAdventure.YUPPERBOUND - getHeight())) {
                    addY();
                }
                break;
            case KeyEvent.VK_W:
                if((getY() - getSpeed()) >= 0) {
                    decY();
                }
                break;
            case KeyEvent.VK_A:
                if((getX() - getSpeed()) >= 0) {
                    decX();
                }
                break;
            case KeyEvent.VK_D:
                if((getX() + getSpeed()) <= (SpaceAdventure.XUPPERBOUND - getWidth())) {
                    addX();
                }
                break;
        }
    }

    @Override 
    public boolean missileCollision(Missile missile) {
        if(currentLevel.missileCollisionEnemy(missile, this)) {
            return true;
        }

        return false;
    }

    @Override
    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public void removeMissile(Missile missile) {
        firedMissiles.remove(missile);
    }

    @Override
    public void shoot() {
        firedMissiles.add(new Missile(new Point(getX() + getWidth(), getY() + ((getHeight() / 2) - 10)), (getSpeed() + 15), KeyEvent.VK_D, this, ColorManager.YELLOW_LIGHT.getColor(), ColorManager.YELLOW_DARK.getColor()));
        commands.put(KeyEvent.VK_SPACE, false);
    }

    @Override
    public void updateMissiles() {
        List<Missile> copyFieredMissiles = new ArrayList<Missile>(firedMissiles);
        for(Missile m : copyFieredMissiles) {
            m.update();
        }
    }

    @Override
    public void update() {
        for(Map.Entry<Integer, Boolean> cmd : commands.entrySet()) {
            if(cmd.getValue()) {
                doCommand(cmd.getKey());
            }
        }
        collisionChecker();
    }

    @Override
    public void collisionChecker() {
        currentLevel.checkCollisionPowerup(this);
    }

    public void setCommand(int keyCode) {
        commands.put(keyCode, true);
    }

    public void resetCommand(int keyCode) {
        commands.put(keyCode, false);
    }

    public void resetCommands() {
        for(Map.Entry<Integer, Boolean> cmd : commands.entrySet()) {
            cmd.setValue(false);
        }
    }

    public void makeInvisible() {
        if(invisible) {
            return;
        }

        invisible = true;
        (powerupTimer = new Timer()).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(currentLevel.isRunning()) {
                    if(powerupTime <= 0) {
                        invisible = false;
                        powerupTime = ORIGINALPOWERUPTIME;
                        powerupTimer.cancel();
                    }else {
                        powerupTime--;
                    }
                }
            }
        }, 0, 1);
    }

    public boolean isInvisible() {
        return invisible;
    }
}