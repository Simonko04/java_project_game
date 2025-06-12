package projectiles;

import character.CharInBattle;
import enemies.Enemy;

/**
 * damage strategy pre obdĺžnik pri začatí battle
 */
public class RectangleDamageStrategy implements ProjectileDamageStrategy{
    /**
     * tu sa zistuje, ze na ktorej casti obdlznika hrac klikol enter, teda je potrebne zistit suradnicu obdlznika, ktory
     * sa pohybuje po obldzniku a nasledne rozhodnut, ze komu ubrat kolko hp
     * @param player pre pripad, ze sa netrafil do zelenej casti je potrebne mu ubrat hp
     * @param enemy ak sa hrac trafil, je potrebne enemy ubrat hp
     * @param tileSize aby bol znamy rozmer obdlznika na obrazovke - toto je kvoli roznym rozliseniam
     * @param projectile_x aby bola znama poloha ukazovacieho obdlznicka
     * @param projectile_y aby bola znama poloha ukazovacieho obdlznicka
     */
    @Override
    public void attack(CharInBattle player, Enemy enemy, int tileSize, int projectile_x, int projectile_y){
        if (projectile_x + tileSize/10 < 5* tileSize || projectile_x > 11 * tileSize){
            player.setHealth(player.getHealth() - 5 * enemy.getLevel());

        }
        else if(projectile_x + tileSize/10 < 6* tileSize || projectile_x > 10 * tileSize){
            player.setHealth(player.getHealth() - 3 * enemy.getLevel());

        }
        else if(projectile_x + tileSize/10 < 7* tileSize || projectile_x > 9 * tileSize){
            player.setHealth(player.getHealth() - 2 * enemy.getLevel());

        }
        else if(projectile_x + tileSize/10 < (7 * tileSize + (tileSize * 2)/3) || projectile_x > (8 * tileSize + tileSize/3)){
            enemy.setHealth(enemy.getHealth() - 5);
        }
        else{
            enemy.setHealth(enemy.getHealth() - 10);

        }
    }
}
