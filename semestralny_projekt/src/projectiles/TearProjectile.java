package projectiles;

import javax.swing.*;
import java.net.URL;
import java.util.Random;

/**
 * slzička od Napstablook
 */
public class TearProjectile extends Projectile {
    private int x_offset;//pre pohyb vlavo a pravo
    private int counter;
    public TearProjectile(int x, int y) {
        super();
        URL img3Url = getClass().getResource("/resources/slzicka.png");
        this.projectile_img = new ImageIcon(img3Url).getImage();
        this.projectile_x = x;
        this.projectile_y = y;
        x_offset = offsetX();
        counter = 0;

    }

    /**
     * pri vytvoreni objektu sa mu urci offset na xovej osi je to od -4 do 4, aby sa pohyboval ako do lava, tak aj
     * do prava
     * @return
     */
    public int offsetX() {
        Random random = new Random();
        int randomNumber = random.nextInt(8) - 4;
        return randomNumber;
    }

    /**
     * zistuje, ci sa hrac nestretol s projektilom
     * @param projX suradnica projektilu
     * @param projY suradnica projektilu
     * @param playerX suradnica hraca
     * @param playerY suradnica hraca
     * @param playerSize suradnica hraca
     * @param projSize suradnica hraca
     * @return
     */
    public boolean collision(int projX, int projY, int playerX, int playerY, int playerSize, int projSize) {
        if (projX < playerX + playerSize &&
                projX + projSize > playerX &&
                projY < playerY + playerSize &&
                projY + projSize > playerY){
            return true;
        }
        return false;
    }

    public int getX_offset() {
        return x_offset;
    }


    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
