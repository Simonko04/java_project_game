import org.junit.jupiter.api.BeforeEach;
import projectiles.ButteflyProjectile;
import projectiles.FlappyProjectile;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FlappyProjectileTest {
    private FlappyProjectile flappyProjectile;

    @BeforeEach
    void setUp() {
        flappyProjectile = new FlappyProjectile(15);
    }

    @org.junit.jupiter.api.Test
    void collisionTest(){
        assertTrue(flappyProjectile.collision(555,1));
    }

    private boolean fits(){
        int upperBorder = flappyProjectile.getUpper_border();
        if (upperBorder > 1 && upperBorder < 6){
            return true;
        }
        return false;
    }

    @org.junit.jupiter.api.Test
    void borderTest(){
        assertTrue(fits());
    }
}