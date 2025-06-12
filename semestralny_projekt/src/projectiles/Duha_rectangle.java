package projectiles;
import character.CharInBattle;
import enemies.Enemy;


import javax.swing.*;
import java.net.URL;

/**
 * toto je obdĺžnik, ktorý sa spustí pri začatí battle
 */
public class Duha_rectangle extends Projectile {
    public Duha_rectangle(int speed, Enemy enemy, CharInBattle player) {
        super();
        this.projectile_speed = speed;
        this.player = player;
        this.enemy = enemy;
        URL img3Url = getClass().getResource("/resources/duha.png");
        this.projectile_img = new ImageIcon(img3Url).getImage();
    }

    /**
     * hrac uz klikol mezernik a je potrebne aby sa mu but ubralo HP, alebo aby sa oponentovi ubralo HP
     * @param tileSize kvoli attack
     */

    public void rectangle_pressed(int tileSize) {
        ProjectileDamageStrategy utok = new RectangleDamageStrategy();
        utok.attack(player, enemy, tileSize, projectile_x, projectile_y);
    }
}
