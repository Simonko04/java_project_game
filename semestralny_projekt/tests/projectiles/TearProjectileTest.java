import org.junit.jupiter.api.BeforeEach;
import projectiles.FlyProjectile;
import projectiles.TearProjectile;

import static org.junit.jupiter.api.Assertions.*;

class TearProjectileTest {
    private TearProjectile projectile;

    @BeforeEach
    void setUp() {
        projectile = new TearProjectile(15, 15);
    }

    @org.junit.jupiter.api.Test
    void counterTest(){
        projectile.setCounter(5);
        assertEquals(5, projectile.getCounter());
    }

    @org.junit.jupiter.api.Test
    void collisionTests(){
        assertTrue(projectile.collision(50, 50, 55, 55, 20, 16));
    }

    @org.junit.jupiter.api.Test
    void collisionTests2(){
        assertFalse(projectile.collision(10, 10, 100, 100, 30, 5));
    }

    @org.junit.jupiter.api.Test
    void offsetTest(){
        assertTrue(offsetter());
    }

    private boolean offsetter(){
        int offset = projectile.getX_offset();
        if (offset >= -4 && offset <= 4){
            return true;
        }
        return false;
    }
}