package enemies;

import javax.swing.*;
import java.net.URL;

public class Froggit extends Enemy {
    public Froggit(double x) {
        this.likelihood = x;
        URL imgUrl = getClass().getResource("/resources/froggit-sprite.png");
        obrazok = new ImageIcon(imgUrl).getImage();
        name = "Froggit";
        this.level = 1;
        this.maxHealth = 20;
        this.health = this.maxHealth;
        nice = "nice";//pre response
        notNice = "notNice";
        scared = "Froggit didn't understand a word but is scared anyways";
        notScared = "Froggit didn't understand a word but is unimpressed, prepare to DIE!";
    }
}
