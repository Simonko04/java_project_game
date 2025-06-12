package items;

import character.CharInBattle;
import enemies.Enemy;

import javax.swing.*;

/**
 * factory na vytvorenie consumables
 * @param <T>
 */
public abstract class FactoryOfConsumables <T extends Consumable> {
    private T papanie;
    public FactoryOfConsumables() {
        this.papanie = createConsumable();
    }

    public ImageIcon getImage(){
        return papanie.getImage();
    }

    /**
     *
     * @param player aby sa vedelo nastavit zdravie
     * @param enemy aby sa vedelo nastavit stastie
     */
    public void consume(CharInBattle player, Enemy enemy){
        try{
            papanie.consume(player, enemy);
        }catch(RanOutException e){}

    }

    public int getAmount(){
        return papanie.getAmount();
    }


    public abstract T createConsumable();
}
