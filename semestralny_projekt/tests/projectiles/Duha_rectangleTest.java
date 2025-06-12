import character.CharInBattle;
import enemies.Enemy;
import enemies.Omniman;
import org.junit.jupiter.api.BeforeEach;
import projectiles.ButteflyProjectile;
import projectiles.Duha_rectangle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Duha_rectangleTest {
    private Duha_rectangle duha_rectangle;
    private Enemy enemy;
    private CharInBattle charInBattle;

    @BeforeEach
    void setUp() {
        enemy = new Omniman(0.1);
        charInBattle = new CharInBattle();
        duha_rectangle = new Duha_rectangle(1, enemy, charInBattle);
    }

    @org.junit.jupiter.api.Test
    void rectanglePressedTest(){
        duha_rectangle.setProjectile_x(0);
        duha_rectangle.rectangle_pressed(10);
        assertEquals(0, charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void rectanglePressed2Test(){
        duha_rectangle.setProjectile_x(105);
        duha_rectangle.rectangle_pressed(10);
        assertEquals(8, charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void rectanglePressed3Test(){
        duha_rectangle.setProjectile_x(95);
        duha_rectangle.rectangle_pressed(10);
        assertEquals(12, charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void rectanglePressed4Test(){
        duha_rectangle.setProjectile_x(85);
        duha_rectangle.rectangle_pressed(10);
        assertEquals(45, enemy.getHealth());
    }

    @org.junit.jupiter.api.Test
    void rectanglePressed5Test(){
        duha_rectangle.setProjectile_x(80);
        duha_rectangle.rectangle_pressed(10);
        assertEquals(40, enemy.getHealth());
    }
    @org.junit.jupiter.api.Test
    void speedTest(){
        assertEquals(1, duha_rectangle.getProjectile_speed());
    }


}