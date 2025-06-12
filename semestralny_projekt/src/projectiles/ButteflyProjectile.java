package projectiles;

import javax.swing.*;
import java.net.URL;

/**
 * motýlí projektil pre Whimsun
 */
public class ButteflyProjectile extends Projectile{
    private int offsetx;
    private int offsety;
    private URL img3Url;

    /**
     *
     * @param x pre pohyb
     * @param y pre pohyb
     * @param offsetx pre nahodny a nepredvidatelny pohyb po osi x
     * @param offsety pre nahodny a nepredvidatelny pohyb po osi y
     */
    public ButteflyProjectile(int x, int y, int offsetx, int offsety) {
        super();
        if (offsety == -1){
            img3Url = getClass().getResource("/resources/right_tilt_butterfly.png");
        }
        else if(offsetx == -1){
            img3Url = getClass().getResource("/resources/left_tilt_butterfly.png");
        }
        else{
            img3Url = getClass().getResource("/resources/butterfly.png");
        }
        this.projectile_img = new ImageIcon(img3Url).getImage();
        this.projectile_x = x;
        this.projectile_y = y;
        this.offsetx = offsetx * 4;//*4, aby sa to nehybalo prilis pomaly
        this.offsety = offsety * 4;
    }

    /**
     * aby sa zistilo, ci sa stretol hrac s projektilom
     * @param projX
     * @param projY
     * @param playerX
     * @param playerY
     * @param playerSize
     * @param projSize
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

    public int getOffsetx() {
        return offsetx;
    }

    public int getOffsety() {
        return offsety;
    }

}
