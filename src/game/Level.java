package game;

import commandable.*;
import powerups.*;
import staticManagers.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The Level class makes the level and its enemies and powerups 
 *
 */
public class Level {
    private Game currentGame;
    private BufferedImage backgroundImage;
    private String name;
    private int level;
    private boolean isRunning = false;
    private List<Powerup> powerups = new ArrayList<Powerup>();
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private Random rnd = new Random();

    public Level(Game game, String name, int level, String spriteName, int numLargeShips, int numSmallShips, int numLives, int numInvisible) {
        currentGame = game;
        this.name = name;
        this.level = level;
        this.backgroundImage = SpriteManager.getImage(spriteName);

        for(int i = 0; i < numLargeShips; i++) {
            int yPos = rnd.nextInt(SpaceAdventure.YUPPERBOUND - 80) + 40;
            int speed = rnd.nextInt(4) + 1;
            int fieringSpeed = rnd.nextInt(4000) + 2000;
            int startDelay = rnd.nextInt(8000) + 300;
            enemies.add(new LargeBattleship(new Point(SpaceAdventure.XUPPERBOUND, yPos), speed, 4, KeyEvent.VK_A, fieringSpeed, startDelay, this));
        }

        for(int i = 0; i < numSmallShips; i++) {
            int yPos = rnd.nextInt(SpaceAdventure.YUPPERBOUND - 80) + 40;
            int speed = rnd.nextInt(3) + 1;
            int startDelay = rnd.nextInt(8000) + 300;
            enemies.add(new SmallBattleship(new Point(SpaceAdventure.XUPPERBOUND, yPos), speed, 3, KeyEvent.VK_A, 1000, startDelay, this));
        }

        for(int i = 0; i < numLives; i++) {
            int yPos = rnd.nextInt(SpaceAdventure.YUPPERBOUND - 60) + 30;
            int speed = rnd.nextInt(4) + 1;
            int startDelay = rnd.nextInt(8000) + 300;
            powerups.add(new Life(new Point(SpaceAdventure.XUPPERBOUND, yPos), speed, KeyEvent.VK_A, startDelay, this));
        }

        for(int i = 0; i < numInvisible; i++) {
            int yPos = rnd.nextInt(SpaceAdventure.YUPPERBOUND - 60) + 30;
            int speed = rnd.nextInt(4) + 1;
            int startDelay = rnd.nextInt(8000) + 300;
            powerups.add(new Invisible(new Point(SpaceAdventure.XUPPERBOUND, yPos), speed, KeyEvent.VK_A, startDelay, this));
        }
    }

    public void start() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public int getNumEnemies() {
        return enemies.size();
    }

    public int getNumPowerups() {
        return powerups.size();
    }

    public BufferedImage getBackground() {
        return backgroundImage;
    }

    public boolean levelCompleted() {
        return enemies.size() == 0 && powerups.size() == 0;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void updateLevel() {
        List<Powerup> copyPowerup = new ArrayList<Powerup>(powerups);
        for(Powerup powerup : copyPowerup) {
            powerup.update();
        }

        List<Enemy> copyEnemies = new ArrayList<Enemy>(enemies);
        for(Enemy enemy : copyEnemies) {
            enemy.update();
            enemy.updateMissiles();
        }
    }

    public void drawLevel(Graphics g) {
        for(Powerup powerup : powerups) {
            powerup.drawYourself(g);
        }

        for(Enemy enemy : enemies) {
            enemy.drawYourself(g);
            enemy.drawMissiles(g);
        }
    }

    public void removePowerup(Powerup powerup) {
        powerups.remove(powerup);
    }

    public void removeEnemy(NPC battleShip) {
        enemies.remove(battleShip);
    }

    public void checkCollisionPowerup(Player player) {
        List<Powerup> copyPowerup = new ArrayList<Powerup>(powerups);
        for(Powerup powerup : copyPowerup) {
            if(powerup.getBounds().intersects(player.getBounds())) {
                powerup.activate(player);
                removePowerup(powerup);
                return;
            }
        }
    }

    public boolean missileCollisionEnemy(Missile missile, Commandable owner) {
        if(missile.objectOutOfBounds()) {
            return false;
        }

        List<NPC> copyEnemies = new ArrayList<NPC>(enemies);
        for(NPC enemy : copyEnemies) {
            if(enemy.getBounds().intersects(missile.getBounds())) {
                owner.addScore(enemy.getScoreToGive());
                enemy.loseLife();
                return true;
            }
        }

        return false;
    }

    public boolean missileCollisionPlayer(Missile missile) {
        if(currentGame.getPlayer().isInvisible()) {
            return false;
        }

        if(missile.getBounds().intersects(currentGame.getPlayer().getBounds())) {
            currentGame.getPlayer().loseLife();
            return true;
        }

        return false;
    }
}