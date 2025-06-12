package enemies;

import javax.swing.*;
import java.net.URL;

public class Napstablook extends Enemy{
    public Napstablook(double x) {
        this.likelihood = x;
        URL imgUrl = getClass().getResource("/resources/napstablook.png");
        obrazok = new ImageIcon(imgUrl).getImage();
        name = "Napstablook";
        this.level = 3;
        this.maxHealth = 30;
        this.health = this.maxHealth;
        nice = "nice";//pre response
        notNice = "notNice";
        scared = "Napstablook cannot stop crying, you animal :(";
        notScared = "Napstablook may be crying, but is not afraid of you, prepare to DIE!";
    }
}
