package projectiles;
import character.CharInBattle;
import enemies.Enemy;

import java.awt.*;

public abstract class Projectile {
    protected int projectile_x;
    protected int projectile_speed;
    protected int projectile_y;
    protected Enemy enemy;
    protected CharInBattle player;
    protected Image projectile_img;
    public Projectile(){};

    public int getProjectile_x() {
        return projectile_x;
    }

    public void setProjectile_x(int projectile_x) {
        this.projectile_x = projectile_x;
    }

    public int getProjectile_speed() {
        return projectile_speed;
    }

    public int getProjectile_y() {
        return projectile_y;
    }

    public void setProjectile_y(int projectile_y) {
        this.projectile_y = projectile_y;
    }

    public Image getProjectile_img() {
        return projectile_img;
    }
}
