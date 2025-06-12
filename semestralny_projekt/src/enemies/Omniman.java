package enemies;

import javax.swing.*;
import java.net.URL;

/**
 * main boss fight
 */
public class Omniman extends Enemy{
    public Omniman(double x) {
        this.likelihood = x;
        URL imgUrl = getClass().getResource("/resources/omniman.png");
        obrazok = new ImageIcon(imgUrl).getImage();
        name = "Omniman";
        this.level = 4;
        this.maxHealth = 50;
        this.health = this.maxHealth;
        nice = "nice";//pre response
        notNice = "notNice";
        scared = "Omniman nearly shat himself";
        notScared = "Omniman is not scared of worms like you, prepare to DIE!";
    }
}
