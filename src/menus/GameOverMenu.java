package menus;

import game.*;
import staticManagers.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * THe GameOver class is the panel that pops up when the player dies or completes all levels
 *
 */
public class GameOverMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private SpaceAdventure window;

    public GameOverMenu(SpaceAdventure window) {
        this.window = window;
        setLayout(null);

        JButton btnExit	   = new JButton("Exit Game");
        JButton btnNewGame = new JButton("New Game");

        btnExit.setBounds((SpaceAdventure.XUPPERBOUND / 2) - 125, (SpaceAdventure.YUPPERBOUND / 2) + 120, 120, 25);
        btnNewGame.setBounds((SpaceAdventure.XUPPERBOUND / 2) + 5, (SpaceAdventure.YUPPERBOUND / 2) + 120, 120, 25);

        add(btnExit);
        add(btnNewGame);

        btnNewGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                GameOverMenu.this.window.startNewGame();
                GameOverMenu.this.window.getGame().startGame();
                GameOverMenu.this.window.setCurrentPanel(GameOverMenu.this.window.getPanel("game"));
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {}

            @Override
            public void mouseExited(MouseEvent arg0) {}

            @Override
            public void mousePressed(MouseEvent arg0) {}

            @Override
            public void mouseReleased(MouseEvent arg0) {}	
        });

        btnExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {}

            @Override
            public void mouseExited(MouseEvent arg0) {}

            @Override
            public void mousePressed(MouseEvent arg0) {}

            @Override
            public void mouseReleased(MouseEvent arg0) {}
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(window.getGame().lastLevel() && window.getGame().getCurrentLevel().levelCompleted()) {
            g.drawImage(SpriteManager.getImage("endofgame"), 0, 0, null);
        }else {
            g.drawImage(SpriteManager.getImage("gameover"), 0, 0, null);
        }

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.WHITE);
        if(window.getGame().getPlayer().getScore() > ScoreManager.getHighscore()) {
            g.drawImage(SpriteManager.getImage("new-highscore"), (SpaceAdventure.XUPPERBOUND / 2) - 140, (SpaceAdventure.YUPPERBOUND / 2) + 42, null);
        }

        g.drawImage(SpriteManager.getImage("sprite-score"), (SpaceAdventure.XUPPERBOUND / 2) - 100, (SpaceAdventure.YUPPERBOUND / 2) + 45, (SpaceAdventure.XUPPERBOUND / 2) - 100 + 18, ((SpaceAdventure.YUPPERBOUND / 2) + 45) + 24, 0, 0, 18, 24, null);
        g.drawString("Your score : " + window.getGame().getPlayer().getScore(), (SpaceAdventure.XUPPERBOUND / 2) - 80, (SpaceAdventure.YUPPERBOUND / 2) + 60);

        g.drawImage(SpriteManager.getImage("sprite-score"), (SpaceAdventure.XUPPERBOUND / 2) - 100, (SpaceAdventure.YUPPERBOUND / 2) + 75, (SpaceAdventure.XUPPERBOUND / 2) - 100 + 18, ((SpaceAdventure.YUPPERBOUND / 2) + 75) + 24, 18, 0, 36, 24, null);
        g.drawString("Current highscore : " + ScoreManager.getHighscore(), (SpaceAdventure.XUPPERBOUND / 2) - 80, (SpaceAdventure.YUPPERBOUND / 2) + 95);
    }
}