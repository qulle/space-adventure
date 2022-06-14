package staticManagers;

import java.awt.Color;

/**
 * The static ColorManager enum has all the custom hex colorcodes for the game
 *
 */
public enum ColorManager {
    YELLOW_LIGHT("#FFD52D"),
    YELLOW_DARK("#DD8A1C"),
    RED_LIGHT("#F7767D"),
    RED_DARK("#DB3A43"),
    BLUE_LIGHT("#5E9DD9"),
    BLUE_DARK("#2D6193");

    private String hex;
    private ColorManager(String hex) {
        this.hex = hex;
    }

    public Color getColor() {
        return Color.decode(hex);
    }
}