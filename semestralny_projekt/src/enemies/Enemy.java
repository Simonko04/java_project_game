package enemies;

import java.awt.*;
import java.util.Random;

/**
 * abstraktna classa, ktora vytvára oponenta
 */
public abstract class Enemy {
    protected int health;
    protected int maxHealth;
    protected int level;
    protected double likelihood;
    protected Image obrazok;
    protected String name;
    protected String nice;
    protected String notNice;
    protected String scared;
    protected String notScared;

    /**
     * tato metoda rozhoduje, ci hrac dostane kladnu, alebo zapornu odpoved v rpg prvkoch hry
     * @return
     */
    public String response(){
        Random rand = new Random();
        if (rand.nextDouble() <= likelihood){
            return nice;
        }
        else{
            return notNice;
        }
    }

    public Image getObrazok() {
        return obrazok;
    }


    public String getName() {
        return name;
    }


    public double getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(double likelihood) {
        this.likelihood = likelihood;
    }

    public int getLevel() {
        return level;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }


    public String getScared() {
        return scared;
    }

    public String getNotScared() {
        return notScared;
    }
}
