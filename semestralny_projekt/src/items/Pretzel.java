package items;

import javax.swing.*;
import java.net.URL;

import character.*;
import enemies.Enemy;

/**
 * classa, ktorá vytvára jedlo pre použitie ako item
 */
public class Pretzel implements Consumable {
    private int amount;
    private ImageIcon pretzelImage;

    public Pretzel() {
        this.amount = 1;
        URL imgUrl = getClass().getResource("/resources/pretzel.png");
        pretzelImage = new ImageIcon(imgUrl);
    }

    /**
     *
     * @param player aby sa dalo nastavit zdravie
     * @param enemy
     * @throws RanOutException pre pripad, ze dosli pretzels
     */
    public void consume(CharInBattle player, Enemy enemy)  throws RanOutException{
        if (amount <= 0) {
            throw new RanOutException("Dosli pretzels");
        }
        player.setHealth(player.getMaxHealth());
        amount -= 1;
        if (amount <= 0) {
            URL img2Url = getClass().getResource("/resources/pretzelOut.png");
            pretzelImage.setImage(new ImageIcon(img2Url).getImage());
        }
    }

    public ImageIcon getImage() {
        return pretzelImage;
    }


    public int getAmount() {
        return amount;
    }


}
