import character.CharInBattle;
import enemies.Omniman;
import items.FactoryOfConsumables;
import items.LuckFactory;
import items.Pretzel;
import items.PretzelFactory;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LuckFactoryTest {
    private FactoryOfConsumables factory;
    private FactoryOfConsumables factory2;
    private Omniman omniman;
    private CharInBattle charInBattle;
    @BeforeEach
    void setUp() {
        factory = new LuckFactory();
        factory2 = new PretzelFactory();
        omniman = new Omniman(0.1);
    }

    @org.junit.jupiter.api.Test
    void consumeExceptionTester(){
        try{
            factory.consume(charInBattle, omniman);
        }catch(Exception e){}
        assertEquals(omniman.getLikelihood(), 0.4);

    }

    @org.junit.jupiter.api.Test
    void getAmountTester(){
        assertEquals(1, factory.getAmount());
    }


}