package items;

public class PretzelFactory extends FactoryOfConsumables {
    @Override
    public Consumable createConsumable() {
        return new Pretzel();
    }
}
