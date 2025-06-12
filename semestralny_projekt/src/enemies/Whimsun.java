package enemies;

import javax.swing.*;
import java.net.URL;

public class Whimsun extends Enemy{
    public Whimsun(double x) {
        this.likelihood = x;
        URL imgUrl = getClass().getResource("/resources/whimsun.png");
        obrazok = new ImageIcon(imgUrl).getImage();
        name = "Whimsun";
        this.level = 2;
        this.maxHealth = 30;
        this.health = this.maxHealth;
        nice = "nice";//pre response
        notNice = "notNice";
        scared = "Halfway through your first word, Whimsun burst into tears and ran away";
        notScared = "Whimsum shook a little, but is now standing still, prepare to DIE!";
    }
}
