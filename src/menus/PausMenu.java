package menus;

import game.*;
import staticManagers.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * The PausMenu is the panel that pops up when the game is paused
 *
 */
public class PausMenu extends JPanel {
    private static final long serialVersionUID = 1L;
    private SpaceAdventure window;

    public PausMenu(SpaceAdventure window) {
        this.window = window;
        setLayout(null);
        setFocusable(true);

        JButton btnExit      = new JButton("Exit Game");
        JButton btnNewGame   = new JButton("New Game");
        JButton btnPlay = new JButton("Play");

        btnExit.setBounds((SpaceAdventure.XUPPERBOUND / 2) - 190, (SpaceAdventure.YUPPERBOUND / 2) + 40, 120, 25);
        btnNewGame.setBounds((SpaceAdventure.XUPPERBOUND / 2) - 60, (SpaceAdventure.YUPPERBOUND / 2) + 40, 120, 25);
        btnPlay.setBounds((SpaceAdventure.XUPPERBOUND / 2) + 70, (SpaceAdventure.YUPPERBOUND / 2) + 40, 120, 25);	

        add(btnExit);
        add(btnNewGame);
        add(btnPlay);

        btnPlay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                PausMenu.this.window.getGame().toggleMenu();
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
                PausMenu.this.window.startNewGame();
                PausMenu.this.window.getGame().startGame();
                PausMenu.this.window.setCurrentPanel(PausMenu.this.window.getPanel("game"));
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

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_M) {
                    PausMenu.this.window.getGame().toggleMenu();
                }
            }
        });
    }
       
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(SpriteManager.getImage("pausmenu"), 0, 0, null);
    }
}