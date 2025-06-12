package panel;

import action.*;


import javax.swing.*;
import java.awt.*;

import java.util.HashMap;

/**
 * stará sa o bežanie panelu
 */
public abstract class GamePanel extends JPanel implements Runnable{
    protected JFrame mainFrame;
    protected int scale;
    protected int tileSize;
    protected int screenWidth;
    protected int screenHeight;
    protected int playerSpeed = 4;
    private volatile boolean running = false;//aby pri prepinani prestal bezat panel a doslo ku garbage collection



    protected int fps = 60;
    protected Thread gameThread;
    protected KeyHandler keyHandler = new KeyHandler();

    private static HashMap<String, GamePanel> panelStorage = new HashMap<>();//aby sa dali prepinat panely

    public void storePanel(String name, GamePanel gamePanel) {
        panelStorage.put(name, gamePanel);
    }
    public void clearPanel(){
        panelStorage.clear();
    }

    public GamePanel(JFrame mainFrame) {
        this.mainFrame = mainFrame;
        updateScale();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    /**
     * v tejto metode sa prepinaju panely, ktore boli pred tym ulozene,
     * @param name
     */
    public void switchExistingPanel(String name) {
        GamePanel storedPanel = panelStorage.get(name);
        stopGameThread();
        mainFrame.getContentPane().removeAll();
        mainFrame.setContentPane(storedPanel);
        mainFrame.revalidate();
        storedPanel.requestFocusInWindow();
        storedPanel.startGameThread();
        System.gc();
    }

    /**
     * v tejto metode sa prepina na neulozenie panely
     * @param newPanel
     */
    public void switchPanel(GamePanel newPanel) {
        stopGameThread();
        mainFrame.getContentPane().removeAll();
        mainFrame.setContentPane(newPanel);
        mainFrame.revalidate();
        newPanel.requestFocusInWindow();
        newPanel.startGameThread();
        System.gc();
    }

    /**
     * tado metoda zastavuje bezanie aktualneho panelu, aby bolo mozne garbage collection
     */
    private void stopGameThread() {
        running = false;
        if (gameThread != null) {
            Thread tempThread = gameThread;
            gameThread = null;
            tempThread.interrupt();
        }
        this.removeAll();
        this.revalidate();
        this.repaint();

    }

    /**
     * spusta hru
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * nastavuje panel na celu obrazovku
     */
    private void updateScale() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenWidth = screenSize.width;
        screenHeight = screenSize.height;
        scale = screenWidth / (16*16);
        tileSize = 16 * scale;
        playerSpeed = scale;
    }

    /**
     * zabezpecuje bezanie hry a pravidelne prekresluje obrazovku
     */
    @Override
    public void run() {
        running = true;
        double drawInterval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (Thread.currentThread() == gameThread) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime > 0) {
                    Thread.sleep((long) remainingTime);
                }
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    protected void update() {}

    protected void paintComponent(Graphics g) {}

    public int getTileSize() {
        return tileSize;
    }

}
