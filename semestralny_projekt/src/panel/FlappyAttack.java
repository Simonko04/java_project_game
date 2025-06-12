package panel;

import character.CharInBattle;
import enemies.Enemy;
import projectiles.FlappyDamageStrategy;
import projectiles.FlappyProjectile;
import projectiles.ProjectileDamageStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * tu sa hrá flappy bird
 */
public class FlappyAttack extends InBattle{
    private Timer timer;
    private int counter;
    private boolean sellectedLast = false;
    private boolean move = true;
    private ProjectileDamageStrategy flappyDamageStrategy = new FlappyDamageStrategy();
    private List<FlappyProjectile> projectiles = new ArrayList<FlappyProjectile>();

    /**
     * v tejto metode sa kazdych 800ms vytvori novy stlp do flappybirda a prida sa do arrayListy
     * @param mainFrame
     * @param enemy
     * @param vBattle
     */
    public FlappyAttack(JFrame mainFrame, Enemy enemy, CharInBattle vBattle){
        super(mainFrame, enemy, vBattle);
        vBattle.setPlayerX(screenWidth/2 - tileSize/8);
        vBattle.setPlayerY(5*tileSize);
        this.fps = 120;//aby plinulejsie padal hrac
        timer = new Timer(800, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                counter++;
                projectiles.add(new FlappyProjectile(screenWidth/2 + 2 * tileSize));
                if (counter == 40){
                    timer.stop();
                    switchExistingPanel("Menu");
                }
            }
        });
        timer.start();
    }

    /**
     * v tejto metode sa vykresluje hrac a stlpy, tak isto sa pri prechode stlpu hracovov poziciov kontroluje kolizia
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
        g2.drawRect(screenWidth/2 - 2 * tileSize, 4*tileSize, 4 * tileSize, 4 * tileSize);
        g2.drawImage(vBattle.getPlayerImage(), vBattle.getPlayerX(), vBattle.getPlayerY(), tileSize / 4,  tileSize / 4, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Enemy health = "+enemy1.getHealth() + "/"+enemy1.getMaxHealth(),
                getWidth() / 2 - 40, 3*tileSize + tileSize / 2);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Player health = "+vBattle.getHealth() + "/"+vBattle.getMaxHealth(),
                getWidth() / 2 - 40, 8*tileSize + tileSize / 2);


        for (FlappyProjectile projectile : projectiles){
            if (projectile.getProjectile_x() <= screenWidth/2 - 2 * tileSize){}
            else{
                g2.drawRect(projectile.getProjectile_x(), 4*tileSize, 4,
                        projectile.getUpper_border()*tileSize/2);
                g2.drawRect(projectile.getProjectile_x(), 4*tileSize + projectile.getUpper_border()*tileSize/2 + tileSize,
                        4, 8*tileSize - (4*tileSize + projectile.getUpper_border()*tileSize/2 + tileSize));
                projectile.setProjectile_x(projectile.getProjectile_x() -2);
            }
            if ((projectile.getProjectile_x() == screenWidth/2 - tileSize/8 + tileSize/4)
                    && projectile.collision(vBattle.getPlayerY(), tileSize)){//tu sa kontroluje kolizia
                flappyDamageStrategy.attack(vBattle, enemy1, tileSize, projectile.getProjectile_x(), projectile.getProjectile_y());
                if (vBattle.getHealth() == 0){
                    switchPanel(new EndOfLevel(mainFrame, vBattle, enemy1));
                }
                else{
                    switchExistingPanel("Menu");
                }
                timer.stop();
            }
            else if (((projectile.getProjectile_x() < screenWidth/2 - tileSize/8 + tileSize/4) &&
                    (projectile.getProjectile_x() > screenWidth/2 - tileSize/8 - 4))
                    && projectile.collision(vBattle.getPlayerY(), tileSize)){//tu sa zabezpecuje klzanie, aby hrac nepresiel cez stlp
                move = false;
            }
        }
        g2.dispose();
    }

    /**
     * ak hrac klikne SPACE, tak ide vyssie, inak pada, ak prave prechadza cez stlp, tak do neho nemoze naburat, takze
     * sa nemeni jeho Y-ova suradnica
     */
    protected void update(){
        if (keyHandler.isSellected()) {
            if(!sellectedLast){
                sellectedLast = true;
                if (vBattle.getPlayerY() > 4*tileSize + tileSize/4){
                    if (move){
                        vBattle.setPlayerY(vBattle.getPlayerY() - tileSize/12);
                    }
                    else {
                        move = true;
                    }
                }

            }
            else{
                sellectedLast = false;
            }
        }
        else{
            if (vBattle.getPlayerY() < 8*tileSize - tileSize/4){
                if (move){vBattle.setPlayerY(vBattle.getPlayerY() + 3);}
                else{
                    move = true;
                }
                sellectedLast = false;
            }
        }

    }
}
