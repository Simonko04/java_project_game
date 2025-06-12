import character.CharInBattle;
import enemies.Enemy;
import enemies.Omniman;
import org.junit.jupiter.api.BeforeEach;
import projectiles.FlappyDamageStrategy;
import projectiles.ProjectileDamageStrategy;
import projectiles.TearDamageStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TearDamageStrategyTest {
    private ProjectileDamageStrategy strategy;
    private CharInBattle charInBattle;
    private Enemy omniman;

    @BeforeEach
    void setUp() {
        strategy = new TearDamageStrategy();
        charInBattle = new CharInBattle();
        omniman = new Omniman(0.1);
    }

    @org.junit.jupiter.api.Test
    void attackTest(){
        strategy.attack(charInBattle, omniman, 15, 15, 15);
        assertEquals(12, charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void attackTest2(){
        charInBattle.setHealth(3);
        strategy.attack(charInBattle, omniman, 15, 15, 15);
        assertEquals(0, charInBattle.getHealth());
    }
}