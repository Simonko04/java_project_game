package panel;
import character.*;
import enemies.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * tu sa rozhoduje o výhre alebo prehre a následne sa po 3och sekundách prepne do menu
 */
public class EndOfLevel extends GamePanel{
    private Image obrazok;
    private int playerHealth;
    private Timer timer;

    /**
     * tato metoda zisti, ze kto ma 0HP a nasledne podla toho rozdhodne, ci hrac prehral/vyhral, taktiez nastavi
     * hracov level pomocou metody playerSetter v CharInBattle a casovac tu je, aby tam ten obrazok chvilu aj bol
     * @param mainFrame
     * @param player
     * @param enemy
     */
    public EndOfLevel(JFrame mainFrame, CharInBattle player, Enemy enemy) {
        super(mainFrame);
        this.playerHealth = player.getHealth();
        if (playerHealth <= 0){
            URL imgUrl = getClass().getResource("/resources/death.png");
            this.obrazok = new ImageIcon(imgUrl).getImage();
        }
        else{
            URL imgUrl = getClass().getResource("/resources/win.png");
            player.playerSetter(enemy);
            this.obrazok = new ImageIcon(imgUrl).getImage();
        }
        timer = new Timer(3000, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                timer.stop();
                player.setHealth(player.getMaxHealth());
                switchPanel(new PanelInMenu(mainFrame, player));
                }
        });
        timer.start();
    }

    /**
     * vykresli obrazok u win/ u died
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(obrazok, 0 ,  0,16 * tileSize, 9 * tileSize, null);
        g2.dispose();
    }
}
