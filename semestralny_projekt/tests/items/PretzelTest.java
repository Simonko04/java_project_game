import character.CharInBattle;
import enemies.Enemy;
import enemies.Omniman;
import items.Pretzel;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PretzelTest {
    private Pretzel pretzel;
    private CharInBattle charInBattle;
    private Enemy omniman;

    @BeforeEach
    void setUp() {
        pretzel = new Pretzel();
        charInBattle = new CharInBattle();
        omniman = new Omniman(0.1);
    }

    @org.junit.jupiter.api.Test
    void amountTester(){
        assertEquals(1, pretzel.getAmount());
    }

    @org.junit.jupiter.api.Test
    void consumeTester(){
        charInBattle.setHealth(12);
        try {
            pretzel.consume(charInBattle, omniman);
        }catch (Exception e){}
        assertEquals(20, charInBattle.getHealth());
    }

    @org.junit.jupiter.api.Test
    void consumeExceptionTester(){
        try{
            pretzel.consume(charInBattle, omniman);
        }catch(Exception e){}
        Exception exception = assertThrows(Exception.class, () -> {
            pretzel.consume(charInBattle, omniman);
        });
    }

}