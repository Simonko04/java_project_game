import character.CharInBattle;
import enemies.Froggit;
import enemies.Napstablook;
import enemies.Omniman;
import enemies.Whimsun;
import items.Luck;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class LuckTest {
    private Luck luck;
    private Omniman omniman;
    private CharInBattle charInBattle;

    @BeforeEach
    void setUp() {
        omniman = new Omniman(1);
        luck = new Luck();
        charInBattle = new CharInBattle();
    }

    @org.junit.jupiter.api.Test
    void amountTester(){
        assertEquals(1, luck.getAmount());
    }

    @org.junit.jupiter.api.Test
    void consumeExceptionTester(){
        try{
            luck.consume(charInBattle, omniman);
        }catch(Exception e){}
        Exception exception = assertThrows(Exception.class, () -> {
            luck.consume(charInBattle, omniman);
        });
    }

    @org.junit.jupiter.api.Test
    void highLikelihoodTester(){
        omniman.setLikelihood(0.1);
        try{
            luck.consume(charInBattle, omniman);
        }catch(Exception e){}
        assertEquals(0.4, omniman.getLikelihood());
    }

    @org.junit.jupiter.api.Test
    void lowHealthTester(){
        omniman.setHealth(23);
        omniman.setLikelihood(0.1);
        try{
            luck.consume(charInBattle, omniman);
        }catch(Exception e){}
        assertEquals(0.8, omniman.getLikelihood());
    }

    @org.junit.jupiter.api.Test
    void noHealthTester(){
        omniman.setHealth(1);
        omniman.setLikelihood(0.1);
        try{
            luck.consume(charInBattle, omniman);
        }catch(Exception e){}
        assertEquals(0.95, omniman.getLikelihood());
    }
}