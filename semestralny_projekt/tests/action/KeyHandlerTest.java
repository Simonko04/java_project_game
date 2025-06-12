import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import action.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

class KeyHandlerTest {
    private KeyHandler keyHandler;
    private Component dummyComponent;
    @BeforeEach
    void setUp() {
        keyHandler = new KeyHandler();
        dummyComponent = new JPanel();
    }

    private KeyEvent createKeyEvent(int eventType, int keyCode) {
        return new KeyEvent(dummyComponent, eventType, System.currentTimeMillis(), 0, keyCode, KeyEvent.CHAR_UNDEFINED);
    }
    @Test
    void keyTyped() {
    }

    @org.junit.jupiter.api.Test
    void keyPressed_W() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_W);
        keyHandler.keyPressed(pressW);
        assertTrue(keyHandler.isUpPressed());
    }

    @org.junit.jupiter.api.Test
    void keyPressed_A() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_A);
        keyHandler.keyPressed(pressW);
        assertTrue(keyHandler.isLeftPressed());
    }
    @org.junit.jupiter.api.Test
    void keyPressed_S() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_S);
        keyHandler.keyPressed(pressW);
        assertTrue(keyHandler.isDownPressed());
    }
    @org.junit.jupiter.api.Test
    void keyPressed_D() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_D);
        keyHandler.keyPressed(pressW);
        assertTrue(keyHandler.isRightPressed());
    }
    @org.junit.jupiter.api.Test
    void keyPressed_Space() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_PRESSED, KeyEvent.VK_SPACE);
        keyHandler.keyPressed(pressW);
        assertTrue(keyHandler.isSellected());
    }

    @org.junit.jupiter.api.Test
    void keyReleasedW() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_W);
        keyHandler.keyReleased(pressW);
        assertFalse(keyHandler.isUpPressed());
    }
    @org.junit.jupiter.api.Test
    void keyReleasedA() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_A);
        keyHandler.keyReleased(pressW);
        assertFalse(keyHandler.isLeftPressed());
    }
    @org.junit.jupiter.api.Test
    void keyReleasedS() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_S);
        keyHandler.keyReleased(pressW);
        assertFalse(keyHandler.isDownPressed());
    }
    @org.junit.jupiter.api.Test
    void keyReleasedD() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_D);
        keyHandler.keyReleased(pressW);
        assertFalse(keyHandler.isRightPressed());
    }
    @org.junit.jupiter.api.Test
    void keyReleasedSpace() {
        KeyEvent pressW = createKeyEvent(KeyEvent.KEY_RELEASED, KeyEvent.VK_SPACE);
        keyHandler.keyReleased(pressW);
        assertFalse(keyHandler.isSellected());
    }
}