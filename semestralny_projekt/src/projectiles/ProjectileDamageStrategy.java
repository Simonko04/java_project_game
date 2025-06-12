package projectiles;

import character.CharInBattle;
import enemies.Enemy;

public interface ProjectileDamageStrategy {
    public void attack(CharInBattle player, Enemy enemy, int tileSize, int projectile_x, int projectile_y);
}
