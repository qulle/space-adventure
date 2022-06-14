package game;

import commandable.Player;
import staticManagers.ScoreManager;
import staticManagers.SpriteManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The Game class is the "main" class for the game, player, levels etc
 *
 */
public class Game extends JPanel {
    private static final long serialVersionUID = 1L;
    private SpaceAdventure window;
    private Player player = new Player(new Point(10, 20), 10);
    private Timer gameLoopTimer;
    private Level currentLevel;
    private int numLevel = 0;

    @SuppressWarnings("serial")
    private List<Level> levels = new ArrayList<Level>() {{
        add(new Level(Game.this, "Merkurius", 1, "world-1", 3, 0, 1, 0));
        add(new Level(Game.this, "Venus", 2, "world-1", 4, 1, 1, 0));
        add(new Level(Game.this, "Tellus", 3, "world-1", 6, 1, 1, 1));
        add(new Level(Game.this, "Mars", 4, "world-1", 8, 2, 2, 0));
        add(new Level(Game.this, "Jupiter", 5, "world-1", 8, 3, 2, 1));
        add(new Level(Game.this, "Saturnus", 6, "world-1", 10, 5, 4, 0));
        add(new Level(Game.this, "Uranus", 7, "world-1", 14, 2, 3, 1));
        add(new Level(Game.this, "Neptunus", 8, "world-1", 17, 4, 3, 2));
        add(new Level(Game.this, "Pluto", 9, "world-1", 20, 4, 3, 2));
    }};

    public Game(SpaceAdventure window) {
        this.window = window;
        player.setLevel(levels.get(0));
        currentLevel = levels.get(0);
        ScoreManager.loadHighscore();

        setFocusable(true);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                player.resetCommand(e.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_P:
                        toggleGameLoop();
                        repaint();
                        break;
                    case KeyEvent.VK_M:
                        toggleMenu();
                        repaint();
                        break;
                    default:
                        player.setCommand(e.getKeyCode());
                }	
            }
        });

        gameLoopTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                if(currentLevel.levelCompleted()) {
                    gameLoopTimer.stop();
                    currentLevel.stop();
                    player.resetCommands();

                    if(lastLevel()) {
                        ScoreManager.handleScore(player.getScore());
                        Game.this.window.setCurrentPanel(Game.this.window.getPanel("gameOver"));
                    }else {
                        Game.this.window.setCurrentPanel(Game.this.window.getPanel("levelCompleted"));
                    }
                }

                if(!player.isAlive()) {
                    gameLoopTimer.stop();
                    currentLevel.stop();
                    ScoreManager.handleScore(player.getScore());
                    Game.this.window.setCurrentPanel(Game.this.window.getPanel("gameOver"));
                }

                player.updateMissiles();
                player.update();
                currentLevel.updateLevel();

                repaint();
                Toolkit.getDefaultToolkit().sync();
            }
        });
    }

    public void toggleMenu() {
        toggleGameLoop();
        if(gameLoopTimer.isRunning()) {
            window.setCurrentPanel(window.getPanel("game"));
        }else {
            window.setCurrentPanel(window.getPanel("pausMenu"));
        }
    }

    private void toggleGameLoop() {
        if(gameLoopTimer.isRunning()) {
            gameLoopTimer.stop();
            currentLevel.stop();
        }else {
            gameLoopTimer.start();
            currentLevel.start();
        }
    }

    public void startGame() {
        gameLoopTimer.start();
        currentLevel.start();
    }

    public void nextLevel() {		
        currentLevel = levels.get(++numLevel);
        player.setLevel(currentLevel);
    }

    public boolean lastLevel() {
        return numLevel == (levels.size() - 1);
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Player getPlayer() {
        return player;
    }
        
    public boolean isRunning() {
        return gameLoopTimer.isRunning();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(currentLevel.getBackground(), 0, 0, null);

        currentLevel.drawLevel(g);
        player.drawYourself(g);
        player.drawMissiles(g);

        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(Color.BLACK);
        g.drawImage(SpriteManager.getImage("enemystatus"), 175, SpaceAdventure.YUPPERBOUND - 27, null);
        g.drawString(String.valueOf(currentLevel.getNumEnemies()), 220, SpaceAdventure.YUPPERBOUND - 12);

        g.drawImage(SpriteManager.getImage("sprite-powerups"), 250, SpaceAdventure.YUPPERBOUND - 27, 250 + 30, (SpaceAdventure.YUPPERBOUND - 27) + 24, 0, 0, 30, 24, null);
        g.drawString(String.valueOf(currentLevel.getNumPowerups()), 290, SpaceAdventure.YUPPERBOUND - 12);

        g.drawImage(SpriteManager.getImage("sprite-score"), 330, SpaceAdventure.YUPPERBOUND - 27, 330 + 18, (SpaceAdventure.YUPPERBOUND - 27) + 24, 0, 0, 18, 24, null);
        g.drawString(String.valueOf(player.getScore()), 360, SpaceAdventure.YUPPERBOUND - 12);

        g.drawImage(SpriteManager.getImage("sprite-score"), 400, SpaceAdventure.YUPPERBOUND - 27, 400 + 18, (SpaceAdventure.YUPPERBOUND - 27) + 24, 18, 0, 36, 24, null);
        g.drawString(String.valueOf(ScoreManager.getHighscore()), 430, SpaceAdventure.YUPPERBOUND - 12);

        g.drawString("Level " + currentLevel.getLevel(), 500, SpaceAdventure.YUPPERBOUND - 12);
        g.drawString("[P] Pause   [M] Menu", 580, SpaceAdventure.YUPPERBOUND - 12);
    }
}