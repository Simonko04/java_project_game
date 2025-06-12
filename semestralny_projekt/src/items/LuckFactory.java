package items;

public class LuckFactory extends FactoryOfConsumables{
    @Override
    public Consumable createConsumable() {
        return new Luck();
    }
}
