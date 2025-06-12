
package panel;

import character.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import enemies.*;

/**
 * main menu
 */
public class PanelInMenu extends GamePanel {
    private CharInBattle vMenu;
    private Image omniImage;
    private Image froggitImage;
    private Image whimsunImage;
    private Image napstablookImage;
    private Image saveImage;
    private Image loadImage;
    private boolean saved = false;
    private boolean loaded = false;
    private int playerX = screenWidth / 2;
    private int playerY = screenHeight - 3* tileSize;
    protected Image playerImage;

    public PanelInMenu(JFrame mainFrame, CharInBattle vMenu) {
        super(mainFrame);
        this.vMenu = vMenu;
        URL imgUrl = getClass().getResource("/resources/playerImage.png");
        playerImage = new ImageIcon(imgUrl).getImage();
        URL img2Url = getClass().getResource("/resources/omniman.png");
        omniImage= new ImageIcon(img2Url).getImage();
        URL img3Url = getClass().getResource("/resources/froggit-sprite.png");
        froggitImage= new ImageIcon(img3Url).getImage();
        URL img4Url = getClass().getResource("/resources/whimsun.png");
        whimsunImage= new ImageIcon(img4Url).getImage();
        URL img5Url = getClass().getResource("/resources/napstablook.png");
        napstablookImage= new ImageIcon(img5Url).getImage();
        URL img6Url = getClass().getResource("/resources/load.png");
        loadImage = new ImageIcon(img6Url).getImage();
        URL img7Url = getClass().getResource("/resources/save.png");
        saveImage = new ImageIcon(img7Url).getImage();
    }

    /**
     * vykresluje povodne menu, obsahuje obrazky oponentov a pod nimi su nakreslene stvorceky, ktore umoznuju
     * zacatie battle, v pripade, ze level je nedostacujuci, stvorecky su sede, taktiez vykresluje save a load, co je
     * pre nacitanie predchadzajuceho levelu
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.drawImage(omniImage, screenWidth - 4* tileSize ,  tileSize,3* tileSize, 3 * tileSize, null);
        g2.drawImage(froggitImage, tileSize ,  2* tileSize,2* tileSize, 2 * tileSize, null);
        g2.drawImage(whimsunImage, 5 *tileSize ,  2* tileSize,2* tileSize, 2 * tileSize, null);
        g2.drawImage(napstablookImage, 9 *tileSize ,  2* tileSize,2* tileSize, 2 * tileSize, null);
        g2.drawImage(saveImage, tileSize, 7* tileSize,2* tileSize, tileSize, null);
        g2.drawImage(loadImage, 13*tileSize, 7* tileSize,2* tileSize, tileSize, null);
        g2.setColor(Color.WHITE);
        g2.fillRect(tileSize+ tileSize/2, 4*tileSize, tileSize, tileSize);
        if (vMenu.getLevel() < 2) {
            g2.setColor(Color.GRAY);
        }
        g2.fillRect(5*tileSize + tileSize/2, 4*tileSize, tileSize, tileSize);
        if (vMenu.getLevel() < 3) {
            g2.setColor(Color.GRAY);
        }
        g2.fillRect(9*tileSize + tileSize/2, 4*tileSize, tileSize, tileSize);
        if (vMenu.getLevel() < 4) {
            g2.setColor(Color.GRAY);
        }
        g2.fillRect(screenWidth - 3* tileSize, 4*tileSize, tileSize, tileSize);
        g2.drawImage(playerImage, playerX, playerY, tileSize, tileSize, null);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.drawString("Main Menu", getWidth() / 2 - 40, 30);
        g2.drawString("LEVEL = " + vMenu.getLevel(), getWidth() / 2 - 30, getHeight() - 30);

        g2.dispose();
    }

    /**
     * uklada hracov progress
     */
    private void saveGame() {
        new Thread(() -> {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.ser"))) {
                out.writeObject(vMenu);
                saved = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * nacita hracov progress
     */
    private void loadGame() {
        new Thread(() -> {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.ser"))) {
                CharInBattle loadedChar = (CharInBattle) in.readObject();
                SwingUtilities.invokeLater(() -> {
                    vMenu = loadedChar;
                    vMenu.setPlayerImage();
                    loaded = true;
                });
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * podla suradnic hraca vybera oponenta, popripade nacita progress, alebo ulozi progress
     */
    protected void update() {
        if (keyHandler.isUpPressed() == true && playerY >= 0) {
            playerY -= playerSpeed;
        }
        if (keyHandler.isDownPressed() == true && playerY <= (screenHeight - 2 * tileSize)) {
            playerY += playerSpeed;
        }
        if (keyHandler.isLeftPressed() == true && playerX >= 0) {
            playerX -= playerSpeed;
        }
        if (keyHandler.isRightPressed() == true && playerX <= (screenWidth -  tileSize)) {
            playerX += playerSpeed;
        }
        if (playerX >= screenWidth - 4* tileSize && playerX < screenWidth - 3*tileSize && playerY < 5 * tileSize && playerY >= 4*tileSize
        && vMenu.getLevel() >= 4) {
            Omniman omniman = new Omniman(0.01);
            switchPanel(new PanelInBattleMenu(mainFrame, omniman, vMenu));
        }
        if (playerX >= (tileSize) && playerX < (2*tileSize + tileSize/2) && playerY < 5 * tileSize && playerY >= 4*tileSize) {
            Froggit froggit = new Froggit(0.8);
            switchPanel(new PanelInBattleMenu(mainFrame, froggit, vMenu));
        }
        if (playerX >= 5*(tileSize) && playerX < (6*tileSize + tileSize/2) && playerY < 5 * tileSize && playerY >= 4*tileSize
                && vMenu.getLevel() >= 2) {
            Whimsun whimsun = new Whimsun(0.6);
            switchPanel(new PanelInBattleMenu(mainFrame, whimsun, vMenu));
        }
        if (playerX >= 9*(tileSize) && playerX < (10*tileSize + tileSize/2) && playerY < 5 * tileSize && playerY >= 4*tileSize
                && vMenu.getLevel() >= 3) {
            Napstablook napstablook = new Napstablook(0.3);
            switchPanel(new PanelInBattleMenu(mainFrame, napstablook, vMenu));
        }
        if (playerX >= tileSize && playerX < tileSize + 2 * tileSize &&
                playerY >= 6 * tileSize && playerY < 8 * tileSize && !saved) {
            saveGame();
        }

        if (playerX >= 13 * tileSize && playerX < 13 * tileSize + 2 * tileSize &&
                playerY >= 6 * tileSize && playerY < 8 * tileSize && !loaded) {
            loadGame();
            vMenu.setPlayerImage();
        }
    }
}
