package projectiles;

import character.CharInBattle;
import enemies.Enemy;

/**
 * stratégia damage pre motýľa
 */
public class ButterflyDamageStrategy implements ProjectileDamageStrategy {
    /**
     *
     * @param player aby bolo komu ubrat
     * @param enemy inheritted
     * @param tileSize inheritted
     * @param projectile_x inheritted
     * @param projectile_y inheritted
     */
    @Override
    public void attack(CharInBattle player, Enemy enemy, int tileSize, int projectile_x, int projectile_y){
        if (player.getHealth() > 5){
            player.setHealth(player.getHealth() - 5);
        }
        else{
            player.setHealth(0);
        }
    }
}
