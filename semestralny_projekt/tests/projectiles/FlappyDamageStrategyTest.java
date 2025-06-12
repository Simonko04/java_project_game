import character.CharInBattle;
import enemies.Enemy;
import enemies.Omniman;
import org.junit.jupiter.api.BeforeEach;
import projectiles.FlappyDamageStrategy;
import projectiles.FlappyProjectile;
import projectiles.ProjectileDamageStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlappyDamageStrategyTest {
    private ProjectileDamageStrategy strategy;
    private CharInBattle charInBattle;
    private Enemy omniman;

    @BeforeEach
    void setUp() {
        strategy = new FlappyDamageStrategy();
        charInBattle = new CharInBattle();
        omniman = new Omniman(0.1);
    }

    @org.junit.jupiter.api.Test
    void damageTest(){
        strategy.attack(charInBattle, omniman, 1, 1, 1);
        assertEquals(8, charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void damage2Test(){
        charInBattle.setHealth(8);
        strategy.attack(charInBattle, omniman, 1, 1, 1);
        assertEquals(0, charInBattle.getHealth());
    }

}