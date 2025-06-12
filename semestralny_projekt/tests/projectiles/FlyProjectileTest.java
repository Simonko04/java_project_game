import org.junit.jupiter.api.BeforeEach;
import projectiles.FlyProjectile;

import static org.junit.jupiter.api.Assertions.*;

class FlyProjectileTest {
    private FlyProjectile projectile;

    @BeforeEach
    void setUp() {
        projectile = new FlyProjectile(1,1, 16*19, 16);
    }

    @org.junit.jupiter.api.Test
    void setX(){
        projectile.setProjectile_x(137);
        assertEquals(137,projectile.getProjectile_x());
    }

    @org.junit.jupiter.api.Test
    void setY(){
        projectile.setProjectile_y(65);
        assertEquals(65,projectile.getProjectile_y());
    }

    @org.junit.jupiter.api.Test
    void offsetTests(){
        projectile.setoffset();
        assertTrue(offsetter());
    }

    @org.junit.jupiter.api.Test
    void collisionTests(){
        assertTrue(projectile.collision(50, 50, 55, 55, 20));
    }

    @org.junit.jupiter.api.Test
    void collisionTests2(){
        assertFalse(projectile.collision(10, 10, 100, 100, 30));
    }

    private boolean offsetter(){
        int offsetx = projectile.getOffset_x();
        int offsety = projectile.getOffset_y();
        if ((offsetx >= -3 && offsetx <= 3) && (offsety >= -3 && offsety <= 3)){
            return true;
        }
        return false;
    }

}