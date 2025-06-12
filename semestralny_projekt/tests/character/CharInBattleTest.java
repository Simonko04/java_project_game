
import action.KeyHandler;
import character.CharInBattle;
import enemies.Enemy;
import enemies.Omniman;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class CharInBattleTest {
    private CharInBattle charInBattle;
    private Enemy enemy;

    @BeforeEach
    void setUp() {
        charInBattle = new CharInBattle();
        enemy = new Omniman(0.1);
    }
    @org.junit.jupiter.api.Test
    void setGetLevel(){
        charInBattle.setLevel(2);
        assertEquals(charInBattle.getLevel(), 2);
    }

    @org.junit.jupiter.api.Test
    void setGetHealth(){
        charInBattle.setHealth(charInBattle.getMaxHealth());
        assertEquals(charInBattle.getMaxHealth(), charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void setGetX(){
        charInBattle.setPlayerX(15);
        assertEquals(charInBattle.getPlayerX(), 15);
    }
    @org.junit.jupiter.api.Test
    void setGetY(){
        charInBattle.setPlayerY(15);
        assertEquals(charInBattle.getPlayerY(), 15);
    }

    @org.junit.jupiter.api.Test
    void playerSetterTest(){
        charInBattle.setLevel(4);
        charInBattle.playerSetter(enemy);
        assertEquals(charInBattle.getLevel(), 5);
    }

    @org.junit.jupiter.api.Test
    void setGetImage() {
        Image expected = new ImageIcon(getClass().getResource("/resources/srdce.png")).getImage();

        charInBattle.setPlayerImage();
        Image actual = charInBattle.getPlayerImage();

        assertTrue(imagesAreEqual(expected, actual));
    }

    private boolean imagesAreEqual(Image img1, Image img2) {
        BufferedImage bimg1 = toBufferedImage(img1);
        BufferedImage bimg2 = toBufferedImage(img2);

        if (bimg1.getWidth() != bimg2.getWidth() || bimg1.getHeight() != bimg2.getHeight()) {
            return false;
        }

        for (int x = 0; x < bimg1.getWidth(); x++) {
            for (int y = 0; y < bimg1.getHeight(); y++) {
                if (bimg1.getRGB(x, y) != bimg2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bimage = new BufferedImage(
                img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}
