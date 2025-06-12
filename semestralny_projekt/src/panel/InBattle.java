package panel;
import character.*;
import enemies.Enemy;
import items.*;

import javax.swing.*;
import java.awt.*;


/**
 * stará sa o vytvorenie oponenta a nastavenie celého priebehu battle
 */
public abstract class InBattle extends GamePanel{
    protected CharInBattle vBattle;
    protected Enemy enemy1;
    protected Image enemyImage;
    protected Image playerImage;
    protected ImageIcon pretzelImage;
    protected ImageIcon luckImage;
    protected FactoryOfConsumables food;
    protected FactoryOfConsumables luck;


    /**
     * vytvara cely combat system jedneho kontretneho oponenta
     * @param mainFrame
     * @param enemy
     * @param vBattle
     */
    public InBattle(JFrame mainFrame, Enemy enemy, CharInBattle vBattle) {
        super(mainFrame);
        enemy1 = enemy;
        this.vBattle = vBattle;
        food = new PretzelFactory();//toto je uz tu kvoli tomu, ze je iba jedno za cely battle
        luck = new LuckFactory();
        enemyImage = enemy.getObrazok();
        playerImage = vBattle.getPlayerImage();
    }
}
