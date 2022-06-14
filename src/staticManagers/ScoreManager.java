package staticManagers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * The static ScoreManager class handles the highscore
 *
 */
public class ScoreManager {
    private static int highScore = 0;

    private static void saveHighscore(int score) {
        DataOutputStream os;

        try {
            os = new DataOutputStream(new FileOutputStream("highscore.dat"));
            os.writeInt(score);
            os.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleScore(int score) {
        if(score > highScore) {
            saveHighscore(score);
        }
    }

    public static void loadHighscore() {
        DataInputStream is;
        try {
            is = new DataInputStream(new FileInputStream("highscore.dat"));
            highScore = is.readInt();
            is.close();
        }catch (FileNotFoundException e) {
            saveHighscore(0);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static int getHighscore() {
        return highScore;
    }
}