package game;

import menus.GameOverMenu;
import menus.LevelCompletedMenu;
import menus.PausMenu;
import menus.StartMenu;
import staticManagers.SpriteManager;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The SpaceAdventure class has the main method and creates all the objects needed to play the game
 *
 */
public class SpaceAdventure extends JFrame {
    public static final int XUPPERBOUND = 1300;
    public static final int YUPPERBOUND = 650;
    private static final long serialVersionUID = 1L;
    private JPanel currentPanel;
    private StartMenu startMenu;
    private Game game;
    private LevelCompletedMenu levelCompleted;
    private GameOverMenu gameOver;
    private PausMenu pausMenu;
    private Map<String, JPanel> allGamePanels = new HashMap<String, JPanel>();

    public static void main(String[] arg) {
        new SpaceAdventure().setVisible(true);
    }

    public SpaceAdventure() {
        setTitle("Space Adventure");
        getContentPane().setPreferredSize(new Dimension(XUPPERBOUND, YUPPERBOUND));
        pack();
        setResizable(false);

        SpriteManager.loadImages();
        SpaceAdventure.centerWindow(this);

        game           = new Game(this);
        gameOver       = new GameOverMenu(this);
        pausMenu       = new PausMenu(this);
        startMenu      = new StartMenu(this);
        levelCompleted = new LevelCompletedMenu(this);

        initPanels();

        setCurrentPanel(startMenu);
        setIconImage(new ImageIcon(SpriteManager.getImage("window-icon")).getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void startNewGame() {
        game = new Game(this);
        initPanels();
    }

    public Game getGame() {
        return game;
    }

    public void initPanels() {
        allGamePanels.put("game", game);
        allGamePanels.put("gameOver", gameOver);
        allGamePanels.put("pausMenu", pausMenu);
        allGamePanels.put("startMenu", startMenu);
        allGamePanels.put("levelCompleted", levelCompleted);
    }

    public JPanel getPanel(String panelName) {
        return allGamePanels.get(panelName);
    }

    public void setCurrentPanel(JPanel panel) {
        currentPanel = panel;
        getContentPane().removeAll();
        getContentPane().add(currentPanel);
        revalidate();
        currentPanel.requestFocusInWindow();
        repaint();
    }

    public static void centerWindow(Window frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int)((screenSize.getHeight() - frame.getHeight()) / 2) - 10;
        frame.setLocation(x, y);
    }
}