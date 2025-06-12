package projectiles;

import character.CharInBattle;
import enemies.Omniman;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ButteflyProjectileTest {
    private ButteflyProjectile butteflyProjectile;

    @BeforeEach
    void setUp() {
        butteflyProjectile = new ButteflyProjectile(1, 1, 1, 1);
    }

    @org.junit.jupiter.api.Test
    void getOffsets(){
        assertEquals(butteflyProjectile.getOffsetx(), butteflyProjectile.getOffsety());
    }

    @org.junit.jupiter.api.Test
    void collisionTest(){
        assertTrue(butteflyProjectile.collision(1,1,1,1,1,1));
    }

    @org.junit.jupiter.api.Test
    void collisionFalseTest(){
        assertFalse(butteflyProjectile.collision(1,1,-1,1,1,1));
    }

    @org.junit.jupiter.api.Test
    void projectileXTester(){
        butteflyProjectile.setProjectile_x(3);
        assertEquals(butteflyProjectile.getProjectile_x(), 3);
    }

    @org.junit.jupiter.api.Test
    void projectileYTester(){
        butteflyProjectile.setProjectile_y(3);
        assertEquals(butteflyProjectile.getProjectile_y(), 3);
    }

}