package panel;
import character.CharInBattle;
import enemies.*;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * v tejto classe sa vyberajú itemy na použitie
 */
public class ItemMenu extends InBattle{
    private Image xko;
    private int buttonSellected ;
    private boolean leftPressedLast = false;
    private boolean rightPressedLast = false;
    private boolean sellectedLast = false;
    public ItemMenu(JFrame mainFrame, Enemy enemy, CharInBattle vBattle) {
        super(mainFrame, enemy, vBattle);
        URL imgUrl = getClass().getResource("/resources/xko.png");
        xko = new ImageIcon(imgUrl).getImage();
        buttonSellected = 1;
        pretzelImage = food.getImage();
        luckImage = luck.getImage();
    }

    /**
     * v tejto metode sa vykresluju pristupne itemy, v pripade, ze su nedostupne su sedym
     * takisto sa tu nachadza kurzor, ktory ukazuje na aktualny item
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
        g2.drawImage(enemyImage, screenWidth / 2 - tileSize, tileSize, 2 * tileSize, 2 * tileSize, null);
        g2.drawImage(pretzelImage.getImage(), screenWidth / 2 - 5* tileSize, 5 * tileSize, 2 * tileSize, 2 * tileSize, null);
        g2.drawImage(luckImage.getImage(), screenWidth / 2 - tileSize, 5 * tileSize, 2 * tileSize, 2 * tileSize, null);
        g2.drawImage(xko, screenWidth / 2 + 3 * tileSize, 5 * tileSize, 2 * tileSize, 2 * tileSize, null);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Enemy health = "+enemy1.getHealth() + "/"+enemy1.getMaxHealth(),
                getWidth() / 2 - 40, 3*tileSize + tileSize / 2);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Player health = "+vBattle.getHealth() + "/"+vBattle.getMaxHealth(),
                getWidth() / 2 - 40, 8*tileSize + tileSize / 2);

        if (buttonSellected == 1) {
            g2.setColor(Color.WHITE);
            g2.fillRect(screenWidth/2 - 4 * tileSize, 7 * tileSize, tileSize/4, tileSize/4);
        }
        if (buttonSellected == 2) {
            g2.setColor(Color.WHITE);
            g2.fillRect(screenWidth/2, 7 * tileSize, tileSize/4, tileSize/4);
        }
        if (buttonSellected == 3) {
            g2.setColor(Color.WHITE);
            g2.fillRect(screenWidth/2 + 4 *tileSize, 7 * tileSize, tileSize/4, tileSize/4);
        }

        g2.dispose();
    }

    /**
     * tato metoda umoznuje pohyb kurzora a tak isto rozhoduje, ze kam sa pohnut moze a kam nie
     * napr. ak uz bol zjedeny pretzel, tak uz sa tam nemoze pohnut
     * v pripade klinutia SPACE sa vykona akcia - a) zjedenie pretzel b) priadanie likelihood c) odidenie
     * ak uz nieco bolo zjedene, tak to zosedne a hrac to pri pohybe "preskoci"
     */
    protected void update(){
        if (keyHandler.isLeftPressed() && buttonSellected > 1) {
            if (!leftPressedLast) {
                buttonSellected -= 1;
                leftPressedLast = true;
                if (food.getAmount() == 0 && buttonSellected == 1) {
                    buttonSellected = 2;
                }
                if (luck.getAmount() == 0 && buttonSellected == 2) {
                    if (food.getAmount() > 0) {
                        buttonSellected = 1;
                    }
                    else{
                        buttonSellected = 3;
                    }

                }
            }
        } else {
            leftPressedLast = false;
        }

        if (keyHandler.isRightPressed() && buttonSellected < 3) {
            if (!rightPressedLast) {
                buttonSellected += 1;
                rightPressedLast = true;
                if (luck.getAmount() == 0 && buttonSellected == 2) {
                    buttonSellected = 3;
                }
            }
        } else {
            rightPressedLast = false;
        }

        if (keyHandler.isSellected()) {
            if (!sellectedLast) {
                if (buttonSellected == 1){
                    food.consume(vBattle, enemy1);
                    if(luck.getAmount() > 0){
                        buttonSellected = 2;
                    }
                    else{
                        buttonSellected = 3;
                    }

                }
                else if(buttonSellected == 2) {
                    luck.consume(vBattle, enemy1);
                    if (food.getAmount() > 0) {
                        buttonSellected = 1;
                    } else {
                        buttonSellected = 3;
                    }
                }
                else if (buttonSellected == 3) {
                    switchExistingPanel("Menu");
                }

                sellectedLast = true;
            }
        } else {
            sellectedLast = false;
        }
    }
}
