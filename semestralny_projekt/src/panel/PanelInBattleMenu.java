package panel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Map;

import character.CharInBattle;
import enemies.*;

/**
 * toto je v podstate menu proti oponentovi, hráč sa tu rozhoduje ako ďalej
 */
public class PanelInBattleMenu extends InBattle {
    private int buttonSellected = 1;
    private Image button1_not;
    private Image button1;
    private Image attack_sellected;
    private Image attack_not;
    private Image act_sellected;
    private Image act_not;
    private Image item_sellected;
    private Image item_not;
    private Image mercy_sellected;
    private Image mercy_not;
    private boolean leftPressedLast = false;
    private boolean rightPressedLast = false;
    private boolean sellectedLast = false;
    private final Map<Integer, Runnable> buttonActions = Map.of(
            1, () -> switchPanel(new DuhaAttack(mainFrame, enemy1, vBattle)),
            2, () -> switchExistingPanel("Act"),
            3, () -> switchExistingPanel("Itemy"),
            4, () -> {
                clearPanel();
                switchPanel(new PanelInMenu(mainFrame, vBattle));
            }
    );//mapa pre lambdu

    /**
     * v tejto metode sa nacitaju obrazky
     * @param mainFrame
     * @param enemy
     * @param vBattle
     */
    public PanelInBattleMenu(JFrame mainFrame, Enemy enemy, CharInBattle vBattle) {
        super(mainFrame, enemy, vBattle);
        URL imgUrl = getClass().getResource("/resources/button_checked.png");
        URL imgUrl2 = getClass().getResource("/resources/button_unchecked.png");
        button1_not = new ImageIcon(imgUrl).getImage();
        button1 = new ImageIcon(imgUrl2).getImage();
        URL imgUrl3 = getClass().getResource("/resources/attack_pressed.png");
        URL imgUrl4 = getClass().getResource("/resources/attack.png");
        attack_sellected = new ImageIcon(imgUrl3).getImage();
        attack_not = new ImageIcon(imgUrl4).getImage();
        URL imgUrl5 = getClass().getResource("/resources/act_pressed.png");
        URL imgUrl6 = getClass().getResource("/resources/act.png");
        act_sellected = new ImageIcon(imgUrl5).getImage();
        act_not = new ImageIcon(imgUrl6).getImage();
        URL imgUrl7 = getClass().getResource("/resources/item_pressed.png");
        URL imgUrl8 = getClass().getResource("/resources/item.png");
        item_sellected = new ImageIcon(imgUrl7).getImage();
        item_not = new ImageIcon(imgUrl8).getImage();
        URL imgUrl9 = getClass().getResource("/resources/mercy_pressed.png");
        URL imgUrl10 = getClass().getResource("/resources/mercy.png");
        mercy_sellected = new ImageIcon(imgUrl9).getImage();
        mercy_not = new ImageIcon(imgUrl10).getImage();
        ItemMenu Itemy = new ItemMenu(mainFrame, enemy1, vBattle);
        Act acty = new Act(mainFrame, enemy1, vBattle);
        storePanel("Itemy", Itemy);
        storePanel("Menu", this);
        storePanel("Act", acty);
    }

    /**
     * v tejto metode sa vykresli cela obrazovka - je tu vetvenie, lebo vybrane tlacitko je tmavsie
     * hrac vybera tlacika A-D
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3)); // Outline thickness of 3 pixels
        g2.drawRect(screenWidth/2 - 6 * tileSize, 4*tileSize, 12*tileSize, 2*tileSize);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString(enemy1.getName(), getWidth() / 2 - 50, 30);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Enemy health = "+enemy1.getHealth() + "/"+enemy1.getMaxHealth(),
                getWidth() / 2 - 40, 3*tileSize + tileSize / 2);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Player health = "+vBattle.getHealth() + "/"+vBattle.getMaxHealth(),
                getWidth() / 2 - 40, 6*tileSize + tileSize / 2);

        if (buttonSellected == 1) {
            g2.drawImage(attack_sellected, screenWidth/ 2 - 6 * tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(act_not, screenWidth/2 - 3* tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(item_not, screenWidth/2 + tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(mercy_not, screenWidth/2 + 4* tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
        }

        else if(buttonSellected == 2) {
            g2.drawImage(act_sellected, screenWidth/2 - 3* tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(attack_not, screenWidth/ 2 - 6 * tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(item_not, screenWidth/2 + tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(mercy_not, screenWidth/2 + 4* tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
        }
        else if (buttonSellected == 3) {
            g2.drawImage(item_sellected, screenWidth/2 + tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(act_not, screenWidth/2 - 3* tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(attack_not, screenWidth/ 2 - 6 * tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
            g2.drawImage(mercy_not, screenWidth/2 + 4* tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
        }


        else if (buttonSellected == 4) {
                g2.drawImage(mercy_sellected, screenWidth/2 + 4* tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
                g2.drawImage(attack_not, screenWidth/ 2 - 6 * tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
                g2.drawImage(item_not, screenWidth/2 + tileSize, screenHeight - 2* tileSize, 2 * tileSize, tileSize, null);
                g2.drawImage(act_not, screenWidth/2 - 3* tileSize, screenHeight - 2*tileSize, 2 * tileSize, tileSize, null);
        }

        g2.drawImage(enemyImage, screenWidth / 2 - tileSize, tileSize, 2 * tileSize, 2 * tileSize, null);

        g2.dispose();
    }

    /**
     * toto je pre vyberanie tlacitok - robi sa to A-D a SPACE sa potvrdi vyber
     */
    protected void update() {
        if (keyHandler.isLeftPressed() && buttonSellected > 1) {
            if (!leftPressedLast) {
                buttonSellected -= 1;
                leftPressedLast = true;
            }
        } else {
            leftPressedLast = false;
        }

        if (keyHandler.isRightPressed() && buttonSellected < 4) {
            if (!rightPressedLast) {
                buttonSellected += 1;
                rightPressedLast = true;
            }
        } else {
            rightPressedLast = false;
        }

        if (keyHandler.isSellected()) {
            if (!sellectedLast) {
                buttonActions.getOrDefault(buttonSellected, () -> {}).run();//vybertlacidla
                sellectedLast = true;
            }
        } else {
            sellectedLast = false;
        }
    }
}
