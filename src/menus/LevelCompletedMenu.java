package menus;

import game.*;
import staticManagers.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The LevelCompleted is the panel that pops up when a level is completed
 *
 */
public class LevelCompletedMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private SpaceAdventure window;

    public LevelCompletedMenu(SpaceAdventure window) {
        this.window = window;
        setLayout(null);

        JButton btnExit      = new JButton("Exit Game");
        JButton btnNewGame   = new JButton("New Game");
        JButton btnNextLevel = new JButton("Next Level");

        btnExit.setBounds((SpaceAdventure.XUPPERBOUND / 2) - 190, (SpaceAdventure.YUPPERBOUND / 2) + 70, 120, 25);
        btnNewGame.setBounds((SpaceAdventure.XUPPERBOUND / 2) - 60, (SpaceAdventure.YUPPERBOUND / 2) + 70, 120, 25);
        btnNextLevel.setBounds((SpaceAdventure.XUPPERBOUND / 2) + 70, (SpaceAdventure.YUPPERBOUND / 2) + 70, 120, 25);

        add(btnExit);
        add(btnNewGame);
        add(btnNextLevel);	

        btnNextLevel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                LevelCompletedMenu.this.window.getGame().nextLevel();
                LevelCompletedMenu.this.window.getGame().startGame();
                LevelCompletedMenu.this.window.setCurrentPanel(LevelCompletedMenu.this.window.getPanel("game"));
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

        btnNewGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                LevelCompletedMenu.this.window.startNewGame();
                LevelCompletedMenu.this.window.getGame().startGame();
                LevelCompletedMenu.this.window.setCurrentPanel(LevelCompletedMenu.this.window.getPanel("game"));
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
        g.drawImage(SpriteManager.getImage("levelcompleted"), 0, 0, null);
    }
}