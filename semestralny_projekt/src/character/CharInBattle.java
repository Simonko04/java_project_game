package character;

import enemies.Enemy;

import javax.swing.*;
import java.net.URL;

/**
 * tato classa sluzi na vytvorenie hraca
 */
public class CharInBattle extends GameCharacter {
    /**
     * kvoli ulozeniu
     */
    private static final long serialVersionUID = 1L;

    public CharInBattle() {
        super();
        URL img3Url = getClass().getResource("/resources/srdce.png");
        playerImage = new ImageIcon(img3Url).getImage();
        maxHealth = 20;
        health = maxHealth;
    }

    /**
     *v pripade, ze hrac vyhra nad este neporazenym oponentom, zmeni sa mu level
     * @param enemy parameter, na nastavenie levelu v pripade uspechu
     */
    public void playerSetter(Enemy enemy){
        if(level == enemy.getLevel()){
            level++;
        }
        health = maxHealth;
    }
}

