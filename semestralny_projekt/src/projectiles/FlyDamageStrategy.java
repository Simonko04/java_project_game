package projectiles;

import character.CharInBattle;
import enemies.Enemy;

/**
 * damage strategy pre muchu
 */
public class FlyDamageStrategy implements ProjectileDamageStrategy {
    /**
     *
     * @param player aby sa mu dalo ubrat hp
     * @param enemy inheritted
     * @param tileSize inheritted
     * @param projectile_x inheritted
     * @param projectile_y inheritted
     */
    @Override
    public void attack(CharInBattle player, Enemy enemy, int tileSize, int projectile_x, int projectile_y){
        if (player.getHealth() > 2){
            player.setHealth(player.getHealth() - 2);
        }
        else{
            player.setHealth(0);
        }
    }
}
