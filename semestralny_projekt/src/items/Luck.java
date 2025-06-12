package items;

import character.CharInBattle;
import enemies.*;

import javax.swing.*;
import java.net.URL;

/**
 * classa, ktorá vytvára štastie pre použitie ako item
 */
public class Luck implements Consumable {
    private int amount;
    private double luckIncrease;
    private ImageIcon luckImage;

    public Luck() {
        this.amount = 1;
        URL imgUrl = getClass().getResource("/resources/trojlistok.png");
        luckImage = new ImageIcon(imgUrl);
        luckIncrease = 0.3;
    }

    /**
     *
     * @param player
     * @param enemy v pripade, ze je mozne nastavi pravdepodobnost vyssie
     * @throws RanOutException pre pripad, ze doslo
     */
    public void consume(CharInBattle player, Enemy enemy)  throws RanOutException {
        if (amount <= 0){//ak uz nie je Luck, tak exception
            throw new RanOutException("Doslo stastie");
        }
        if (enemy.getLikelihood() + luckIncrease >= 1) {
            enemy.setLikelihood(1);
        }
        else{
            if (enemy.getHealth() >= enemy.getMaxHealth()/2){//ak ma oponent vela HP
                enemy.setLikelihood(enemy.getLikelihood() + luckIncrease);
            }
            else if (enemy.getHealth() >= enemy.getMaxHealth()/4){// ak ma viacej ako 1/4
                enemy.setLikelihood(0.8);
            }
            else{//ak ma velmi malo
                enemy.setLikelihood(0.95);
            }
        }
        amount -= 1;
        if (amount <= 0){
            URL img2Url = getClass().getResource("/resources/luckOut.png");
            luckImage.setImage(new ImageIcon(img2Url).getImage());
        }
    }

    public ImageIcon getImage() {
        return luckImage;
    }

    public int getAmount() {
        return amount;
    }
}
