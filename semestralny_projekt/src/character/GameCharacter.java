package character;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.net.URL;

public abstract class GameCharacter  implements Serializable  {
    protected int level = 8;
    protected int playerX;
    protected int playerY;
    protected transient Image playerImage;
    protected int health;
    protected int maxHealth;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Image getPlayerImage() {
        return playerImage;
    }

    /**
     * nastavuje obrazok pre pripad, ze by sa zmenil v pripade serializacie sa to stava
     */
    public void setPlayerImage() {
        URL img3Url = getClass().getResource("/resources/srdce.png");
        playerImage = new ImageIcon(img3Url).getImage();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }

}