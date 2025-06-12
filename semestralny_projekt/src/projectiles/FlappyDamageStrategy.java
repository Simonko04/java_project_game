package projectiles;

import character.CharInBattle;
import enemies.Enemy;

/**
 * damage strategy pre stĺpy pri flappy birdovi
 */
public class FlappyDamageStrategy implements ProjectileDamageStrategy{
    /**
     *
     * @param player aby sa mu dalo ubrat hp
     * @param enemy inheritted
     * @param tileSize inheritted
     * @param projectile_x inheritted
     * @param projectile_y inheritted
     */
    public void attack(CharInBattle player, Enemy enemy, int tileSize, int projectile_x, int projectile_y){
        if (player.getHealth() > 12){
            player.setHealth(player.getHealth() - 12);
        }
        else{
            player.setHealth(0);
        }
    }
}
