package panel;

import character.CharInBattle;
import enemies.Enemy;


import javax.swing.*;

/**
 * abstract class, ktorá slúži najmä na ovládanie počas battle vo štvorčeku
 */
public abstract class Attack extends InBattle{
    protected Timer timer;
    public Attack(JFrame frame, Enemy enemy, CharInBattle vBattle) {

        super(frame, enemy, vBattle);
        vBattle.setPlayerX(screenWidth/2 - tileSize/8);//nastavi hraca do stredu
        vBattle.setPlayerY(5*tileSize);
    }

    /**
     * toto je pre pohyb hraca po hracej ploche
     */
    protected void update() {
        if (keyHandler.isUpPressed() && vBattle.getPlayerY() > 4 * tileSize) {
            vBattle.setPlayerY(vBattle.getPlayerY() - playerSpeed);
        }
        if (keyHandler.isDownPressed() && vBattle.getPlayerY() <= (6 * tileSize - tileSize/4 - 3)){
            vBattle.setPlayerY(vBattle.getPlayerY() + playerSpeed);
        }
        if (keyHandler.isLeftPressed() && vBattle.getPlayerX() > screenWidth/2 - tileSize) {
            vBattle.setPlayerX(vBattle.getPlayerX() - playerSpeed);
        }
        if (keyHandler.isRightPressed() && vBattle.getPlayerX() < (screenWidth/2 + tileSize - tileSize/4) ) {
            vBattle.setPlayerX(vBattle.getPlayerX() + playerSpeed);
        }
    }
}
