package panel;
import character.CharInBattle;
import enemies.Enemy;

import javax.swing.*;
import java.awt.*;

/**
 * v tejto casti sa v podstate implementuju rpg prvky
 */
public class Act extends InBattle{
    private boolean leftPressedLast = false;
    private boolean rightPressedLast = false;

    private boolean sellectedLast;
    private int buttonSellected = 1;

    public Act(JFrame frame, Enemy enemy, CharInBattle vBattle) {
        super(frame, enemy, vBattle);

    }

    /**
     * v tejto methode sa vykresluje menu, v ktorom si vybera hrac sposob komunikacie s enemy
     * farby napisov sa menia podla toho, ze ktora moznost je vybrana v methode update()
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
        g2.drawString("Enemy health = "+enemy1.getHealth() + "/"+enemy1.getMaxHealth(),
                getWidth() / 2 - 40, 3*tileSize + tileSize / 2);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, tileSize/8));
        g2.drawString("Player health = "+vBattle.getHealth() + "/"+vBattle.getMaxHealth(),
                getWidth() / 2 - 40, 8*tileSize + tileSize / 2);

        if (buttonSellected == 1) {
            g2.setColor(Color.ORANGE);

        }
        else{
            g2.setColor(Color.WHITE);
        }
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Check", screenWidth/2 - 4* tileSize, 5*tileSize);
        if (buttonSellected == 2) {
            g2.setColor(Color.ORANGE);
        }
        else{
            g2.setColor(Color.WHITE);
        }
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Flirt", screenWidth/2 + tileSize, 5*tileSize);

        if (buttonSellected == 3) {
            g2.setColor(Color.ORANGE);
        }
        else{
            g2.setColor(Color.WHITE);
        }
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Threaten", screenWidth/2 - 4* tileSize, 7*tileSize);

        g2.dispose();
    }

    /**
     * tu sa pomocou WASD meni rozsvieteny napis a nasledne sa pomocou SPACE vybera moznost dalsej akcie
     * stats ukaze hracovi statistky oponenta, Flirt sa pokusi ukoncit battle s oponentom pomocou lichotiek
     * a threaten sa ho pokusi vystrasit, pravdepodobnost uspechu sa urcuje podla atributu likelihood v enemy triede
     */
    protected void update() {
        if (keyHandler.isLeftPressed() && buttonSellected == 2) {
            if (!leftPressedLast) {
                buttonSellected = 1;
                leftPressedLast = true;
            }
        } else {
            leftPressedLast = false;
        }

        if (keyHandler.isRightPressed()) {
            if (!rightPressedLast) {
                buttonSellected = 2;
                rightPressedLast = true;
            }
        } else {
            rightPressedLast = false;
        }
        if (keyHandler.isUpPressed() && buttonSellected == 3) {
            if (!rightPressedLast) {
                buttonSellected = 1;
                rightPressedLast = true;
            }
        } else {
            rightPressedLast = false;
        }
        if (keyHandler.isDownPressed()) {
            if (!rightPressedLast) {
                buttonSellected = 3;
                rightPressedLast = true;
            }
        } else {
            rightPressedLast = false;
        }

        if (keyHandler.isSellected()) {
            if (!sellectedLast) {
                if (buttonSellected == 1){
                    switchPanel(new Response(mainFrame, enemy1, "stats", vBattle));
                }
                else if(buttonSellected == 2) {
                    switchPanel(new Response(mainFrame, enemy1, enemy1.response(), vBattle));
                }
                else if (buttonSellected == 3) {
                    if(enemy1.response().equals("nice")){
                        switchPanel(new Response(mainFrame, enemy1, "scared", vBattle));
                    }
                    else{
                        switchPanel(new Response(mainFrame, enemy1, "notScared", vBattle));
                    }

                }

                sellectedLast = true;
            }
        } else {
            sellectedLast = false;
        }
    }

}
