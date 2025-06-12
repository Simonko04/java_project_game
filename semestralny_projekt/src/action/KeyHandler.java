package action;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * tato classa sluzi na zaznamenia tlacitok
 */
public class KeyHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed, sellected;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     *
     * @param e the event to be processed vybera, ze co sa stane
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (key == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (key == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            sellected = true;
        }


    }

    /**
     * tu sa rozhoduje, ci sa pustil kluc
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            sellected = false;
        }

    }

    public boolean isUpPressed() {
        return upPressed;
    }


    public boolean isDownPressed() {
        return downPressed;
    }


    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }


    public boolean isSellected() {
        return sellected;
    }

}
