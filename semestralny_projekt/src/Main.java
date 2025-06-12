import javax.swing.*;

import character.CharInBattle;
import panel.*;


import java.awt.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setTitle("Undertale-like combat system");

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        PanelInMenu menuPanel;
        CharInBattle player = new CharInBattle();
        player.setLevel(1);
        menuPanel = new PanelInMenu(frame, player);

        mainPanel.add(menuPanel, "Menu");

        frame.add(mainPanel);
        gd.setFullScreenWindow(frame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        menuPanel.startGameThread();

    }
}