package items;
import character.CharInBattle;
import enemies.Enemy;

import javax.swing.*;

public interface Consumable {
    public ImageIcon getImage();
    public void consume(CharInBattle player, Enemy enemy)  throws RanOutException;
    public int getAmount();
}
