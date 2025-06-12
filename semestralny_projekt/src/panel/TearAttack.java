package panel;

import enemies.Enemy;
import character.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


import projectiles.*;

/**
 * útok sĺz od napstablook
 */
public class TearAttack extends Attack{
    private List<TearProjectile> projectiles = new ArrayList<TearProjectile>();
    public TearAttack(JFrame frame, Enemy enemy, CharInBattle vBattle) {
        super(frame, enemy, vBattle);
        timer = new Timer(400, new ActionListener() {//casovac, aby sa kazdych 400ms spawnli 2 projektily

            public void actionPerformed(ActionEvent e) {
                if (projectiles.size() < 75) {
                    projectiles.add(new TearProjectile(screenWidth/2,  tileSize + tileSize/2));
                    projectiles.add(new TearProjectile(screenWidth/2 + tileSize / 4,  tileSize + tileSize/2));
                    repaint();
                } else {
                    timer.stop();
                    switchExistingPanel("Menu");
                }
            }
        });
        timer.start();
    }

    /**
     * nakresli prostredie, projektyly a skontroluje, ci hrac nenabural pomocou metodu v triede TearProjectile
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


        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Enemy health = "+enemy1.getHealth() + "/"+enemy1.getMaxHealth(),
                getWidth() / 2 - 40, 3*tileSize + tileSize / 2);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Player health = "+vBattle.getHealth() + "/"+vBattle.getMaxHealth(),
                getWidth() / 2 - 40, 6*tileSize + tileSize / 2);


        for (TearProjectile projectile : projectiles) {
            int projX = projectile.getProjectile_x();
            int projY = projectile.getProjectile_y();
            int projSize = tileSize / 8;
            int playerX = vBattle.getPlayerX();
            int playerY = vBattle.getPlayerY();
            int playerSize = tileSize / 4;


            boolean collision = projectile.collision(projX, projY, playerX, playerY, playerSize, projSize);
            if (collision) {
                timer.stop();
                ProjectileDamageStrategy utok = new TearDamageStrategy();
                utok.attack(vBattle, enemy1, tileSize, projectile.getProjectile_x(), projectile.getProjectile_y());
                if (vBattle.getHealth() == 0){
                    switchPanel(new EndOfLevel(mainFrame, vBattle, enemy1));
                }
                else {
                    switchExistingPanel("Menu");
                }
            }

            g2.drawImage(projectile.getProjectile_img(), projectile.getProjectile_x(), projectile.getProjectile_y(), tileSize/8, tileSize/8, null);
            projectile.setProjectile_y(projectile.getProjectile_y() + 3);
            projectile.setCounter(projectile.getCounter() + 1);
            if (projectile.getCounter() == 4 && projectile.getProjectile_x() > screenWidth/2 - tileSize &&
                    projectile.getProjectile_x() < screenWidth/2 + tileSize - tileSize/8 - 3) {
                projectile.setProjectile_x(projectile.getProjectile_x() + projectile.getX_offset());
                projectile.setCounter(0);
            }


        }



        g2.drawImage(vBattle.getPlayerImage(), vBattle.getPlayerX(), vBattle.getPlayerY(), tileSize / 4,  tileSize / 4, null);



        g2.dispose();
    }

}
