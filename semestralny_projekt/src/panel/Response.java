package panel;
import character.CharInBattle;
import enemies.Enemy;

import javax.swing.*;
import java.awt.*;

/**
 * odpoved enemy na rpg prvky v act
 */
public class Response extends InBattle{
    private String reaction;

    public Response(JFrame frame, Enemy enemy, String reaction, CharInBattle vBattle) {
        super(frame, enemy, vBattle);
        this.reaction = reaction;
    }

    /**
     * nakresli response na rpg prvky - pokracovanie Act class
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(screenWidth/2 - 6 * tileSize, 4*tileSize, 12*tileSize, 4*tileSize);
        g2.drawImage(enemyImage, screenWidth/2 - tileSize, tileSize, 2 * tileSize,2 * tileSize, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));


        if (reaction.equals("stats") ) {
            g2.setFont(new Font("Arial", Font.BOLD, tileSize/4));
            g2.drawString(enemy1.getName()+ " level = "+ enemy1.getLevel() + " and HP = "
                    +enemy1.getHealth()+"/"+enemy1.getMaxHealth(), screenWidth/2 - 3* tileSize, 6*tileSize);
        }
        if (reaction.equals("nice") ) {
            g2.setFont(new Font("Arial", Font.BOLD, tileSize/4));
            g2.drawString(enemy1.getName()+" has fallen in love", screenWidth/2 - 3* tileSize, 6*tileSize);
        }
        if (reaction.equals("notNice") ) {
            g2.setFont(new Font("Arial", Font.BOLD, tileSize/4));
            g2.drawString(enemy1.getName()+" didn't like it, prepare to DIE!", screenWidth/2 - 3* tileSize, 6*tileSize);
        }
        if (reaction.equals("scared") ) {
            g2.setFont(new Font("Arial", Font.BOLD, tileSize/4));
            g2.drawString(enemy1.getScared(), screenWidth/2 - 3* tileSize, 6*tileSize);
        }
        if (reaction.equals("notScared") ) {
            g2.setFont(new Font("Arial", Font.BOLD, tileSize/4));
            g2.drawString(enemy1.getNotScared(), screenWidth/2 - 3* tileSize, 6*tileSize);
        }

        g2.dispose();
    }

    /**
     * po kliknuti enter sa pokracuje dalej, ak bol hrac uspesny, tak vyhral, ak nie, tak zacina battle, ak si
     * iba vypytal stats, tak je presunuty do battle menu, na toto musi stlacit SPACE
     */
    protected void update() {
        if (keyHandler.isSellected() && reaction.equals("stats")) {
            switchExistingPanel("Menu");
        }
        if (keyHandler.isSellected() && reaction.equals("nice")) {
            switchPanel(new EndOfLevel(mainFrame, vBattle, enemy1));
        }
        if (keyHandler.isSellected() && reaction.equals("notNice")) {
            switchPanel(new DuhaAttack(mainFrame, enemy1, vBattle));
        }
        if (keyHandler.isSellected() && reaction.equals("stats")) {
            switchExistingPanel("Menu");
        }
        if (keyHandler.isSellected() && reaction.equals("scared")) {
            switchPanel(new EndOfLevel(mainFrame, vBattle, enemy1));
        }
        if (keyHandler.isSellected() && reaction.equals("notScared")) {
            switchPanel(new DuhaAttack(mainFrame, enemy1, vBattle));
        }
    }
}
