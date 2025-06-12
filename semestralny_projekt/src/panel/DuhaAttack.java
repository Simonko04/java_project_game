package panel;

import character.CharInBattle;
import enemies.*;
import projectiles.*;
import javax.swing.*;
import java.awt.*;


/**
 * classa, ktorá načíta farebný obdĺžnik, ktorým môže hráč ubrať HP enemy
 */
public class DuhaAttack extends InBattle{
    private Duha_rectangle duhaRectangle;
    private boolean tam = true;
    public DuhaAttack(JFrame mainFrame, Enemy enemy, CharInBattle vBattle) {
        super(mainFrame, enemy, vBattle);
        duhaRectangle = new Duha_rectangle(scale, enemy, vBattle);
        duhaRectangle.setProjectile_x(4*tileSize);
        fps = 60 * (enemy.getLevel()+1);//zmena fps, aby sa pohyboval rychlejsie
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.drawImage(duhaRectangle.getProjectile_img(), 4 * tileSize,  4*tileSize,8*tileSize, 3 * tileSize, null);
        g2.drawImage(enemyImage, screenWidth / 2 - tileSize, tileSize, 2 * tileSize, 2 * tileSize, null);

        g2.setColor(Color.WHITE);
        g2.fillRect(duhaRectangle.getProjectile_x(), 4*tileSize, tileSize/10, 3*tileSize);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Enemy health = "+enemy1.getHealth() + "/"+enemy1.getMaxHealth(),
                getWidth() / 2 - 40, 3*tileSize + tileSize / 2);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Player health = "+vBattle.getHealth() + "/"+vBattle.getMaxHealth(),
                getWidth() / 2 - 40, 7*tileSize + tileSize / 2);

        g2.dispose();
    }

    /**
     * tato metoda reaguje na SPACE a nasledne sa podla strategy rozhoduje, co sa stane dalej(ubranie HP hracovi/enemy)
     * v pripade, ze nikto nema 0HP, tak hra pokracuje dalej do utoku, kde sa switche panel, v pripade, ze je dakto
     * ma 0HP, tak hrac bud vyhra/prehra podla toho, ze kto ma 0HP
     */
    protected void update() {
        if (duhaRectangle.getProjectile_x() <= 4*tileSize) {
            tam = true;
        }
        if (duhaRectangle.getProjectile_x() + duhaRectangle.getProjectile_speed() >= (12*tileSize - tileSize/10)) {
            tam = false;
        }
        if (tam) {
            duhaRectangle.setProjectile_x(duhaRectangle.getProjectile_x() + duhaRectangle.getProjectile_speed());
        }
        if(!tam){
            duhaRectangle.setProjectile_x(duhaRectangle.getProjectile_x() - duhaRectangle.getProjectile_speed());
        }
        if (keyHandler.isSellected()){
            duhaRectangle.rectangle_pressed(tileSize);
            fps = 60;
            if (enemy1.getHealth() <= 0 || vBattle.getHealth() <= 0){
                switchPanel(new EndOfLevel(mainFrame, vBattle, enemy1));
            }
            else {
                if (enemy1.getLevel() == 3) {
                    switchPanel(new TearAttack(mainFrame, enemy1, vBattle));
                } else if (enemy1.getLevel() == 1) {
                    switchPanel(new FlyingAttack(mainFrame, enemy1, vBattle));
                } else if (enemy1.getLevel() == 2) {
                    switchPanel(new ButteflyAttack(mainFrame, enemy1, vBattle));
                } else if (enemy1.getLevel() == 4) {
                    switchPanel(new FlappyAttack(mainFrame, enemy1, vBattle));
                }
            }
        }

    }
}
