package staticManagers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 * The SpriteManager class handles all the images in the game
 *
 */
public class SpriteManager {
    private static final String spritePath = "./sprites/";
    private static HashMap<String, BufferedImage> bufferedImages = new HashMap<String, BufferedImage>();
    private static boolean hasLoaded = false;
    
    @SuppressWarnings("serial")
    private static ArrayList<String> imagesToLoad = new ArrayList<String>() {{
        add("world-1.png");
        add("window-icon.png");
        add("enemystatus.png");
        add("new-highscore.png");
        add("startscreen.png");
        add("pausmenu.png");
        add("levelcompleted.png");
        add("endofgame.png");
        add("gameover.png");
        add("sprite-life.png");
        add("sprite-score.png");
        add("sprite-player.png");
        add("sprite-powerups.png");
        add("sprite-large-battleship.png");
        add("sprite-small-battleship.png");
    }};

    public static void loadImages() {
        if(hasLoaded) {
            return;
        }

        for(String spriteName : imagesToLoad) {
            try {
                bufferedImages.put(spriteName.split("\\.")[0], ImageIO.read(new File(spritePath + spriteName)));
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

        hasLoaded = true;
    }
       
    public static BufferedImage getImage(String spriteName) {
        return bufferedImages.get(spriteName);
    }
}