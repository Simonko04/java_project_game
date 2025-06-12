package projectiles;

import javax.swing.*;
import java.net.URL;
import java.util.Random;

/**
 * musie projektily pre froggita
 */
public class FlyProjectile extends Projectile{
    private ImageIcon projectile_img2;
    private int offset_x, offset_y;//pre nahodny pohyb much
    private int screenWidth, tileSize;

    /**
     *
     * @param x kvoli zaciatocnej suradnici
     * @param y kvoli zaciatocnej suradnici
     * @param screenWidth kvoli nastaovaniu offsetu - pre pripad, ze nabural o okraj, tak sa nehybe
     * @param tileSize kvoli nastaovaniu offsetu - pre pripad, ze nabural o okraj, tak sa nehybe
     */
    public FlyProjectile(int x, int y, int screenWidth, int tileSize){
        super();
        this.projectile_x = x;
        this.projectile_y = y;
        this.screenWidth = screenWidth;
        this.tileSize = tileSize;
        URL imgUrl = getClass().getResource("/resources/mucha.gif");
        this.projectile_img2 = new ImageIcon(imgUrl);
    }
    public ImageIcon getProjectile_img2() {
        return projectile_img2;
    }

    /**
     * nahodne sa vybera smer, ktorym mucha pojde, aby sa hrac musel vyhnut a nebolo to lahko predvidatelne
     */
    public void setoffset(){
        Random random = new Random();
        int randomNumber = random.nextInt(5) - 2;
        offset_x = randomNumber;
        random = new Random();
        randomNumber = random.nextInt(5) - 2;
        offset_y = randomNumber;
    }

    public int getOffset_x() {
        return offset_x;
    }

    public int getOffset_y() {
        return offset_y;
    }

    /**
     * nastavuje xova suradnica, kym sa sprace do vyhradenej plochy
     * @param novyprojectile_x suradnica
     */
    @Override
    public void setProjectile_x(int novyprojectile_x) {
        if (novyprojectile_x > screenWidth/2 - tileSize && novyprojectile_x < screenWidth/2 + tileSize - 16){
            this.projectile_x = novyprojectile_x;
        }
    }

    /**
     * nastavuje xova suradnica, kym sa sprace do vyhradenej plochy
     * @param novyprojectile_y suradnica
     */
    @Override
    public void setProjectile_y(int novyprojectile_y) {
        if (novyprojectile_y > 4 * tileSize && novyprojectile_y < 6 * tileSize - 16){
            this.projectile_y = novyprojectile_y;
        }
    }

    /**
     * overuje sa stret hraca a muchy
     * @param projX
     * @param projY
     * @param playerX
     * @param playerY
     * @param playerSize
     * @return
     */
    public boolean collision(int projX, int projY, int playerX, int playerY, int playerSize) {
        if (projX < playerX + playerSize &&
                projX + 16 > playerX &&
                projY < playerY + playerSize &&
                projY + 16 > playerY){
            return true;
        }
        return false;
    }
}
