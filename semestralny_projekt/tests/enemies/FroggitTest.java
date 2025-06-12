package enemies;

import action.KeyHandler;
import org.junit.jupiter.api.BeforeEach;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class FroggitTest {
    private Froggit froggit;
    private Napstablook napstablook;
    private Omniman omniman;
    private Whimsun whimsun;
    @BeforeEach
    void setUp() {
        froggit = new Froggit(1);
        napstablook = new Napstablook(1);
        omniman = new Omniman(1);
        whimsun = new Whimsun(1);
    }
    @org.junit.jupiter.api.Test
    void responseTester(){
        String response = froggit.response();
        assertEquals(response, "nice");
    }

    @org.junit.jupiter.api.Test
    void responseTester2(){
        froggit.setLikelihood(0);
        String response = froggit.response();
        assertEquals(response, "notNice");
    }

    @org.junit.jupiter.api.Test
    void nameTester(){
        String name = froggit.getName();
        assertEquals(name, "Froggit");
    }

    @org.junit.jupiter.api.Test
    void setGetLikelihood() {
        froggit.setLikelihood(1);
        assertEquals(froggit.getLikelihood(), 1);
    }
    @org.junit.jupiter.api.Test
    void levelGetter() {
        assertEquals(froggit.getLevel(), 1);
    }

    @org.junit.jupiter.api.Test
    void setGetHealth() {
        froggit.setHealth(froggit.getMaxHealth());
        assertEquals(froggit.getMaxHealth(), froggit.getHealth());
    }

    @org.junit.jupiter.api.Test
    void getScaredTest() {
        String scared = "Froggit didn't understand a word but is scared anyways";
        assertEquals(froggit.getScared(), scared);
    }
    @org.junit.jupiter.api.Test
    void getnotScaredTest() {
        String notscared = "Froggit didn't understand a word but is unimpressed, prepare to DIE!";
        assertEquals(froggit.getNotScared(), notscared);
    }

}