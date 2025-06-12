package panel;

import character.CharInBattle;
import enemies.Enemy;
import projectiles.ButteflyProjectile;
import projectiles.ButterflyDamageStrategy;
import projectiles.ProjectileDamageStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * classa, ktorá riadi útok motýlov pri útoku Whimsun
 */
public class ButteflyAttack extends Attack{
    private List<ButteflyProjectile> projectiles = new ArrayList<ButteflyProjectile>();
    private int instance = 0;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private int offsetx;
    private int offsety;
    private ProjectileDamageStrategy damageStrategy = new ButterflyDamageStrategy();
    public ButteflyAttack(JFrame mainFrame, Enemy enemy, CharInBattle vBattle){
        super(mainFrame, enemy, vBattle);
        this.x = screenWidth/2 + tileSize/2;
        this.y = 3*tileSize;
        this.x2 = screenWidth/2 - tileSize/2;
        this.y2 = 3*tileSize;
        this.offsetx = 0;
        this.offsety = 1;
        startTimersInSequence();
    }

    /**
     * tato metoda kresli utoky lietajucih motylov, ktore idu vo vlnach, menia sa v metode startTimersInSequence()
     * taktiez overuje stret hraca a projektilov, ktore predstavuju motyle, dokopy sa vystrieda 4x
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
        g2.drawImage(enemyImage, screenWidth / 2 - tileSize, tileSize, 2 * tileSize, 2 * tileSize, null);
        g2.drawRect(screenWidth/2 - tileSize, 4*tileSize, 2 * tileSize, 2 * tileSize);
        g2.drawImage(vBattle.getPlayerImage(), vBattle.getPlayerX(), vBattle.getPlayerY(), tileSize / 4,  tileSize / 4, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Enemy health = "+enemy1.getHealth() + "/"+enemy1.getMaxHealth(),
                getWidth() / 2 - 40, 3*tileSize + tileSize / 2);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Player health = "+vBattle.getHealth() + "/"+vBattle.getMaxHealth(),
                getWidth() / 2 - 40, 6*tileSize + tileSize / 2);

        for (ButteflyProjectile projectile : projectiles) {
            g2.drawImage(projectile.getProjectile_img(), projectile.getProjectile_x(), projectile.getProjectile_y(), tileSize/4, tileSize/4, null);
            projectile.setProjectile_x(projectile.getProjectile_x() + projectile.getOffsetx());
            projectile.setProjectile_y(projectile.getProjectile_y() + projectile.getOffsety());
            if(projectile.collision(projectile.getProjectile_x(), projectile.getProjectile_y(), vBattle.getPlayerX(),
                    vBattle.getPlayerY(), tileSize/4, tileSize/4)){
                timer.stop();
                damageStrategy.attack(vBattle, enemy1, tileSize, projectile.getProjectile_x(), projectile.getProjectile_y());
                if (vBattle.getHealth() == 0){
                    switchPanel(new EndOfLevel(mainFrame, vBattle, enemy1));
                }
                else {
                    switchExistingPanel("Menu");
                }
            }
        }
        g2.dispose();
    }

    /**
     * tato metoda nastavuje smer letu motylov a odkial letia, takisto ich pridava do listu, po uplinuti
     * bol hrac uspesny a ma dalsiu moznost pokracovat
     */
    private void startTimersInSequence() {
        timer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (projectiles.size() < 70) {
                    projectiles.add(new ButteflyProjectile(x, y , offsetx, offsety));
                    projectiles.add(new ButteflyProjectile(x2, y2, offsetx, offsety));
                    repaint();
                } else {
                    if (instance == 0){
                        instance = 1;
                        x = screenWidth/2 - 2 * tileSize;
                        x2 = x;
                        y = 4*tileSize + tileSize/8;
                        y2 = 5*tileSize + tileSize/8;
                        offsetx = 1;
                        offsety = 0;
                        timer.stop();
                        projectiles.clear();
                        startTimersInSequence();
                    }
                    else if (instance == 1){
                        instance = 2;
                        x = screenWidth/2 + 2 * tileSize;
                        x2 = screenWidth/2 + 3 * tileSize;
                        y = 3*tileSize;
                        y2 = 3*tileSize;
                        offsetx = -1;
                        offsety = 1;
                        timer.stop();
                        projectiles.clear();
                        startTimersInSequence();
                    }
                    else if (instance == 2){
                        instance = 3;
                        x = screenWidth/2 + 2 * tileSize;
                        x2 = screenWidth/2 + 3 * tileSize;
                        y = 6*tileSize;
                        y2 = 6*tileSize;
                        offsetx = - 1;
                        offsety = - 1;
                        timer.stop();
                        projectiles.clear();
                        startTimersInSequence();
                    }
                    else{
                        timer.stop();
                        switchExistingPanel("Menu");
                    }
                }
            }
        });
        timer.start();
    }
}