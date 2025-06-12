package panel;

import character.CharInBattle;
import enemies.Enemy;
import projectiles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * tu je combat systém froggita - hráča naháňajú muchy
 */
public class  FlyingAttack extends Attack{
    private List<FlyProjectile> projectiles = new ArrayList<FlyProjectile>();
    private FlyProjectile projectile1;
    private FlyProjectile projectile2;
    private FlyProjectile projectile3;
    private FlyProjectile projectile4;
    private int counter = 0;
    private boolean collision1, collision2, collision3, collision4;

    /**
     * tu sa vytvoria 4 muchy a nasledne sa im pomocou casovacu 4x zmeni smer letu
     * @param mainFrame
     * @param enemy
     * @param vBattle
     */
    public FlyingAttack(JFrame mainFrame, Enemy enemy, CharInBattle vBattle) {
        super(mainFrame, enemy, vBattle);
        projectile1 = new FlyProjectile(screenWidth/2 - tileSize/8, 4*tileSize, screenWidth, tileSize);
        projectile2 = new FlyProjectile(screenWidth/2 + tileSize/8, 4*tileSize, screenWidth, tileSize);
        projectile3 = new FlyProjectile(screenWidth/2 + 2*tileSize/8, 4*tileSize, screenWidth, tileSize);
        projectile4 = new FlyProjectile(screenWidth/2 - 2*tileSize/8, 4*tileSize, screenWidth, tileSize);
        timer = new Timer(400, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                counter++;
                projectile1.setoffset();
                projectile2.setoffset();
                projectile3.setoffset();
                projectile4.setoffset();
                if (counter == 20){
                    timer.stop();
                    switchExistingPanel("Menu");
                }
            }
        });
        timer.start();
    }

    /**
     * tuna sa iba vykresluju hrac, muchy a kontroluje kolizia
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

        projectile1.setProjectile_x(projectile1.getProjectile_x() + projectile1.getOffset_x());
        projectile1.setProjectile_y(projectile1.getProjectile_y() + projectile1.getOffset_y());
        projectile2.setProjectile_x(projectile2.getProjectile_x() + projectile2.getOffset_x());
        projectile2.setProjectile_y(projectile2.getProjectile_y() + projectile2.getOffset_y());
        projectile3.setProjectile_x(projectile3.getProjectile_x() + projectile3.getOffset_x());
        projectile3.setProjectile_y(projectile3.getProjectile_y() + projectile3.getOffset_y());
        projectile4.setProjectile_x(projectile4.getProjectile_x() + projectile4.getOffset_x());
        projectile4.setProjectile_y(projectile4.getProjectile_y() + projectile4.getOffset_y());

        projectile1.getProjectile_img2().paintIcon(this, g2, projectile1.getProjectile_x(), projectile1.getProjectile_y());
        projectile2.getProjectile_img2().paintIcon(this, g2, projectile2.getProjectile_x(), projectile2.getProjectile_y());
        projectile3.getProjectile_img2().paintIcon(this, g2, projectile3.getProjectile_x(), projectile3.getProjectile_y());
        projectile3.getProjectile_img2().paintIcon(this, g2, projectile4.getProjectile_x(), projectile4.getProjectile_y());

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Enemy health = "+enemy1.getHealth() + "/"+enemy1.getMaxHealth(),
                getWidth() / 2 - 40, 3*tileSize + tileSize / 2);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Player health = "+vBattle.getHealth() + "/"+vBattle.getMaxHealth(),
                getWidth() / 2 - 40, 6*tileSize + tileSize / 2);

        collisions();
        g2.dispose();
    }

    /**
     * tuna sa kontroluju kolizie, ak ku nejakej doslo, tak sa spusti FlyDamageStrategy, tak isto v pripade
     * 0hp hrac prehral, inak sa vracia do inBattleMenu, kde sa rozhoduje dalej
     */
    private void collisions() {
        collision1 = projectile1.collision(projectile1.getProjectile_x(), projectile1.getProjectile_y(), vBattle.getPlayerX(),
                vBattle.getPlayerY(), tileSize/4);
        collision2 = projectile2.collision(projectile2.getProjectile_x(), projectile2.getProjectile_y(), vBattle.getPlayerX(),
                vBattle.getPlayerY(), tileSize/4);
        collision3 = projectile3.collision(projectile3.getProjectile_x(), projectile3.getProjectile_y(), vBattle.getPlayerX(),
                vBattle.getPlayerY(), tileSize/4);
        collision4 = projectile4.collision(projectile4.getProjectile_x(), projectile4.getProjectile_y(), vBattle.getPlayerX(),
                vBattle.getPlayerY(), tileSize/4);
        if (collision1 || collision2 || collision3 || collision4){
            timer.stop();
            ProjectileDamageStrategy utok = new FlyDamageStrategy();
            utok.attack(vBattle, enemy1, tileSize, projectile1.getProjectile_x(), projectile1.getProjectile_y());
            if (vBattle.getHealth() == 0){
                switchPanel(new EndOfLevel(mainFrame, vBattle, enemy1));
            }
            else {
                switchExistingPanel("Menu");
            }
        }
    }
}
