import character.CharInBattle;
import enemies.Enemy;
import enemies.Omniman;
import org.junit.jupiter.api.BeforeEach;
import projectiles.FlyDamageStrategy;
import projectiles.ProjectileDamageStrategy;
import projectiles.TearDamageStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlyDamageStrategyTest {
    private ProjectileDamageStrategy strategy;
    private CharInBattle charInBattle;
    private Enemy omniman;

    @BeforeEach
    void setUp() {
        strategy = new FlyDamageStrategy();
        charInBattle = new CharInBattle();
        omniman = new Omniman(0.1);
    }

    @org.junit.jupiter.api.Test
    void attackTest(){
        strategy.attack(charInBattle, omniman, 15, 15, 15);
        assertEquals(18, charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void attackTest2(){
        charInBattle.setHealth(1);
        strategy.attack(charInBattle, omniman, 15, 15, 15);
        assertEquals(0, charInBattle.getHealth());
    }
}