package game;

import staticManagers.SpriteManager;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * The GameObject is the top superclass that all objects in the game uses
 *
 */
public abstract class GameObject {
    public abstract void update();
    public abstract void drawYourself(Graphics g);
    public abstract void move(int keyCode);
    public abstract void collisionChecker();

    private BufferedImage spriteImage;
    private Point position;
    private Rectangle objectBounds;
    private int speed; 
    private int width;
    private int height;
    private int life;

    public GameObject(Point position, String spriteName, int speed, int life, int width, int height) {
        this.position = position;
        this.spriteImage = SpriteManager.getImage(spriteName);
        this.speed = speed;
        this.life = life;
        this.width = width;
        this.height = height;
        objectBounds = new Rectangle(position.x, position.y, width, height);
    }

    public BufferedImage getSpriteImage() {
        return spriteImage;
    }

    public Rectangle getBounds() {
        return objectBounds;
    }

    public int getX() {
        return position.x;
    }

    public int getY() {
        return position.y;
    }

    public boolean objectOutOfBounds() {
        if(position.x >= (SpaceAdventure.XUPPERBOUND) || position.x <= (0 - width) || position.y <= (0 - height) || position.y >= (SpaceAdventure.YUPPERBOUND + height)) {
            return true;
        }
        
        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLife() {
        return life;
    }

    public void loseLife() {
        if(life > 0) {
            life--;
        }
    }

    public void gainLife() {
        if(life < 5) {
            life++;
        }
    }
       
    public boolean isAlive() {
        return life > 0;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void addY() {
        position.y += speed;
        objectBounds.y = position.y;
    }

    public void decY() {
        position.y -= speed;
        objectBounds.y = position.y;
    }

    public void addX() {
        position.x += speed;
        objectBounds.x = position.x;
    }

    public void decX() {
        position.x -= speed;
        objectBounds.x = position.x;
    }
}