import character.CharInBattle;
import enemies.Enemy;
import enemies.Omniman;
import org.junit.jupiter.api.BeforeEach;
import projectiles.ButteflyProjectile;
import projectiles.ButterflyDamageStrategy;
import projectiles.ProjectileDamageStrategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ButterflyDamageStrategyTest {
    private ProjectileDamageStrategy projectileDamageStrategy;
    private Enemy enemy;
    private CharInBattle charInBattle;

    @BeforeEach
    void setUp() {
        projectileDamageStrategy = new ButterflyDamageStrategy();
        charInBattle = new CharInBattle();
        enemy = new Omniman(0.1);
    }
    @org.junit.jupiter.api.Test
    void attackGreater(){
        projectileDamageStrategy.attack(charInBattle, enemy, 1, 1 ,1);
        assertEquals(15, charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void attackLower(){
        charInBattle.setHealth(3);
        projectileDamageStrategy.attack(charInBattle, enemy, 1, 1 ,1);
        assertEquals(0, charInBattle.getHealth());
    }

}