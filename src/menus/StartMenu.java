package menus;

import game.*;
import staticManagers.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The StartMenu class is the panel that shows when the game first starts
 *
 */
public class StartMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private SpaceAdventure window;

    public StartMenu(SpaceAdventure window) {
        this.window = window;
        setLayout(null);

        JButton btnExit      = new JButton("Exit Game");
        JButton btnStartGame = new JButton("Start Game");

        btnExit.setBounds((SpaceAdventure.XUPPERBOUND / 2) - 125, (SpaceAdventure.YUPPERBOUND / 2) + 70, 120, 25);
        btnStartGame.setBounds((SpaceAdventure.XUPPERBOUND / 2) + 5, (SpaceAdventure.YUPPERBOUND / 2) + 70, 120, 25);

        add(btnExit);
        add(btnStartGame);

        btnStartGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                StartMenu.this.window.setCurrentPanel(StartMenu.this.window.getPanel("game"));
                StartMenu.this.window.getGame().startGame();
                StartMenu.this.window.getGame().getCurrentLevel().start();
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
        g.drawImage(SpriteManager.getImage("startscreen"), 0, 0, null);
    }
}